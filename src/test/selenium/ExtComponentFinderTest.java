package test.selenium;
import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @(#)ExtComponentFinderTest.java		Feb 1, 2013
 *
 * Copyright (c) 2013 BrainPad, Inc. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * BrainPad, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with BrainPad, Inc.
 */

/**
 * @author kato
 *
 */
public class ExtComponentFinderTest {

    ExtComponentFinder testee;
    private WebDriver driver;

    @Before
    public void before() {
        this.driver = new FirefoxDriver();
        this.driver.get("http://stroustrup.brainpad.co.jp/ng/top.html");
    }

    @After
    public void afterClass() {
        this.driver.close();
    }

    @Test
//    @Ignore
    public void つかいかた() throws Exception {
        testee = new ExtComponentFinder(driver);

        WebElement element = testee.find("#tutorial");
        element.click();
    }

    @Test
//    @Ignore
    public void 見つからないときはnull() throws Exception {
        testee = new ExtComponentFinder(driver);

        WebElement element = testee.find("hoge");
        assertNull(element);
    }

    @Test
    public void 見つけたコンポーネントがformFieldの場合は内部の入力部品を返す() throws Exception {
        testee = new ExtComponentFinder(driver);

        // Screen transition until the input parts comes out
        testee.find("#top > tabpanel > tabbar tab", 1).click();
        testee.find("button#new").click();

        WebElement element = testee.find("configurationgroupnew textfield");
        assertEquals("input", element.getTagName());
    }

}
