package com.zgurski;

import org.apache.commons.lang3.RandomStringUtils;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println(RandomStringUtils.random(5,true,true));
        System.out.println(RandomStringUtils.random(30, "abc"));
        System.out.println(RandomStringUtils.random(35, "abc"));
    }
}