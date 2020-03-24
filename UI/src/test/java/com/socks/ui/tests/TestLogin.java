package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.socks.api.conditions.Conditions;
import com.socks.api.payload.UserPayload;
import com.socks.api.services.UserApiService;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
@Epic("Login")
public class TestLogin extends BaseUITest {

    //    RestAssured restAssured = new RestAssured();
    private UserApiService userApiService = new UserApiService();

    public static Set<Cookie> cookies;
    public static Cookie logged_in;
    public static Cookie  mdSid;


    @Step
    @Story("Demo")
    @Test
    @Jira(" Jira-3435")
    @Issue("Jira-3436  ")
    public void userCanLoginWithValidCredentials() {

        //given
        UserPayload userPayload = new UserPayload()
                .username("USER-" + RandomStringUtils.randomNumeric(5))
                .password("12345")
                .email("usik@test.com");

        userApiService.registerNewUser(userPayload)
                .shouldHave(Conditions.statusCode(200));

        //when
        log.info("----1---");

        MainPage
                .open()
                .loginAs(userPayload.username(), userPayload.password())
        ;


        //then
        LoggedUserPage logoutUserPage = at(LoggedUserPage.class);

        logoutUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        logoutUserPage.userName().shouldHave(Condition.text("Logged in as"));

        logoutUserPage.userName("#howdy > a").shouldHave(Condition.text("Logged in as")); //тоже самое что выше

        logoutUserPage.checkWalueOfElements("#howdy > a", "Logged in as"); //тоже самое что выше



        log.info("----2---");

        cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        logged_in = WebDriverRunner.getWebDriver().manage().getCookieNamed("logged_in");
        mdSid = WebDriverRunner.getWebDriver().manage().getCookieNamed("md.sid");
        System.out.println(logged_in);
        System.out.println(mdSid);


        log.info("----3---");

        $("#tabCatalogue").waitUntil(Condition.text("Catalogue "), 10000); // ожидает элемент 10 секунд, если появится раньше, о не будет выжидать все 10сек


        log.info("----4---");  //#tabCatalogue > ul > li > div > div > div:nth-child(3) > ul > li:nth-child(1) > a

        $("#tabCatalogue > a").should(visible, enabled).hover()
                .shouldHave(cssValue("text-transform", "uppercase"));
        $("#tabCatalogue > a").should(visible, enabled).contextClick().$$("li >div > div > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a");
        //$$("").;

        sleep(5000);


        log.info("----5--- поиск по выпадающему списку");
        //open("http://192.168.99.101/category.html");
        $("#tabCatalogue > a").click();
        $("select").click();
        $("select").selectOptionContainingText("Name");


        sleep(5000);


        log.info("----6---");


    }


    @Test
    void userCanLoginWithCookies() {


        MainPage
                .open()
        ;

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

        System.out.println(WebDriverRunner.getWebDriver().manage().getCookies());

        System.out.println("----1----"+cookies);

        for (Cookie cookie:cookies){
            WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        }

//        WebDriverRunner.getWebDriver().manage().addCookie(logged_in);
//        WebDriverRunner.getWebDriver().manage().addCookie(mdSid);


        MainPage
                .open()
        ;


        LoggedUserPage logoutUserPage = at(LoggedUserPage.class);
        logoutUserPage.userName().shouldHave(Condition.text("Logged in as"));

        WebDriverRunner.getWebDriver().manage().getCookies();
        System.out.println("----2----"+cookies);
    }
}
