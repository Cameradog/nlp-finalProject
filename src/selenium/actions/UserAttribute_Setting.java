package selenium.actions;

import org.openqa.selenium.WebDriver;

public class UserAttribute_Setting extends Common
{
	/*
	 * queryのindex指定用。基本固定値。画面に項目が増えれば、変動する可能性あり
	 */
	private final int ATTRIBUTE_NAME;
	private final int ATTRIBUTE_CANCEL;
	private final int ATTRIBUTE_VALUE;
	private final int VALUE_ADD;
	private int icon_count;

	//コンストラクタ
	public UserAttribute_Setting( WebDriver driver )
	{
		ATTRIBUTE_NAME = 1;
		ATTRIBUTE_VALUE = 0;
		VALUE_ADD = 1;
		ATTRIBUTE_CANCEL = 1;
		icon_count = 0;
		Set_Drive( driver );
	}

	/**
	 * ユーザー属性新規作成ボタン
	 */
	public boolean Attribute_Add()
	{
		return Js_Click( "bp_newButton#new" );
	}

	/**
	 * ユーザー属性削除ボタン
	 * @return
	 */
	public boolean Attribute_Delete()
	{
		return Js_Click( "bp_deleteButton#delete" );
	}

	/**
	 * 属性値新規作成ボタン
	 */
	public boolean Value_Add()
	{
		icon_count = Get_Icon();

		return Js_Click( "bp_newButton#new", VALUE_ADD );
	}

	/**
	 * 属性値の編集ボタン
	 *
	 * @param index	編集したい列
	 * @return
	 */
	public boolean Edit_Item( int index )
	{
		index = index + icon_count;

		return Edit_Click( index );
	}

	/**
	 * 属性値の削除ボタン
	 *
	 * @param index	編集したい列
	 * @return
	 */
	public boolean Delete_Item( int index )
	{
		index = index + icon_count;

		return Delete_Click( index );
	}

	/**
	 * 属性値の取消ボタン
	 *
	 * @param index	行数指定
	 * @return
	 */
	public boolean Return_Item( int index )
	{
		index = index + icon_count;

		return Undo_Click( index );
	}

	/**
	 * ユーザー属性の編集ボタン
	 *
	 * @param index		行数指定
	 * @return
	 */
	public boolean Attribute_Edit( int index )
	{
		icon_count = Get_Icon();

		return Edit_Click( index );
	}

	/**
	 * ユーザー属性の取消ボタン
	 *
	 * @param index	行数指定
	 * @return
	 */
	public boolean Attribute_Return( int index )
	{
		return Undo_Click( index );
	}

	/**
	 * 属性名編集
	 *
	 * @param edit	入力する属性名
	 * @return
	 */
	public boolean Edit_Attributename( String edit )
	{
		return Js_Sendkey( "bp_text#name", edit );
	}

	/**
	 * 属性値編集
	 *
	 * @param edit	入力する属性名
	 * @return
	 */
	public boolean Edit_Value( String edit )
	{
		return Js_Sendkey( "bp_text#valueName", edit );
	}

	/**
	 * 属性値のOKボタン
	 */
	public boolean Value_OK( )
	{
		return Js_Click( "bp_button#ok", ATTRIBUTE_VALUE );
	}

	/**
	 * 属性値のキャンセルボタン
	 */
	public boolean Value_Cancel( )
	{
		return Js_Click( "bp_button#cancel" );
	}

	/**
	 * 属性名のOKボタン
	 */
	public boolean Attribute_OK( )
	{
		icon_count = Get_Icon();

		return Js_Click( "bp_button#ok", ATTRIBUTE_NAME );
	}

	/**
	 * 属性名のキャンセルボタン
	 */
	public boolean Attribute_Cancel( )
	{
		return Js_Click( "bp_button#cancel", ATTRIBUTE_CANCEL );
	}

	/**
	 * エラーダイアログのOKボタン
	 */
	public boolean Error_OK( )
	{
		return Js_Click( "button#ok" );
	}
}
