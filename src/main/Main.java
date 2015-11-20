package main;

import data.FieldType;
import fileService.ReadFile;

public class Main {
	ReadFile r;
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		r = new ReadFile();
		start();
	}
	
	public void start(){
		readFile("resource/twitterFile/tweet.txt" , FieldType.four);
	}
	
	public void readFile(String path, FieldType t){
		r.readFile(path , t);
	}
}
