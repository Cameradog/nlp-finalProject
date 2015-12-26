package main;

import common.Constant;

public class DebugStarter {

	public static void main(String[] args){
		setting();
		new Main().start();
	}
	
	public static void setting(){
		Constant.FilePath = "resource/twitterFile/tweet_raw_2.txt";
		//default, lexicon, improve
		Constant.rawDataProcess="lexicon";
			
		Constant.removePunAndNum = true;
		Constant.removeUnMeaning = true;
		Constant.stem = true;
		Constant.negation = true;
		Constant.hasStopword = false;
		
		//navie, me
		Constant.classifier="me";
		
		//uni, bi, unibi, unipo ,no
		Constant.classifierFeature="uni"; 
	}
}
