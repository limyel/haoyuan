package com.limyel.haoyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HaoyuanApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(HaoyuanApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
