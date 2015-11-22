package feature;
import edu.stanford.nlp.process.*;
public class Tokenization{
	public String tokenize() {
		
		String test = "Hello, There are many apples.";
		String content = test;
		//content = content.split(",")[3];
		String[] content_token = content.split(" ");
		for(String e1: content_token){
			String stemming = new Morphology().stem(e1);
		}
		
		for(String e2: content_token){
			String lemma = new Morphology().lemma(e2, "VBG");//tag?
		}
		//stopword
		
		return test;
	}
}