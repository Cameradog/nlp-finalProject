package main;

import java.io.IOException;
import java.util.ArrayList;

import common.Constant;
import data.FieldType;
import data.FourField;
import feature.Stopwords;
import fileService.ReadFileService;

public class Main {
	ReadFileService r;
	Stopwords stopwords;
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		start();
	}
	
	public void start(){
		readTrainingData("resource/twitterFile/tweet.txt" , FieldType.four);
		preProcessing();
		
		for(int i =0 ; i < Constant.trainingData.size(); i++){
			FourField f = Constant.trainingData.get(i);

		}
		
		
	}
	
	public void readTrainingData(String path, FieldType t){
		ReadFileService.getServ().readTrainingData(path , t);
	}
	
	public void preProcessing(){
		stopwords = new Stopwords();
		stopwords.createStopwordsBank();

		
		for(int i = 0 ; i < Constant.trainingData.size() ;i++){
			
			// stop words
			String content = Constant.trainingData.get(i).content;
			String newContent = stopwords.getRemovedStopword(content);
			Constant.trainingData.get(i).content = newContent;
			
			// pos tag
			
			
		}	
	}
}
