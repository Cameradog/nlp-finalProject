package feature;
import edu.stanford.nlp.process.*;
import main.Main;
public class Tokenization{
	
	public static void main(String[] args){//�o�̬O���Ftest�ӥ[��main�A���ӥi�H�R��
		Tokenization t = new Tokenization();
		t.tokenize();
	}
	
	public String tokenize() {
		
		String test = "Hello, there are many apples.";//����
		String content = test;//��ɭ�Ū����content
		String[] content_token = content.split(" ");//�u���ťաA�M�r���y���۳s���P�_����C
		content = "";
		for(String e1: content_token){
			String stemming = new Morphology().stem(e1);//stemming
			content += stemming + " ";//stem�᭫��
		}
		System.out.print(content);
		
		for(String e2: content_token){
			//VBG -> ���wVBG�ܦ��쫬�A��stem�Ϊk�ۦP�A�򥻤W�Τ���C
			String lemma = new Morphology().lemma(e2, "VBG");
		}
		return test;
	}
}