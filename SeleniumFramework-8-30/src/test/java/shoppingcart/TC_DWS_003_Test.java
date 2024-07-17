package shoppingcart;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.genericutility.ListenerUtility;

@Listeners(ListenerUtility.class)
public class TC_DWS_003_Test extends BaseClass{
	@Test
	public void addProduct() {
		hp.getAddToCartButtons().get(1).click();
		Assert.assertEquals(hp.getConfirmMessage().isDisplayed(), true,"product failed to add");
		test.log(Status.PASS, "product added successfully");
		eWait.until(ExpectedConditions.invisibilityOf(hp.getConfirmMessage()));
	}
}
