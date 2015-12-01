package selenium.actions;

import org.openqa.selenium.*;

/**
 * スコアグループ/スコア項目設定の各種操作
 */

public class ScoreGroup_Setting extends Common
{
    //XPATH調整用
	private final int GROUP_OK;
	private final int GROUP_CACEL;
	private final int EDIT_OK;
	private final int ITEM_NEW;

	//コンストラクタ
	public ScoreGroup_Setting( WebDriver driver )
	{
		GROUP_OK = 1;
		EDIT_OK = 0;
		GROUP_CACEL = 1;
		ITEM_NEW = 1;
		Set_Drive( driver );
	}

	/**
	 * スコアグループ新規作成ボタン
	 */
	public boolean Group_Add()
	{
		return Js_Click( "bp_newButton#new" );
	}

	/**
	 * スコア項目新規作成ボタン
	 */
	public boolean Item_Add()
	{
		return Js_Click( "bp_newButton#new", ITEM_NEW );
	}

	/**
	 * グループの編集ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean Group_Edit( int index )
	{
		//XPATH指定用の調整
		index--;

		return Edit_Click( index );
	}

	public boolean Group_Delete()
	{
		return Js_Click( "bp_deleteButton#delete" );
	}

	/**
	 * グループの取消ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean Group_Return( int index )
	{
		index--;

		return Delete_Click( index );
	}

	/**
	 * スコアのクリアボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean Score_Clear( int index )
	{
		index--;

		Elms_Click( "rt_clearGridButtonIcon", index,  Element_Type.CSS );

		return true;
	}

	/**
	 * スコアグループ名編集
	 *
	 * @param edit	入力するスコアグループ名
	 * @return
	 */
	public boolean Edit_Groupname( String edit )
	{
		return Js_Sendkey( "bp_text#name", edit );
	}

	/**
	 * 保持期間編集
	 *
	 * @param edit	入力する保持期間
	 * @return
	 */
	public boolean Edit_Expires( String edit )
	{
		return Js_Sendkey( "bp_text#expire", edit );
	}

	/**
	 * セッション毎に1度だけチェックボックス
	 */
	public boolean Session_Check( )
	{
		return Js_Click( "bp_checkbox#scorePerSession" );
	}

	/**
	 * スコア項目名編集
	 *
	 * @param edit	入力するスコア項目名
	 * @return
	 */
	public boolean Edit_Scoreitem( String edit )
	{
		return Js_Sendkey( "bp_text#keyName", edit );
	}

	/**
	 * スコア項目の編集ボタン
	 *
	 * @param index	編集したい行数
	 * @return
	 */
	public boolean Item_Edit( int index )
	{
		index--;

		return Edit_Window_Click( index );
	}

	/**
	 * スコア項目の削除ボタン
	 *
	 * @param index	編集したい行数
	 * @return
	 */
	public boolean Item_Delete( int index )
	{
		//XPATH指定用の調整
		index--;

		return Delete_Window_Click( index );
	}

	/**
	 * スコア項目の取消ボタン
	 *
	 * @param index	編集したい行数
	 * @return
	 */
	public boolean Item_Return( int index )
	{
		//XPATH指定用の調整
		index--;

		return Undo_Window_Click( index );
	}

	/**
	 * スコア項目名のOKボタン
	 */
	public boolean Itemedit_OK( )
	{
		return Js_Click( "bp_button#ok", EDIT_OK );
	}

	/**
	 * スコア項目名のキャンセルボタン
	 */
	public boolean Itemedit_Cancel( )
	{
		return Js_Click( "bp_button#cancel" );
	}

	/**
	 * スコアグループのOKボタン
	 */
	public boolean Group_OK( )
	{
		return Js_Click( "bp_button#ok", GROUP_OK );
	}

	/**
	 * スコアグループのキャンセルボタン
	 */
	public boolean Group_Cancel( )
	{
		return Js_Click( "bp_button#cancel", GROUP_CACEL );
	}

	/**
	 * エラーダイアログのOKボタン
	 */
	public boolean Error_OK( )
	{
		return Js_Click( "button#ok" );
	}

}
