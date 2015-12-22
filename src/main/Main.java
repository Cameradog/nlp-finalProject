package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import classifer.MaxEnt_predict;
import classifer.MaxEnt_train;
import common.Constant;
import data.FieldType;
import data.FourField;
import feature.CreateNgram;
import feature.RemovePunctuation;
import feature.RemoveStopwords;
import fileService.ReadFileService;

public class Main {
	ReadFileService r;
	RemoveStopwords rmvStopwords;
	RemovePunctuation rmvPun;
	CreateNgram crtNgram;
	MaxEnt_train mt;
	MaxEnt_predict mp;
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		start();
	}
	
	public void start(){
		
		readTrainingData("resource/twitterFile/tweet1.txt" , FieldType.four);
		preProcessing();
		
		for(int i =0 ; i < Constant.trainingData.size(); i++){
			FourField f = Constant.trainingData.get(i);

		}
		
		
	}
	
	public void readTrainingData(String path, FieldType t){
		ReadFileService.getServ().readTrainingData(path , t);
	}
	
	public void preProcessing(){
		rmvStopwords = new RemoveStopwords();
		rmvStopwords.createStopwordsBank();
		
		rmvPun = new RemovePunctuation();
		crtNgram = new CreateNgram();
		
		for(int i = 0 ; i < Constant.trainingData.size() ;i++){
			
			// stop words
			String content = Constant.trainingData.get(i).content;
			String newContent = rmvStopwords.getLineWithNoStopwords(content);
			//Constant.trainingData.get(i).content = newContent;
			System.out.println("New Content:" +newContent);
			
			// pos tag
			
			// punctuation
			newContent = rmvPun.getLineWithNoPunctuation(newContent);
			//System.out.println("New Content w/o pun:" +newContent +"\n");
			
			//update
			Constant.trainingData.get(i).content = newContent;
		}	
		
		//ngram
		//n gram with val
		Map<String,Integer> unigramVal = crtNgram.getNgramMapWithValue(Constant.trainingData,FieldType.four, 1);
		
		//n gram with polarity
		Map<String,Integer> unigramPol = crtNgram.getNgramMapWithPolarity(Constant.trainingData,FieldType.four, 1);

		//training 
		mt = new MaxEnt_train();
		mt.output_train(3);//input num for Ngram, 0 for not doing Ngram
		mt.training();
		
		//predict
		mp = new MaxEnt_predict();
		mp.predict(3);//input num for Ngram, 0 for not doing Ngram
		
		
	}
}
