package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payload.UserPayload;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiService extends ApiService {


    public AssertableResponse registerNewUser(UserPayload userPayload) {
        log.info("________________________________________________");
        log.info("#1  About to create new user  - {}", userPayload);

        Response response = setup()
                .body(userPayload)
                .when()
                .post("/register");

        log.info("#2  Successfully send response to create new user  {}",userPayload);  //(Что мы успешно отправили на регистрацию)

        return new AssertableResponse(response);

    }
}
