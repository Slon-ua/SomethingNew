package com.socks.api.tests;

import io.qameta.allure.LabelAnnotation;
import io.qameta.allure.LinkAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@LabelAnnotation(name = "story")
@LinkAnnotation(type = "custom")
public @interface Jira {
    String value();
}
