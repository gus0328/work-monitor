package com.iccm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJdbcHttpSession//开启spring session
@ComponentScan("com.iccm.*")
@EnableCaching//开启缓存
@EnableScheduling//开启定时
@EnableTransactionManagement//开启事物管理
public class WorkMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkMonitorApplication.class, args);
	}

}
