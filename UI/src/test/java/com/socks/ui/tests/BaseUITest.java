package com.socks.ui.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.socks.api.conditions.Condition;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collection;

public class BaseUITest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = System.getProperty("host","http://192.168.99.101"); // для задачи хоста вручную через консоль или автоматом второе значение
        Configuration.baseUrl = "http://192.168.99.101";
        Configuration.browserSize = "1366x768";
     //   Configuration.browser = "chrome";

    }

    protected  <T> T at(Class<T> pageClass) {   //Дженерики, метод для  возврата класса конкретного типа (например класс типа String)
        return Selenide.page(pageClass);          // c его помощью можно присваивать переменной все содержимое Боди респонса от сервера, а после проверять
    }
}
