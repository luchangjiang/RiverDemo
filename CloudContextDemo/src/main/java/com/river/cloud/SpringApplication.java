package com.river.cloud;

import org.springframework.boot.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.bootstrap.BootstrapImportSelectorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-11-04 22:09
 **/
public class SpringApplication {

    public ConfigurableApplicationContext run(String... args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ConfigurableApplicationContext context = null;
        Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
        configureHeadlessProperty();         //此处会加载spring-cloud-context提供的监听器org.springframework.cloud.bootstrap.BootstrapApplicationListener.class
        SpringApplicationRunListeners listeners = getRunListeners(args);
        listeners.starting();
        try {
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(
                    args);            //此处会发布ApplicationEnvironmentPreparedEvent事件，触发BootstrapApplicationListener中的代码
            ConfigurableEnvironment environment = prepareEnvironment(listeners,
                    applicationArguments);
            configureIgnoreBeanInfo(environment);
            Banner printedBanner = printBanner(environment);
            context = createApplicationContext();
            exceptionReporters = getSpringFactoriesInstances(
                    SpringBootExceptionReporter.class,
                    new Class[] { ConfigurableApplicationContext.class }, context);
            prepareContext(context, environment, listeners, applicationArguments,
                    printedBanner);
            refreshContext(context);
            afterRefresh(context, applicationArguments);
            stopWatch.stop();
            if (this.logStartupInfo) {
                new StartupInfoLogger(this.mainApplicationClass)
                        .logStarted(getApplicationLog(), stopWatch);
            }
            listeners.started(context);
            callRunners(context, applicationArguments);
        }
        catch (Throwable ex) {
            handleRunFailure(context, ex, exceptionReporters, listeners);
            throw new IllegalStateException(ex);
        }

        try {
            listeners.running(context);
        }
        catch (Throwable ex) {
            handleRunFailure(context, ex, exceptionReporters, null);
            throw new IllegalStateException(ex);
        }
        return context;
    }

    private ConfigurableApplicationContext bootstrapServiceContext(
            ConfigurableEnvironment environment, final SpringApplication application,
            String configName) {
        /**            此处代码主要是从各处获取属性配置，此处忽略           **/// TODO: is it possible or sensible to share a ResourceLoader?
        SpringApplicationBuilder builder = new SpringApplicationBuilder()
                .profiles(environment.getActiveProfiles()).bannerMode(Banner.Mode.OFF)
                .environment(bootstrapEnvironment)
                // Don't use the default properties in this builder
                .registerShutdownHook(false).logStartupInfo(false)
                .web(WebApplicationType.NONE);        //SpringApplicationBuilder的作用：1.构建SpringApplication  2.构建ApplicationContext        //这里需要思考一下，springboot在启动时已经构建了一个SpringApplication实例，为何此处又构建了一个         //这是因为这个SpringApplication实例的构建环境和SringBoot原生构建的那个不同，看一下上一行代码就能明白
        final SpringApplication builderApplication = builder.application();
        if(builderApplication.getMainApplicationClass() == null){
            builder.main(application.getMainApplicationClass());
        }
        if (environment.getPropertySources().contains("refreshArgs")) {
            builderApplication
                    .setListeners(filterListeners(builderApplication.getListeners()));
        }        //从springFactories文件中查找BootstrapConfiguration的配置类
        builder.sources(BootstrapImportSelectorConfiguration.class);        //构建出BootstrapContext
        final ConfigurableApplicationContext context = builder.run();
        context.setId("bootstrap");
        // 设置BootstrapContext成为应用Context的父容器,此处分析见步骤4
        addAncestorInitializer(application, context);
        // It only has properties in it now that we don't want in the parent so remove
        // it (and it will be added back later)
        bootstrapProperties.remove(BOOTSTRAP_PROPERTY_SOURCE_NAME);
        mergeDefaultProperties(environment.getPropertySources(), bootstrapProperties);
        return context;
    }

    public void initialize(ConfigurableApplicationContext context) {  //这个context是应用Context
        while (context.getParent() != null && context.getParent() != context) {
            context = (ConfigurableApplicationContext) context.getParent();
        }
        reorderSources(context.getEnvironment());            //完成应用容器的父容器的设置
        new ParentContextApplicationContextInitializer(this.parent)
                .initialize(context);
    }

    private static class ParentContextApplicationContextInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private final ApplicationContext parent;

        ParentContextApplicationContextInitializer(ApplicationContext parent) {
            this.parent = parent;
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            applicationContext.setParent(this.parent);  //设置应用Context的父容器
        }

    }
}
