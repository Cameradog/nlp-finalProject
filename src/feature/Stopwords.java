package feature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Stopwords {
	
	public static void main(String[] args) throws IOException{//�]�O���եΡA���ӥi�R��
		Stopwords s = new Stopwords();
		s.stopword();
	}
	
	public String stopword() throws IOException{
		//Ū��stopword.txt�s�@��hashtable
		BufferedReader br = new BufferedReader(new FileReader("english.stop.txt"));
		Hashtable stopwords = new Hashtable();
		String word;
		while((word = br.readLine()) != null){
			stopwords.put(word, 0);
		}
		
		String line = "Hello, there are many apples.";//����
		String newline = "";
		String[] token = line.toLowerCase().split(" ");//�ഫ���p�g��A�i����ΡA�@�ˤ]�u���ťաA�ҥH�i��ɭP�M�Ÿ��۳s���S��k�L�o
		for(String e1: token){
			if(!stopwords.containsKey(e1)){
				newline += e1 + " ";
			}
		}
		System.out.print(newline);
		
		return newline;
	}
}
