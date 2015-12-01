package selenium.actions;

import org.openqa.selenium.WebDriver;


/**
 * 広告セグメント設定
 * ユーザーセグメントと違うのはセグメントの選択と取消ボタンがない。
 * それ以外は同じ
 * @author a.toru.yano
 *
 */
public class Ads_Segment extends Usersegment_Setting
{
    //コンストラクタ
    public Ads_Segment( WebDriver driver )
    {
        super( driver );
        Set_Drive( driver );
    }

    /**
     * 広告セグメントタブ
     * @return
     */
    public boolean Tab_Segment()
    {
        return Click( "広告セグメント", Element_Type.LINK );
    }

    /**
     * セグメントを選択
     * @param index
     */
    public void Select_Segment( int index )
    {
        ecf.selectCombobox( "bp_combo#segment", index );

        return;
    }
}
