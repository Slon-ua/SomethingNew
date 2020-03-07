package com.socks.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    @Step ("Open main page")
    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();
    }

    @Step
    public void loginAs(String username, String password) {
        $("#login > a").click();
        $("#username-modal").setValue(username);
        Selenide.sleep(2000);
        $("#password-modal").setValue(password);
        Selenide.sleep(2000);
        $("#login-modal p button").click();

    }

    @Step
    public static String dataName(String name) {
        return "[data-e2e='" + name + "]'";
    }

    @Step
    public static SelenideElement element(String name) {return $(dataName(name));}
}
