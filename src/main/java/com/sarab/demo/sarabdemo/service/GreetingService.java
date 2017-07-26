package com.sarab.demo.sarabdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@RefreshScope
public class GreetingService {

	private static final Logger logger = LoggerFactory.getLogger(GreetingService.class);

	@Autowired
	private EurekaClient discoveryClient;

	@Value("${serviceName}")
	private String serviceName;

	@HystrixCommand(fallbackMethod="myFallbackMethod")
	public String getGreeting() {
		RestTemplate restTemplate = new RestTemplate();
		String greeting = restTemplate.getForObject(fetchGreetingServiceUrl() + "/greeting-service/greeting",
				String.class);
		return greeting;
	}
	
	public String myFallbackMethod() {
		return "Vadakam";
	}

	private String fetchGreetingServiceUrl() {

		InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceName, false);
		logger.debug("instanceID: {}", instance.getId());

		String fortuneServiceUrl = instance.getHomePageUrl();
		logger.debug("greeting service homePageUrl: {}", fortuneServiceUrl);

		return fortuneServiceUrl;
	}

}
