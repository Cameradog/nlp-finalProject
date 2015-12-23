package main;

import classifer.MaxEnt_predict;
import classifer.MaxEnt_train;

public class Operate {
	MaxEnt_train mt;
	MaxEnt_predict mp;
	public void MEclassifier(){
		//training 
		mt = new MaxEnt_train();
		mt.output_train(0);//input num for Ngram, 0 for not doing Ngram
		mt.training();
				
		//predict
		mp = new MaxEnt_predict();
		mp.predict(0);//input num for Ngram, 0 for not doing Ngram
	}
	
	public void unigram(){
		mt = new MaxEnt_train();
		mt.output_train(1);
		mt.training();
				
		mp = new MaxEnt_predict();
		mp.predict(1);
	}
	
	public void bigram(){
		mt = new MaxEnt_train();
		mt.output_train(2);
		mt.training();
				
		mp = new MaxEnt_predict();
		mp.predict(2);
	}
	
	public void unibigram(){
		mt = new MaxEnt_train();
		mt.output_train(3);
		mt.training();
				
		mp = new MaxEnt_predict();
		mp.predict(3);
	}
	
	public void unipos(){
		mt = new MaxEnt_train();
		mt.output_train(4);
		mt.training();
				
		mp = new MaxEnt_predict();
		mp.predict(4);
	}
}
