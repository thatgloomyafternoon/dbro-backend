package com.fw.dbro.backend.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FacialUtil {

  public String faceDetect(MultipartFile multipartFile, UUID userAuthId) throws IOException {
    Path path = Paths.get("/home/ubuntu/dbro-facerec/img-temp/", userAuthId.toString() + ".jpg");
    Files.write(path, multipartFile.getBytes());
    ProcessBuilder processBuilder = new ProcessBuilder("python3", "/home/ubuntu/dbro-facerec/dbro_facerec.py", userAuthId.toString());
    Process process = processBuilder.start();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String output = "", line = "";
    while((line = bufferedReader.readLine()) != null) {
      output += line;
    }
    bufferedReader.close();
    process.destroy();
    return output;
  }

}
