package books;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.genericutility.ListenerUtility;
@Listeners(ListenerUtility.class)
public class TC_DWS_005_Test extends BaseClass{
	@Test(groups = "smoke")
	public void clickOnBooks() throws EncryptedDocumentException, IOException {
		hp.getBooksLink().click();
		String expectedTitle = excelLib.getStringDataFromExcel("Books", 1, 0);
		Assert.assertEquals(driver.getTitle(), expectedTitle,"Books page is not  displayed");
		test.log(Status.PASS, "Books page is displayed");
	}
}
