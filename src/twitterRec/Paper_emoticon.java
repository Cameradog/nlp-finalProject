package twitterRec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Constant;
import data.FourField;
import twitter4j.Place;

public class Paper_emoticon {
	static PrintWriter output,output_with_emoji;
	FileReader file;
	BufferedReader reader;
	Matcher matcher;
	Pattern pattern;
	int mode = 1;
	int exportCounter = 0;
	int posC = 0;
	int neuC = 0;
	int negC = 0;
	int noneC = 0;
	String fileDir = "tweet_RAW.txt";
	String filename = "tweet_paper_origin";
	
	String hashTag = "";
	String content = "";
	String polarity = "";
	int count =0;
	
	//paper defined emojticons
	String oriPosPole = "🙂|😃|☺";
	String oriNegPole = "☹|🙁|😢";
	
//	public static void main(String[] args){
//		Paper_emoticon m = new Paper_emoticon();
//
//
//	}
	
	public Paper_emoticon(){

		//System.out.println("Export: "+m.exportCounter);
		//System.out.println("Pos: "+m.posC+"\nNeu: "+m.neuC+"\nNeg: "+m.negC+"\nNone: "+m.noneC);
	}
	
	public void execute(String path, boolean hasEmoji){
		output();
		output_with_emoji();
		readFile(path);
		extract(hasEmoji);
		System.out.println(count);
		output.close();
		output_with_emoji.close();
	}
	
	public void readFile(String path) {
		try {
			file = new FileReader(path);
			reader = new BufferedReader(file);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//extract original paper definition data from raw data
	public void extract(boolean hasEmoji){
		String line;
		try {	
			while((line = reader.readLine()) != null){
				hashTag = line;
				content = reader.readLine();
				reader.readLine();
				reader.readLine();
				polarity = mode_origin();	
				System.out.println(content);
				if(!polarity.equals("none")){
					System.out.println(88);
				}
				if(!polarity.equals("none") && hasEmoji){
					System.out.println("84");
					if(!content.equals("")){			
						addData(hashTag,content,polarity);
					}
//					output_with_emoji.println(hashTag);
//					output_with_emoji.println(content);
//					output_with_emoji.println(polarity);
//					output_with_emoji.println("***");
				}
				
				if(!polarity.equals("none") && !hasEmoji){
					System.out.println("99");
					if(!content.equals("")){
						addData(hashTag,content,polarity);
					}
//					output.println(hashTag);
//					output.println(content);
//					output.println(polarity);
////					output.println();
//					output.println("***");
					exportCounter++;
				}
				System.out.println(exportCounter);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void addData(String hashTag , String content, String polarity){
		FourField f = new FourField();
		ArrayList<String> hashTagALi = new ArrayList<String>();
		for(int i = 0 ; i < hashTag.split("\\s").length; i++){
			hashTagALi.add(hashTag.split("\\s")[i]);
		}
		f.hashTag = hashTagALi;
		f.content = this.content;
		f.polarity = this.polarity;
		Constant.trainingData.add(f);
		
	}
	//original paper definition
	public String mode_origin(){
		int poleCount = 0;
		pattern = Pattern.compile(oriPosPole);
		matcher = pattern.matcher(content);
		if(matcher.find()){
			poleCount++;
		}
		
		pattern = Pattern.compile(oriNegPole);
		matcher = pattern.matcher(content);
		if(matcher.find()){
			poleCount--;
		}
		
		if(poleCount==0){
			noneC++;
			return "none";
		}
		else if(poleCount>0){
			posC++;
			return "pos";
		}
		else if(poleCount<0){
			negC++;
			return "neg";
		}
		noneC++;
		return "none";
	}
	
	//output file
	public void output(){
		try {
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".txt",false)));
			output.print("");
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".txt",true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void output_with_emoji(){
		try {
			output_with_emoji = new PrintWriter(new BufferedWriter(new FileWriter(filename+"_with_emoji.txt",false)));
			output_with_emoji.print("");
			output_with_emoji = new PrintWriter(new BufferedWriter(new FileWriter(filename+"_with_emoji.txt",true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
