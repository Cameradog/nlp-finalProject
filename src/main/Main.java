package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import twitterRec.Paper_emoticon;
import lexicon.CreateLexiconMap;
import classifer.MaxEnt_predict;
import classifer.MaxEnt_train;
import common.Constant;
import data.FieldType;
import feature.CreateNgram;
import feature.RemoveEmoji;
import feature.RemovePunctuation;
import feature.RemoveStopwords;
import feature.Tokenization;
import fileService.ReadFileService;

public class Main {
	ReadFileService r;
	RemoveStopwords rmvStopwords;
	RemovePunctuation rmvPun;
	RemoveEmoji re;
	CreateNgram crtNgram;
	MaxEnt_train mt;
	MaxEnt_predict mp;
	CreateLexiconMap cl;
	Tokenization ti;
	Paper_emoticon pm;
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		cl = new CreateLexiconMap();
		ti = new Tokenization();
		pm = new Paper_emoticon();
		re = new RemoveEmoji();
		start();
	}
	
	public void start(){
		readTrainingDataPaperEmotion("resource/twitterFile/tweets_emoji.txt", false);
		//readTrainingData("resource/twitterFile/tweet_RAWW.txt" , FieldType.four);
		//createLexiconMap();
		preProcessing();	
	}
	
	public void readTrainingData(String path, FieldType t){
		ReadFileService.getServ().readTrainingData(path , t);
	}
	
	public void readTrainingDataPaperEmotion(String path, boolean hasEmoji){
		pm.execute(path, hasEmoji);
	}
	
	public void createLexiconMap(){	
		cl.execute();
	}
	
	public void removeEmojiInTwitterData(){
		
	}
	
	public void preProcessing(){
		rmvStopwords = new RemoveStopwords();
		rmvStopwords.createStopwordsBank();
		
		rmvPun = new RemovePunctuation();
		System.out.println(Constant.trainingData.size());

		for(int i = Constant.trainingData.size()-1 ; i >=0  ;i--){		
			// stop words
			String content = Constant.trainingData.get(i).content;
			String newContent = rmvStopwords.getLineWithNoStopwords(content);
			// punctuation
			newContent = rmvPun.getLineWithNoPunctuation(newContent);	
			//tokenization
			newContent = ti.tokenize(newContent);
			//update
			Constant.trainingData.get(i).content = newContent;
			
			String line = Constant.trainingData.get(i).content;
			/*
			//lexicon
			String polarity = cl.getLinePolarity(line);
			if(polarity.equals("ignore")){
				Constant.trainingData.remove(i);
			} else{
				Constant.trainingData.get(i).polarity = polarity;
			}	*/	
		}	
		//System.out.println(Constant.trainingData.size());
		//System.out.println(CreateLexiconMap.count);
		for(int i = 0 ; i < Constant.trainingData.size() ;i++){		
			System.out.println(Constant.trainingData.get(i).content +" " + Constant.trainingData.get(i).polarity);		
		}	
		
		//MEClassifier();
		//training
		//me
		//mt = new MaxEnt_train();
		//mt.tweetout();
		//mt.training();		
		
		//svm
		//dp = new DocumentParser();
		//dp.parseDoc();
		//dp.count();
		//dp.tfIdfCalculator();
	}
	
	public void lexicon(){
		
	}
	
	public void createNgram(int n ){
		crtNgram = new CreateNgram();	
		//ngram
		//n gram with val
		Map<String,Integer> unigramVal = crtNgram.getNgramMapWithValue(Constant.trainingData,FieldType.four, 1);
		
		//n gram with polarity
		Map<String,Integer> unigramPol = crtNgram.getNgramMapWithPolarity(Constant.trainingData,FieldType.four, 1);
	}
	
	public void MEClassifier(){
		//training 
		mt = new MaxEnt_train();
		mt.output_train(0);//input num for Ngram, 0 for not doing Ngram
		mt.training();
		
		//predict
		mp = new MaxEnt_predict();
		mp.predict(0);//input num for Ngram, 0 for not doing Ngram
	}
}
