package classifer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Map;

import feature.CreateNgram;
import opennlp.maxent.BasicContextGenerator;
import opennlp.maxent.ContextGenerator;
import opennlp.model.GenericModelReader;
import opennlp.model.MaxentModel;

public class MaxEnt_predict {
	CreateNgram crtNgram;
	static MaxentModel _model;
	ContextGenerator _cg = new BasicContextGenerator();

	public MaxEnt_predict(){
		
	}
	public MaxEnt_predict(MaxentModel m) {
		_model = m;
	}
	
	public void predict(int N){
		//這裡改test資料，和model檔名
		String dataFileName = "test.txt", modelFileName = "tweetforMaxentModel.txt";

		MaxEnt_predict predictor = null;
		try {
			MaxentModel m = new GenericModelReader(new File(modelFileName)).getModel();
			predictor = new MaxEnt_predict(m);
			
			Hashtable<String, Integer> MapforNgram = new Hashtable<String, Integer>();
	        BufferedReader br = new BufferedReader(new FileReader(dataFileName));
	        while (br.ready()) {
				String line = br.readLine();
				MapforNgram.put(line, 0);
				String[] words = line.split(" ");
				
				if(N != 0 && words.length >= N){//for ngram
					for(int i = 0; i < words.length; i++){//get ngram
						String ngram = "";
						if(i == words.length - N + 1) break;
						for(int j = i; j < N + i; j++){
							ngram += words[j] + " ";
						}
						double[] ocs = _model.eval(ngram.split(" ")); //ocs: 每一個instance的top-n結果的信心值
						//System.out.println("(" + _model.getBestOutcome(ocs) + ", "+ ocs[ocs.length-1] + ") " + ngram);	
						
						if(_model.getBestOutcome(ocs).equals("pos"))
							MapforNgram.put(line, MapforNgram.get(line) + 1);
						else if(_model.getBestOutcome(ocs).equals("neg"))
							MapforNgram.put(line, MapforNgram.get(line) - 1);
					}
				} else {//normal condition
					double[] ocs = _model.eval(words); //ocs: 每一個instance的top-n結果的信心值
					//System.out.println("(" + _model.getBestOutcome(ocs) + ", "+ ocs[ocs.length-1] + ") " + line);	
					
					if(_model.getBestOutcome(ocs).equals("pos"))
						MapforNgram.put(line, MapforNgram.get(line) + 1);
					else if(_model.getBestOutcome(ocs).equals("neg"))
						MapforNgram.put(line, MapforNgram.get(line) - 1);
				}
			}

			for(Map.Entry<String, Integer> entry : MapforNgram.entrySet()){
				System.out.print(entry.getKey() + " " );
				if(entry.getValue() > 0) 
					System.out.println("pos");
				else if(entry.getValue() < 0) 
					System.out.println("neg");
				else if(entry.getValue() == 0) 
					System.out.println("neu");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
