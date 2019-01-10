package com.gq.data.report.common.utils;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadPoolUtil {
	public static ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		static{
		taskExecutor.setQueueCapacity(50);
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setKeepAliveSeconds(5000);  
		taskExecutor.initialize();
	}
}
