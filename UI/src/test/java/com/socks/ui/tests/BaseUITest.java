package com.socks.ui.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.socks.api.conditions.Condition;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.util.Collection;

public class BaseUITest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = System.getProperty("host","http://192.168.99.101"); // для задачи хоста вручную через консоль или автоматом второе значение
        Configuration.baseUrl = System.getProperty("host","http://192.168.99.101");
        Configuration.browserSize = "1366x768";
        Configuration.timeout=4000;
        Configuration.browser = "chrome";

   //     Configuration.browser= "com.socks.pages.ui.MyCustomDriver";
   //     SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));

   //        Configuration.browser = "firefox";


   //    4)   Метод при котором вызывается и енвайрмен и его проперти
 /*       String env = System.getProperty("env");  // этим кодом мы настраиваем работу выбора энвайрмент

        Map myVars = new HashMap();
        myVars.put("env",env);

        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, myVars);  // вызов пропертей енвайрмента и его переменных (env.host)
          RestAssured.baseURI = config.host();  //вызов конкретной проперти
*/

        Configuration.remote =  "http://192.168.99.109:4444/wd/hub";

    }

    protected  <T> T at(Class<T> pageClass) {   //Дженерики, метод для  возврата класса конкретного типа (например класс типа String)
        return Selenide.page(pageClass);          // c его помощью можно присваивать переменной все содержимое Боди респонса от сервера, а после проверять
    }


    @BeforeEach
    void registerAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));

    }
}
