package selenium.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import test.selenium.ExtComponentFinder;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

/**
 * 共通処理用クラス ブラウザ起動、ログイン、画像取得、HTML要素取得などのテストケースに依存しないものはここ
 */
public class Common {
	static byte count = 0; // エラーカウント用
	public static final int WAIT_SHORT = 3; // タイムアウト設定用(3秒)
	public static final int WAIT_LONG = 6; // タイムアウト設定用(6秒)
	public final int RETRY; // リトライ回数
	public final int STEALE, OK, FALSE, INVALID, NOTVISIBUL; // 例外処理判別用
	public static Browser browser; // ブラウザ判別用
	public static WebDriver set_driver; // ドライバ代入用
	protected ExtComponentFinder ecf;
	private Grid_Click gc;

	// コンストラクタ
	public Common() {
		set_driver = null;
		RETRY = 3;
		OK = 0;
		STEALE = 1;
		FALSE = -1;
		INVALID = 2;
		NOTVISIBUL = 3;
	}

	/**
	 * WebDriverをセット
	 * 
	 * @param driver
	 */
	public void Set_Drive(final WebDriver driver) {
		set_driver = driver;

		ecf = new ExtComponentFinder(driver);
		gc = new Grid_Click(driver);

		return;
	}

	/**
	 * WebDriverをゲット
	 * 
	 * @return
	 */
	public WebDriver Get_Drive() {
		return set_driver;
	}

	/**
	 * ブラウザ選択。コンソールから数値入力で起動ブラウザを選択する
	 * 
	 * @return
	 */
	public WebDriver Start_driver() {
		WebDriver driver;
		String str;
		// final Dimension dime = new Dimension(1366 , 768);
		// //Windowサイズ調整用。好みに応じて
		final Dimension dime = new Dimension(1280, 768); // マニュアル用サイズ
		// final Dimension dime = new Dimension(1600 , 1200 );
		// //Windowサイズ調整用。好みに応じて

		while (true) {
			/*
			System.out.println("Browser choice\n1.Firefox 2．IE 3. Chrome\n");
			final BufferedReader input = new BufferedReader(
					new InputStreamReader(System.in));

			try {
				str = input.readLine();
			} catch (final IOException e) {
				// TODO 自動生成された catch ブロック
				str = "";
				e.printStackTrace();
			}
			*/
			
			str = "1"; //Firefox 

			if (str.equals("1")) {
				final FirefoxProfile profile = new FirefoxProfile();
				// ダウンロードするファイルの保存先フォルダを指定　0:デスクトップ 1：ダウンロードフォルダ
				// 2:ダウンロードに指定された最後のフォルダ
				profile.setPreference("browser.download.folderList", 0);

				// ダウンロードするファイルの保存先フォルダが指定してあればそれを使う
				profile.setPreference("browser.download.useDownloadDir", true);
				
				profile.setPreference("intl.accept_languages", Locale.CHINA.toLanguageTag());

				// 指定したmimeタイプは有無を言わさずダウンロードする(ダウンロード確認ダイアログが表示されなくなる)
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/comma-separated-values");

				driver = new FirefoxDriver(profile); // 作成したプロファイルでFirefox(のドライバー)を起動する

				// サイズ調整。なくても問題ない
				driver.manage().window().setSize(dime);

				browser = Browser.FIREFOX;
				break;
			} else if (str.equals("2")) {

				System.setProperty("webdriver.ie.driver",
						"IEDriver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

				// サイズ調整。なくても問題ない
				driver.manage().window().setSize(dime);

				browser = Browser.IE;
				break;
			} else if (str.equals("3")) {
				// Chrome起動時は必須
				System.setProperty("webdriver.chrome.driver",
						"chromedriver\\chromedriver.exe");
				driver = new ChromeDriver();

				// サイズ調整。なくても問題ない
				driver.manage().window().setSize(dime);

				browser = Browser.CHROME;
				break;
			} else {
				System.out.println("Please enter 1 ~ 3 \n");
			}

		}

		// 要素が見つからない場合のタイムアウトは6秒
		driver.manage().timeouts().implicitlyWait(WAIT_LONG, TimeUnit.SECONDS);
		set_driver = driver;

		return driver;
	}

	/**
	 * 画像保存用。ファイルパスは任意に編集すべし！
	 *
	 * @param filepath
	 *            ファイルを保存するパス
	 * @param filename
	 *            ファイル名
	 * @throws InterruptedException
	 */
	public void GetScreenshot(final String filepath, final String filename)
			throws InterruptedException {
		String str;
		String base;

		if (browser == Browser.FIREFOX) {
			str = "Firefox";
		} else if (browser == Browser.IE) {
			str = "IE";
		} else {
			str = "Chrome";
		}

		base = "Image storage" + File.separator + str + File.separator + filepath + File.separator;

		// ないと早すぎる場合がある
		Thread.sleep(1500);

		final File srcFile = ((TakesScreenshot) set_driver)
				.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(base + filename));
		} catch (final IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return;
	}

