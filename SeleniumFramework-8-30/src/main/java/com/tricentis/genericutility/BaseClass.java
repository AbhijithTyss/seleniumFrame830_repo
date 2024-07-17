package com.tricentis.genericutility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricentis.objectrepository.HomePage;
import com.tricentis.objectrepository.LoginPage;
import com.tricentis.objectrepository.WelcomePage;

public class BaseClass {
	public static WebDriver driver;
	public static ExtentReports extReport;
	public ExtentTest test;
	public static ExtentTest listTest;
	
	public WebDriverWait eWait;
	
	public WelcomePage wp;
	public LoginPage lp;
	public HomePage hp;
	public static String time;
	public JavaUtility javaLib;
	public FileUtility fileLib;
	public ExcelUtility excelLib;
	@BeforeSuite(alwaysRun = true)
	public void configReport() {
		javaLib=new JavaUtility();
		time = javaLib.getSystemTime();
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_report/EcommerceReport_"+time+".html");
		extReport=new ExtentReports();
		extReport.attachReporter(spark);
	}
	@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser(@Optional("chrome") String browserName) throws IOException {
		
		if (browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		eWait=new WebDriverWait(driver, Duration.ofSeconds(20));
		fileLib=new FileUtility();
		driver.get(fileLib.getDataFromProperty("url"));
	}
	@BeforeMethod(alwaysRun = true)
	public void login(Method method) throws IOException {
		test = extReport.createTest(method.getName());
		listTest=test;
		wp=new WelcomePage(driver);
		wp.getLoginLink().click();
		lp=new LoginPage(driver);
		lp.getEmailTextField().sendKeys(fileLib.getDataFromProperty("email"));
		lp.getPasswordTextField().sendKeys(fileLib.getDataFromProperty("password"));
		lp.getLoginButton().click();
		excelLib=new ExcelUtility();
		String expectedTitle = excelLib.getStringDataFromExcel("login", 1, 2);
		Assert.assertEquals(driver.getTitle(),expectedTitle,"User failed to login" );
		test.log(Status.PASS, "Home page is displayed");
		hp=new HomePage(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void logout() {
		hp.getLogoutLink().click();
		test.log(Status.INFO, "user logged out");
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
	@AfterSuite(alwaysRun = true)
	public void reportBackup() {
		extReport.flush();
	}
}
