package feature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import common.Constant;

import fileService.ReadFileService;

public class Stopwords {
	static String testingSentence = "Hello, there are many apples.";
	
	//for testing 
	public static void main(String[] args) throws IOException{
		//Stopwords s = new Stopwords();
		//s.createStopwordsBank();
		//s.removeStopword(testingSentence);
	}
	
	public void createStopwordsBank(){
		ReadFileService.getServ().readStopWordFile("resource/english.stop.txt");
	}
	
	public String getRemovedStopword(String line) throws IOException{			
		String newline = "";
		String[] token = line.toLowerCase().split(" ");
		for(String e1: token){
			//has appear
			if(!Constant.stopwords.containsKey(e1)){
				newline += e1 + " ";
			}
		}
		return newline;
	}
}
