package com.test.core;

import org.openqa.selenium.By;

public class Locator {

    private final String name;
    private final By by;

    public Locator(String name, By by) {
        this.name = name;
        this.by = by;
    }

    public String getName() {
        return name;
    }

    public By getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("Locator[%s: %s]", name, by.toString());
    }

    public static Locator byXpath(String name, String xpath) {
        return new Locator(name, By.xpath(xpath));
    }

    public static Locator byId(String name, String id) {
        return new Locator(name, By.id(id));
    }

    public static Locator byCss(String name, String cssSelector) {
        return new Locator(name, By.cssSelector(cssSelector));
    }

    public static Locator byClassName(String name, String className) {
        return new Locator(name, By.className(className));
    }

    public static Locator byName(String name, String elementName) {
        return new Locator(name, By.name(elementName));
    }

    public static Locator byLinkText(String name, String linkText) {
        return new Locator(name, By.linkText(linkText));
    }

    public static Locator byPartialLinkText(String name, String partialLinkText) {
        return new Locator(name, By.partialLinkText(partialLinkText));
    }

    public static Locator byTagName(String name, String tagName) {
        return new Locator(name, By.tagName(tagName));
    }
}
