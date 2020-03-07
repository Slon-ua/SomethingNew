package com.socks.ui.tests;

import com.codeborne.selenide.Condition;
import com.socks.ui.CatalogPage;
import com.socks.ui.ShoppingCardPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
@Epic("Ssopping card")
public class ShoppingCardTest extends BaseUITest{

    @Step
    @Story("Demo")
    @Test
    @Jira("Jira-3437")
    @Issue("Jira-3437")
    void userCanAddItemToCard() {
        ShoppingCardPage cardPage = CatalogPage.open()
                .addToCardByIndex(1)
                .addToCardByIndex(2)
                .addToCardByIndex(3)
                .goToCard();

        log.info("-----1-----");
        cardPage.totalAmount().shouldBe(Condition.text("$132.99"));

        log.info("-----2-----");
        cardPage.totalAmount().shouldHave(Condition.exactText("$132.99"));

        log.info("-----3-----");
        cardPage
                .totalAmount("$132.99")
                .productName(1)
                .productName(2)
                .productName(3)
                ;
    }
}
