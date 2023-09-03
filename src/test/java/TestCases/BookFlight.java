package TestCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.TestBase;
import FlightBooking.Book_a_flight;


public class BookFlight extends TestBase {
	  
	/* this code is developed for loading browser and ROC Login */
	@BeforeClass
	public void launchBrowser() throws IOException {
		TestBase tbase= new TestBase();
		tbase.Base_url();
		

		
		
		
	}
	/* this code is developed for validating Login to ROC */
	@Test(description="This is login validate")
	public void Validate() throws Exception {
		
		Book_a_flight log= new Book_a_flight();	
		log.searchflight();
		
		
		
		
		//add content, manage tests etc

		
	}

}
