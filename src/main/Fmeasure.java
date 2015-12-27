package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fmeasure {
	double rightCount=0;
	double allCount=0;
	
	public static void main(String[] args){
		new Fmeasure().compare("resource/testdata/predict_ans.txt" , "resource/testdata/ans.txt");
	}
	
	public void compare(String predictPath , String ansPath){
		String[] labels = new String[]{"pos", "neg", "neu"};
		for(int i = 0; i < 3 ; i++){
			BufferedReader br_pre_ans;
			BufferedReader br_ans;
			try {
				br_pre_ans = new BufferedReader(new FileReader(predictPath));
				br_ans = new BufferedReader(new FileReader(ansPath));
				int true_pos = 0, true_neg = 0;
				int false_pos = 0, false_neg = 0;
				double P = 0, R = 0, F1 = 0;
			
				while(br_ans.ready()){
					String ans_line =br_pre_ans.readLine();
					String pre_line =br_ans.readLine();
					String pre_ans = ans_line.replaceAll(".* ", "");
					String ans =pre_line.replaceAll(".* ", "");
					
					System.out.println(ans_line);
					if(pre_ans.equals(ans)){
						rightCount++;
					}
					allCount++;
					
					if(labels[i].equals(ans) && labels[i].equals(pre_ans)){
						true_pos++;
					}
					else if(labels[i].equals(ans) && !labels[i].equals(pre_ans)){
						false_neg++;
					}
					else if(!labels[i].equals(ans) && labels[i].equals(pre_ans)){
						false_pos++;		
					}
					else if(!labels[i].equals(ans) && !labels[i].equals(pre_ans)){
						true_neg++;
					}
					else
						System.out.println("Miss something!");				
				}
				
				P = (double)true_pos / (true_pos + false_pos);
				R = (double)true_pos / (true_pos + false_neg);
				F1 = 2*P*R / (P+R);
				
				System.out.println(labels[i] + " F1-measure: " + F1);
				br_pre_ans.close();
				br_ans.close();		

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(rightCount/3);
		System.out.println(allCount/3);
	}
}
