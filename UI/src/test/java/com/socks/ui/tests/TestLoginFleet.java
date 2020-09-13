package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.socks.api.payload.UserLogin;
import com.socks.api.services.UserApiService;
import com.socks.ui.LoggedUserPageFleet;
import com.socks.ui.MainPageFleet;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Slf4j
@Epic("Login")
public class TestLoginFleet extends BaseUITest {

    //    RestAssured restAssured = new RestAssured();
    private UserApiService userApiService = new UserApiService();


    @Step
    @Story("Fleet")
    @Tag("Fleet")
    @Test
    @Jira(" Jira-3435")
    @Issue("Jira-3436  ")
    @Description("User_can_login_to_dashboard_from_UI")
    public void userCanLoginWithValidCredentials() {

        //given
        UserLogin userLogin = new UserLogin()
                .login("linden.eskimo@gmail.com")
                .password("qwerty");

        MainPageFleet
                .open()
                .loginASS(userLogin.login(),userLogin.password())
        ;

        //then
        LoggedUserPageFleet logoutUserPageFleet = at(LoggedUserPageFleet.class);

        logoutUserPageFleet.leftNav().shouldHave(Condition.text("Fleet"));
        logoutUserPageFleet.userName().shouldHave(Condition.text("Nadezhda Moha"));

        logoutUserPageFleet.userName("#app-container > div > div > div > div > div > div.sidebar-scroll-wrap.d-flex.flex-column > div.flex-grow-1 > ul > div > h4").shouldHave(Condition.text("Nadezhda Super Company")); //тоже самое что выше

        logoutUserPageFleet.checkWalueOfElements("#app-container > main > div.container-fluid.flex-grow-1 > div.position-relative > div:nth-child(2) > div:nth-child(1) > div > div > h3 > span", "Vehicle status"); //тоже самое что выше
    }
}
