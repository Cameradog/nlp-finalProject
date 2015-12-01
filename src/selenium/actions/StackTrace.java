package selenium.actions;


/****************************************************************************************************************************/
/*																															*/
/*											コンソール出力用																*/
/*																															*/
/*																															*/
/****************************************************************************************************************************/

public class StackTrace
{
	  public static String calledFrom()
	  {
	        StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
	        if (steArray.length <= 3)
	        {
	            return "";
	        }

	        StackTraceElement ste = steArray[3];
	        StringBuilder sb = new StringBuilder();

	        sb.append(ste.getMethodName())      // メソッド名取得
	            .append("(")
	            .append(ste.getFileName())      // ファイル名取得
	            .append(":")
	            .append(ste.isNativeMethod())   // ファイル名取得
	            .append(":")
	            .append(ste.getLineNumber())    // 行番号取得
	            .append(")");

	        return sb.toString();
	  }
}
