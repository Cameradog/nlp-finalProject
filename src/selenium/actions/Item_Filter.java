package selenium.actions;

import java.util.List;

import test.selenium.ExtComponentFinder;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;


public class Item_Filter extends Usersegment_Setting
{
	private final int CANCEL;
	private final int USERFILTER_NEW;
	private final int ITEMFILTER_NEW;
	private final int RANKINGFILTER_NEW;
	private final int ATTRIBUTE_NEW;
	private final int SCORE_NEW;
	private final int SELECT;
	private final int OK;
	private final int RECOMMEND;
	private final int ATTRIBUTE;
	private final int SCORE;
//	private final int USER_FLAG;
	private final ExtComponentFinder ecf;
    //どちらを先に開いたかでXPATHがずれるので、その判定用
	private final int user_edit;
	String xpath = "//div[@class=\"x-grid-cell-inner\"]/img";

	//コンストラクタ
	public Item_Filter( final WebDriver driver )
	{
	    super( driver );
		CANCEL = 2;
		user_edit = 0;
		USERFILTER_NEW = 0;
		ITEMFILTER_NEW = 1;
		RANKINGFILTER_NEW = 2;
		ATTRIBUTE_NEW = 2;
		SCORE_NEW = 3;
		SELECT = 0;
		RECOMMEND = 1;
        ATTRIBUTE = 0;
	    SCORE = 1;
		OK = 2;
        count = 0;

		ecf = new ExtComponentFinder( driver );

	}

	/**
	 *ユーザー軸新規作成ボタン
	 */
	public boolean Userfilter_Add()
	{
		return Js_Click( "bp_newButton#new", USERFILTER_NEW );
	}

	/**
	 *アイテム軸新規作成ボタン
	 */
	public boolean Itemfilter_Add()
	{
		return Js_Click( "bp_newButton#new", ITEMFILTER_NEW );
	}

	/**
     *アイテム軸新規作成ボタン
     */
    public boolean Rankingfilter_Add()
    {
        return Js_Click( "bp_newButton#new", RANKINGFILTER_NEW );
    }

	/**
	 *属性新規作成ボタン
	 */
	@Override
    public boolean Attribute_Add()
	{
		return Js_Click( "bp_newButton#new", ATTRIBUTE_NEW );
	}

	/**
	 *スコア新規作成ボタン
	 */
	@Override
    public boolean Score_Add()
	{
		return Js_Click( "bp_newButton#new", SCORE_NEW );
	}

	/**
	 * ユーザー軸タブ
	 * @return
	 * @throws InterruptedException
	 */
	public boolean Tab_User() throws InterruptedException
	{
		Click( "ユーザー軸アイテムフィルタ", Element_Type.LINK  );

		Thread.sleep(500);

		return true;
	}

	/**
	 * アイテム軸タブ
	 * @return
	 * @throws InterruptedException
	 */
	public boolean Tab_Item() throws InterruptedException
	{
		Click( "アイテム軸アイテムフィルタ", Element_Type.LINK  );

		Thread.sleep(500);

		return true;
	}

	/**
     * ランキング/枠補完タブ
     * @return
     * @throws InterruptedException
     */
    public boolean Tab_Ranking() throws InterruptedException
    {
        Click( "ランキング/枠補完アイテムフィルタ", Element_Type.LINK  );

        Thread.sleep(500);

        return true;
    }

	/**
	 * ユーザー軸の編集ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 */
	public boolean User_Edit( final int index )
	{
		count = Get_Icon();

		return Edit_Click( index - 1 );
	}

	/**
	 * ユーザー軸のコピーボタン
	 *
	 * @param index		編集したい行数
	 */
	public boolean User_Copy( final int index )
	{
		count = Get_Icon();

		return Copy_Click( index - 1 );
	}

	/**
	 * アイテム軸の編集ボタン
	 *
	 * @param index		編集したい行数
	 * @return
	 * @throws InterruptedException
	 */
	public boolean Item_Edit( int index ) throws InterruptedException
	{

		index = index + user_edit;

		return Edit_Click( index );
	}

