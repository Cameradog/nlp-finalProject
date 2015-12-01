package selenium.actions;

import org.openqa.selenium.WebDriver;

public class Item_Setting extends Common
{

	public Item_Setting( WebDriver driver )
	{
		Set_Drive( driver );
	}

	/**
	 * フィールド名編集ボタン
	 *
	 * @return
	 */
	public boolean Field_Edit()
	{
		return Js_Click( "bp_editButton" );
	}

	/**
	 * レコメンド緊急停止
	 *
	 * @return
	 */
	public boolean Stop_Recommend()
	{
		return Js_Click( "bp_disableButton#itemEmergency" );
	}

	/**
	 * アイテムコード編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Itemcode( String edit )
	{
		return Sendkey( "code", edit ,Element_Type.NAME );
	}

	/**
	 * アイテム名編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Itemname( String edit )
	{
		return Sendkey( "name", edit ,Element_Type.NAME );
	}

	/**
	 * 価値編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Price( String edit )
	{
		return Sendkey( "price", edit ,Element_Type.NAME );
	}

	/**
	 * 価格編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Profit( String edit )
	{
		return Sendkey( "profit", edit ,Element_Type.NAME );
	}

	/**
	 * グループ名編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Group( String edit )
	{
		return Sendkey( "grouping", edit ,Element_Type.NAME );
	}

	/**
	 * カテゴリ1名編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Category1( String edit )
	{
		return Sendkey( "category0", edit ,Element_Type.NAME );
	}

	/**
	 * カテゴリ2名編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Category2( String edit )
	{
		return Sendkey( "category1", edit ,Element_Type.NAME );
	}

	/**
	 * カテゴリ3名編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Category3( String edit )
	{
		return Sendkey( "category2", edit ,Element_Type.NAME );
	}

	/**
	 * カテゴリ4名編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Category4( String edit )
	{
		return Sendkey( "category3", edit ,Element_Type.NAME );
	}

	/**
	 * カテゴリ5名編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Category5( String edit )
	{
		return Sendkey( "category4", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用1編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field1( String edit )
	{
		return Sendkey( "field0", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用2編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field2( String edit )
	{
		return Sendkey( "field1", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用3編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field3( String edit )
	{
		return Sendkey( "field2", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用4編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field4( String edit )
	{
		return Sendkey( "field3", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用5編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field5( String edit )
	{
		return Sendkey( "field4", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用6編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field6( String edit )
	{
		return Sendkey( "field5", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用7編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field7( String edit )
	{
		return Sendkey( "field6", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用8編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field8( String edit )
	{
		return Sendkey( "field7", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用9編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field9( String edit )
	{
		return Sendkey( "field8", edit ,Element_Type.NAME );
	}

	/**
	 * 汎用10編集
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Field10( String edit )
	{
		return Sendkey( "field9", edit ,Element_Type.NAME );
	}

	/**
	 * アイテム検索
	 * @param edit　検索するアイテムコード
	 * @return
	 */
	public boolean Search_Item( String edit )
	{
		return Js_Sendkey( "bp_text#searchForm_itemCode", edit );
	}

	/**
	 * 検索ボタン
	 *
	 * @return
	 */
	public boolean Search_Start( )
	{
		return Js_Click( "bp_button#searchForm_submit", 0 );
	}

	/**
	 * OKボタン
	 * @return
	 */
	public boolean OK( )
	{
		return Js_Click( "bp_button#ok", 0 );
	}

	/**
	 * キャンセルボタン
	 * @return
	 */
	public boolean Cancel( )
	{
		return Js_Click( "bp_button#cancel", 0 );
	}


}
