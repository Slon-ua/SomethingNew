package com.socks.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoggedUserPage {

    public  SelenideElement logoutBtn(){
        return $("#logout");
    }

    public SelenideElement userName() {
        return $("#howdy > a");
    }

    public SelenideElement userName(String s) {
        return $(s);
    }

    public SelenideElement checkWalueOfElements(String elector, String verificationText) {
        return $(elector).shouldHave(Condition.text(verificationText));
    }
}