	/**
	 * アイテム軸のコピーボタン
	 *
	 * @param index		編集したい行数
	 */
	public boolean Item_Copy( int index )
	{

		index = index + user_edit;

		return Copy_Click( index );
	}

	/**
	 * OKボタン
	 */
	@Override
    public boolean OK( )
	{
		return Js_Click( "bp_button#ok", OK );
	}

	/**
	 * ユーザー軸キャンセルボタン
	 */
	public boolean User_Cancel( )
	{
		return Js_Click( "bp_button#cancel", CANCEL );
	}

	/**
	 * アイテム軸キャンセルボタン
	 */
	public boolean Item_Cancel( )
	{
		return Js_Click( "bp_button#cancel" );
	}

    /**
     * ユーザー属性のキャンセルボタン
     */
    @Override
    public boolean Cancel_Attribute( )
    {
        return Js_Click( "bp_button#cancel", ATTRIBUTE );
    }

    /**
     * スコア条件のキャンセルボタン
     */
    @Override
    public boolean Cancel_Score( )
    {
        return Js_Click( "bp_button#cancel", SCORE );
    }

	/**
	 * フィルタ名編集
	 *
	 * @param edit	入力するフィルタ名
	 * @return
	 */
	public boolean Edit_Filtername( final String edit )
	{
		return Js_Sendkey( "bp_text#name", edit );
	}

	/**
	 * フィルタ種別選択
	 *
	 * @param index  1:除外する 2:除外対象から外す 3:除外を強制する
	 * @return
	 */
	public void Select_Filtettype( final int index  )
	{
		ecf.selectCombobox( "rt_filterSelectCombo#type", index );

		return;
	}

	/**
	 * コンバージョン済みを除外
	 */
	public boolean Conversion( )
	{
		return Js_Click( "bp_checkbox#conversionCheck" );
	}

	/**
	 * アイテムコード編集
	 *
	 * @param edit	アイテムコード
	 * @return
	 */
	public boolean Edit_Itemcode( final String edit )
	{
		return Sendkeys( "codeField", edit, Element_Type.NAME, 0 );
	}

