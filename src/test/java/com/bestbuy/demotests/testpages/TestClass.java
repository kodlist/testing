package com.bestbuy.demotests.testpages;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.bestbuy.demotests.testlisteners.TestListener;

import com.bestbuy.demotests.pageobjects.basket.*;
import com.bestbuy.demotests.pageobjects.checkout.*;
import com.bestbuy.demotests.pageobjects.details.*;
import com.bestbuy.demotests.pageobjects.home.*;
import com.bestbuy.demotests.pageobjects.results.*;
import com.bestbuy.demo.utils.TextFile.TextFile;
import com.bestbuy.demo.utils.enumerations.*;

@Listeners(TestListener.class)
public class TestClass extends com.bestbuy.demotests.BaseTest.BaseTestClass{

	private void addToLog(String text) {
		(new TextFile()).add("\n " + text);
	}
	
	@DataProvider(name = "keywordValues")
	public static Object[][] primeNumbers() {
	      return new Object[][] {
	    	  						{"iphone"}, 
//	    	  						{"ipad mini"}, 
//	    	  						{"iphone 6"},
//	    	  						{"screen protector"},
//	    	  						{"screens"},
//	    	  						{"charger samsung"},
//	    	  						{"charger iphone"},
//	    	  						{"watch"},
//	    	  						{"watches for women"},
	    	  					};
	   }

	@Test(dataProvider = "keywordValues")
	public void transactionFailsForIncorrectPaymentInfo(String keyword) { 	
		
		HomePage homePage = new HomePage(driver);					
		
		ResultsPage resultsPage = homePage.open()
				                          .search(keyword);
					
		DetailsPage detailsPage = resultsPage.selectOffer("Online Only")
				                             .selectResult(5);
		
		BasketPage basketPage = detailsPage.addToCart();
		
		SecureCheckout secureCheckout = basketPage.checkOut();
		
		CheckOut checkout = secureCheckout.select(MemberType.GUEST)
										  .continueCheckout();
		
		checkout.shipTo().fillInfo();
		checkout.continueToPayment();
		checkout.paymentMethod().selectCreditCard();
		checkout.paymentDetails().fillInfo();
		checkout.checkSameAsShipping();
		checkout.submitPayment();
		
		assertTrue(checkout.errors().messages().size() > 0);
	
		addToLog(checkout.errors().toString());		
			
	}

}
