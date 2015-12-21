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
	public static void main(String[] args){
		//
	}	
	public void tweetout(){
		BufferedReader br;
		BufferedWriter bw;
		try{
			br = new BufferedReader(new FileReader("resource/twitterFile/tweet.txt"));
			bw = new BufferedWriter(new FileWriter("tweetforMaxent.txt"));
			ArrayList<FourField> trainingData = Constant.trainingData;
			for(int i = 0; i < trainingData.size() ; i++ ){
				String content = trainingData.get(i).content;
				String label = trainingData.get(i).polarity;
				bw.write(content.trim());
				bw.write(" " + label);
				bw.newLine();	
				System.out.println(content);
				
			}
			bw.close();
			br.close();
		//}
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
	
	public void ngram(){
		CreateNgram crtNgram = new CreateNgram();
		//Map crtNgram.getNgramMap(Constant.trainingData, 3);
	}
}
