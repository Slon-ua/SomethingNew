package com.socks.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MainPageFleet {

    @Step ("Open main page")
    public static MainPageFleet open() {
        Selenide.open("/");
        return new MainPageFleet();
    }

    @Step
    public void loginASS (String login, String password) {
        $("#root > main > div > div > div > div > div.form-side > form > label:nth-child(1) > input").setValue(login);
        Selenide.sleep(2000);
        $("#root > main > div > div > div > div > div.form-side > form > label:nth-child(2) > input").setValue(password);
        Selenide.sleep(2000);
        $("#root > main > div > div > div > div > div.form-side > form > div > div.w-xs-100.pb-3.pb-md-0 > div > button").click();

    }

    @Step
    public static String dataName(String name) {
        return "[data-e2e='" + name + "]'";
    }

    @Step
    public static SelenideElement element(String name) {return $(dataName(name));}
}
