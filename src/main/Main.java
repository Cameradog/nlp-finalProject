package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import twitterRec.Improved_emoticon;
import twitterRec.Paper_emoticon;
import lexicon.CreateLexiconMap;
import classifer.MaxEnt_predict;
import classifer.MaxEnt_train;
import common.Constant;
import data.FieldType;
import feature.CreateNgram;
import feature.Negtation;
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
	Improved_emoticon ie;
	Negtation ng;

	String filepath = Constant.FilePath;

	/*
	 * public static void main(String[] args){ new Main(); }
	 */

	public Main() {
		cl = new CreateLexiconMap();
		ti = new Tokenization();
		pm = new Paper_emoticon();
		re = new RemoveEmoji();
		ie = new Improved_emoticon();
	}

	public void start() {
		rawDataProcessing();
		preProcessing();
		classifier();
	}

	public void rawDataProcessing() {
		String rawDataProcess = Constant.rawDataProcess;
		if (rawDataProcess.equals("default")) {
			readTrainingDataPaperEmoticon(filepath, false);
		} else if (rawDataProcess.equals("improve")) {
			System.out.println("improve");
			readTrainingDataImprovedEmoticon(filepath, false);
		} else if (rawDataProcess.equals("lexicon")) {
			createLexiconMap();
			readingTrainingData(filepath);
		}
	}

	public void preProcessing() {
		rmvStopwords = new RemoveStopwords();
		rmvStopwords.createStopwordsBank();

		rmvPun = new RemovePunctuation();
		ng = new Negtation();
		for (int i = Constant.trainingData.size() - 1; i >= 0; i--) {
			String content = Constant.trainingData.get(i).content;
			String newContent = content;

			// stop words
			if (Constant.hasStopword) {
				newContent = rmvStopwords.getLineWithNoStopwords(content);
				
			}
			// punctuation
			if (Constant.removePunAndNum) {
//				newContent = rmvPun.rmNum(newContent);
//				newContent = rmvPun.rmPunct(newContent);
				newContent = rmvPun.rmPunctAndNum(newContent);
			}

			if (Constant.stem) {
				newContent = ti.tokenize(newContent);
			}

			if (Constant.removeUnMeaning) {

			}

			if (Constant.negation) {
				newContent = ng.mergeDont(newContent);
			}
			// update
			Constant.trainingData.get(i).content = newContent;
			//System.out.println(newContent);
			// remove if white space
			boolean isWhitespace = newContent.matches("^\\s*$");
			if (isWhitespace || Constant.trainingData.get(i).polarity.equals("none")) {
				Constant.trainingData.remove(i);
			}
			String line = Constant.trainingData.get(i).content;

			if (Constant.rawDataProcess.equals("lexicon")) {
				String polarity = cl.getLinePolarity(line);
				if (polarity.equals("ignore")) {
					Constant.trainingData.remove(i);
				} else {
					Constant.trainingData.get(i).polarity = polarity;
				}
			}
		}
		System.out.println(Constant.trainingData.size());
		System.out.println(CreateLexiconMap.count);
	}

	public void classifier() {
		Operate o = new Operate();
		
		if (Constant.classifier.equals("navie")) {

		} else if (Constant.classifier.equals("me")) {
			if (Constant.classifierFeature.equals("uni")) {
				o.unigram();
				
			} else if (Constant.classifierFeature.equals("bi")) {
				o.bigram();
			} else if (Constant.classifierFeature.equals("unibi")) {
				o.unibigram();
			} else if (Constant.classifierFeature.equals("unipo")) {
				o.unipos();
			} else if (Constant.classifierFeature.equals("no")) {
				o.MEclassifier();
			}
		}
	}

	public void createNgram(int n) {
		crtNgram = new CreateNgram();
		// ngram
		// n gram with val
		Map<String, Integer> unigramVal = crtNgram.getNgramMapWithValue(
				Constant.trainingData, FieldType.four, 1);

		// n gram with polarity
		Map<String, Integer> unigramPol = crtNgram.getNgramMapWithPolarity(
				Constant.trainingData, FieldType.four, 1);
	}

	public void readTrainingDataPaperEmoticon(String path, boolean hasEmoji) {
		pm.execute(path, hasEmoji);
	}

	public void readTrainingDataImprovedEmoticon(String path, boolean hasEmoji) {
		ie.execute(path, hasEmoji);
	}
	
	public void readingTrainingData(String path){
		ReadFileService.getServ().readTrainingData(path, FieldType.four);
	}

	public void createLexiconMap() {
		cl.execute();
	}
}
