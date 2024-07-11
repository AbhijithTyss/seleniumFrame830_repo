package extentreports;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricentis.genericutility.JavaUtility;

public class ToLearnExtentReports {
	@Test
	public void report() {
		JavaUtility jLib=new JavaUtility();
		String timestamp = jLib.getSystemTime();
		//step1: create ExtentSparkReporter object
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_report/ExtentReport"+timestamp+".html");
		
		//step2: create instance of ExtentReports
		ExtentReports extReport=new ExtentReports();
		
		// step3: attach ExtentSparkReporter to ExtentReports
		extReport.attachReporter(spark);
		
		//step4: create instance of ExtentTest
		ExtentTest test = extReport.createTest("report");
		
		// step5: call log() and provide status
		test.log(Status.PASS, "added log message into the report");
		
		// step6: call flush()
		extReport.flush();
	}
}
