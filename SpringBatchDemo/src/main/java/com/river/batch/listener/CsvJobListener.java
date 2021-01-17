package com.river.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author jian
 * @date 2019/4/28
 * @description
 * 监听Job执行情况，则定义一个类实现JobExecutorListener，并定义Job的Bean上绑定该监听器
 */
@Slf4j
public class CsvJobListener implements JobExecutionListener {

    private long startTime;
    private long endTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        log.info("job process start...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        log.info("job process end...");
        log.info("elapsed time: " + (endTime - startTime) + "ms");
    }
}