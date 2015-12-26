package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fmeasure {

	//public static void main(String[] args) throws IOException {
		
	//}
	
	public void compare() throws IOException{
		String[] labels = new String[]{"pos", "neg", "neu"};
		for(int i = 0; i < 3 ; i++){
			BufferedReader br_pre_ans = new BufferedReader(new FileReader("resource/testdata/predict_ans.txt"));
			BufferedReader br_ans = new BufferedReader(new FileReader("resource/testdata/ans.txt"));
			int true_pos = 0, true_neg = 0;
			int false_pos = 0, false_neg = 0;
			double P = 0, R = 0, F1 = 0;
			
			while(br_ans.ready()){
				String pre_ans = br_pre_ans.readLine().replaceAll(".* ", "");
				String ans = br_ans.readLine().replaceAll(".* ", "");
				
				if(labels[i].equals(ans) && labels[i].equals(pre_ans))
					true_pos++;
				else if(labels[i].equals(ans) && !labels[i].equals(pre_ans))
					false_neg++;
				else if(!labels[i].equals(ans) && labels[i].equals(pre_ans))
					false_pos++;
				else if(!labels[i].equals(ans) && !labels[i].equals(pre_ans))
					true_neg++;
				else
					System.out.println("Miss something!");				
			}
			
			P = (double)true_pos / (true_pos + false_pos);
			R = (double)true_pos / (true_pos + false_neg);
			F1 = 2*P*R / (P+R);
			
			System.out.println(labels[i] + " F1-measure: " + F1);
			
			br_pre_ans.close();
			br_ans.close();		
		}
	}
}
