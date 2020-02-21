package com.river.QuartzDemo.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自定义定时任务
 */
@Slf4j
public class ScheduledJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //执行任务逻辑....
        log.info("执行自定义定时任务");
    }
}
