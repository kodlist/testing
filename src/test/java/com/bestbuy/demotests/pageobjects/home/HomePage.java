package com.bestbuy.demotests.pageobjects.home;

import org.openqa.selenium.WebDriver;

import com.bestbuy.demo.exceptions.HtmlElementsException;
import com.bestbuy.demo.loader.HtmlElementLoader;
import com.bestbuy.demotests.page.*;
import com.bestbuy.demotests.pageobjects.results.ResultsPage;

public class HomePage extends Page {
	
	private final String siteUrl = "http://www.bestbuy.ca";
	private final String expectedUrl = "www.bestbuy.ca";
	private final String expectedTitle = "Best Buy Canada";
	
	private final String homePageError = "home page is not displayed";
	
	private SearchHeader searchHeader;

	public HomePage(WebDriver driver) {
		super(driver);
		HtmlElementLoader.populatePageObject(this, driver);
	}

	public HomePage open() {		
		getDriver().get(siteUrl);		 
		
		if (!urlContains(expectedUrl) || !titleContains(expectedTitle)) 
			throw new HtmlElementsException(homePageError);
				
		return this;
	}		
	
	public ResultsPage search(String keyword) {				
		searchHeader.search(keyword);
	
		ResultsPage resultsPage = new ResultsPage(getDriver());
		if (resultsPage.totalCount() == 0)
			throw new RuntimeException("there are no results for the search");
		
		return resultsPage;
	}
		
}
