//package com.socks.common;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import io.restassured.RestAssured;
//import org.junit.jupiter.api.BeforeAll;
//
//public class BaseTest {
//
//    @BeforeAll
//    static void setUp() {
//        RestAssured.baseURI = System.getProperty("host","http://192.168.99.101"); // для задачи хоста вручную через консоль или автоматом второе значение
//        Configuration.baseUrl = "http://192.168.99.101";
//
//     //   Configuration.browser = "chrome";
//
//    }
//}
