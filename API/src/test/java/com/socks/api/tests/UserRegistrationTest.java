package com.socks.api.tests;

import com.socks.api.payload.UserPayload;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class UserRegistrationTest {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI="http://192.168.99.101";
    }

    @Test
    void testCanRegisterNewUserWithValidCredentials() {

        //given  -  не обязательный параметр, пишется для читабельности кода, за что отвечает этот кусок
       UserPayload userPayload = new UserPayload()
               .username("USER-"+RandomStringUtils.randomNumeric(5))
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
       registrNewUser(userPayload)
               .then()//.log().all()
               .body("id", not(isEmptyString()))
               .statusCode(200);
    }

    @Test
    void testCanNotCreateSameUserTwice() {
        //given
        UserPayload userPayload = new UserPayload()
                .username("USER-"+RandomStringUtils.randomNumeric(5))
                .password("12345")
                .email("usik@test.com");

        //when
        registrNewUser(userPayload)
                .then()//.log().all()
                .body("id", not(isEmptyString()))
                .statusCode(200);

        //then
        registrNewUser(userPayload)
                .then()//.log().all()
                .statusCode(500);
    }



    private RequestSpecification setup(){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                ;
    }

    private Response registrNewUser(UserPayload userPayload){
        return setup()
                .body(userPayload)
                .when()
                .post("/register");
    }

}
