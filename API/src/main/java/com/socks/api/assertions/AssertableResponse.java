//*** С помощьюэтого класс производится обработка респонса от РЕСТ-ашурда с его дальнейшей проверкой и написанием логов на результат
package com.socks.api.assertions;

import com.socks.api.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j                    //для добавления конкретных логов для каждого condition
@RequiredArgsConstructor
// при наличии final переменной нужно прописать эту Анотацию которая проинициализирует эту переменную и создать нужный конструктор

public class AssertableResponse {

    private final Response response;  //вот наличие той самой final переменной. Тут передается респонс от РЕст-ашюрда, для того, чтоб мы его уже вывели по своему


    @Step("API responce should have {condition}")
    //тут в ковычках можно указать альтернатив текст и передать переменные. @Step - этот текст отражается в Allure)


    public AssertableResponse shouldHave(Condition condition) {  //метод проверки соответствия элемента респонса ожидаемым значениям
        log.info("About to check condition [ {} ]", condition);  // первая часть лога проверки, в скобках 2я часть лога из конкретных классов 'conditions'
        RestAssured.registerParser("text/plain", Parser.JSON);

        condition.check(response);  //передает через интерфейс методу condition.check  переменную, которую получил из файла ApiService (респонс от РЕСТ_ашурда) при обращении к этому методу - AssertableResponse
        return this;  // означает вернить сам себя, инстанс этого же обьекта
    }



    public String getValueLikeString(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной
/*
        log.info("-----------1-------------"+response.jsonPath().getMap("_embedded.customer[0]"));
       // log.info("-----------2-------------"+response.jsonPath().getMap(jsonPath));
        log.info("-----------3-------------"+response.jsonPath().get("_embedded.customer[0].lastName"));
        log.info("-----------3-------------"+response.jsonPath().getString("_embedded.customer[0].lastName"));
        log.info("-----------4-------------"+response.jsonPath().getString(jsonPath));
        log.info("-----------5-------------"+jsonPath);  //.assertThat().body("data.leagueId", equalTo(35)) RestAssured.registerParser
     //   log.info("-----------6-------------"+response.then().assertThat().body("_embedded.customer[0].lastName",not(isEmptyString())));
*/
       return  response.jsonPath().getString(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
        // return response.getCookies();
    }

    public Map<Object, Object> getValueLikeJSON(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной
/*
        log.info("-----------1-------------"+response.jsonPath().getMap("_embedded.customer[0]"));
        log.info("-----------2-------------"+response.jsonPath().getMap(jsonPath));
        log.info("-----------3-------------"+response.jsonPath().get("_embedded.customer[0].lastName"));
        log.info("-----------4-------------"+response.jsonPath().getString(jsonPath));
        log.info("-----------5-------------"+jsonPath);
*/
        return  response.jsonPath().getMap(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
        // return response.getCookies();
    }


    public Map<String, String> getAllCookies(String s){ //Выведит все куки из ответа от сервера
        return response.getCookies();
    }

    public String getCookiesByName(String jsonPath, String name){  //Выведит куки по имени,  из ответа от сервера
        return response.getCookie(name);
    }

    @Step
    public <T> T asPojo(Class<T> tClass) {   //Дженерики, метод для  возврата класса конкретного типа (например класс типа String)
        return response.as(tClass);          // c его помощью можно присваивать переменной все содержимое Боди респонса от сервера, а после проверять
    }

    /* //код без коментов
    public class AssertableResponse {
    private final Response response;

    public AssertableResponse shouldHave(Condition condition){

        log.info("About to check condition[{}]",condition);

        condition.check(response);
        return this;
    }

    public String getValue(String jsonPath){
        return  response.jsonPath().getString(jsonPath);
    }

    public <T> T asPojo(Class<T> tClass){
        return response.as(tClass);
    }
     */
}
