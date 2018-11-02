package com.changhr.nettygo.chapter7;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度示例
 *
 * @author changhr
 * @create 2018-10-19 17:19
 */
public class ScheduleExamples {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    /**
     * 使用ScheduleExecutorService来在60秒的延迟之后执行一个任务
     */
    public static void schedule(){
        // 创建一个其线程池具有10个线程的ScheduleExecutorService
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        // 创建一个Runnable，以供调度稍后进行
        ScheduledFuture<?> future = executorService.schedule((Runnable)() -> {
            // 该任务要打印的消息
            System.out.println("Now it is 60 seconds later");
            // 调度任务从现在开始的60秒之后执行
        }, 60, TimeUnit.SECONDS);
        // ...
        // 一旦调度任务执行完成，就关闭ScheduledExecutorService以释放资源
        executorService.shutdown();
    }

    /**
     * 使用EventLoop调度任务
     */
    public static void scheduleViaEventLoop() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        // 创建一个Runnable以供调度稍后执行
        channel.eventLoop().schedule((Runnable)() ->{
            // 要执行的代码
            System.out.println("60 seconds later");
            // 调度任务从现在开始的60秒之后执行
        }, 60, TimeUnit.SECONDS);
    }

    /**
     * 使用EventLoop调度周期性的任务
     */
    public static void scheduleFixedViaEventLoop() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        // 创建一个Runnable，以供调度稍后执行
        channel.eventLoop().scheduleAtFixedRate((Runnable)() -> {
            // 这将一直运行，直到ScheduleFuture被取消
            System.out.println("Run every 690 seconds");
            // 调度在60秒之后，并且以后每间隔60秒运行一次
        }, 60, 60, TimeUnit.SECONDS);
    }

    /**
     * 使用ScheduledFuture取消任务
     */
    public static void cancelingTaskUsingScheduledFuture() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        // 调度任务，并获得所返回的ScheduledFuture
        io.netty.util.concurrent.ScheduledFuture<?> future = channel.eventLoop().scheduleAtFixedRate((Runnable) () -> {
            System.out.println("Run every 60 seconds");
        }, 60, 60, TimeUnit.SECONDS);
        // some other code that runs...
        boolean mayInterruptIfRunning = false;
        //取消该任务，防止它再次运行
        future.cancel(mayInterruptIfRunning);
    }

}
