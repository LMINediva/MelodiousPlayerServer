package com.melodiousplayer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.melodiousplayer.mapper")
public class MelodiousPlayerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelodiousPlayerServerApplication.class, args);
    }

}
