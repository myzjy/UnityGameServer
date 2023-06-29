package com.gameServer.webGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class GateWayApplication {
  
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}