package net.scphy.support;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import net.scphy.util.ExceptionUtils;
import net.scphy.util.LogUtils;
import net.scphy.util.ObjectUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author scphy 2024/3/9
 */
@Slf4j
public class AsyncSupport {

    public static final ThreadPoolExecutor ASYNC_SUPPORT_THREAD_POOL = new ThreadPoolExecutor(
            10,
            10,
            2,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("async-support-%d").build(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void runAsync(Runnable runnable) {
        runAsync(runnable, null);
    }

    public static void runAsync(Runnable task, Consumer<Exception> onException) {
        String requestId = LogUtils.getRequestId();
        CompletableFuture.runAsync(() -> {
            LogUtils.setRequestId(requestId);
            try {
                task.run();
            } catch (Exception e) {
                if (ObjectUtils.isEmpty(onException)) {
                    log.error("runAsync error, {}", ExceptionUtils.getStackTrace(e));
                } else {
                    onException.accept(e);
                }
            } finally {
                LogUtils.removeRequestId();
            }
        }, ASYNC_SUPPORT_THREAD_POOL);
    }

}
