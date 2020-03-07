package com.socks.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShoppingCardPage {

    public SelenideElement totalAmount(){
     //   $("").shouldBe(Condition.text(sum));
        return $("#cartTotal");
    }

    public ShoppingCardPage totalAmount(String sum){
         $("#cartTotal").shouldHave(Condition.exactText(sum));
         return this;
    }

    public ShoppingCardPage productName(Integer index, String price){
         $$("td:nth-child(5)").get(index-1).shouldHave(Condition.exactText(price));
        return this;
    }

    public ShoppingCardPage productName(Integer index){
        $$("td:nth-child(5)").get(index-1).shouldBe(Condition.not(empty));
        return this;
    }
}
