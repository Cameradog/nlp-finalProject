package lexicon;

import java.util.Map;

import common.Constant;
import data.Word;
import feature.PosTag;
import fileService.ReadFileService;

public class CreateLexiconMap {
	// 0-neu
	// 1-pos
	// -1-neg
	int polarity;
	PosTag pt;
	public static int count=0;
	
	public static void main(String[] args){
		CreateLexiconMap c =new CreateLexiconMap();
		c.readFile();
		c.test();
	}
	
	public void test(){
		//System.out.println(getLinePolarity("dog"));
	}
	/*
	 * public static void main(String[] args){ new CreateLexiconMap(); }
	 */

	public CreateLexiconMap() {
		pt = new PosTag();
	}

	public void execute() {
		readFile();
		
	}

	public void readFile() {
		ReadFileService.getServ().readLexiconDatabese(
				"resource/subjclueslen1-HLTEMNLP05.tff");
		ReadFileService.getServ().readPosWordsFile("resource/positive-words.txt");
		ReadFileService.getServ().readNegWordsFile("resource/negative-words.txt");
	}

	public String getLinePolarity(String line) {
		String[] words = line.split("\\s+");
		// 0 for pos
		// 1 for neg
		// 2 for neu
		int[] array = new int[3];
		boolean enter = false;
		int score;
		String result="";
		for (int i = 0; i < words.length; i++) {
			String pos = pt.getWordPosTag(words[i]);
			//System.out.println(words[i] +" " + pos);
			if (pos != null) {
				Word w = new Word();
				w.word = words[i];
				w.pos = pos;
				// get polarity if exist;
				Map<Word, String> hMap = Constant.lexicon;
				//System.out.println(w.word +" " + w.pos +" "+hMap.get(w));

				if (hMap.get(w) != null) {
					if(!enter){
						enter = true;
						count++;
					}
					if(hMap.get(w).equals("positive")){
						array[0] +=1;
						
					} else if(hMap.get(w).equals("negative")){
						array[1] +=1;
						//neu
					} else {
						array[2] +=1;
					}
				}
			}		
		}
		score =countPolarity(array);
		if(!enter){
			result = "neu";
		}else{
			if(score ==0){
				result =  "neu";
			} else if(score >0){
				result =  "pos";
			} else if(score <0){
				result = "neg";
			}
		}
		return result;
	}

	public int countPolarity(int[] array) {
		int score=0;
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				score +=array[0] * 1;
			} else if (i == 1) {
				score +=array[1] * -1;
			} else if (i == 2) {
				score +=array[2] * 0;
			}
		}
		return score;
	}
}
