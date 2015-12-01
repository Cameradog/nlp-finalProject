package posTag_example;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class main {
	//tagger model
	MaxentTagger tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
	
	public static void main(String[] args){
		String[] sampleWords = {"Amy","and","Ken","are","girl","and","boy","who","live","in","America","."};
		String sampleWords2 = "Amy and Ken are girl and boy who live in America.";
		String wordsWithTag;
		String[] tag;
		
		main m = new main();
		System.out.println();
		
		//tag a single word
		System.out.println("## tag a single word ##");
		System.out.println("Word/taggerForm/PosTag");
		for(int i=0; i<sampleWords.length; i++){
			wordsWithTag = m.tagger.tagTokenizedString(sampleWords[i]);
			tag = m.tagger.tagTokenizedString(sampleWords[i]).split("_");
			System.out.println(sampleWords[i]+"/"+wordsWithTag+"/"+tag[1]);
		}
		
		//tag whole sentence
		System.out.println();
		System.out.println("## tag whole sentence ##");
		System.out.println("Word/taggerForm");
		wordsWithTag = m.tagger.tagTokenizedString(sampleWords2);
		System.out.println(sampleWords2+"\r333"+wordsWithTag);
	}
	
}
