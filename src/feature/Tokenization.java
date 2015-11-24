package feature;
import edu.stanford.nlp.process.*;
import main.Main;
public class Tokenization{
	
	public static void main(String[] args){//這裡是為了test而加的main，應該可以刪除
		Tokenization t = new Tokenization();
		t.tokenize();
	}
	
	public String tokenize() {
		
		String test = "Hello, there are many apples.";//測資
		String content = test;//到時候讀取的content
		String[] content_token = content.split(" ");//只切空白，和逗號句號相連的判斷不到。
		content = "";
		for(String e1: content_token){
			String stemming = new Morphology().stem(e1);//stemming
			content += stemming + " ";//stem後重組
		}
		System.out.print(content);
		
		for(String e2: content_token){
			//VBG -> 指定VBG變成原型，跟stem用法相同，基本上用不到。
			String lemma = new Morphology().lemma(e2, "VBG");
		}
		return test;
	}
}