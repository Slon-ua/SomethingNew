package com.socks.api.tests;

import com.socks.api.conditions.Conditions;
import com.socks.api.payload.UserLogin;
import com.socks.api.services.LoginApiService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

@Slf4j
public class TestLogin extends BaseAPITest {

    private final LoginApiService loginApiService = new LoginApiService();

    private UserLogin userLogin = new UserLogin()
            .device_token("73e879b8-eac0-46df-aa32-16bddadcebff")
            .device_type("web")
            .login_by("manual")
            .password("qwerty")
            .login("linden.eskimo@gmail.com");

    @Test
    @Tag("Fleet")
    @Jira("Jira-3437")
    @Issue("Jira-3437")
    @Description("User_can_login_to_dashboard")
    void testCanLoginWithRealCredentianals() {
        //      System.out.println("________________________________________________");


        String pojo = loginApiService.autorizationUser(userLogin)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("email", not(isEmptyString())))
                .shouldHave(Conditions.bodyField("email", equalTo(("linden.eskimo@gmail.com"))))
                .getValueLikeString("email");

        log.info("All JSON ===1===> " + pojo);
    }
}