package com.tricentis.genericutility;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverUtility {
	public Actions act;
	
	public WebDriverUtility(WebDriver driver) {
		act=new Actions(driver);
	}
	public void dragAndDrop(WebElement src,WebElement target) {
		act.dragAndDrop(src, target).perform();
	}
	
	public void mouseHoverOnElement(WebElement element) {
		act.moveToElement(element).perform();
	}
	
	public void clickAndHold(WebElement element) {
		 act.clickAndHold(element).perform();
	}
	public void switchToWindowByUrl(WebDriver driver,String url) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String id:allWindowIds) {
			String actUrl = driver.switchTo().window(id).getCurrentUrl();
			if (actUrl.contains(url)) {
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver,String title) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String id:allWindowIds) {
			String actTitle = driver.switchTo().window(id).getTitle();
			if (actTitle.contains(title)) {
				break;
			}
		}
		
	}
	
	public WebDriver switchToFrame(WebDriver driver,int index) {
		return driver.switchTo().frame(index);
	}
	
	public WebDriver switchToFrame(WebDriver driver,String nameOrId) {
		return driver.switchTo().frame(nameOrId);
	}
	
	public WebDriver switchToFrame(WebDriver driver,WebElement frameElement) {
		return driver.switchTo().frame(frameElement);
	}
	
	public Alert switchToAlert(WebDriver driver) {
		return driver.switchTo().alert();
	}
}
