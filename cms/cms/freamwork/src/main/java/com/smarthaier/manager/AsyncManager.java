package com.smarthaier.manager;

import com.smarthaier.common.utils.SpringUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsyncManager {

    private ScheduledExecutorService service = SpringUtils.getBean("scheduledExecutorService");
    private AsyncManager(){}
    static  final  TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    static  final int delayTime = 10;
    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me()
    {
        return me;
    }

    public void execute(TimerTask timerTask){

        service.schedule(timerTask,delayTime,timeUnit);
    }
    public void shutdown(){
        service.shutdown();
    }
}
