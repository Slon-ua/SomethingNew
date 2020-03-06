package com.socks.api.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiService {

    protected static String userTokenOrCookies;  // в эту перерменную через фуню loginToken() записываем Токен или Куки

    protected RequestSpecification setup() {
        RestAssured.registerParser("text/plain", Parser.JSON);

        loginToken();

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookie(userTokenOrCookies)
    //            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())  //при этих логах получаю ошибку
                .filters(new AllureRestAssured())
                ;
    }



    public void loginToken() {
        //authorisation logick with RestAssured
        userTokenOrCookies = "logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA\").get(\"/customers";
        // userTokenOrCookies = "token";
        //return "token";
    }
}
