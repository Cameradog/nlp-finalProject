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
import feature.RemoveEmoji;
import twitter4j.Place;

public class Paper_emoticon {
	FileReader file,fileN;
	BufferedReader reader,readerN;
	Matcher matcher;
	Pattern pattern;
	RemoveEmoji em;
	int mode = 1;
	int exportCounter = 0;
	// 1M/20000
	// 2M/46000
	int rawDataSize = 1000000;
	int neutralDataSize = 0;
	int posC = 0;
	int neuC = 0;
	int negC = 0;
	int noneC = 0;
	
	String hashTag = "";
	String content = "";
	String polarity = "";
	int count =0;
	
	//paper defined emojticons
	String oriPosPole = "🙂|😃|☺";
	String oriNegPole = "☹|🙁|😢";
	
	public Paper_emoticon(){
		em = new RemoveEmoji();
	}
	
	public void execute(String path, boolean hasEmoji){
		readFile(path);
		extract(hasEmoji);
		addNeuTralData();
	}
	
	public void readFile(String path) {
		try {
			file = new FileReader(path);
			reader = new BufferedReader(file);
			fileN = new FileReader("resource/twitterFile/neutral_RAW.txt");
			readerN = new BufferedReader(fileN);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//extract original paper definition data from raw data
	public void extract(boolean hasEmoji){
		String line;
		int count = 0;
		System.out.println("emoji extraction...");
		try {	
			while((line = reader.readLine()) != null){
				if(count == rawDataSize){
					break;
				}
				
				hashTag = line;
				content = reader.readLine();
				reader.readLine();
				reader.readLine();
				polarity = mode_origin();	

				if(!polarity.equals("none") && hasEmoji){
					boolean isWhitespace = content.matches("^\\s*$");
					if(!isWhitespace){	
						addData(hashTag,content,polarity);
					}
				}
				
				if(!polarity.equals("none") && !hasEmoji){
					content =em.rmEmoji(content);
					boolean isWhitespace = content.matches("^\\s*$");
					if(!isWhitespace){
						addData(hashTag,content,polarity);
					}
				}
				count++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void addNeuTralData(){
		String line;
		int counter = 0;
		int ranNum = (int) Math.random()*0 +1;
		int dataSize = ranNum*neutralDataSize;
		boolean isWhitespace;
		System.out.println("add neutral raw data...");
		try {
			while((line = readerN.readLine()) != null){
				if(counter == dataSize){
					break;
				}
				
				if(counter%ranNum == 0){
					hashTag = line;
					content = readerN.readLine();
					readerN.readLine();
					readerN.readLine();
					polarity = "neu";
					isWhitespace = content.matches("^\\s*$");
					if(!isWhitespace){	
						addData(hashTag, content, polarity);
					}
					counter++;
				}
				else{
					readerN.readLine();
					readerN.readLine();
					readerN.readLine();
					counter++;
				}
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
}
