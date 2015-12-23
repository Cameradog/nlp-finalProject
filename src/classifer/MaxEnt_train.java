package classifer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import common.Constant;
import data.FourField;
import feature.CreateNgram;
import opennlp.maxent.BasicEventStream;
import opennlp.maxent.GIS;
import opennlp.maxent.PlainTextByLineDataStream;
import opennlp.maxent.io.GISModelWriter;
import opennlp.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.model.AbstractModel;
import opennlp.model.EventStream;
import opennlp.model.OnePassRealValueDataIndexer;

public class MaxEnt_train {
	public void output_train(int N){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("tweetforMaxent.txt"));
			ArrayList<FourField> trainingData = Constant.trainingData;
			for(int i = 0; i < trainingData.size() ; i++ ){
				String content = trainingData.get(i).content;
				String label = trainingData.get(i).polarity;
				String[] words = content.split(" ");
				
				if(N == 3){//unigram + bigram
					for(int j = 0; j < words.length; j++){
						bw.write(words[j] + " " + label);//unigram
						bw.newLine();
						
						if(j == words.length - 1) break;
						bw.write(words[j] + " " + words[j+1]);
						bw.newLine();
					}
				}else if(N != 0 && words.length >= N){
					for(int j = 0; j < words.length; j++){//get ngram
						String ngram = "";
						if(j == words.length - N + 1) break;
						for(int k = j; k < N + j; k++){
							ngram += words[k] + " ";
						}
						bw.write(ngram.trim() + " " + label);
						bw.newLine();
					}	
				} else {
					bw.write(content.trim());
					bw.write(" " + label);
					bw.newLine();	
				}
				System.out.println(content);
			}
			bw.close();
		}catch(IOException e){
			//;
		}
		
	}
	
	public static boolean USE_SMOOTHING = false;
	public static double SMOOTHING_OBSERVATION = 0.1;
	public void training(){
		String dataFileName = new String("tweetforMaxent.txt");
		String modelFileName = dataFileName.substring(0, dataFileName.lastIndexOf('.')) + "Model.txt";
		try{
			FileReader datafr = new FileReader(new File(dataFileName));
			EventStream es = new BasicEventStream(new PlainTextByLineDataStream(datafr));
	
			GIS.SMOOTHING_OBSERVATION = SMOOTHING_OBSERVATION;
			AbstractModel model = GIS.trainModel(100, new OnePassRealValueDataIndexer(es, 0), USE_SMOOTHING); // 第一個參數是iterations數
	
			File outputFile = new File(modelFileName);
			GISModelWriter writer = new SuffixSensitiveGISModelWriter(model, outputFile);
			writer.persist();
		}catch(IOException e) {
			//;
		}
	}
}
