package com.socks.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoggedUserPageFleet {

    @Step
    public  SelenideElement userName(){
        return $("#app-container > nav > div > div.user.d-inline-block > div > button > span.name.mr-1.d-none.d-lg-inline-block");
    }

    @Step
    public SelenideElement leftNav() {
        return $("#app-container > div > div > div > div > div > div.sidebar-scroll-wrap.d-flex.flex-column > div.flex-grow-1 > ul > li.active.nav-item > a");
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