	/**
	 * ログイン_brainpad
	 * 
	 * @throws InterruptedException
	 */
	public void Login_Asp() throws InterruptedException {
		String site;

		site = "http://192.168.1.19/manager/";

		set_driver.get(site);

		Sendkey("username", "autotest", Element_Type.NAME);

		Sendkey("password", "autotest123", Element_Type.NAME);

		Click("input[type=\"submit\"]", Element_Type.CSS);
		
		Click("rt_siteGridLink", Element_Type.CLASS, 0);

		return;
	}
	public void Login_Asp2() throws InterruptedException {
		String site;

		site = "http://demo.retair.com/manager/";

		set_driver.get(site);

		Sendkey("username", "testben", Element_Type.NAME);

		Sendkey("password", "000000", Element_Type.NAME);

		Click("input[type=\"submit\"]", Element_Type.CSS);
		
		Click("rt_siteGridLink", Element_Type.CLASS, 0);

		return;
	}
	/**
	 * 設置型用
	 * 
	 * @throws InterruptedException
	 */
	public void Login_Rtm() throws InterruptedException {
		String site;

		site = "http://stroustrup.brainpad.co.jp/rtm/";

		set_driver.get(site);

		Sendkey("username", "admin", Element_Type.NAME);

		Sendkey("password", "password", Element_Type.NAME);

		Click("input[type=\"submit\"]", Element_Type.CSS);

		/**
		 * サイト選択は現状、indexで指定するしかない 0：秋田さんサイト 1：谷口さんサイト 2：矢野テスト 3:奈良サイト
		 */
		Click("rt_siteGridLink", Element_Type.CLASS, 2);

		return;
	}

	/**
	 * ログイン_マニュアル用
	 * 
	 * @throws InterruptedException
	 */
	public void Login_Manual() throws InterruptedException {
		String site;

		site = "http://demo.retair.com/manager/";

		set_driver.get(site);

		Sendkey("username", "yongpine", Element_Type.NAME);

		Sendkey("password", "retchat1501", Element_Type.NAME);

		Click("input[type=\"submit\"]", Element_Type.CSS);

		Click("rt_siteGridLink", Element_Type.CLASS, 0);

		return;
	}

	/**
	 * ログアウト
	 */
	public void Logout() {
		Js_Click("bp_button#logout");

		return;
	}

	/**
	 * 管理画面ログイン用
	 */
	public void Login(final int num) throws InterruptedException {
		String site, user, pass;

		site = "http://192.168.1.19/manager/";

		set_driver.get(site);

		switch (num) {
		case 1:
			user = "auto1@retair.com";
			pass = "uCz}HM7'DBO5";

			break;

		case 2:
			user = "auto2@retair.com";
			pass = "z47qDF]i:c0P";

			break;

		case 3:
			user = "auto3@retair.com";
			pass = "Wq6Qn)Mu!Ra9";

			break;

		case 4:
			user = "auto4@retair.com";
			pass = "EdeVW6+k9:QN";

			break;

		case 5:
			user = "auto5@retair.com";
			pass = "RbnPl14d+B=K";

			break;

		case 6:
			user = "auto6@retair.com";
			pass = "TF0+8n|5Mdfw";

			break;

		case 7:
			user = "auto7@retair.com";
			pass = "rs]D1Ko\"yuY6";

			break;

		case 8:
			user = "auto8@retair.com";
			pass = "l_xdo=imZ21C";

			break;

		default:
			user = "auto1@retair.com";
			pass = "uCz}HM7'DBO5";

			break;
		}

		Sendkey("username", user, Element_Type.NAME);

		Sendkey("password", pass, Element_Type.NAME);

		Click("input[type=\"submit\"]", Element_Type.CSS);

		// Click( "//div[@class=\"x-grid-cell-inner\"]" , Element_Type.XPATH );
		Elms_Click("rt_siteGridLink", 0, Element_Type.CLASS);

		return;
	}

