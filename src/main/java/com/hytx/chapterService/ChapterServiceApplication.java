package com.hytx.chapterService;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
@EnableAsync
@EnableScheduling
@MapperScan("com.hytx.chapterService.dao")
public class ChapterServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ChapterServiceApplication.class);
	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(ChapterServiceApplication.class, args);
		logger.info("ChapterServiceApplication ...启动完成 ..");
	}
}
