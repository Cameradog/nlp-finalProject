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
	FileReader file;
	BufferedReader reader;
	Matcher matcher;
	Pattern pattern;
	RemoveEmoji em;
	int mode = 1;
	int exportCounter = 0;
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
