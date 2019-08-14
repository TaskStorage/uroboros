package com.taskstorage.uroboros.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEnc {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(8);
        System.out.println(bCryptPasswordEncoder.encode("12345678"));
    }
}