	/**
	 * 要素取得。Waitクラスを使用して要素出現まで待つ。
	 *
	 * @param elm_str
	 *            HTML要素の文字列
	 * @param type
	 *            要素の属性。idとかnameとか
	 * @return 要素取得に失敗したらNULLを返す
	 */
	public WebElement Wait_Element(final String elm_str, final Element_Type type) {
		WebElement elm;
		final WebDriverWait wait = new WebDriverWait(set_driver, WAIT_SHORT);

		final ExpectedCondition<WebElement> elm_wait = new ExpectedCondition<WebElement>() {
			WebElement elm;

			public WebElement apply(final WebDriver driver) {
				switch (type) {
				case ID:
					elm = driver.findElement(By.id(elm_str));
					break;

				case CSS:
					elm = driver.findElement(By.cssSelector(elm_str));
					break;

				case XPATH:
					elm = driver.findElement(By.xpath(elm_str));
					break;

				case NAME:
					elm = driver.findElement(By.name(elm_str));
					break;

				case LINK:
					elm = driver.findElement(By.linkText(elm_str));
					break;

				case CLASS:
					elm = driver.findElement(By.className(elm_str));
					break;

				case TAG:
					elm = driver.findElement(By.tagName(elm_str));

				default:
					elm = null;
					break;
				}

				return elm;
			}
		};

		try {
			elm = wait.until(elm_wait);
		} catch (final Exception e) {
			// タイムアウトしたらNULL
			elm = null;

			System.out.println("dom element is NULL. Might be the speed is too fast or the element name is wrong\n\n");
		}

		return elm;
	}

	/**
	 * 要素取得。Waitクラスを使用して要素出現まで待つ。 elm_strの要素名を持つものを全て取ってくる 要素名がユニークではない場合に使用する
	 *
	 * @param elm_str
	 *            HTML要素の文字列
	 * @param type
	 *            要素の属性。idとかnameとか
	 * @return 要素取得に失敗したらNULLを返す
	 */
	public List<WebElement> Wait_Elements(final String elm_str,
			final Element_Type type) {
		List<WebElement> elms;
		final WebDriverWait wait = new WebDriverWait(set_driver, WAIT_SHORT);

		final ExpectedCondition<List<WebElement>> elm_wait = new ExpectedCondition<List<WebElement>>() {
			List<WebElement> Elements;

			public List<WebElement> apply(final WebDriver driver) {
				switch (type) {
				case ID:
					Elements = driver.findElements(By.id(elm_str));
					break;

				case CSS:
					Elements = driver.findElements(By.cssSelector(elm_str));
					break;

				case XPATH:
					Elements = driver.findElements(By.xpath(elm_str));
					break;

				case NAME:
					Elements = driver.findElements(By.name(elm_str));
					break;

				case LINK:
					Elements = driver.findElements(By.linkText(elm_str));
					break;

				case CLASS:
					Elements = driver.findElements(By.className(elm_str));
					break;

				case TAG:
					Elements = driver.findElements(By.tagName(elm_str));
					break;

				default:
					Elements = null;
					break;
				}

				return Elements;
			}
		};

		try {
			elms = wait.until(elm_wait);
		} catch (final Exception e) {
			// タイムアウトしたらNULL
			elms = null;

			System.out.println("NULLが代入されています！！スピードが速すぎるか、要素名が間違ってる！？\n\n");
		}

		return elms;
	}

	/**
	 * フレーム内操作に切り替え。フレーム内の要素を操作する場合に必ず最初にコールする必要あり。 NGではいらないかも？
	 *
	 * @return フレーム切り替えに失敗したらNULLを返す
	 */
	public WebDriver Switch_Frame() {
		Wait<WebDriver> wait;
		wait = new WebDriverWait(set_driver, WAIT_SHORT);

		final ExpectedCondition<WebDriver> elm_wait = new ExpectedCondition<WebDriver>() {
			public WebDriver apply(final WebDriver set_driver) {
				return set_driver.switchTo().frame("modal_window_content");
			}
		};

		try {
			set_driver = wait.until(elm_wait);
		} catch (final Exception e) {
			set_driver = null;

			System.out.println("driverにNULLが代入されています！！スピードが速すぎるか、要素名が間違ってる！？\n"
					+ StackTrace.calledFrom());
		}

		return set_driver;
	}

