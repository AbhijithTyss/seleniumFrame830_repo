package com.tricentis.genericutility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerUtility extends BaseClass implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getName();
		TakesScreenshot ts=(TakesScreenshot) driver;
		String photo = ts.getScreenshotAs(OutputType.BASE64);
		screenTest.addScreenCaptureFromBase64String(photo);
		System.out.println(screenTest);
		System.out.println(test);
		File temp = ts.getScreenshotAs(OutputType.FILE);
		
		File dest=new File("./screenshots/"+methodName+".png");
		try {
			FileHandler.copy(temp, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
