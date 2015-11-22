package feature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Stopwords {
	public String stopword() throws IOException{
		//make stopwords dictionary.
		BufferedReader br = new BufferedReader(new FileReader("english.stop.txt"));
		Hashtable stopwords = new Hashtable();
		String word;
		while((word = br.readLine()) != null){
			stopwords.put(word, 0);
		}
		
		String line = "Hello, there are many apples.";
		String newline = "";
		String[] token = line.split(" ");
		for(String e1: token){
			if(!stopwords.containsKey(e1)){
				newline += e1 + " ";
			}
		}
		
		return newline;
	}
}