	// 文字列チェック。渡した文字列が表示されてるページ内になければfalse
	public boolean String_Check(final String str) {
		boolean result = true;
		// WebElementインスタンスだと要素指定が必要になるためこちらのほうが汎用性が高いはず
		final Selenium sel = new WebDriverBackedSelenium(set_driver,
				"http://stroustrup.brainpad.co.jp/v4/");

		// 表示されてるページ内に渡した文字列が存在しなければfalseが返ってくる
		result = sel.isTextPresent(str);

		if (result != true) {
			System.out.println("エラーメッセージが出てない？もしくは文言不正！？"
					+ StackTrace.calledFrom());
		}

		return result;
	}

	/**
	 * リストから指定したindex値を選択する。削除予定のメソッド
	 *
	 * @param elm_str
	 *            HTML要素の文字列
	 * @param elm_type
	 *            要素の属性。idとかnameとか
	 * @param index
	 *            リスト内のindex値
	 * @return リストの選択ができなかったらfalseを返す
	 */
	protected boolean Select(final String elm_str, final Element_Type type,
			final int index) {
		boolean result = false;
		String str = null;
		int max = 0;
		List<WebElement> list = null;
		WebElement elm = null;
		Select select = null;

		do {
			try {
				elm = Wait_Element(elm_str, type);
				select = new Select(elm);

				// リスト全体を取得
				list = select.getOptions();
				max = list.size();

				result = true;
			} catch (final StaleElementReferenceException e) {
				System.out.println("例外対応中...\n");
			}
		} while (result != true);

		// indexがmaxより大きいなら不正
		if (index > max) {
			// 実装ミス以外に来ないはず
			return false;
		}

		// 指定したindexのリスト文字列取得
		elm = list.get(index);
		str = elm.getText();

		select.selectByVisibleText(str);

		return true;
	}

	/**
	 * 文字列入力。要素名を直接指定する場合はこっちを使う
	 *
	 * @param elm_str
	 *            HTML要素の文字列
	 * @param edit
	 *            入力する文字列
	 * @param type
	 *            要素の属性。idとかnameとか
	 * @return 書き込めない＝FALSEではないので注意
	 */
	public boolean Sendkey(final String elm_str, final String edit,
			final Element_Type type) {
		WebElement elm = null;
		elm = Wait_Element(elm_str, type);

		if (elm == null) {
			System.out.println(elm_str + " is NULL...\n" + StackTrace.calledFrom());
			return false;
		}

		for (int count = 0; count < RETRY; count++) {
			try {
				elm.clear();
				elm.sendKeys(edit);
				break;
			} catch (final StaleElementReferenceException e) {
				Retry_Massage();
				System.out.println("StaleElementReferenceException\n\n"
						+ StackTrace.calledFrom());
				elm = Wait_Element(elm_str, type);
			} catch (final InvalidElementStateException e1) {
				Retry_Massage();
				System.out.println("InvalidElementStateException\n\n"
						+ StackTrace.calledFrom());
			}

		}

		return true;
	}

	/**
	 * リトライ用
	 */
	private void Retry_Massage() {
		try {
			System.out.println("trying...\n" + StackTrace.calledFrom());
			Thread.sleep(200);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}

		return;
	}

