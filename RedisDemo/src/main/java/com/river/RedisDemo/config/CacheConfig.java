package com.river.RedisDemo.config;

import com.alibaba.fastjson.JSON;
import com.river.RedisDemo.util.AesUtil;
import io.lettuce.core.CallbackPassword;
import io.lettuce.core.RedisURI;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.annotation.PostConstruct;
import java.time.Duration;


/**
 * Created by @author yihui in 15:03 18/11/8.
 */
@Configuration
public class CacheConfig {

    public interface CacheManagerNames {
        String REDIS_CACHE_MANAGER = "redisCacheManager";
        String EHCACHE_CACHE_MANAGER = "ehCacheManager";
    }
    /**
     * @Description @Primary 设置默认加载manager
     * @param bean
     * @return org.springframework.cache.ehcache.EhCacheCacheManager
     * @date 2019/5/31 4:38 PM
     * @auther lixin
     */
    @Bean(name = CacheManagerNames.EHCACHE_CACHE_MANAGER)
    @Primary
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager(bean.getObject());
    }

    @Bean(name = CacheManagerNames.REDIS_CACHE_MANAGER)
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofDays(30));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(configuration).build();

    }

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean(){
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.setShared(true);
        return bean;
    }

    /**
     * @Description 自定义缓存  key规则
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @date 2019/5/31 4:38 PM
     * @auther lixin
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                // 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                sb.append(JSON.toJSONString(obj).hashCode());
            }
            return sb.toString();
        };
    }

    @PostConstruct
    public void initCallbackPassword() {
        RedisURI.callbackPassword = new CallbackPassword() {
            @Override
            public char[] decrypt(char[] password) {
                return AesUtil.decrypt(password);
            }
        };
    }

    /**
     *  设置 redis 数据默认过期时间，默认1天
     *  设置@cacheable 序列化方式
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofDays(1));
        return configuration;
    }
}
