//*** этот клас универсальный, для проверки соответствия респонса (кода HTTP)  заданому состоянию (conditions) в тесте.
// Вызывается из теста UserRegistrationTest.java из задает значение проверки в скобок (200)
package com.socks.api.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

// @ToString           //  выводит человеко-читаемые логи из этого класса, но ниже мы переопределили этот метод

@RequiredArgsConstructor   // при наличии final переменной нужно прописать эту Анотацию которая проинициализирует эту переменную и создать нужный конструктор

public class StatusCodeCondition implements Condition{ //во всех кондишинах нужно имплементировать Интерфейс Condition

    private final int expectedStatusCode;  // final - сделает дальнейшее изменение объекта невозможным.
    // Значение этой переменной присваивается при вызове метода из UserRegistrationTest.java из скобок (200)

    @Override
    public void check(Response response) {
        response.then().assertThat().statusCode(expectedStatusCode);  // имплементируем этот метод из Интерфейса Condition и проверяет на соответствие значению переменной 'expectedStatusCode'
    }


    @Override  //переопределили метод 'toString' в котором мы можем сами писать текст сообжения в логе
    public String toString() {
        return "Status Code is = " + expectedStatusCode;
    }



    /* //код без коментов
    private final int expectedStatusCode;

    @Override
    public void check(Response response) {
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Override
    public String toString() {
        return "Status Code is = " + expectedStatusCode;
     }
     */
}
