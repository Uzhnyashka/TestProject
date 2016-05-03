package com.testproject.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by bobyk on 03/05/16.
 */
public class Test {

    public static void main(String args[]){
        String password = "parol";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
    };
}
