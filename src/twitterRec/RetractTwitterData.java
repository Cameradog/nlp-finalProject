package twitterRec;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import selenium.actions.Common;

public class RetractTwitterData {
	WebDriver driver;
	String url = "https://twitter.com/search?q=from%3Anytimes%20since%3A2000-12-04%20until%3A2015-12-02&src=typd";
	Common c;

	public static void main(String[] args) {
		RetractTwitterData rtd = new RetractTwitterData();
		rtd.exec();
	}

	public RetractTwitterData() {
		c = new Common();
	}

	public void exec() {
		startBrowser();
		gotoPage();
		getArticle();
	}

	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(200,50));
	}

	public void gotoPage() {
		driver.get(url);
		Force_Waiting();
	}

	public void getArticle() {
		while (true) {
			scrollUp();
			Force_Waiting();
			for (int i = 0; i < 20; i++) {
				try{
					String result = (String)((JavascriptExecutor) driver)
							.executeScript("return document.getElementsByClassName('tweet-text')["
									+ i + "].innerHTML");
					String time = (String)((JavascriptExecutor) driver)
							.executeScript("return $('.js-short-timestamp')[" +i+"].innerHTML");
					System.out.println(result +" " + time);
				} catch(Exception e){
					System.out.println("Not get");
				}
			}
			Force_Waiting();
			emptyArticle();
			scrollDown();
			Force_Waiting_Longer();
			
			Force_Waiting();
		}

	}

	public void emptyArticle() {
		((JavascriptExecutor) driver)
				.executeScript("$('.js-navigable-stream').empty();");
	}

	public void scrollDown() {
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}
	
	public void scrollUp(){
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,0);");
	}

	/**
	 * when page has delay, need to call Thread sleep to force waiting
	 */
	public void Force_Waiting() {
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Force_Waiting_Longer(){
		try {
			Thread.sleep(15000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
