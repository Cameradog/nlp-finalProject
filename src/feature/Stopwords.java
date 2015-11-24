package feature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Stopwords {
	
	public static void main(String[] args) throws IOException{//也是測試用，應該可刪除
		Stopwords s = new Stopwords();
		s.stopword();
	}
	
	public String stopword() throws IOException{
		//讀取stopword.txt製作成hashtable
		BufferedReader br = new BufferedReader(new FileReader("english.stop.txt"));
		Hashtable stopwords = new Hashtable();
		String word;
		while((word = br.readLine()) != null){
			stopwords.put(word, 0);
		}
		
		String line = "Hello, there are many apples.";//測資
		String newline = "";
		String[] token = line.toLowerCase().split(" ");//轉換成小寫後，進行切割，一樣也只切空白，所以可能導致和符號相連的沒辦法過濾
		for(String e1: token){
			if(!stopwords.containsKey(e1)){
				newline += e1 + " ";
			}
		}
		System.out.print(newline);
		
		return newline;
	}
}
