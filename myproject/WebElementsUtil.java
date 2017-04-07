package com.symantec.cws.automation.test.framework.ui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symantec.cws.automation.test.framework.util.Utils;

/**
 * This contains all method related to web elements.
 * 
 * @author Avinesh_Kumar
 *
 */

public class WebElementsUtil {

	private static final Logger LOG = LoggerFactory.getLogger(WebElementsUtil.class);
	private static final int DEFAULT_WAIT = 15;
	private static final int DEFAULT_MAX_WAIT_ELEMENT_TOHIDE = 120;

	private WebDriver driver;
	private static Map<WebDriver, WebElementsUtil> webDriverUtilsPool;
	private Actions actions;

	private WebElementsUtil(WebDriver webDriver) {
		driver = webDriver;
		actions = new Actions(driver);
	}

	public static WebElementsUtil getInstance(WebDriver driver) {
		WebElementsUtil automUtils = null;
		if (webDriverUtilsPool == null) {
			automUtils = new WebElementsUtil(driver);
			webDriverUtilsPool = new HashMap<WebDriver, WebElementsUtil>();
			webDriverUtilsPool.put(driver, automUtils);
		} else {
			if (webDriverUtilsPool.keySet().contains(driver)) {
				automUtils = webDriverUtilsPool.get(driver);
			} else {
				automUtils = new WebElementsUtil(driver);
				webDriverUtilsPool.put(driver, automUtils);
			}
		}
		return automUtils;
	}

	public Actions getActionsInstance() {
		return actions;
	}

	/**
	 * return element if it is visible on page.
	 * 
	 * @param t
	 * @param locatorValue
	 * @param wait
	 * @return
	 */
	public WebElement getVisibleElement(LocatorType t, String locatorValue, int wait) {
		WebElement element = null;

		switch (t) {
		case ID:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			break;
		case CLASSNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			break;

		case XPATH:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));

