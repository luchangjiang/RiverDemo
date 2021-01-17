package com.river.guava.config;

import com.github.rholder.retry.*;
import com.river.guava.exception.NeedRetryException;
import com.river.guava.listener.RetryLogListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 构造重试器
 */
@Component
public class ProductRetryerBuilder {

    public Retryer build() {
        //定义重试机制
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                //retryIf 重试条件
                //.retryIfException()
                //.retryIfRuntimeException()
                //.retryIfExceptionOfType(Exception.class)
                //.retryIfException(Predicates.equalTo(new Exception()))
                //.retryIfResult(Predicates.equalTo(false))
                .retryIfExceptionOfType(NeedRetryException.class)

                // 等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))

                // 停止策略 : 尝试请求3次
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))

                // 时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))

                // 默认的阻塞策略：线程睡眠
                //.withBlockStrategy(BlockStrategies.threadSleepStrategy())
                // 自定义阻塞策略：自旋锁
                .withBlockStrategy(new SpinBlockStrategy())
                // 自定义等待策略
                .withWaitStrategy(new AlipayWaitStrategy())
                // 自定义重试监听器
                .withRetryListener(new RetryLogListener())

                .build();

        return retryer;

    }
}