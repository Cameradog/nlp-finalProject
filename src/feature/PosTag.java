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
		return getPosTagList(tag);
	}
	
	public String getPosTagList(String posTag) {
		if (posTag.contains("JJ")) {
			return "adj";
		} else if (posTag.contains("NN")) {
			return "noun";
		} else if( posTag.contains("RB")){
			return "adverb";
		} else if( posTag.contains("VB")){
			return "verb";
		}
		return null;
	}
}
