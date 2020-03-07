package com.socks.ui;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {
    public static CatalogPage open(){
        Selenide.open("/category.html");
        return page(CatalogPage.class);
       // return new CatalogPage();
    }

    public CatalogPage addToCardByIndex(int index) {
        $$("#products a.btn-primary").get(index-1).click();
        return this;
    }

    public ShoppingCardPage goToCard(){
        $("#numItemsInCart").click();
        return page(ShoppingCardPage.class);
    }
}
