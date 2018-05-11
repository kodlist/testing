package com.bestbuy.demotests.pageobjects.home;

import org.openqa.selenium.support.FindBy;

import com.bestbuy.demo.annotations.Name;
import com.bestbuy.demo.element.*;

@Name("SEARCH_HEADER")
@FindBy(className = "main-navigation-container") 
public class SearchHeader extends HtmlElement{
		
	@Name("SEARCH_FIELD")
	@FindBy(id = "ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_TxtSearchKeyword")	
	private TextInput searchKeywordTxt;
	
	@Name("SEARCH_BUTTON")
	@FindBy(id = "ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_BtnSubmitSearch")
	private Button searchBtn;	
	  		
	public void search(String keyword) {
		searchKeywordTxt.click();  
		searchKeywordTxt.clear();
		searchKeywordTxt.sendKeys(keyword);
		searchBtn.click();		
	}
	
}