			break;
		case CSSSELECTOR:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));

			break;

		case NAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));

			break;

		case TAGNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));

			break;

		case LINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));

			break;

		case PARTIALLINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));

			break;
		default:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));

			break;
		}

		return element;
	}

	/**
	 * return list of web element if it element identified by locator is visible
	 * on page.
	 * 
	 * @param t
	 * @param locatorValue
	 * @param wait
	 * @return
	 */
	public List<WebElement> getVisibleElements(LocatorType t, String locatorValue, int wait) {
		List<WebElement> element = null;

		switch (t) {
		case ID:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
			break;
		case CLASSNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorValue)));
			break;

		case XPATH:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));

			break;
		case CSSSELECTOR:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));

			break;

		case NAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));

			break;

		case TAGNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorValue)));

			break;

		case LINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));

			break;

		case PARTIALLINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));

			break;
		default:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));

			break;
		}

		return element;
	}

	public boolean waitUntilWebElementHidden(LocatorType t, String locatorValue, int wait) {

		boolean isHidden = false;

		switch (t) {
		case ID:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorValue)));
			break;
		case CLASSNAME:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.className(locatorValue)));
			break;

		case XPATH:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locatorValue)));

			break;
		case CSSSELECTOR:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locatorValue)));

			break;

		case NAME:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.name(locatorValue)));

			break;

		case TAGNAME:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(locatorValue)));

			break;

		case LINKTEXT:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(locatorValue)));

			break;

		case PARTIALLINKTEXT:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(locatorValue)));

			break;
		default:
			isHidden = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorValue)));

			break;
		}

		return isHidden;
	}

	/**
	 * return web element if it is present in DOM, this method doesn't check the
	 * visibility of <br>
	 * web element on page. For web element visibility refer
	 * {@link #getVisibleElement(LocatorType, String, int)}
	 *
	 * @param t
	 * @param locatorValue
	 * @param wait
	 * @return
	 * @see #getVisibleElement(LocatorType, String, int)
	 * 
	 */
	private WebElement getWebElement(LocatorType t, String locatorValue, int wait) {
		WebElement element = null;

		switch (t) {
		case ID:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
			break;
		case CLASSNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
			break;

		case XPATH:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));

			break;
		case CSSSELECTOR:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));

			break;

		case NAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));

			break;

		case TAGNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));

			break;

		case LINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));

			break;

		case PARTIALLINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));

			break;
		default:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));

			break;
		}

		return element;
	}

	/**
	 * return List of web element if it is present in DOM, this method doesn't
	 * check the visibility of <br>
	 * web element on page. For web element visibility refer
	 * {@link #getVisibleElements(LocatorType, String, int)}
	 *
	 * @param t
	 * @param locatorValue
	 * @param wait
	 * @return
	 */
	private List<WebElement> getWebElements(LocatorType t, String locatorValue, int wait) {
		List<WebElement> element = null;

		switch (t) {
		case ID:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(locatorValue)));
			break;
		case CLASSNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(locatorValue)));
			break;

		case XPATH:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locatorValue)));

			break;
		case CSSSELECTOR:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(locatorValue)));

			break;

		case NAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(locatorValue)));

			break;

		case TAGNAME:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(locatorValue)));

			break;

		case LINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(locatorValue)));

			break;

		case PARTIALLINKTEXT:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));

			break;
		default:
			element = (new WebDriverWait(driver, wait))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(locatorValue)));

			break;
		}

		return element;
	}

	/**
	 * return web element if it is present in DOM, this method doesn't check the
	 * visibility of <br>
	 * web element on page. For web element visibility refer
	 * {@link #getVisibleElement(LocatorType, String, int)}
	 *
	 * @param type
	 *            Locator Type
	 * @param locatorValue
	 *            text value of locator.
	 * @param wait
	 *            time in sec.
	 * @throws Exception
	 */

	public WebElement getElement(LocatorType type, String locatorValue, int wait) {
		WebElement element = null;

		try {
			if (type == null)
				return null;

			element = getWebElement(type, locatorValue, wait);

			if (element == null) {
				System.out.println("got null element. retrying to find element..");
				element = getWebElement(type, locatorValue, wait);
			}

			if (element == null) {
				throw new NullPointerException(
						"Null. Couldn't find element with " + type.get() + " value :" + locatorValue + ".");
			}

		} catch (NoSuchElementException e) {

		} catch (Exception e) {
			throw e;
		}
		return element;
	}

	/**
	 * return List of web element if it is present in DOM, this method doesn't
	 * check the visibility of <br>
	 * web element on page. For web element visibility refer
	 * {@link #getVisibleElements(LocatorType, String, int)}
	 *
	 * 
	 * @param type
	 * @param locatorValue
	 * @param wait
	 * @return
	 */
	public List<WebElement> getElements(LocatorType type, String locatorValue, int wait) {
		List<WebElement> element = null;

		try {
			if (type == null)
				return null;

			element = getWebElements(type, locatorValue, wait);

			if (element == null) {
				System.out.println("got null element. retrying to find element..");
				element = getWebElements(type, locatorValue, wait);
			}

			if (element == null) {
				throw new NullPointerException(
						"Null. Couldn't find element with " + type.get() + " value :" + locatorValue + ".");
			}

		} catch (NoSuchElementException e) {

		} catch (Exception e) {
			throw e;
		}
		return element;
	}

	/**
	 * It will turn off the implicit wait.
	 * 
	 * @param driver
	 */
	public void turnOffImplicitWaits(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/**
	 * It will set the implicit wait to 30 sec. Use only when you are using
	 * {@code turnOffImplicitWaits} method.
	 * 
	 * @param driver
	 */
	public void turnOnImplicitWaits(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
	}

	/**
	 * find element with no implicit wait.
	 * 
	 * @param t
	 * @param locatorValue
	 * @return
	 */
	public WebElement getElementNoImplicitWait(LocatorType t, String locatorValue) {
		turnOffImplicitWaits(driver);
		WebElement element = getElementNoCheck(t, locatorValue);
		turnOnImplicitWaits(driver);
		return element;
	}

	/**
	 * find elements with no implicit wait.
	 * 
	 * @param t
	 * @param locatorValue
	 * @return
	 */
	public List<WebElement> getElementsNoImplicitWait(LocatorType t, String locatorValue) {
		turnOffImplicitWaits(driver);
		List<WebElement> elements = getElementsNoChecks(t, locatorValue);
		turnOnImplicitWaits(driver);
		return elements;
	}
	
	/**
	 * return WebElement if it is present in DOM otherwise null.
	 * @param t
	 * @param locatorValue
	 * @return
	 */
	public WebElement isElementLocated(LocatorType t, String locatorValue) {
		try {
			return getElementNoCheck(t, locatorValue);
		}catch(NoSuchElementException e) {
			return null;
		}
	}
	
	/**
	 * default findElement
	 * 
	 * @param t
	 * @param locatorValue
	 * @return
	 */
	public WebElement getElement(WebElement parentElement, LocatorType childLocatorTyp, String childLocatorValue) {
		turnOffImplicitWaits(driver);
		WebElement element = null;

		switch (childLocatorTyp) {
		case ID:
			element = parentElement.findElement(By.id(childLocatorValue));
			break;
		case CLASSNAME:
			element = parentElement.findElement(By.className(childLocatorValue));
			break;

		case XPATH:
			element = parentElement.findElement(By.xpath(childLocatorValue));
			break;
		case CSSSELECTOR:
			element = parentElement.findElement(By.cssSelector(childLocatorValue));
			break;

		case NAME:
			element = parentElement.findElement(By.name(childLocatorValue));
			break;

		case TAGNAME:
			element = parentElement.findElement(By.tagName(childLocatorValue));
			break;

		case LINKTEXT:
			element = parentElement.findElement(By.linkText(childLocatorValue));
			break;

		case PARTIALLINKTEXT:
			element = parentElement.findElement(By.partialLinkText(childLocatorValue));
			break;
		default:
			element = parentElement.findElement(By.id(childLocatorValue));
			break;
		}
		turnOnImplicitWaits(driver);

		return element;
	}

	/**
	 * default findElement
	 * 
	 * @param t
	 * @param locatorValue
	 * @return
	 */
	public WebElement getElementNoCheck(LocatorType t, String locatorValue) {
		turnOffImplicitWaits(driver);
		WebElement element = null;

		switch (t) {
		case ID:
			element = driver.findElement(By.id(locatorValue));
			break;
		case CLASSNAME:
			element = driver.findElement(By.className(locatorValue));
			break;

		case XPATH:
			element = driver.findElement(By.xpath(locatorValue));
			break;
		case CSSSELECTOR:
			element = driver.findElement(By.cssSelector(locatorValue));
			break;

		case NAME:
			element = driver.findElement(By.name(locatorValue));
			break;

		case TAGNAME:
			element = driver.findElement(By.tagName(locatorValue));
			break;

		case LINKTEXT:
			element = driver.findElement(By.linkText(locatorValue));
			break;

		case PARTIALLINKTEXT:
			element = driver.findElement(By.partialLinkText(locatorValue));
			break;
		default:
			element = driver.findElement(By.id(locatorValue));
			break;
		}
		turnOnImplicitWaits(driver);

		return element;
	}
	
	

	/**
	 * default findElements
	 * 
	 * @param t
	 * @param locatorValue
	 * @param wait
	 * @return
	 */
	public List<WebElement> getElementsNoChecks(LocatorType t, String locatorValue) {
		List<WebElement> element = null;

		switch (t) {
		case ID:
			element = driver.findElements(By.id(locatorValue));
			break;
		case CLASSNAME:
			element = driver.findElements(By.className(locatorValue));
			break;

		case XPATH:
			element = driver.findElements(By.xpath(locatorValue));
			break;
		case CSSSELECTOR:
			element = driver.findElements(By.cssSelector(locatorValue));
			break;

		case NAME:
			element = driver.findElements(By.name(locatorValue));
			break;

		case TAGNAME:
			element = driver.findElements(By.tagName(locatorValue));
			break;

		case LINKTEXT:
			element = driver.findElements(By.linkText(locatorValue));
			break;

		case PARTIALLINKTEXT:
			element = driver.findElements(By.partialLinkText(locatorValue));
			break;
		default:
			element = driver.findElements(By.id(locatorValue));
			break;
		}

		return element;
	}

	/**
	 * {@link #getElement(LocatorType, String, int)} with {@link #DEFAULT_WAIT}
	 * = {@value #DEFAULT_WAIT}
	 * 
	 * @param locator
	 * @param locatorValue
	 * @return
	 */
	public WebElement getElement(LocatorType locator, String locatorValue) {
		return getElement(locator, locatorValue, DEFAULT_WAIT);
	}

	public WebElement getElementById(String locatorValue) {
		return getElement(LocatorType.ID, locatorValue, DEFAULT_WAIT);
	}

	public WebElement getElementByCSS(String locatorValue) {
		return getElement(LocatorType.CSSSELECTOR, locatorValue, DEFAULT_WAIT);
	}

	public WebElement getElementByXpath(String locatorValue) {
		return getElement(LocatorType.XPATH, locatorValue, DEFAULT_WAIT);
	}

	/**
	 * {@link #getElements(LocatorType, String, int)} with {@link #DEFAULT_WAIT}
	 * = {@value #DEFAULT_WAIT}
	 * 
	 * @param locator
	 * @param locatorValue
	 * @return
	 */
	public List<WebElement> getElements(LocatorType locator, String locatorValue) {
		return getElements(locator, locatorValue, DEFAULT_WAIT);
	}

	public List<WebElement> getElementsById(String locatorValue) {
		return getElements(LocatorType.ID, locatorValue, DEFAULT_WAIT);
	}

	public List<WebElement> getElementsByCSS(String locatorValue) {
		return getElements(LocatorType.CSSSELECTOR, locatorValue, DEFAULT_WAIT);
	}

	public List<WebElement> getElementsByXpath(String locatorValue) {
		return getElements(LocatorType.XPATH, locatorValue, DEFAULT_WAIT);
	}

	/**
	 * return the text of web element.
	 * 
	 * @see #getElement(LocatorType, String, int)
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param waitInSec
	 * @return
	 */
	public String getText(LocatorType locatorType, String locatorValue, int waitInSec) {
		try {
			return getElement(locatorType, locatorValue, waitInSec).getText();
		} catch (Exception e) {
			LOG.error(String.format("Error in getting text from %s element of type %s", locatorValue,
					locatorType.toString()), e);
		}
		return "";
	}

	public String getTextById(String locatorValue) {
		return getText(LocatorType.ID, locatorValue, DEFAULT_WAIT);
	}

	public String getTextByCSS(String locatorValue) {
		return getElement(LocatorType.CSSSELECTOR, locatorValue, DEFAULT_WAIT).getText();
	}

	public String getTextByXpath(String locatorValue) {
		return getElement(LocatorType.XPATH, locatorValue, DEFAULT_WAIT).getText();
	}

	/**
	 * click web element.
	 * 
	 * @see #getElement(LocatorType, String, int)
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param waitInSec
	 * @return
	 */
	public void clickElement(LocatorType locatorType, String locatorValue, int waitInSec) {
		getElement(locatorType, locatorValue, waitInSec).click();
	}

	/**
	 * click web element with default wait.
	 * 
	 * @see #getElement(LocatorType, String, int)
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public void clickElement(LocatorType locatorType, String locatorValue) {
		getElement(locatorType, locatorValue, DEFAULT_WAIT).click();
	}

	public void clickElementById(String locatorValue) {
		getElement(LocatorType.ID, locatorValue, DEFAULT_WAIT).click();
	}

	public void clickElementByCSS(String locatorValue) {
		getElement(LocatorType.CSSSELECTOR, locatorValue, DEFAULT_WAIT).click();
	}

	public void clickElementByXpath(String locatorValue) {
		getElement(LocatorType.XPATH, locatorValue, DEFAULT_WAIT).click();
	}

	public void clickCheckBoxById(String elementId) {
		String css = "#"+elementId+" +ins";
		WebElement element = getElement(LocatorType.CSSSELECTOR, css, DEFAULT_WAIT);
		scrollWebElementIntoView(element);
		element.click();
	}
	
    public void clickRadioById(String elementId) {
    	String css = "#"+elementId+" +ins";
    	WebElement element = getElement(LocatorType.CSSSELECTOR, css, DEFAULT_WAIT);
		scrollWebElementIntoView(element);
		element.click();
	}
    
    public void clickCheckBoxByCSS(String css) {
		css = css+" +ins";
		WebElement element = getElement(LocatorType.CSSSELECTOR, css, DEFAULT_WAIT);
		scrollWebElementIntoView(element);
		element.click();
	}
	
    public void clickRadioByCSS(String css) {
    	css = css+" +ins";
    	WebElement element = getElement(LocatorType.CSSSELECTOR, css, DEFAULT_WAIT);
		scrollWebElementIntoView(element);
		element.click();
	}


	/**
	 * send keys to web element.
	 * 
	 * @see #getElement(LocatorType, String, int)
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param waitInSec
	 * @return
	 */
	public void sendKeys(LocatorType locatorType, String locatorValue, String text, int waitInSec) {
		WebElement element = getElement(locatorType, locatorValue, waitInSec);
		element.clear();
		element.sendKeys(text);
	}

	public void sendKeys(LocatorType locatorType, String locatorValue, String text) {
		WebElement element = getElement(locatorType, locatorValue, DEFAULT_WAIT);
		element.clear();
		element.sendKeys(text);
	}

	public void sendKeysById(String locatorValue, String text) {
		WebElement element = getElement(LocatorType.ID, locatorValue, DEFAULT_WAIT);
		element.clear();
		element.sendKeys(text);
	}

	// start dropdown related functions.

	/**
	 * select dropdown by visible text.
	 * 
	 * @param type
	 * @param locatorValue
	 * @param wait
	 * @param text
	 */
	public void selectDropDownByText(LocatorType type, String locatorValue, int wait, String text) {
		WebElement el = getElement(type, locatorValue, wait);
		if (el != null) {
			Select dropdown = new Select(el);
			dropdown.selectByVisibleText(text);
		}
	}

	/**
	 * select dropdown by visible text with default wait
	 * 
	 * @param type
	 * @param locatorValue
	 * @param text
	 */
	public void selectDropDownByText(LocatorType type, String locatorValue, String text) {
		WebElement el = getElement(type, locatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			dropdown.selectByVisibleText(text);
		}
	}

	/**
	 * get dropdown element by ID and select by visible text with default wait
	 * 
	 * @param type
	 * @param locatorValue
	 * @param text
	 */
	public void selectDropDownByTextID(String locatorValue, String text) {
		WebElement el = getElement(LocatorType.ID, locatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			dropdown.selectByVisibleText(text);
		}
	}

	/**
	 * select dropdown by value.
	 * 
	 * @param type
	 * @param locatorValue
	 * @param wait
	 * @param text
	 */
	public void selectDropDownByValue(LocatorType type, String locatorValue, int wait, String text) {
		WebElement el = getElement(type, locatorValue, wait);
		if (el != null) {
			Select dropdown = new Select(el);
			dropdown.selectByValue(text);
		}
	}

	/**
	 * select dropdown by value with default wait
	 * 
	 * @param type
	 * @param locatorValue
	 * @param text
	 */
	public void selectDropDownByValue(LocatorType type, String locatorValue, String text) {
		WebElement el = getElement(type, locatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			dropdown.selectByValue(text);
		}
	}

	/**
	 * get dropdown element by ID and select dropdown value by value attribute
	 * with default wait
	 * 
	 * @param type
	 * @param locatorValue
	 * @param text
	 */
	public void selectDropDownByValueID(String locatorValue, String text) {
		WebElement el = getElement(LocatorType.ID, locatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			dropdown.selectByValue(text);
		}
	}

	/**
	 * This Api automatically suffix <code><b>string:</b> </code> to value of dropdown options as in new implementation of ui, it is going<br>
	 * to be case for every dropdown.<br>
	 * get dropdown element by ID and select dropdown value by value attribute
	 * with default wait
	 * 
	 * @param type
	 * @param locatorValue
	 * @param text
	 */
	public void selectDropDownByStringValueID(String locatorValue, String value) {
		String modvalue = "string:"+value;
		selectDropDownByValueID(locatorValue, modvalue);
	}
	
	/**
	 * This Api automatically suffix <code><b>number:</b> </code> to value of dropdown options as in new implementation of ui, it is going<br>
	 * to be case for every dropdown.<br>
	 * get dropdown element by ID and select dropdown value by value attribute
	 * with default wait
	 * 
	 * @param type
	 * @param locatorValue
	 * @param text
	 */
	public void selectDropDownByNumValueID(String locatorValue, String value) {
		String modvalue = "number:"+value;
		selectDropDownByValueID(locatorValue, modvalue);
	}
	
	public List<String> getSelectOptions(LocatorType locatorType, String selectLocatorValue) {
		WebElement el = getElement(locatorType, selectLocatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			return dropdown.getOptions().parallelStream().map(opt -> opt.getText().trim()).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	/**
	 * get all element text of a dropdown select by ID of select element
	 * 
	 * @param selectLocatorValue
	 * @return List of element text
	 */
	public List<String> getSelectOptionsById(String selectLocatorValue) {
		return getSelectOptions(LocatorType.ID, selectLocatorValue);
	}

	/**
	 * get all element text of a dropdown select by CSS of select element
	 * 
	 * @param selectLocatorValue
	 * @return List of element text
	 */
	public List<String> getSelectOptionsByCss(String selectLocatorValue) {
		return getSelectOptions(LocatorType.CSSSELECTOR, selectLocatorValue);
	}

	/**
	 * get all element text of a dropdown select by XPATH of select element
	 * 
	 * @param selectLocatorValue
	 * @return List of element text
	 */
	public List<String> getSelectOptionsByXpath(String selectLocatorValue) {
		return getSelectOptions(LocatorType.XPATH, selectLocatorValue);
	}
	
	public List<String> getSelectOptionsValue(LocatorType locatorType, String selectLocatorValue) {
		WebElement el = getElement(locatorType, selectLocatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			return dropdown.getOptions().stream().map(opt -> opt.getAttribute("value")).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	/**
	 * get all element value attribute of a dropdown select by ID of select element
	 * 
	 * @param selectLocatorValue
	 * @return List of element text
	 */
	public List<String> getSelectOptionsValueById(String selectLocatorValue) {
		return getSelectOptionsValue(LocatorType.ID, selectLocatorValue);
	}

	/**
	 * get all element value attribute of a dropdown select by CSS of select element
	 * 
	 * @param selectLocatorValue
	 * @return List of element text
	 */
	public List<String> getSelectOptionsValueByCss(String selectLocatorValue) {
		return getSelectOptionsValue(LocatorType.CSSSELECTOR, selectLocatorValue);
	}

	/**
	 * get all element value attribute of a dropdown select by XPATH of select element
	 * 
	 * @param selectLocatorValue
	 * @return List of element text
	 */
	public List<String> getSelectOptionsValueByXpath(String selectLocatorValue) {
		return getSelectOptionsValue(LocatorType.XPATH, selectLocatorValue);
	}
	/**
	 * multi select elements from dropdown enabled with 'multiple' option
	 * 
	 * @param type
	 * @param locatorValue
	 * @param valueToSelect
	 */
	public void multiSelectDropDownByValue(LocatorType type, String locatorValue, List<String> valueToSelect) {
		WebElement el = getElement(type, locatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			el.sendKeys(Keys.LEFT_CONTROL);
			for (String value : valueToSelect)
				dropdown.selectByValue(value);
		}
	}
	
	/**
	 * get list of selected option in multi select dropdown.
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public List<String> getDropDownSelectedOptions(LocatorType locatorType, String locatorValue){
		WebElement el = getElement(locatorType, locatorValue, DEFAULT_WAIT);
		List<String> selectedOptions = new ArrayList<String>();
		if (el != null) {
			Select dropdown = new Select(el);
			for(WebElement sopt: dropdown.getAllSelectedOptions()) {
				selectedOptions.add(sopt.getText());
			}
		}
		return selectedOptions;
	}
	
	/**
	 * get selected option in dropdown.
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public String getDropDownSelectedOption(LocatorType locatorType, String locatorValue){
		WebElement el = getElement(locatorType, locatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			return dropdown.getFirstSelectedOption().getText();
		}
		return "";
	}
	
	public List<String> getDropDownSelectedOptionsById(String locatorValue){
		return getDropDownSelectedOptions(LocatorType.ID, locatorValue);
	}
	// end dropdown related functions.

	public String getTextFieldAndAreaValue(LocatorType locatorType, String locatorValue){
		WebElement tf = getElement(locatorType, locatorValue);
		return tf.getAttribute("value");
	}

	/**
	 * Use this only for angular element. This will wait upto 2 min with poll
	 * interval of 5 sec to web element(that has class attribute 'ng-hide') to
	 * be hidden.
	 * 
	 * @param maskWrapper
	 *            web element that contains class ng-hide or ng-show
	 */
	public void waitAngularElementToHidden(LocatorType type, String locatorValue) {
		waitAngularElementToHidden(type, locatorValue, DEFAULT_MAX_WAIT_ELEMENT_TOHIDE);
	}

	/**
	 * Use this only for angular element. This will wait upto 2 min with poll
	 * interval of 5 sec to web element(that has class attribute 'ng-hide') to
	 * be hidden.
	 * 
	 * @param maskWrapper
	 *            web element that contains class ng-hide or ng-show
	 * @param waitInSec
	 *            approx wait in sec
	 */
	public void waitAngularElementToHidden(LocatorType type, String locatorValue, int waitInSec) {
		WebElement maskWrapper = getElement(type, locatorValue);
		int waitInterval = 5;
		int MAXCOUNT = waitInSec / waitInterval;
		int count = 0;
		String cssclass = maskWrapper.getAttribute("class");
		System.out.println("0: "+cssclass);
		while (!cssclass.contains("ng-hide")) {
			if (count > MAXCOUNT) {
				break;
			}
			Utils.wait(waitInterval);
			cssclass = maskWrapper.getAttribute("class");
			System.out.println(cssclass);
			count++;
		}
	}
	
	/**
	 * Use this only for angular element. This will wait upto 2 min with poll
	 * interval of 5 sec to web element(that has class attribute 'ng-hide') to
	 * be hidden.
	 * 
	 * @param maskWrapper
	 *            web element that contains class ng-hide or ng-show
	 * @param waitInSec
	 *            approx wait in sec
	 */
	public void waitAngularElementToHidden(WebElement parentElement, LocatorType childElementType, String childElementTypeLocatorValue, int waitInSec) {
		WebElement maskWrapper = getElement(parentElement, childElementType, childElementTypeLocatorValue);
		int waitInterval = 1;
		int MAXCOUNT = waitInSec / waitInterval;
		int count = 0;
		String cssclass = maskWrapper.getAttribute("class");
		System.out.println("0: "+cssclass);
		while (!cssclass.contains("ng-hide")) {
			if (count > MAXCOUNT) {
				break;
			}
			Utils.wait(waitInterval);
			cssclass = maskWrapper.getAttribute("class");
			System.out.println(cssclass);
			count++;
			if(count>=5) {
				waitInterval = 5;
			}
		}
	}

	/**
	 * This will wait upto 2 min with poll interval of 5 sec to web element to
	 * be hidden.
	 * 
	 * @param element
	 *            web element that contains style attribute 'display:none'
	 */
	public void waitElementToHiddenByDisplayAttrib(LocatorType type, String locatorValue) {
		waitElementToHiddenByDisplayAttrib(type, locatorValue, DEFAULT_MAX_WAIT_ELEMENT_TOHIDE);
	}

	/**
	 * This will wait upto 2 min with poll interval of 5 sec to web element(that
	 * has style attribute 'display:none') to be hidden.
	 * 
	 * @param element
	 *            web element that contains style attribute 'display:none'
	 * @param waitInSec
	 *            approx wait in sec
	 */
	public void waitElementToHiddenByDisplayAttrib(LocatorType type, String locatorValue, int waitInSec) {
		WebElement element = getElement(type, locatorValue);
		int waitInterval = 5;
		int MAXCOUNT = waitInSec / waitInterval;
		int count = 0;
		String style = element.getAttribute("style");
		style = style.replaceAll(" ", "");
		while (!style.contains("display:none;")) {
			if (count > MAXCOUNT) {
				break;
			}
			Utils.wait(waitInterval);
			style = element.getAttribute("style");
			style = style.replaceAll(" ", "");
			System.out.println(style);
			count++;
		}
	}

	/**
	 * scroll the element if it is scrollable, on current page. element should
	 * be visible.
	 * 
	 * @param id
	 *            element id attribute value to be scrolled.
	 * @param h
	 *            integer value.
	 */
	public void scrollTop(String id, int h) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "$('#" + id + "').scrollTop(" + h + ");";
		js.executeScript(command);
	}

	/**
	 * execute the javascript on current browser page and return the result.
	 * 
	 * @param script
	 *            javascript to be executed.
	 * @return
	 */
	public Object executeJScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(script);
	}

	/**
	 * return the List of text contained in the web elements.
	 * 
	 * @param webelements
	 *            List of web elements
	 * @return
	 */
	public List<String> webElementsToText(List<WebElement> webelements) {
		List<String> elements = new ArrayList<String>();
		for (WebElement e : webelements) {
			elements.add(e.getText());
		}
		return elements;
	}
	
	public void clickByIdJS(String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "$('#" + id + "').click();";
		js.executeScript(command);
	}
	
	public void clickByCSSJS(String css) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "$('" + css + "').click();";
		js.executeScript(command);
	}
	
	public void clickByClassNameJS(String clsName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "$('." + clsName + "').click();";
		js.executeScript(command);
	}
	
	public void scrollToElement(String containerId, String targetElementId) {
		String js = "$('#"+containerId+"').mCustomScrollbar('scrollTo',$('#"+targetElementId+"'));";
		executeJScript(js);
	}
	
	public void scrollToElementByCSS(String containerCSS, String targetElementCSS) {
		String js = "$('"+containerCSS+"').mCustomScrollbar('scrollTo',$('"+targetElementCSS+"'));";
		executeJScript(js);
	}
	
	public void scrollTo(String containerId, int pxValueFromTop) {
		String js = "$('#"+containerId+"').mCustomScrollbar('scrollTo',$('#"+pxValueFromTop+"'));";
		executeJScript(js);
	}
	
	public void scrollWebElementIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	

	public void multiSelectDropDownByText(LocatorType locType, String locatorValue, List<String> valueToSelect) {
		WebElement el = getElement(locType, locatorValue, DEFAULT_WAIT);
		if (el != null) {
			Select dropdown = new Select(el);
			el.sendKeys(Keys.LEFT_CONTROL);
			for (String value : valueToSelect)
				dropdown.selectByVisibleText(value);
		}

	}

	
	/**
	 * This function will select the option based on custom attribute like label,text etc
	 * @param type
	 * @param locatorValue
	 * @param wait
	 * @param attribute
	 * @param text
	 */
	public void selectDropDownByAttribute(LocatorType type, String locatorValue, int wait, String attribute,
			String text) {
		Utils.wait(1);
		WebElement el = getElement(type, locatorValue, wait);
		if (el != null) {
			Select dropdown = new Select(el);
			String elemValue = dropdown.getOptions().stream()
					.filter(elem -> elem.getAttribute(attribute).equalsIgnoreCase(text)).findFirst().get()
					.getAttribute("value");
			dropdown.selectByValue(elemValue);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// LOG.debug("hello");
		WebElementsUtil webel = new WebElementsUtil(new FirefoxDriver());
	}

}
