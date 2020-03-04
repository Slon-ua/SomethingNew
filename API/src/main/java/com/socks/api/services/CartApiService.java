package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartApiService extends ApiService {

    @Step
    public AssertableResponse getUserOrdersInCart(){
        log.info("________________________________________________");
        log.info("#1  About to open cat of user with next credentials - {}",userTokenOrCookies);

        Response response =
                setup() //  Подставляем куки которые получаем после авторизации пользователя
                        //.cookie("{_TRAEFIK_BACKEND=http://front-end:8079; md.sid=s%3A176ucMyDu-EjdXfq8FbeeKpiJ0eyZfU8.XZnYfBp6a7BPR3%2BI7JNhYAihpC%2FIoazI8uYpv7g1YNU; logged_in=176ucMyDu-EjdXfq8FbeeKpiJ0eyZfU8}")
                        .cookie("TRAEFIK_BACKEND=http://front-end:8079; md.sid=s%3A176ucMyDu-EjdXfq8FbeeKpiJ0eyZfU8.XZnYfBp6a7BPR3%2BI7JNhYAihpC%2FIoazI8uYpv7g1YNU; logged_in=176ucMyDu-EjdXfq8FbeeKpiJ0eyZfU8;")
                        // .cookie("md.sid=s%3AAOJihVjYjwNh7rLWgeOL_vn8pUEuNgPo.gEU4vt%2FLZ4CiAirNZY2cazCjLn9HelWFnuNrKn7RM20; logged_in=AOJihVjYjwNh7rLWgeOL_vn8pUEuNgPo")
                        .get("/cart");
        System.out.println("Respons from REST-asured with value of cart #1 = " + response.getBody().asString());
        System.out.println("Respons from REST-asured with set-Cookies #2 = " + response.getCookies());

       // return response;
        return new AssertableResponse(response);
    }
}
