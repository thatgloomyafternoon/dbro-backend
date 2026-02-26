package com.fw.dbro.backend;

import com.fw.dbro.backend.utils.SpringBootBackEndStartup;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbroBackend {

  public static void main(String[] args) {
    SpringBootBackEndStartup.run(DbroBackend.class, args);
  }

}
