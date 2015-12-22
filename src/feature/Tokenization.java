package feature;

import edu.stanford.nlp.process.*;
import main.Main;

public class Tokenization {

	public static void main(String[] args) {// �o�̬O���Ftest�ӥ[��main�A���ӥi�H�R��
		Tokenization t = new Tokenization();
		System.out.println(t.tokenize("I'm happy rings"));
	}

	public String tokenize(String text) {
		String content = text;
		String[] content_token = content.split(" ");
		content = "";
		for (String e1 : content_token) {
			String stemming = new Morphology().stem(e1);// stemming
			if (stemming != null) {
				content += stemming + " ";
			}
		}
		return content;
	}
}