package selenium.actions;

import org.openqa.selenium.*;

/**
 * メインメニュー
 *
 * @author a.toru.yano
 *
 */
public class Menu_Select extends Common
{

	//コンストラクタ
	public Menu_Select( final WebDriver driver )
	{
		Set_Drive( driver );
	}


	//レポート管理クリック
	public boolean Report_Manage()
	{
		return Click( "rt_navi_report_header-body", Element_Type.ID );
	}

	//レポートクリック
	public boolean Report() throws InterruptedException
	{
		return Click( "推荐报表", Element_Type.LINK );
	}

	//レコメンド管理クリック
	public boolean Recommend_manage()
	{
		return Click( "rt_navi_recoManage_header-body", Element_Type.ID );
	}

	//Web recommendation setting
	//Direct crawl Text: preset using Simplified Chinese
	public boolean Recommend_Web() throws InterruptedException
	{
		return Click("网页/手机/wifi\n推荐设置", Element_Type.LINK );
	}

	//メールレコメンド設定
	public boolean Recommend_Mail() throws InterruptedException
	{
		return Click( "邮件推荐设置", Element_Type.LINK );
	}

	//広告配信レコメンド設定
	public boolean Recommend_Ads() throws InterruptedException
	{
		return Click( "广告推荐设置", Element_Type.LINK );
	}

	//コンテンツ管理クリック
	public boolean Contents_manage()
	{
		return Click( "rt_navi_contentManage_header-body", Element_Type.ID );
	}

	//Webレコメンドコンテンツ設定
	public boolean Contentes_Web()throws InterruptedException
	{
	    return Click( "网页/手机/wifi\n推荐内容设置", Element_Type.LINK );
	}

	//メールレコメンドコンテンツ設定
	public boolean Contentes_Mail()throws InterruptedException
	{
	    return Click( "邮件推荐内容设置", Element_Type.LINK );
	}

	//アイテムリスト設定
	public boolean Contentes_Itemlist()throws InterruptedException
	{
	    return Click( "设置项目列表", Element_Type.LINK );
	}

	//テンプレート設定
	public boolean Contentes_Template()throws InterruptedException
	{
	    return Click( "网页/手机/wifi\n推荐设置", Element_Type.LINK );
	}

	//スコアリング管理クリック
	public boolean Score_Segment_manage()throws InterruptedException
	{
	    return Click( "rt_navi_scoreManage_header-body", Element_Type.ID );
	}

	//スコアグループ設定
	public boolean Score_Group()throws InterruptedException
	{
	    return Click( "评分群组设置", Element_Type.LINK );
	}

	//スコアルール設定
	public boolean Score_Rule()throws InterruptedException
	{
	    return Click( "评分规则设置", Element_Type.LINK );
	}

	//ユーザー属性設定
	public boolean Segment_User_Attribute()throws InterruptedException
	{
	    return Click( "用户属性配置", Element_Type.LINK );
	}

	//セグメント設定
	public boolean Segment_User_Segment()throws InterruptedException
	{
	    return Click( "用户分组设置", Element_Type.LINK );
	}

	//ユーザー情報表示
	public boolean Segment_User_Information()throws InterruptedException
	{
	    return Click( "用户信息查询", Element_Type.LINK );
	}

	//サイト管理クリック
	public boolean Site_manage()
	{
	    return Click( "rt_navi_siteManage_header-body", Element_Type.ID );
	}

	//アイテム設定
	public boolean Site_ItemSetting()throws InterruptedException
	{
	    return Click( "项目设置", Element_Type.LINK );
	}

	//サイト基本設定
	public boolean Site_BaseSetting()throws InterruptedException
	{
	    return Click( "网站的喜好", Element_Type.LINK );
	}

	//コンバージョンページ設定
	public boolean Site_Conversionpage()throws InterruptedException
	{
	    return Click( "购买页面设置", Element_Type.LINK );
	}

	//テーブル設定
	public boolean Table_Setting()throws InterruptedException
	{
	    return Click( "设置表", Element_Type.LINK );
	}

	//自社サイト・関連サイト設定
	public boolean Site_Referredsite()throws InterruptedException
	{
	    return Click( "公司网站及附属网站设置", Element_Type.LINK );
	}

	//アイテムフィルター設定
	public boolean Site_Itemfilter()throws InterruptedException
	{
	    return Click( "项目的过滤器设置", Element_Type.LINK );
	}

	//効果測定レポート設定
	public boolean Report_Basic()throws InterruptedException
	{
	    return Click( "报表设置", Element_Type.LINK );
	}

	//Rtoasterコードの表示
	public boolean Site_Showcode()
	{
		return Click( "显示RETchat代码", Element_Type.LINK );
	}

	//サイト一覧
	public boolean Site_View()throws InterruptedException
	{
	    return Click( "网站列表", Element_Type.LINK );
	}

	//システム設定
	public boolean System_Set()throws InterruptedException
    {
        return Click( "系统管理", Element_Type.LINK );
    }

	//ライセンス設定
    public boolean License()throws InterruptedException
    {
        return Click( "许可证", Element_Type.LINK );
    }

    //オペレーター一覧
    public boolean Site_Group()throws InterruptedException
    {
        return Click( "网站群组列表", Element_Type.LINK );
    }

    //オペレーター一覧
    public boolean Operertor()throws InterruptedException
    {
        return Click( "操作列表", Element_Type.LINK );
    }

	//サーバー設定
	public boolean Server()throws InterruptedException
    {
        return Click( "服务器", Element_Type.LINK );
    }

	//検索エンジン設定
    public boolean Search()throws InterruptedException
    {
        return Click( "詮索エンジン", Element_Type.LINK );
    }

    //リファラ設定
    public boolean Refferer()throws InterruptedException
    {
        return Click( "参照位址", Element_Type.LINK );
    }

    //リファラ設定
    public boolean Limit()throws InterruptedException
    {
        return Click( "范围", Element_Type.LINK );
    }

	//Rtoasterロゴ
	public boolean Logo()throws InterruptedException
	{
	    return Click( "rt_logo", Element_Type.CLASS );
	}

}
