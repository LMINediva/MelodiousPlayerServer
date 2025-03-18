package com.melodiousplayer;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MelodiousPlayerServerApplicationTests {


    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/database?useUnicode=true" +
                "&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        String name = encryptor.encrypt("rds");
        String password = encryptor.encrypt("rds");
        System.out.println("mysql url: " + url);
        System.out.println("mysql name: " + name);
        System.out.println("mysql password: " + password);

    }

}