	/**
	 * アイテムコード編集（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param edit	アイテムコード
	 * @return
	 */
	public boolean Edit_Itemcode_right( final String edit )
	{
		return Sendkeys( "codeField", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * アイテムコードの条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Itemcode( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#codeType", index );

		return;
	}

	/**
	 * アイテムコードの条件指定（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Itemcode_right( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#codeType", index );

		return;
	}

	/**
	 * アイテム名編集
	 *
	 * @param edit	アイテム名
	 * @return
	 */
	public boolean Edit_Itemname( final String edit )
	{
		return Sendkeys( "nameField", edit, Element_Type.NAME, 0 );
	}

	/**
	 * アイテム名編集（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param edit	アイテム名
	 * @return
	 */
	public boolean Edit_Itemname_right( final String edit )
	{
		return Sendkeys( "nameField", edit, Element_Type.NAME, RECOMMEND );
	}


	/**
	 * アイテム名の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Itemname( final int index )
	{
	    ecf.selectCombobox( "rt_stringOperatorCombo#nameType", index );

		return;
	}

	/**
	 * アイテム名の条件指定（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Itemname_right( final int index )
	{
	    ecf.selectCombobox( "rt_stringOperatorCombo#nameType", RECOMMEND, index );

		return;
	}

	/**
	 * グループ名編集
	 *
	 * @param edit	グループ名
	 * @return
	 */
	public boolean Edit_Group( final String edit )
	{
		return Sendkeys( "groupField", edit, Element_Type.NAME, 0 );
	}

	/**
	 * グループ名編集
	 *
	 * @param edit	グループ名（アイテム軸のレコメンドアイテム条件はこっち）
	 * @return
	 */
	public boolean Edit_Group_right( final String edit )
	{
		return Sendkeys( "groupField", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * グループの条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Group( final int index )
	{
		return List_Click( "groupType", index );
	}

	/**
	 * グループの条件指定（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Group_right( final int index )
	{
		return List_Click( "groupType", index, RECOMMEND );
	}

	/**
	 * 価格上限編集
	 *
	 * @param edit	価格上限
	 * @return
	 */
	public boolean Edit_Pricefrom( final String edit )
	{
		return Sendkeys( "priceFromField", edit, Element_Type.NAME, 0 );
	}

	/**
	 * 価格上限編集（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param edit	価格上限
	 * @return
	 */
	public boolean Edit_Pricefrom_right( final String edit )
	{
		return Sendkeys( "priceFromField", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * 価格下限編集
	 *
	 * @param edit	価格下限
	 * @return
	 */
	public boolean Edit_Priceto( final String edit )
	{
		return Sendkeys( "priceToField", edit, Element_Type.NAME, 0 );
	}

	/**
	 * 価格下限編集（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param edit	価格下限
	 * @return
	 */
	public boolean Edit_Priceto_right( final String edit )
	{
		return Sendkeys( "priceToField", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * 価値上限編集
	 *
	 * @param edit	価値上限
	 * @return
	 */
	public boolean Edit_Profitfrom( final String edit )
	{
		return Sendkeys( "profitFromField", edit, Element_Type.NAME, 0 );
	}

	/**
	 * 価値上限編集（アイテム軸のレコメンドアイテム条件はこっち）
	 *
	 * @param edit	価値上限
	 * @return
	 */
	public boolean Edit_Profitfrom_right( final String edit )
	{
		return Sendkeys( "profitFromField", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * 価値下限編集
	 *
	 * @param edit	価値下限
	 * @return
	 */
	public boolean Edit_Profitto( final String edit )
	{
		return Sendkeys( "profitToField", edit, Element_Type.NAME, 0 );
	}

	/**
	 * 価値下限編集
	 *
	 * @param edit	価値下限
	 * @return
	 */
	public boolean Edit_Profitto_right( final String edit )
	{
		return Sendkeys( "profitToField", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * カテゴリ1編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category0( final String edit )
	{
		return Sendkeys( "category0Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * カテゴリ1編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category0_right( final String edit )
	{
		return Sendkeys( "category0Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * カテゴリ1の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category0( final int index )
	{
		return List_Click( "category0Type", index );
	}

	/**
	 * カテゴリ1の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category0_right( final int index )
	{
		return List_Click( "category0Type", index, RECOMMEND );
	}

	/**
	 * カテゴリ2編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category1( final String edit )
	{
		return Sendkeys( "category1Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * カテゴリ2編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category1_right( final String edit )
	{
		return Sendkeys( "category1Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * カテゴリ2の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category1( final int index )
	{
		return List_Click( "category1Type", index );
	}

	/**
	 * カテゴリ2の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category1_right( final int index )
	{
		return List_Click( "category1Type", index, RECOMMEND );
	}

	/**
	 * カテゴリ3編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category2( final String edit )
	{
		return Sendkeys( "category2Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * カテゴリ3編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category2_right( final String edit )
	{
		return Sendkeys( "category2Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * カテゴリ3の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category3( final int index )
	{
		return List_Click( "category2Type", index );
	}

	/**
	 * カテゴリ3の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category3_right( final int index )
	{
		return List_Click( "category2Type", index, RECOMMEND );
	}

	/**
	 * カテゴリ4編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category3( final String edit )
	{
		return Sendkeys( "category3Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * カテゴリ4編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category3_right( final String edit )
	{
		return Sendkeys( "category3Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * カテゴリ4の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category4( final int index )
	{
		return List_Click( "category3Type", index );
	}

	/**
	 * カテゴリ4の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category4_right( final int index )
	{
		return List_Click( "category3Type", index, RECOMMEND );
	}

	/**
	 * カテゴリ5編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category4( final String edit )
	{
		return Sendkeys( "category4Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * カテゴリ5編集
	 *
	 * @param edit	カテゴリ名
	 * @return
	 */
	public boolean Edit_Category4_right( final String edit )
	{
		return Sendkeys( "category4Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * カテゴリ5の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category5( final int index )
	{
		return List_Click( "category4Type", index );
	}

	/**
	 * カテゴリ5の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Category5_right( final int index )
	{
		return List_Click( "category4Type", index, RECOMMEND );
	}

	/**
	 * フィールド1編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field0( final String edit )
	{
		return Sendkeys( "field0Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド1編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field0_right( final String edit )
	{
		return Sendkeys( "field0Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド1の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Field0( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#field0Type", index );

		return;
	}

	/**
	 * フィールド1の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field0_right( final int index )
	{
		return List_Click( "Field0Type", index, RECOMMEND );
	}

	/**
	 * フィールド2編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field1( final String edit )
	{
		return Sendkeys( "field1Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド2編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field1_right( final String edit )
	{
		return Sendkeys( "field1Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド2の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field1( final int index )
	{
		return List_Click( "Field1Type", index );
	}

	/**
	 * フィールド2の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field1_right( final int index )
	{
		return List_Click( "Field1Type", index, RECOMMEND );
	}

	/**
	 * フィールド3編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field2( final String edit )
	{
		return Sendkeys( "field2Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド3編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field2_right( final String edit )
	{
		return Sendkeys( "field2Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド3の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field2( final int index )
	{
		return List_Click( "Field2Type", index );
	}

	/**
	 * フィールド3の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field2_right( final int index )
	{
		return List_Click( "Field2Type", index, RECOMMEND );
	}

	/**
	 * フィールド4編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field3( final String edit )
	{
		return Sendkeys( "field3Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド4編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field3_right( final String edit )
	{
		return Sendkeys( "field3Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド4の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field3( final int index )
	{
		return List_Click( "Field3Type", index );
	}

	/**
	 * フィールド4の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field3_right( final int index )
	{
		return List_Click( "Field3Type", index, RECOMMEND );
	}

	/**
	 * フィールド5編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field4( final String edit )
	{
		return Sendkeys( "field4Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド5編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field4_right( final String edit )
	{
		return Sendkeys( "field4Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド5の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field4( final int index )
	{
		return List_Click( "Field4Type", index );
	}

	/**
	 * フィールド5の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field4_right( final int index )
	{
		return List_Click( "Field4Type", index, RECOMMEND );
	}

	/**
	 * フィールド6編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field5( final String edit )
	{
		return Sendkeys( "field5Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド6編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field5_right( final String edit )
	{
		return Sendkeys( "field5Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド6の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field5( final int index )
	{
		return List_Click( "Field5Type", index );
	}

	/**
	 * フィールド6の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field5_right( final int index )
	{
		return List_Click( "Field5Type", index, RECOMMEND );
	}

	/**
	 * フィールド7編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field6( final String edit )
	{
		return Sendkeys( "field6Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド7編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field6_right( final String edit )
	{
		return Sendkeys( "field6Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド7の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field6( final int index )
	{
		return List_Click( "Field6Type", index );
	}

	/**
	 * フィールド7の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field6_right( final int index )
	{
		return List_Click( "Field6Type", index, RECOMMEND );
	}

	/**
	 * フィールド8編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field7( final String edit )
	{
		return Sendkeys( "field7Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド8編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field7_right( final String edit )
	{
		return Sendkeys( "field7Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド8の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field7( final int index )
	{
		return List_Click( "Field7Type", index );
	}

	/**
	 * フィールド8の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field7_right( final int index )
	{
		return List_Click( "Field7Type", index, RECOMMEND );
	}

	/**
	 * フィールド9編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field8( final String edit )
	{
		return Sendkeys( "field8Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド9編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field8_right( final String edit )
	{
		return Sendkeys( "field8Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド9の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field8( final int index )
	{
		return List_Click( "Field8Type", index );
	}

	/**
	 * フィールド9の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field8_right( final int index )
	{
		return List_Click( "Field8Type", index, RECOMMEND );
	}

	/**
	 * フィールド10編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field9( final String edit )
	{
		return Sendkeys( "field9Field", edit, Element_Type.NAME, 0 );
	}

	/**
	 * フィールド10編集
	 *
	 * @param edit	フィールド名
	 * @return
	 */
	public boolean Edit_Field9_right( final String edit )
	{
		return Sendkeys( "field9Field", edit, Element_Type.NAME, RECOMMEND );
	}

	/**
	 * フィールド10の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public boolean Select_Field9( final int index )
	{
		return List_Click( "Field9Type", index );
	}

	/**
	 * フィールド10の条件指定
	 *
	 * @param index	選択リストのindex値
	 * @return
	 */
	public void Select_Field9_right( final int index )
	{
		ecf.selectCombobox( "rt_stringOperatorCombo#field9Type", index );

		return;
	}

	/**
	 * 例外フラグ　グループチェック
	 * @return
	 */
	public boolean Check_Exceptiongroup()
	{
	    return Js_Click( "bp_checkbox#exceptionGrouping" );
	}

	/**
     * 例外フラグ　カテゴリチェック
     * @return
     */
    public boolean Check_Exception_Category1()
    {
        return Js_Click( "bp_checkbox#exceptionCategory0" );
    }

    /**
     * 例外フラグ　カテゴリチェック
     * @return
     */
    public boolean Check_Exception_Category2()
    {
        return Js_Click( "bp_checkbox#exceptionCategory1" );
    }

    /**
     * 例外フラグ　カテゴリチェック
     * @return
     */
    public boolean Check_Exception_Category3()
    {
        return Js_Click( "bp_checkbox#exceptionCategory2" );
    }

    /**
     * 例外フラグ　カテゴリチェック
     * @return
     */
    public boolean Check_Exception_Category4()
    {
        return Js_Click( "bp_checkbox#exceptionCategory3" );
    }

    /**
     * 例外フラグ　カテゴリチェック
     * @return
     */
    public boolean Check_Exception_Category5()
    {
        return Js_Click( "bp_checkbox#exceptionCategory4" );
    }

	/**
	 * アイテム条件のリスト選択時に使用
	 * @param str
	 * @param index
	 * @return
	 */
	private boolean List_Click( final String str, final int index )
	{
		//リスト内の文字列を選択するために一度リストを展開する
		WebElement elm = null;

		elm = Wait_Element( str, Element_Type.NAME );

		if( elm == null )
		{
			return false;
		}

		elm.click();

		return true;//List_Item_Click( index, SELECT );
	}

	/**
	 * アイテム条件のリスト選択時に使用
	 * @param str
	 * @param index
	 * @return
	 */
	private boolean List_Click( final String str, final int index, final int num )
	{
		//リスト内の文字列を選択するために一度リストを展開する
		List<WebElement> elms;

		elms = Wait_Elements( str, Element_Type.NAME );

		if( elms == null )
		{
			return false;
		}

		elms.get( num ).click();

		return true;//List_Item_Click( index, SELECT );
	}

	/**
	 * アイテムフィルタ用
	 *
	 * @param elm_str	HTML要素の文字列
	 * @param edit		入力する文字列
	 * @param type	要素の属性。idとかnameとか
	 * @return			書き込めない＝FALSEではないので注意
	 */
	private boolean Sendkeys( final String elm_str, final String edit, final Element_Type type, final int num )
	{
		List<WebElement> elms;
		elms = Wait_Elements( elm_str, type );

		if( elms == null )
		{
			System.out.println(elm_str + " is NULL...\n" + StackTrace.calledFrom() );
			return false;
		}

		for( int count = 0; count < RETRY ;count++ )
		{
			try
			{
				elms.get( num ).clear( );
				elms.get( num ).sendKeys( edit );
				break;
			}
			catch( final StaleElementReferenceException e )
			{
				System.out.println("StaleElementReferenceException\n\n" + StackTrace.calledFrom() );
				elms = Wait_Elements( elm_str, type );
			}
			catch(final InvalidElementStateException e1)
			{
				System.out.println("InvalidElementStateException\n\n" + StackTrace.calledFrom() );
			}
			catch(final WebDriverException e3 )
			{
				System.out.println("WebDriverException\n\n" + StackTrace.calledFrom() );
			}

		}

		return true;
	}

}
