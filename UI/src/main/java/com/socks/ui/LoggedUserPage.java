package com.socks.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoggedUserPage {

    @Step
    public  SelenideElement logoutBtn(){
        return $("#logout");
    }

    @Step
    public SelenideElement userName() {
        return $("#howdy > a");
    }

    @Step
    public SelenideElement userName(String s) {
        return $(s);
    }

    @Step
    public SelenideElement checkWalueOfElements(String elector, String verificationText) {
        return $(elector).shouldHave(Condition.text(verificationText));
    }
}
