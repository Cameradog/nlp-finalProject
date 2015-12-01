package test.selenium;

import java.util.Random;

import org.openqa.selenium.*;

import selenium.actions.Common;
import selenium.actions.Element_Type;

public class Recommend extends Common
{

	WebDriver driver;
	WebElement elm,elm2;
	String str;
	String conversion = "//input[@value='通常コンバージョン']";
	String current_window_id;

	Recommend( )
	{
		driver = Start_driver();

		driver.get("http://demo.retair.com/");
	}

	Recommend( String user )
	{
		str = user;

		driver = Get_Drive();

		driver.get("http://search.yongpine.com/");
	}

	public void レコメンドアイテム最適化( ) throws InterruptedException
	{
		int loop = 0;
		int item08 = 0;
		int item02 = 0;
		int item10 = 0;
		int item39 = 0;
		int item21 = 0;

		for( loop = 0; loop < 20 ; loop++ )
		{
			elm = driver.findElement( By.linkText("コンテンツ表示確認1") );
			elm.click();

			elm2 = driver.findElement( By.id("content1") );
			str = elm2.getText();

			if( str.endsWith("0008") )
			{
				item08++;
			}
			else if( str.endsWith("0002") )
			{
				item02++;
			}
			else if( str.endsWith("0010") )
			{
				item10++;
			}
			else if( str.endsWith("0021") )
			{
				item21++;
			}
			else if ( str.endsWith("0039") )
			{
				item39++;
			}
			else
			{

			}


			Thread.sleep(500);

			elm = driver.findElement( By.linkText("Top") );
			elm.click();


		}

		System.out.printf(" item02: %d\n item08:%d\n item10:%d\n item21:%d\n item39:%d\n",item02,item08,item10,item21,item39 );

		return;
	}

	public void コンバージョンまでする_自動最適化用(  ) throws InterruptedException
	{
		int loop = 0;
		int item08 = 0;
		int item02 = 0;
		int item10 = 0;
		int item39 = 0;
		int item21 = 0;

		for( loop = 0; loop < 20 ; loop++ )
		{
			elm = driver.findElement( By.linkText("コンテンツ表示確認1") );
			elm.click();

			Thread.sleep(500);

			elm2 = driver.findElement( By.id("content1") );
			str = elm2.getText();

			if( str.endsWith("0008") )
			{
				item08++;
			}
			else if( str.endsWith("0002") )
			{
				item02++;
			}
			else if( str.endsWith("0010") )
			{
				item10++;
			}
			else if( str.endsWith("0021") )
			{
				item21++;
			}
			else if ( str.endsWith("0039") )
			{
				item39++;
			}
			else
			{

			}

			elm2 = driver.findElement( By.cssSelector("a") );
			elm2.click();

			elm2 = driver.findElement( By.xpath( conversion ));
			elm2.click();

			elm = driver.findElement( By.linkText("Top") );
			elm.click();

			Thread.sleep(500);
		}

		System.out.printf(" item02: %d\n item08:%d\n item10:%d\n item21:%d\n item39:%d\n",item02,item08,item10,item21,item39 );

		return;
	}

	public void adjustment( String user, int max ) throws InterruptedException
	{
		int loop = 0;
		Random ran = new Random();
		int flag;

		if( !user.equals("") )
		{
			elm = driver.findElement( By.id("uid") );
			elm.sendKeys( user );
			elm = driver.findElement( By.cssSelector("button") );
			elm.click();
		}

		for( loop = 0; loop < max ; loop++ )
		{

			while( !Click( "コンテンツ表示確認1", Element_Type.LINK ) );

			Thread.sleep(500);

			Click( "a",Element_Type.CSS );

			//Whether conversion to
			flag = ran.nextInt( 4 );

			if( flag  == 0 )
			{
				Click( conversion, Element_Type.XPATH );
			}

			while( !Click( "Top", Element_Type.LINK ) );

			Thread.sleep(800);
		}

		return;
	}


	public void 周期調整( String user, int max ) throws InterruptedException
	{
		int loop = 0;

		if( !user.equals("") )
		{
			elm = driver.findElement( By.id("uid") );
			elm.sendKeys( user );
			elm = driver.findElement( By.cssSelector("button") );
			elm.click();
		}

		for( loop = 0; loop < max ; loop++ )
		{

			while( !Click( "周期情報データ用", Element_Type.LINK ) );

			Thread.sleep(500);

			Click( "a", Element_Type.CSS );

			Click( conversion, Element_Type.XPATH );

			while( !Click( "Top", Element_Type.LINK ) );

			Thread.sleep(800);
		}

		return;
	}

	public void 流入調整( String word, int max ) throws InterruptedException
	{
		int loop = 0;
		Random ran = new Random();
		String edit = null;
		int flag;

		edit = "http://stroustrup.brainpad.co.jp/index.html?user_id=" + str;

		Sendkey( "l", edit, Element_Type.NAME );

		Sendkey( "q", word, Element_Type.NAME );

		Click( "input[type=\"button\"]", Element_Type.CSS );

		Switch_Window();

		for( loop = 0; loop < max ; loop++ )
		{

			while( !Click( "流入キーワードデータ作成用", Element_Type.LINK ) );

			Thread.sleep(500);

			Click( "a", Element_Type.CSS );

			//コンバージョンするか否か
			flag = ran.nextInt( 4 );

			if( flag  == 0 )
			{
				Click( conversion, Element_Type.XPATH );
			}

			while( !Click( "Top", Element_Type.LINK ) );

			Thread.sleep(800);
		}

		driver.switchTo().window( current_window_id );

		return;
	}

	/**
	 * 検索用テストサイトでは別窓で開くため、その対応
	 */
	private void Switch_Window()
	{
		String new_window_id = null;

		//後で比較するために、現在のウインドウIDを取得
		current_window_id = driver.getWindowHandle();

		//別ウインドウで開くリンクをクリック
		Click( "//html/body/div[3]/ul/li/a", Element_Type.XPATH );

		//開いたウインドウも含め全部のウインドウIDを取得する
		java.util.Set<String> window_ids = driver.getWindowHandles();

		for( String id :window_ids )
		{
			if( !id.equals( current_window_id ) )
			{
				//現在のウインドウIDと違っていたら格納
				//最後に格納されたIDが一番新しく開かれたウインドウと判定
				new_window_id = id;
			}
		}

		//最後に格納したウインドウIDにスイッチ
		driver.switchTo().window( new_window_id );

		return;

	}

	public boolean Close()
	{
	    driver.close();

	    return true;
	}
}
