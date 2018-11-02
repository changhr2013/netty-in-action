package com.changhr.nettygo.chapter7;

import java.util.Collections;
import java.util.List;

/**
 * 事件循环示例
 *
 * @author changhr
 * @create 2018-10-19 16:49
 */
public class EventLoopExamples {

    public static void executeTaskInEventLoop(){
        boolean terminated = true;
        //...
        while(!terminated){
            // 阻塞，直到有事件已经就绪可被运行
            List<Runnable> readyEvents = blockUntilEventReady();
            // 循环遍历，并处理所有的事件
            for (Runnable readyEvent : readyEvents) {
                readyEvent.run();
            }
        }
    }

    private static final List<Runnable> blockUntilEventReady() {
        return Collections.singletonList(() -> {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
    }
}
