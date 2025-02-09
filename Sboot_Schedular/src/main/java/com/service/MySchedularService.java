package com.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MySchedularService {

	private static final Logger logger = LoggerFactory.getLogger(MySchedularService.class);

//	@Scheduled(fixedRate = 2000, initialDelay = 5000L)
	@Scheduled(fixedRateString = "${schedular1.fixedRateInMS}", initialDelay = 1000L)
	public void job() {
		logger.info("Job1 Run Time : " + new Date());
	}
	

	@Scheduled(fixedRate = 86400000L , initialDelayString = "#{@getIntervalTime}")		// we have set the fixedRate = 86400000L miliseconds (i.e, one day interval)    
	public void job3() {															// We can change = 10000 (=> 10sec)
		logger.info("**Job2 Run Time : " + new Date());
	}
	
	

}
