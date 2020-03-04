package com.socks.api.tests;

import com.socks.api.conditions.Conditions;
import com.socks.api.payload.UserPayload;
import com.socks.api.responses.UserRegistrationResponce;
import com.socks.api.services.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

@Slf4j
public class UserRegistrationTest extends BaseTest{

    private final UserApiService userApiService = new UserApiService();  //final - переменная закрыта на изменение

//    @BeforeAll
//    static void setUp() {
//        RestAssured.baseURI = "http://192.168.99.101";
//    }

    @Test
    void testCanRegisterNewUserWithValidCredentials() {

        //given  -  не обязательный параметр, пишется для читабельности кода, за что отвечает этот кусок
        UserPayload userPayload = new UserPayload()
                .username("USER-" + RandomStringUtils.randomNumeric(5))
                .password("12345")
                .email("usik@test.com");

       /*
        UserPayload userPayload = new UserPayload();
        userPayload.setUsername("USER-"+RandomStringUtils.randomNumeric(5));
        userPayload.setPassword("PASS1");
        userPayload.setEmail("usik@test.com");
        */
       /*
        String body = "{\n" +
                "  \"username\": \"USER1\",\n" +
                "  \"password\": \"PASS1\",\n" +
                "  \"email\": \"usik@test.com\"\n" +
                "}";
        */

       /* RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(userPayload)
                .when()
                .post("/register")
                .then().log().all()
                .body("id", not(isEmptyString()))
                .statusCode(200);
       */

        //expect
        UserRegistrationResponce pojo = userApiService.registerNewUser(userPayload)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())))
        //        .getValueLikeString("id")
                .asPojo(UserRegistrationResponce.class)  //Работает через pojo, только нужно будет поменять тип данных
                ;

          BDDAssertions.then(pojo.getId()).isNotEmpty();
      //  assertThat(pojo.getId()).isNotEmpty();

          log.info("All JSON use jsonPath ===1===> "+pojo);
          log.info("All JSON use assertThat ===2===> "+pojo.getId());
        //  log.info("All JSON ======> "+pojo.getId().length());


//               .then()//.log().all()
//               .body("id", not(isEmptyString()))
//               .statusCode(200);
    }

    @Test
    void testCanNotCreateSameUserTwice() {
        //given
        UserPayload userPayload = new UserPayload()
                .username("USER-" + RandomStringUtils.randomNumeric(5))
                .password("12345")
                .email("usik@test.com");

        //when
        Map<String, String> cookies = userApiService.registerNewUser(userPayload)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())))
                .getAllCookies("");

        log.info("All COOKIAS ======> "+cookies);

        //then
        log.info("#3 Check thet we can't create new user");
        String cookiByName = userApiService.registerNewUser(userPayload)
                .shouldHave(Conditions.statusCode(500))
                .getCookiesByName("", "md.sid");

        log.info("cookiByName \"md.sid\" ======> "+cookiByName);

//        //when
//        userApiService.registerNewUser(userPayload)
//                .then()//.log().all()
//                .body("id", not(isEmptyString()))
//                .statusCode(200);
//
//        //then
//        userApiService.registerNewUser(userPayload)
//                .then()//.log().all()
//                .statusCode(500);
    }


/*
    private RequestSpecification setup(){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                ;
    }

    private Response registerNewUser(UserPayload userPayload){
        return setup()
                .body(userPayload)
                .when()
                .post("/register");
    }
*/
}
