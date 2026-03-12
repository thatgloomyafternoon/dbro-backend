package com.fw.dbro.backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public class SpringBootBackEndStartup {

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootBackEndStartup.class);

  public static void run(Class<?> mainClass, String[] args) {
    SpringApplication springApplication = new SpringApplication(mainClass);
    Environment environment = springApplication.run(args).getEnvironment();
    logStartup(environment);
  }

  private static void logStartup(Environment env) {
    LOGGER.info("\n---------------------------------------------------------------\n" +
      "  APPLICATION IS INITIALIZED AND RUNNING!\n" +
      "  Application Name : {}\n" +
      "  URL              : http://localhost:{}\n" +
      "  Active Profiles  : {}\n" +
      "---------------------------------------------------------------",
      env.getProperty("spring.application.name"),
      env.getProperty("server.port"),
      env.getActiveProfiles());
  }

}
