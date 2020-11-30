package me.java.library.rpc.thrift.client.discovery;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class ThriftConsulServerListUpdater implements ServerListUpdater {


    private final AtomicBoolean isActive = new AtomicBoolean(false);
    private final long initialDelayMs;
    private final long refreshIntervalMs;
    private volatile ScheduledFuture<?> scheduledFuture;

    public ThriftConsulServerListUpdater() {
        this(30000);
    }

    public ThriftConsulServerListUpdater(long refreshIntervalMs) {
        this(0, refreshIntervalMs);
    }

    public ThriftConsulServerListUpdater(long initialDelayMs, long refreshIntervalMs) {
        this.initialDelayMs = initialDelayMs;
        this.refreshIntervalMs = refreshIntervalMs;
    }

    private static ScheduledThreadPoolExecutor getRefreshExecutor() {
        return LazyHolder.serverListRefreshExecutor;
    }

    @Override
    public synchronized void start(UpdateAction updateAction) {
        if (isActive.compareAndSet(false, true)) {
            Runnable scheduledRunnable = () -> {
                if (!isActive.get()) {
                    if (scheduledFuture != null) {
                        scheduledFuture.cancel(true);
                    }
                    return;
                }

                try {
                    updateAction.doUpdate();
                } catch (Exception e) {
                    log.warn("Failed one do update action", e);
                }

            };

            scheduledFuture = getRefreshExecutor().scheduleWithFixedDelay(
                    scheduledRunnable,
                    initialDelayMs,
                    refreshIntervalMs,
                    TimeUnit.MILLISECONDS
            );
        } else {
            log.info("Already active, no other operation");
        }
    }

    @Override
    public void stop() {
        if (isActive.compareAndSet(true, false)) {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
        } else {
            log.info("Not active, no other operation");
        }
    }

    private static class LazyHolder {
        private static final int CORE_THREAD = 2;
        static ScheduledThreadPoolExecutor serverListRefreshExecutor;
        private static Thread shutdownThread;

        static {
            ThreadFactory factory = new ThreadFactoryBuilder()
                    .setNameFormat("ThriftConsulServerListUpdater-%d")
                    .setDaemon(true)
                    .build();

            serverListRefreshExecutor = new ScheduledThreadPoolExecutor(CORE_THREAD, factory);

            shutdownThread = new Thread(() -> {
                log.info("Shutting down the Executor Pool for ThriftConsulServerListUpdater");
                shutdownExecutorPool();
            });

            Runtime.getRuntime().addShutdownHook(shutdownThread);
        }

        private static void shutdownExecutorPool() {
            if (serverListRefreshExecutor != null) {
                serverListRefreshExecutor.shutdown();

                if (shutdownThread != null) {
                    try {
                        Runtime.getRuntime().removeShutdownHook(shutdownThread);
                    } catch (IllegalStateException e) {
                        log.error("Failed to shutdown the Executor Pool for ThriftConsulServerListUpdater", e);
                    }
                }

            }
        }
    }
}
