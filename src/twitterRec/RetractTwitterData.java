package twitterRec;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Templates;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import selenium.actions.Common;

public class RetractTwitterData {
	static PrintWriter output;
	String filename = "news_test";
	String tweets;
	int tweetCount = 0;
	Pattern pattern;
	Matcher matcher;
	WebDriver driver;
	String url = "https://twitter.com/search?f=tweets&vertical=default&q=from%3AIndependent%20since%3A2015-01-01%20until%3A2015-12-22&src=typd";
	Common c;

	public static void main(String[] args) {
		RetractTwitterData rtd = new RetractTwitterData();
		rtd.output();
		rtd.exec();
		output.close();
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
//					System.out.println(result +" " + time);
					disassemble(result,time);
					System.out.println("Time: " +time +"\n" + "Content: "+ result +"\n\n");
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
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Force_Waiting_Longer(){
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disassemble(String content,String time){
		String filterStr = "(href=)|(class=)|(src=)";
		String splitStr = "<a|</a>|<b>|</b>|<img|<span|</span>";
		String splitStr2 = " |\r|\n|\r\n";
		String[] disassStr;
		String[] disassStr2;
		pattern = Pattern.compile(filterStr);
		disassStr = content.split(splitStr);
		tweets = "";
		tweetCount++;

		for(int i=0; i<disassStr.length; i++){
			matcher = pattern.matcher(disassStr[i]);
			if(!matcher.find() && !disassStr[i].equals(" ") && !disassStr[i].equals("")){
				disassStr2 = disassStr[i].split(splitStr2);
//				System.out.println("i: "+i+" "+disassStr[i]);
				for(int j=0; j<disassStr2.length; j++){
					if(!disassStr2[j].equals("")){
						tweets = tweets + disassStr2[j] + " ";
//						System.out.println("j: "+j+" "+disassStr2[j]);
					}
				}
			}
		}
		if((tweets.substring(tweets.length()-1)).equals(" ")){
			tweets = tweets.substring(0,tweets.length()-1);
		}

		System.out.println("tweet count: "+tweetCount);
		System.out.println(tweets);
		System.out.println(time);
		System.out.println("***");

		output.println(tweets);
		output.println("***");
//		output.println();
	}

	//output file
	public void output(){
		try {
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".txt",false)));
			output.print("");
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".txt",true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
