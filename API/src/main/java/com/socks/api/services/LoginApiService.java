package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payload.UserLogin;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginApiService extends ApiService{
    @Step
    public AssertableResponse autorizationUser(UserLogin userLogin){
        log.info("________________________________________________");
        log.info("#1  About login to dashboard with next credentials: email - {}",userLogin);


        Response response = setup()
                .body(userLogin)
                .when()
                .post("/userApi/fleets/login");
        System.out.println("Respons from REST-asured with value of user #1 = " + response.getBody().asString());
        System.out.println("Respons from REST-asured with set-Cookies #2 = " + response.getCookies());

        // return response;
        return new AssertableResponse(response);
    }
}
