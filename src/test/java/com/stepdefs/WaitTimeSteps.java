package com.stepdefs;

import com.pages.Homepage;
import com.pages.WaitTimePage;
import com.settings.RunFeatures;
import com.utility.Common;
import com.utility.WaitTimeJson;

import cucumber.api.java.en.*;
import junit.framework.Assert;

public class WaitTimeSteps extends RunFeatures {

	Homepage homepage = new Homepage(driver);
	WaitTimeJson waittimejson = new WaitTimeJson();
	WaitTimePage waittime = new WaitTimePage(driver);
	Common common = new Common();

@SuppressWarnings("deprecation")
@Given("^The user is already on the Homepage$")
public void the_user_is_already_on_the_Homepage() {
	Assert.assertTrue("user is not on the Homepage", homepage.homePageisDisplayed());
}

@And("^The user clicks on Waittime button$")
public void the_user_clicks_on_Waittime_button() {
	homepage.clickWaitTime();
    
}

@Then("^The user must be able to see the waittime which matches the waittime API$")
public void the_user_must_be_able_to_see_the_waittime_which_matches_the_waittime_API() throws Exception {
	String waititmeval = waittimejson.readjsonfromurl()+" minutes";
	String waitimeonapp = waittime.displayedWaitTime();
	System.out.println("api"+ waititmeval);
	System.out.println("app" + waitimeonapp);
	String result = null;
	if (waititmeval.equals(waitimeonapp)) {
		Assert.assertEquals(waititmeval, waitimeonapp);
	}
	else {
		common.simpleWait(20000);
		String waititmeval2 = waittimejson.readjsonfromurl() + " minutes";
		String waitimeonapp2 = waittime.displayedWaitTime();
		System.out.println("api2" + waititmeval2);
		System.out.println("app2" + waitimeonapp2);
		Assert.assertEquals(waititmeval2, waitimeonapp2);
	}
    homepage.clickHomeButton();
}

}
