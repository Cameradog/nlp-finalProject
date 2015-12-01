package selenium.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

/**
 * テンプレート設定
 * @author a.toru.yano
 *
 */
public class Template_Setting extends Rule
{
    private String temp;
    private String tempgroup;

    /**
     * コンストラクタ
     * @param driver
     */
    public Template_Setting( WebDriver driver )
    {
        super( driver );

        temp = "rt_webTemplateConfig#webTemplateConfig";
        tempgroup = "rt_webTemplateGroupConfig#webTemplateGroupConfig";
    }


	/**
	 * テンプレート設定タブ
	 * @return
	 */
	public boolean Tab_Template()
	{
	    return Click( "模板设置", Element_Type.LINK );
	}

	/**
	 * テンプレートグループ設定タブ
	 * @return
	 */
	public boolean Tab_Template_Group()
    {
        return Click( "模板群组设置", Element_Type.LINK );
    }

	/**
	 * テンプレート新規作成
	 * @return
	 */
	public boolean Template_Add()
	{
		return Js_Click( "bp_newButton#new" );
	}

	/**
	 * テンプレートの編集ボタン
	 * @param index
	 * @return
	 */
	public boolean Template_Edit( int index )
	{
	    index--;

        return gc.Edit_Click( temp, index );
	}

	/**
     * Webテンプレートグループの編集ボタン
     *
     * @param index     編集したい行数
     * @return
     */
    public boolean Template_Group_Edit( int index )
    {
        index--;

        return gc.Edit_Click( tempgroup, index );
    }

	/**
	 * 削除ボタン
	 * @return
	 */
	public boolean Template_Delete()
	{
		return Js_Click( "bp_deleteButton#delete" );
	}

	/**
	 * テンプレートの取消ボタン
	 * @param index
	 * @return
	 */
	public boolean Template_Undo( int index )
	{
		return gc.Undo_Click( temp, index );
	}

	/**
     * テンプレートグループの取消ボタン
     * @param index
     * @return
     */
    public boolean Template_Group_Undo( int index )
    {
        return gc.Undo_Click( tempgroup, index );
    }

	/**
	 * テンプレート名編集
	 *
	 * @param edit	入力するテンプレート名
	 * @return
	 */
	public boolean Edit_Templatename( String edit )
	{
		return Sendkey( "name", edit , Element_Type.NAME );
	}

	/**
	 * 備考編集
	 *
	 * @param edit	入力する文字列
	 * @return
	 */
	public boolean Edit_Memo( String edit )
	{
		return Sendkey( "memo", edit, Element_Type.NAME );

	}

	/**
	 * テンプレート編集
	 *
	 * @return
	 */
	public boolean Edit_Template( )
	{
		String str = "";
		String contents = "";
		File file = new File( "template.txt" );

		//直接文字列入力はイケてないのでファイルから入力する
		try
		{
			FileReader red = new FileReader( file );
			BufferedReader br = new BufferedReader( red );

			try
			{
				while( true )
				{
					str = br.readLine( );

					if( str == null )
					{
						break;
					}

					contents = contents + str + "\n";
				}

				red.close();
			}
			catch( IOException e)
			{
				System.out.println(e);
			}

		}
		catch( FileNotFoundException e )
		{
			return false;
		}

		return Sendkey( "html", contents, Element_Type.NAME );
	}

	/**
	 * OKボタン
	 * @return
	 */
	public boolean Template_OK( )
	{
		return Js_Click( "bp_button#ok", 0 );
	}

	/**
	 * キャンセルボタン
	 * @return
	 */
	public boolean Template_Group_Cancel( )
	{
		return Js_Click( "bp_button#cancel", 1 );
	}

}
