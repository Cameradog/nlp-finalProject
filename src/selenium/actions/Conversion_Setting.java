package selenium.actions;

import test.selenium.ExtComponentFinder;

import org.openqa.selenium.WebDriver;

public class Conversion_Setting extends Common
{
	private final int SITE_GROUP;
	private ExtComponentFinder ecf;

	//コンストラクタ
	public Conversion_Setting( WebDriver driver )
	{
		SITE_GROUP = 1;
		ecf = new ExtComponentFinder( driver );
		Set_Drive( driver );
	}

	/**
	 * コンバージョンページ作成ボタン
	 */
	public boolean Conversion_Add()
	{
		return Js_Click( "bp_newButton#new" );
	}

	/**
	 * 設定グループ変更_一覧表示中
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Sitegroup( int index )
	{
		ecf.selectCombobox( "bp_combo#siteGroup", index );

		return;
	}

	/**
	 * 設定グループ変更_編集画面中
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Edit_Sitegroup( int index )
	{
		ecf.selectCombobox( "bp_combo#siteGroup", SITE_GROUP, index );

		return;
	}

	/**
	 * コンバージョンページ編集
	 * @param index　編集したい行数を指定する
	 * @return
	 */
	public boolean Conversion_Edit( int index )
	{
		return Edit_Click( index );
	}

	/**
	 * コンバージョンページ取消
	 * @param index　編集したい行数を指定する
	 * @return
	 */
	public boolean Conversion_Return( int index )
	{
		return Undo_Click( index );
	}

	/**
	 * コンバージョンページ名編集
	 * @param edit
	 * @return
	 */
	public boolean Edit_Name( String edit )
	{
		return Js_Sendkey( "bp_text#name", edit );

	}

	/**
	 * サイトドメイン編集
	 * @param edit
	 * @return
	 */
	public boolean Edit_Domain( String edit )
	{
		return Js_Sendkey( "bp_text#host", edit );
	}

	/**
	 * ページ編集
	 * @param edit
	 * @return
	 */
	public boolean Edit_Page( String edit )
	{
		return Js_Sendkey( "bp_text#page", edit );
	}

	/**
	 * ページの条件選択
	 * @param index
	 */
	public void Select_Page( int index )
	{
		ecf.selectCombobox( "conversionPageOperatorCombo#typeComb", index );

		return;
	}

	/**
	 * オプション選択
	 */
	public boolean Option_Lastclick()
	{
		return Js_Click( "bp_radio#optionLastClick" );
	}

	/**
	 * オプション選択
	 */
	public boolean Option_Allclick()
	{
		return Js_Click( "bp_radio#optionAllClick" );
	}

	/**
	 * OKボタン
	 */
	public boolean OK( )
	{
		return Js_Click( "bp_button#ok" );
	}

	/**
	 * キャンセルボタン
	 */
	public boolean Cancel( )
	{
		return Js_Click( "bp_button#cancel" );
	}

	/**
	 * エラーダイアログのOKボタン
	 */
	public boolean Error_OK( )
	{
		return Js_Click( "button#ok" );
	}


}
