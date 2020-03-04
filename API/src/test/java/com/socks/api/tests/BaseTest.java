package com.socks.api.tests;

import io.restassured.RestAssured;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;

@RequiredArgsConstructor

public class BaseTest {

    private static final String ENV_VARIABLE_KEY = "env";  //  инициал статическую файнл переменную, котоой присаиваем имя проперти из  onfig.properties  и дальше в коде используем ее

    @BeforeAll
    static void setUp() {

        // 1)   RestAssured.baseURI = "http://192.168.99.100";
        // 2)
        RestAssured.baseURI = System.getProperty("host","http://192.168.99.101"); // для задачи хоста вручную через консоль или автоматом второе значение


        // 3)  МЕТОД для которого мы создавали Интерфейс ProgectConfig, в ресорсах confir.properties  и добавляли в депенденси Гредла библиотеку JavaOWNER

        // ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());  //подключение/инициализация к файлу с пропертями (ProgectConfig) и настройка разрешения задавания значений переменных через консоль
        // создаем переменную к которой присваиваем 1ну пропертю из ProgectConfig/config.properties . создаем столько сколько есть пропертей
        // ', System.getProperties()' -- отвечает за то, чтоб брало проперти из компндной строки -Dhost=http://aaa.com
        //   RestAssured.baseURI = config.host();  //вызов конкретной проперти


        // 4)   Метод при котором вызывается и енвайрмен и его проперти
 /*       String env = System.getProperty("env");  // этим кодом мы настраиваем работу выбора энвайрмент

        Map myVars = new HashMap();
        myVars.put("env",env);

        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, myVars);  // вызов пропертей енвайрмента и его переменных (env.host)
          RestAssured.baseURI = config.host();  //вызов конкретной проперти
*/

        // 5)  Сокращаем код выше

     /*   Map myVars = singletonMap(ENV_VARIABLE_KEY, getProperty(ENV_VARIABLE_KEY));

        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, myVars, System.getProperties());  // вызов пропертей енвайрмента и его переменных (env.host)
           RestAssured.baseURI = config.host();  //вызов конкретной проперти
*/

    }
}
