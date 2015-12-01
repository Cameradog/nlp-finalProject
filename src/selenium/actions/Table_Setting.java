package selenium.actions;

import org.openqa.selenium.*;

public class Table_Setting extends Common
{
	/*
	 * queryのindex指定用。基本固定値。画面に項目が増えれば、変動する可能性あり
	 */
	private final int TABLE_OK;
	private final int TABLE_CANCEL;
	private final int VALUE_OK;
	private final int VALUE_NEW;
	private Grid_Click gc;

	//コンストラクタ
	public Table_Setting( WebDriver driver )
	{
		TABLE_OK = 1;
		TABLE_CANCEL = 1;
		VALUE_OK = 0;
		VALUE_NEW = 1;
		gc = new Grid_Click( driver );
		Set_Drive( driver );
	}

	/**
	 * キーワード設定タブ
	 * @return
	 */
	public boolean Tab_Keyword()
	{
	    return Click( "キーワードテーブル設定", Element_Type.LINK );
	}

	/**
	 * ドメインテーブル設定タブ
	 * @return
	 */
	public boolean Tab_Domain()
    {
        return Click( "ドメインテーブル設定", Element_Type.LINK );
    }

	/**
	 * IPアドレス設定タブ
	 * @return
	 */
	public boolean Tab_IP()
    {
        return Click( "IPアドレステーブル設定", Element_Type.LINK );
    }

	/**
	 * キーワードテーブル作成ボタン
	 */
	public boolean Table_Add()
	{
		return Js_Click( "bp_newButton#new" );
	}

	/**
	 * キーワード新規作成ボタン
	 */
	public boolean Value_Add()
	{
		return Js_Click( "bp_newButton#new", VALUE_NEW );
	}

	/**
	 * キーワードテーブルの編集ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean Keywordtable_Edit( int index )
	{
	    index--;

		return gc.Edit_Click( "rt_keywordTable#keywordTable", index );
	}

	/**
	 * キーワードテーブルの取消ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean Keywordtable_Undo( int index )
	{
        index--;

        return gc.Undo_Click( "rt_keywordTable#keywordTable", index );
	}

	/**
     * ドメインテーブルの編集ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Domaintable_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_domainTable#keywordTable", index );
    }

    /**
     * ドメインテーブルの取消ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Domaintable_Undo( int index )
    {
        index--;

        return gc.Undo_Click( "rt_domainTable#keywordTable", index );
    }

    /**
     * IPアドレステーブルの編集ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Iptable_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_ipTable#ipTable", index );
    }

    /**
     * IPアドレステーブルの取消ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Iptable_Undo( int index )
    {
        index--;

        return gc.Undo_Click( "rt_ipTable#ipTable", index );
    }

    /**
     * キーワードテーブルの項目の編集ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Keywordvalue_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_keywordTableEdit", index );
    }

	/**
	 * キーワードテーブル項目の削除ボタン
	 *
	 * @param index	編集したい行数
	 * @return
	 */
	public boolean KeywordValue_Delete( int index )
	{
        index--;

        return gc.Delete_Click( "rt_keywordTableEdit", index );
	}

    /**
     * キーワードテーブルの項目の編集ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Domainvalue_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_domainTableEdit", index );
    }

    /**
     * キーワードテーブル項目の削除ボタン
     *
     * @param index 編集したい行数
     * @return
     */
    public boolean DomainValue_Delete( int index )
    {
        index--;

        return gc.Delete_Click( "rt_domainTableEdit", index );
    }

    /**
     * キーワードテーブルの項目の編集ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Ipvalue_Edit( int index )
    {
        index--;

        return gc.Edit_Click( "rt_ipTableEdit", index );
    }

    /**
     * キーワードテーブル項目の削除ボタン
     *
     * @param index 編集したい行数
     * @return
     */
    public boolean IpValue_Delete( int index )
    {
        index--;

        return gc.Delete_Click( "rt_ipTableEdit", index );
    }

	/**
	 * テーブル名編集
	 *
	 * @param edit	入力するテーブル名
	 * @return
	 */
	public boolean Edit_Tablename( String edit )
	{
		return Js_Sendkey( "bp_text#name", edit );
	}

	/**
	 * 項目編集
	 *
	 * @param edit	入力する項目内容
	 * @return
	 */
	public boolean Edit_Value( String edit )
	{
		return Js_Sendkey( "bp_text#valueName", edit );
	}

	/**
	 * 項目のOKボタン
	 */
	public boolean Value_OK( )
	{
		return Js_Click( "bp_button#ok", VALUE_OK );
	}

	/**
	 * 項目のキャンセルボタン
	 */
	public boolean Value_Cancel( )
	{
		return Js_Click( "bp_button#cancel" );
	}

	/**
	 * テーブル名のOKボタン
	 */
	public boolean Table_OK( )
	{
		return Js_Click( "bp_button#ok", TABLE_OK );
	}

	/**
	 * テーブル名のキャンセルボタン
	 */
	public boolean Table_Cancel( )
	{
		return Js_Click( "bp_button#cancel", TABLE_CANCEL );
	}

	/**
	 * エラーダイアログのOKボタン
	 */
	public boolean Error_OK( )
	{
		return Js_Click( "button#ok" );
	}

}
