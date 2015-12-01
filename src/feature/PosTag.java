package feature;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PosTag {
	//tagger model
	MaxentTagger tagger;
	
	public PosTag(){
		tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
	}

	public String getWordPosTag(String word){
		String tag = tagger.tagTokenizedString(word).split("_")[1];
		System.out.println(tag);
		return tag;
	}
}
