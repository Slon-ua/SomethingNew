//*** этот клас универсальный, для проверки соответствия респонса (наличия конкретного текста в JSON респонсе) заданому
// состоянию (conditions) в тесте.
// Вызывается из теста UserRegistrationTest.java из задает значение проверки в скобок (200)

package com.socks.api.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

// @ToString           //  выводит человеко-читаемые логи из этого класса, но ниже мы переопределили этот метод

@RequiredArgsConstructor
// при наличии final переменной нужно прописать эту Анотацию которая проинициализирует эту переменную и создать нужный конструктор

public class BodyFieldCondition implements Condition { //во всех кондишинах нужно имплементировать Интерфейс Condition

    private final String jsonPath;  // final - сделает дальнейшее изменение объекта невозможным.
    // Значение этой переменной присваивается при вызове метода из UserRegistrationTest.java из скобок (строка, ***)
    private final Matcher matcher;
    // Значение этой переменной присваивается при вызове метода из UserRegistrationTest.java из скобок (***, текст совпадения или метод)

    @Override
    public void check(Response response) {
        response.then().assertThat().body(jsonPath, matcher);
        // имплементируем этот метод из Интерфейса Condition и проверяет на соответствие значению переменной 'matcher'  в тексте переменной jsonPath
    }

    @Override   //переопределили метод 'toString' в котором мы можем сами писать текст сообжения в логе
    public String toString() {
        return "Body field  [ " + jsonPath + " ]  is  " + matcher;
    }

    /*//код без коментов
    public class BodyFieldCondition implements Condition{

    private final String jsonPath;
    private final Matcher matcher;


    @Override
    public void check(Response response) {
        response.then().assertThat().body(jsonPath, matcher);
   }

    @Override
    public String toString() {
        return "Body field ["+jsonPath+"] is "+ matcher;
    }
     */
}