	/**
	 * Button click. Click here if the element is unique to use here if you want to specify the elements name directly
	 *
	 * @param elm_str
	 *            HTML element string
	 * @param type
	 *            Elements of the attribute
	 * @return Return false if you fail
	 */
	public boolean Click(final String elm_str, final Element_Type type) {
		WebElement elm = null;

		elm = Wait_Element(elm_str, type);

		if (elm == null) {
			System.out.println(elm_str + " is NULL...\n" + StackTrace.calledFrom());
			return false;
		}

		for (int count = 0; count < RETRY; count++) {
			// Exit if button disable or hide
			if (!elm.isEnabled() || !elm.isDisplayed()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ボタンクリック。要素名を直接指定する場合はこっちを使う エレメントが複数ある場合はこっち
	 *
	 * @param elm_str
	 *            HTML要素の文字列
	 * @param type
	 *            要素の属性。idとかnameとか
	 * @param index
	 *            そのエレメントが何番目にあるか指定
	 * @return
	 */
	public boolean Click(final String elm_str, final Element_Type type,
			final int index) {
		return Elms_Click(elm_str, index, type);
	}

	/**
	 * JavaScriptで要素を指定する場合はこっちを呼ぶ(引数なし) 通常はこちらを使う
	 */
	public boolean Js_Click(final String query) {
		return Js_Click(query, 0);
	}

	/**
	 * <p>
	 * JavaScriptで要素を指定する場合はこっちを呼ぶ(引数あり)
	 * </p>
	 * <p>
	 * 1つの画面内にitemidが同一のもがある場合に使用する上の要素からindexを指定する。
	 * </p>
	 * indexは0から始まる。例：スコアグループ編集ダイアログのスコア保持期間はindex=1
	 * 
	 * @param query
	 * @param index
	 * @return
	 */
	public boolean Js_Click(final String query, final int index) {
		WebElement elm = null;

		elm = ecf.find(query, index);

		if (elm == null) {
			System.out.println(query + " is NULL...\n" + StackTrace.calledFrom());
			return false;
		}

		for (int count = 0; count < RETRY; count++) {
			// ボタンがDisableか非表示なら抜ける
			if (!elm.isEnabled() || !elm.isDisplayed()) {
				return false;
			}

			try {
				elm.click();
				break;
			} catch (final StaleElementReferenceException e) {
				Retry_Massage();

				System.out.println("StaleElementReferenceException\n\n"
						+ StackTrace.calledFrom());
				elm = ecf.find(query, index);
			} catch (final InvalidElementStateException e1) {
				Retry_Massage();
				System.out.println("InvalidElementStateException\n\n"
						+ StackTrace.calledFrom());
			}
		}

		return true;
	}

	/**
	 * JavaScriptで要素を指定する場合はこっちを呼ぶ。NGはこちらで呼ぶのがメイン
	 * 
	 * @param query
	 *            ComponentQueryの検索文
	 * @param edit
	 *            入力したい文字列
	 * @return
	 */
	public boolean Js_Sendkey(final String query, final String edit) {
		WebElement elm = null;

		elm = ecf.find(query, 0);

		if (elm == null) {
			System.out.println(query + " is NULL...\n" + StackTrace.calledFrom());
			return false;
		}

		for (int count = 0; count < RETRY; count++) {
			try {
				elm.clear();
				elm.sendKeys(edit);
				break;
			} catch (final StaleElementReferenceException e) {
				Retry_Massage();
				System.out.println("StaleElementReferenceException\n\n"
						+ StackTrace.calledFrom());
				elm = ecf.find(query, 0);
			}
		}

		return true;
	}

	/**
	 * JavaScriptで要素を指定する場合はこっちを呼ぶ。NGはこちらで呼ぶのがメイン
	 * 
	 * @param query
	 *            ComponentQueryの検索文
	 * @param edit
	 *            入力したい文字列
	 * @param index
	 *            複数検索にヒットした場合のindex値
	 * @return
	 */
	public boolean Js_Sendkey(final String query, final String edit,
			final int index) {
		WebElement elm = null;

		elm = ecf.find(query, index);

		if (elm == null) {
			System.out.println(query + " is NULL...\n" + StackTrace.calledFrom());
			return false;
		}

		for (int count = 0; count < RETRY; count++) {
			try {
				elm.clear();
				elm.sendKeys(edit);
				break;
			} catch (final StaleElementReferenceException e) {
				Retry_Massage();
				System.out.println("StaleElementReferenceException\n\n"
						+ StackTrace.calledFrom());
				elm = ecf.find(query, index);
			} catch (final InvalidElementStateException e1) {
				Retry_Massage();
				System.out.println("InvalidElementStateException\n\n"
						+ StackTrace.calledFrom());
			}
		}

		return true;
	}

	/**
	 * 登録件数取得。見える件数(最大20件)の取得
	 * 
	 * @return
	 */
	public int Get_Number_View() {
		String str = null;
		int index = 0;
		WebElement elm = null;
		int number = 0;

		elm = ecf.find("tbtext#displayItem");

		str = elm.getText();

		//To be converted to a number by taking a count string
		//@TODO i18n
		//显示 1 - 1条，共 1 条
		index = str.lastIndexOf("条");
		int index2 = str.lastIndexOf("共");
		str = str.substring(index2 + 1, index);

		number = Integer.parseInt(str.trim());

		return number;
	}

	/**
	 * 登録件数取得。サイト全体の登録件数の取得
	 * 
	 * @return
	 */
	public int Get_Number_Mumertor() {
		int index = 0;
		WebElement elm = null;
		String str, str2;
		int strlen;
		int number = 0;

		elm = ecf.find("label#recordLimit");

		str = elm.getText();

		strlen = str.length();

		// 「:」まで文字列を取って数値に変換する
		index = str.indexOf(":");
		str2 = str.substring(index, strlen);

		// 分子だけ切り取ってint型に変換
		index = str2.indexOf("/");
		strlen = str2.length();

		str2 = str2.substring(1, index);

		number = Integer.parseInt(str2);

		return number;
	}

	/**
	 * サイト全体の登録可能最大件数の取得
	 * 
	 * @return
	 */
	public int Get_Number_Denominator() {
		int index = 0;
		WebElement elm = null;
		String str, str2;
		int strlen;
		int number = 0;

		elm = ecf.find("label#recordLimit");

		str = elm.getText();

		strlen = str.length();

		// 「:」まで文字列を取って数値に変換する
		index = str.indexOf(":");
		str2 = str.substring(index, strlen);

		// 分母だけ切り取ってint型に変換
		index = str2.indexOf("/");
		strlen = str2.length();

		str2 = str2.substring(index + 1, strlen);

		number = Integer.parseInt(str2);

		return number;
	}

	/**
	 * 最大登録数チェック
	 * 
	 * @return
	 */
	public boolean Get_MaxNumber() {
		int index = 0;
		WebElement elm = null;
		String str, str2, str3;
		int strlen;

		elm = ecf.find("label#recordLimit");

		str = elm.getText();

		strlen = str.length();

		// 「:」まで文字列を取って数値に変換する
		index = str.indexOf(":");
		str2 = str.substring(index, strlen);

		// 分子だけ切り取ってint型に変換
		index = str2.indexOf("/");
		strlen = str2.length();

		str3 = str2.substring(1, index);
		str = str2.substring(index + 1, strlen);

		if (!str.equals(str3)) {
			return false;
		}

		return true;
	}

	/**
	 * 削除ダイアログのOKボタン
	 *
	 * @return
	 */
	public boolean Delete_OK() {
		return Js_Click("button#yes");
	}

	/**
	 * 削除ダイアログのキャンセルボタン
	 *
	 * @return
	 */
	public boolean Delete_Cancel() {
		return Js_Click("button#no");
	}

	/**
	 * インポートボタン
	 * 
	 * @return
	 */
	public boolean Import() {
		return Js_Click("bp_importButton");
	}

	/**
	 * インポー時のファイル選択
	 * 
	 * @return
	 */
	public boolean file_Select() {
		String str;
		str = gc.Get_Id("rt_filefield") + "-browseButtonWrap";

		return Click(str, Element_Type.ID);
	}
	
	public boolean file_Select_sendkey(String path) {
		String str;
		str = gc.Get_Id("rt_filefield") + "-button-fileInputEl";
		System.out.println(str);
		return Sendkey(str,path, Element_Type.ID);
		//return Click(str, Element_Type.ID);
	}
	/**get input id & sendkey (after file path)
	 * 
	 * @param filepath
	 */
	public void file_upload_sendkey(String path) {

		String str;
		str = gc.Get_Id("rt_filefield") + "-button-fileInputEl";
		System.out.println(str);
		WebElement uploadcsv = set_driver.findElement(By.id(str));
		uploadcsv.sendKeys(path);
	}
	public void file_uploadpic_sendkey(String path) {

		String str;
		str = gc.Get_Id("rt_filefield") + "-inputEl";
		System.out.println(str);
		WebElement uploadcsv = set_driver.findElement(By.id(str));
		JavascriptExecutor j = (JavascriptExecutor) set_driver;
		j.executeScript("document.getElementById('" + str
				+ "').removeAttribute('readonly');");// 清空屬性
		uploadcsv.sendKeys(path);
	}
	/**
	 * ページングの直指定
	 * 
	 * @param page
	 *            指定するページ数
	 * @return
	 */
	public boolean Set_Page(final String page) {
		WebElement elm = null;

		Sendkey("inputItem", page, Element_Type.NAME);

		// Find() element obtained by the acquired it's was the element with key operation straight specified unstable in
		elm = Wait_Element("inputItem", Element_Type.NAME);

		elm.sendKeys(Keys.ENTER);

		return true;
	}

	/**
	 * リスト選択時に使用
	 *
	 * @param index
	 * @return
	 */
	public String List_Select(final int index) {
		String number = null;
		String str = null;

		number = Integer.toString(index);

		number = "[" + number + "]";

		str = "//div[@class=\"x-boundlist-list-ct x-unselectable\"]/div"
				+ number;

		return str;
	}

	/**
	 * 前ページへ
	 */
	public boolean Preview() {
		return Js_Click("button#prev");
	}

	/**
	 * 最初のページへ
	 */
	public boolean Frist() {
		return Js_Click("button#frist");
	}

	/**
	 * 次ページへ
	 */
	public boolean Next() {
		return Js_Click("button#next");
	}

	/**
	 * 最後のページへ
	 */
	public boolean Last() {
		return Js_Click("button#last");
	}

	public boolean Close() {
		return Click("x-tool-close", Element_Type.CLASS);
	}

	/**
	 * ダイアログのOKボタン
	 * 
	 * @return
	 */
	public boolean OK() {
		return Js_Click("button#ok");
	}

	/**
	 * ダイアログのキャンセルボタン
	 * 
	 * @return
	 */
	public boolean Cancel() {
		return Js_Click("bp_button#cancel");
	}

	/**
	 * ウィンドウ内の削除ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Ok_Window_Click() {
		return Elms_Window_Click("rt_btnOk", 0, Element_Type.CLASS);
	}

	/**
	 * Check the check box
	 *
	 * @param index
	 *            The number of rows you want to put check begins
	 * @return
	 */
	public boolean Check_Box(int index) {
		List<WebElement> elms = null;

		// Array adjustment because from 0
		index = index - 1;

		elms = Wait_Elements("x-grid-row-checker", Element_Type.CLASS);

		if (elms == null) {
			return false;
		}

		while (true) {
			try {
				elms.get(index).click();
				break;
			} catch (final StaleElementReferenceException e) {
				Retry_Massage();

				System.out.println("StaleElementReferenceException\n\n"
						+ StackTrace.calledFrom());
				elms = Wait_Elements("x-grid-row-checker", Element_Type.CLASS);
			}
		}

		return true;
	}

	/**
	 * ファイルから文字列を取得する（文字数が多いもの用） ファイルはあらかじめ用意しておく
	 * 
	 * @return
	 */
	public String Load_File(final String path) {
		String str = "";
		final File file = new File(path);

		// 直接文字列入力はイケてないのでファイルから入力する
		try {
			final FileReader red = new FileReader(file);
			final BufferedReader br = new BufferedReader(red);

			try {
				while (true) {
					str = br.readLine();

					if (str == null) {
						break;
					}
				}

				red.close();
			} catch (final IOException e) {
				System.out.println(e);
			}

		} catch (final FileNotFoundException e) {
			return null;
		}

		return str;
	}

	/**
	 * 入力中の文字列を取ってくる。
	 *
	 * @param query
	 * @param index
	 * @return
	 */
	static public String Element_Value(final String query, final int index) {
		JavascriptExecutor jsdr = null;
		String value = null;

		jsdr = (JavascriptExecutor) set_driver;

		value = (String) jsdr.executeScript("return Ext.ComponentQuery.query('"
				+ query + "')" + "[" + index + "].getValue();");

		return value;
	}

	/**
	 * ローディングアイコンが表示中はループ
	 *
	 * @return
	 */
	public void Loading() {
		WebElement elm = null;

		elm = Wait_Element("x-mask-msg-text", Element_Type.CLASS);

		while (elm.isDisplayed()) {
			elm = Wait_Element("x-mask-msg-text", Element_Type.CLASS);
		}

		return;
	}

	/**
	 * グリッドのボタンクリック用 最上段の「編集」のindexは0、「取消」は1...・・ 2段目以降はそのボタン数分ずらしたもの（削除予定のメソッド）
	 *
	 * @param index
	 *            　　行数指定
	 * @return 指定したXPATHの文字列
	 */
	protected boolean Xpath_Click(final int index) {
		// XPATH指定調整用
		List<WebElement> elms = null;

		// XPATH指定以外の方法はない？
		final String xpath = "//div[@class=\"x-grid-cell-inner\"]/img";

		elms = Wait_Elements(xpath, Element_Type.XPATH);

		if (elms == null) {
			return false;
		}

		elms.get(index).click();

		return true;
	}

	/**
	 * 削除ボタン
	 * 
	 * @return
	 */
	public boolean Delete() {
		return Js_Click("bp_deleteButton");
	}

	/**
	 * 一覧の編集ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Edit_Click(final int index) {
		return Elms_Click("bp_editGridButtonIcon", index, Element_Type.CLASS);
	}

	/**
	 * 一覧の取消ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Undo_Click(final int index) {
		return Elms_Click("bp_undoGridButtonIcon", index, Element_Type.CLASS);
	}

	/**
	 * 一覧の削除ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Delete_Click(final int index) {
		return Elms_Click("bp_deleteGridButtonIcon", index, Element_Type.CLASS);
	}

	/**
	 * ウィンドウ内の編集ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Edit_Window_Click(final int index) {
		return Elms_Window_Click("bp_editGridButtonIcon", index,
				Element_Type.CLASS);
	}

	/**
	 * ウィンドウ内の取消ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Undo_Window_Click(final int index) {
		return Elms_Window_Click("bp_undoGridButtonIcon", index,
				Element_Type.CLASS);
	}

	/**
	 * ウィンドウ内の削除ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Delete_Window_Click(final int index) {
		return Elms_Window_Click("bp_deleteGridButtonIcon", index,
				Element_Type.CLASS);
	}

	/**
	 * 一覧のルールボタン
	 * 
	 * @param indexfCheck_Box_
	 *            　行数指定
	 * @return
	 */
	public boolean Rule_Click(final int index) {
		return Elms_Click("rt_ruleIcon", index, Element_Type.CLASS);
	}

	/**
	 * コピーボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Copy_Click(final int index) {
		return Elms_Click("bp_copyGridButtonIcon", index, Element_Type.CLASS);
	}

	/**
	 * スケジュールの日付ボタン
	 * 
	 * @param index
	 *            　行数指定
	 * @return
	 */
	public boolean Toggle_Click(final int index) {
		return Elms_Click("rt_multiToggleButton", index, Element_Type.CLASS);
	}

	/**
	 * アイコン数を取得する。一覧から編集ダイアログ内にあるボタンをクリックする際に使用
	 * 
	 * @return
	 */
	public int Get_Icon() {
		List<WebElement> elms = null;

		elms = Wait_Elements("bp_editGridButtonIcon", Element_Type.CLASS);

		// 配列のインデックスなので[-1]する
		return elms.size() - 1;

	}

	public boolean Import_File(final String filename)
			throws InterruptedException {
		try {
			final Runtime rt = Runtime.getRuntime();
			rt.exec("import\\" + filename);
		} catch (final IOException ex) {
			ex.printStackTrace();
			return false;
		}

		Thread.sleep(1500);

		Ok_Window_Click();

		return true;
	}

	/**
	 * 一覧にある編集ボタンクリック用
	 * 
	 * @param elm_str
	 *            HTML要素の文字列
	 * @param index
	 *            要素数のインデックス値
	 * @param type
	 *            要素のタイプ
	 * @return
	 */
	protected boolean Elms_Click(final String elm_str, final int index,
			final Element_Type type) {
		List<WebElement> elms;

		elms = Wait_Elements(elm_str, type);

		if (elms == null) {
			return false;
		}

		for (int count = 0; count < RETRY; count++) {
			try {
				elms.get(index).click();
				break;
			} catch (final StaleElementReferenceException e) {
				Retry_Massage();

				// この例外の場合は要素の再取得が必要
				elms = Wait_Elements(elm_str, type);
			}
		}

		return true;
	}

	/**
	 * 一覧にある編集ボタンクリック用
	 * 
	 * @param elm_str
	 *            HTML要素の文字列
	 * @param index
	 *            要素数のインデックス値
	 * @param type
	 *            要素のタイプ
	 * @return
	 */
	protected boolean Elms_Window_Click(final String elm_str, final int index,
			final Element_Type type) {
		List<WebElement> elms;

		elms = gc.Elements_Indialog(elm_str, type);

		if (elms == null) {
			return false;
		}

		// ないとエラーにもならずクリックも出来てない場合がある...
		try {
			Thread.sleep(500);
		} catch (final InterruptedException e4) {
			// TODO 自動生成された catch ブロック
			e4.printStackTrace();
		}

		for (int count = 0; count < RETRY; count++) {
			try {
				elms.get(index).click();
				break;
			} catch (final StaleElementReferenceException e) {
				Retry_Massage();

				// この例外の場合は要素の再取得が必要
				elms = Wait_Elements(elm_str, type);
			}

		}

		return true;
	}
}
