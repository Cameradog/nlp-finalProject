/**
 * @(#)ExtComponentFinder.java		Feb 1, 2013
 *
 * Copyright (c) 2013 BrainPad, Inc. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * BrainPad, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with BrainPad, Inc.
 */
package test.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author kato
 *
 */
public class ExtComponentFinder {
    private final JavascriptExecutor driver;
    private final WebDriver webdriver;

    /**
     * driver that implement the {@link JavascriptExecutor} interface.
     *
     * @param driver
     */
    public ExtComponentFinder(final WebDriver driver) {
        this.driver = (JavascriptExecutor) driver;
        this.webdriver = driver;
    }

    /**
     * <p>
     * Use the ComponentQuery of ExtJS find the {@link WebElement}.
     * Search results in the case of multiple return the first one found.
     * It finds components xtype: inherits the field
     * (Labels and input part is not directly obtain the input components have a set) and returns inside the input part if.
     * </p>
     *
     * @see #find(String, int)
     *
     * @param query ComponentQuery search sentence
     * @return The corresponding element. To the first one if you have multiple applicable
     */
    public WebElement find(final String query) {
        return find(query, 0);
    }

    /**
     * <p>
     * Use the ComponentQuery of ExtJS find the {@link WebElement}.
     * It finds components xtype: inherits the field
     * (Labels and input part is not directly obtain the input components have a set) and returns inside the input part.
     * </p>
     * <p>
     * For example, (and may not be direct children) include alias within panel widget.parentPanel on ExtJS,
     * look for the button itemId is #new as following.
     * <code>find("parentPanel button#new");</code>
     * </p>
     *
     * For more information about manual reference ExtJS.
     * Modified to call from WebDriverWait (2013/12/1)
     * In order to cause vomiting exception not catch up this is not a screen operation
     *
     * @param query Search sentence of ComponentQuery
     * @param index Index of the element that starts from 0
     * @return The corresponding element
     * @see http://docs.sencha.com/ext-js/4-1/#!/api/Ext.ComponentQuery
     */
    public WebElement find( final String query, final int index )
    {
        WebElement element;
        WebDriverWait wait = new WebDriverWait( this.webdriver, 6 );

        ExpectedCondition<WebElement> elm_wait = new ExpectedCondition<WebElement>( )
        {
            WebElement elm;
            private JavascriptExecutor jsdr;

            public WebElement apply( WebDriver driver )
            {
                jsdr = (JavascriptExecutor) driver;

                elm = (WebElement) jsdr.executeScript(
                        "var components = Ext.ComponentQuery.query('" + query + "');" +
                "console.log(components);" + 
                                "if (components && components.length > " + index + ") {" +
                                "  var component = components[" + index + "];" +
                                "  if (component.isXType('field')) {" +
                                "    return Ext.getDom(component.getInputId());" +
                                "  } else {" +
                                "    return component.getEl().dom;" +
                                "  }" +
                                "} else { return null; }");

                return elm;
            }
        };

        try
        {
            element = wait.until( elm_wait );
        }
        catch(Exception e)
        {
            //NULL After timeout
            element = null;
            System.out.println("Failure to fetch to the elements \n\n" );
        }

        return element;
    }

	/**
	 * Combo box for selection (if the query in the page is unique)
	 *
	 * @param query
	 * @param storeIndex
	 */
    public void selectCombobox(final String query, final int storeIndex)
    {
        selectCombobox(query, 0, storeIndex);

        return;
    }

    /**
     * Combo box for selection (if there is more than one query in the page)
     *
     * @param query
     * @param componentIndex
     * @param storeIndex
     */
    public void selectCombobox(final String query, final int componentIndex, final int storeIndex)
    {
        this.driver.executeScript(
                "var components = Ext.ComponentQuery.query('" + query + "');" +
                "if (components && components.length > " + componentIndex + ") {" +                
                "  var component = components[" + componentIndex + "];" +
                "  if (component.isXType('combobox')) {" +
                "    console.log(components); " +
                "    console.log(component); " + 
                "    console.log(component.getStore().getAt(" + storeIndex + ")); " + 
                "    component.select(component.getStore().getAt(" + storeIndex + "));" +
                "  }" +
                "}");

        return;
    }

    /**
     * If individual ItemId of the radio group is unique 
     * @param query
     * @param type
     */
    public void RadioGroup_Select( final String query, final String type )
    {
        RadioGroup_Select( query, 0, type );

        return;
    }

    /**
     * If individual ItemId of the radio group is more than one 
     * @param query
     */
    public void RadioGroup_Select( final String query, final int index , final String type )
    {
        this.driver.executeScript( "Ext.ComponentQuery.query('" + query + "')[" + index + "].select('" + type + "')" );

        return;
    }

}
