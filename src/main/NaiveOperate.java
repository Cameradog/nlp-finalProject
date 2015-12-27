package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.NClob;
import java.util.HashMap;

import classifer.BigramNaive;
import feature.CreateNgram;
import common.Constant;

public class NaiveOperate {
	FileReader file;
	BufferedReader reader;
	BigramNaive bn;
	CreateNgram cn;
	PrintWriter output;
	HashMap<String, Integer> uniPos = new HashMap<String,Integer>();
	HashMap<String, Integer> uniNeu = new HashMap<String,Integer>();
	HashMap<String, Integer> uniNeg = new HashMap<String,Integer>();
	HashMap<String, Integer> biPos = new HashMap<String,Integer>();
	HashMap<String, Integer> biNeu = new HashMap<String,Integer>();
	HashMap<String, Integer> biNeg = new HashMap<String,Integer>();
	HashMap<String, Integer> ptPos = new HashMap<String,Integer>();
	HashMap<String, Integer> ptNeu = new HashMap<String,Integer>();
	HashMap<String, Integer> ptNeg = new HashMap<String,Integer>();
	
	String filename = "predict_naive"; //output file name
	String testFilePath = "test_naive.txt"; //test file name
	double compareRight = 0;
	double compareWrong = 0;
	
	public NaiveOperate(){
		cn = new CreateNgram();
		bn = new BigramNaive();
		readFile();
		output();
//		getUniMap();
//		getBiMap();
//		getPosTagMap();
	}
	

//	public static void main(String[] args){
//		setting();
//		new Main().start();
//		
////		System.out.println(Constant.trainingData);
//		NaiveOperate n = new NaiveOperate();
//		try {
//			n.unigramTest();
//			n.output.close();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void setting(){
//		Constant.FilePath = "resource/twitterFile/tweet_RAW_3M.txt";
//		//default, lexicon, improve
//		Constant.rawDataProcess="default";
//			
//		Constant.removePunAndNum = true;
//		Constant.removeUnMeaning = true;
//		Constant.stem = true;
//		Constant.negation = true;
//		Constant.hasStopword = true;
		
		//navie, me
//		Constant.classifier="navie";
		
		//uni, bi, unibi, unipo ,no
//		Constant.classifierFeature="uni"; 
	}
	
	public void unigramTest() throws IOException{
		getUniMap();
		getNaiveResult("uni");
	}
	
	public void bigramTest() throws IOException{
		getUniMap();
		getBiMap();
		getNaiveResult("bi");
	}
	
	public void uniposTest() throws IOException{
		getUniMap();
		getPosTagMap();
		getNaiveResult("unipo");
	}
	
	public void improveUniposTest() throws IOException{
		getUniMap();
		getPosTagMap();
		getNaiveResult("improveUnipo");
	}
	
	public void readFile(){
		try {
			file = new FileReader("resource/testdata/"+testFilePath);
			reader = new BufferedReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getNaiveResult(String classifier) throws IOException{
		String content = "";
		String returnPole = "";
		compareRight = 0;
		compareWrong	 = 0;
		while((content = reader.readLine()) != null){
			if(classifier.equals("uni")){
				bn.setTestString(content);
				bn.unigramTraining(uniPos, uniNeu, uniNeg);
				System.out.println(content);
				returnPole = bn.getUniResult();
				System.out.println(bn.getUniResult());
				output.print(content+" ");
				output.println(bn.getUniResult());
			}
			else if(classifier.equals("bi")){
				bn.setTestString(content);
				bn.biigramTraining(uniPos, uniNeu, uniNeg, biPos, biNeu, biNeg);
				System.out.println(content);
				returnPole = bn.getBiResult();
				System.out.println(bn.getBiResult());
				output.print(content+" ");
				output.println(bn.getBiResult());
			}
			else if(classifier.equals("unipo")){
				bn.setTestString(content);
				bn.unigramTraining(uniPos, uniNeu, uniNeg);
				bn.posunigramTraining(ptPos, ptNeu, ptNeg);
				System.out.println(content);
				returnPole = bn.getUniposResult();
				System.out.println(bn.getUniposResult());
				output.print(content+" ");
				output.println(bn.getUniposResult());
			}
			else if(classifier.equals("improveUnipo")){
				bn.setTestString(content);
				bn.unigramTraining(uniPos, uniNeu, uniNeg);
				bn.posunigramTraining(ptPos, ptNeu, ptNeg);
				bn.loglikely();
				System.out.println(content);
				returnPole = bn.getImproveUniposResult();
				System.out.println(bn.getImproveUniposResult());
				output.print(content+" ");
				output.println(bn.getImproveUniposResult());
			}

			if(bn.getHandLabel().equals(returnPole)){
				compareRight++;
				System.out.println(compareRight);
			}
			else if(!bn.getHandLabel().equals(returnPole)){
				compareWrong++;
				System.out.println(compareWrong);
			}
		}
		System.out.println("Correct Ans Rate: "+(compareRight/(compareRight+compareWrong)*100)+"%");
	}
	
	public void getUniMap(){
		cn.getNgramMapWithValue(Constant.trainingData , 1, "pos");
		cn.getNgramMapWithValue(Constant.trainingData , 1, "neu");
		cn.getNgramMapWithValue(Constant.trainingData , 1, "neg");
//		cn.ngramResampling();
		uniPos = (HashMap<String, Integer>) cn.getNgramMap("pos");
		uniNeu = (HashMap<String, Integer>) cn.getNgramMap("neu");
		uniNeg = (HashMap<String, Integer>) cn.getNgramMap("neg");
	}
	
	public void getBiMap(){
		cn.getNgramMapWithValue(Constant.trainingData , 2, "pos");
		cn.getNgramMapWithValue(Constant.trainingData , 2, "neu");
		cn.getNgramMapWithValue(Constant.trainingData , 2, "neg");
//		cn.ngramResampling();
		biPos = (HashMap<String, Integer>) cn.getNgramMap("pos");
		biNeu = (HashMap<String, Integer>) cn.getNgramMap("neu");
		biNeg = (HashMap<String, Integer>) cn.getNgramMap("neg");
	}
	
	public void getPosTagMap(){
		ptPos = (HashMap<String, Integer>) cn.getNgramMapWithPosTag(Constant.trainingData , 1, "pos");
		ptNeu = (HashMap<String, Integer>) cn.getNgramMapWithPosTag(Constant.trainingData , 1, "neu");
		ptNeg = (HashMap<String, Integer>) cn.getNgramMapWithPosTag(Constant.trainingData , 1, "neg");
	}
	
	public void output(){
		try {
			output = new PrintWriter(new BufferedWriter(new FileWriter("resource/testdata/"+filename+".txt",false)));
			output.print("");
			output = new PrintWriter(new BufferedWriter(new FileWriter("resource/testdata/"+filename+".txt",true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
