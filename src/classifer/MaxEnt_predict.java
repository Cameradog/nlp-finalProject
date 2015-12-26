package classifer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Map;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import feature.CreateNgram;
import feature.RemovePunctuation;
import feature.RemoveStopwords;
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
		String dataFileName = "resource/testdata/test.txt", modelFileName = "resource/testdata/tweetforMaxentModel.txt";
		RemoveStopwords rs = new RemoveStopwords();
		RemovePunctuation rp = new RemovePunctuation();
		MaxentTagger tagger;
		MaxEnt_predict predictor = null;
		try {
			tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
			MaxentModel m = new GenericModelReader(new File(modelFileName)).getModel();
			predictor = new MaxEnt_predict(m);
			
			Hashtable<String, Integer> MapforNgram = new Hashtable<String, Integer>();
	        BufferedReader br = new BufferedReader(new FileReader(dataFileName));
	        while (br.ready()) {
				String line = br.readLine();
				line =rs.getLineWithNoStopwords(line);
				line = rp.rmPunctAndNum(line);
							
				
				MapforNgram.put(line, 0);
				String[] words = line.split(" ");
				if(N == 4){//uni + pos
					for(int i = 0; i < words.length; i++){
						String tag = tagger.tagTokenizedString(words[i]);
						String unipos = words[i] + " " + tag;
						double[] uiocs = _model.eval(unipos.split(" ")); //ocs: 每一個instance的top-n結果的信心值
						
						if(_model.getBestOutcome(uiocs).equals("pos"))
							MapforNgram.put(line, MapforNgram.get(line) + 1);
						else if(_model.getBestOutcome(uiocs).equals("neg"))
							MapforNgram.put(line, MapforNgram.get(line) - 1);
					}
				}else if(N == 3){//uni + bi
					for(int i = 0; i < words.length; i++){
						String unigram = words[i] + " ";
						double[] uiocs = _model.eval(unigram.split(" ")); //ocs: 每一個instance的top-n結果的信心值
						
						if(_model.getBestOutcome(uiocs).equals("pos"))
							MapforNgram.put(line, MapforNgram.get(line) + 1);
						else if(_model.getBestOutcome(uiocs).equals("neg"))
							MapforNgram.put(line, MapforNgram.get(line) - 1);
						
						if(i == words.length - 1) break;
						String ngram = words[i] + " " + words[i+1];
						
						double[] ocs = _model.eval(ngram.split(" ")); //ocs: 每一個instance的top-n結果的信心值
						
						if(_model.getBestOutcome(ocs).equals("pos"))
							MapforNgram.put(line, MapforNgram.get(line) + 1);
						else if(_model.getBestOutcome(ocs).equals("neg"))
							MapforNgram.put(line, MapforNgram.get(line) - 1);
					}
					for(int i = 0; i < words.length; i++){//get ngram
						String ngram = "";
						if(i == words.length) break;
						for(int j = i; j < 1 + i; j++){
							ngram += words[j] + " ";
						}
						double[] ocs = _model.eval(ngram.split(" ")); //ocs: 每一個instance的top-n結果的信心值
						//System.out.println("(" + _model.getBestOutcome(ocs) + ", "+ ocs[ocs.length-1] + ") " + ngram);	
						
						if(_model.getBestOutcome(ocs).equals("pos"))
							MapforNgram.put(line, MapforNgram.get(line) + 1);
						else if(_model.getBestOutcome(ocs).equals("neg"))
							MapforNgram.put(line, MapforNgram.get(line) - 1);
					}
					
				}else if(N != 0 && words.length >= N){//for ngram
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
			
					if(_model.getBestOutcome(ocs).equals("pos"))
						MapforNgram.put(line, MapforNgram.get(line) + 1);
					else if(_model.getBestOutcome(ocs).equals("neg"))
						MapforNgram.put(line, MapforNgram.get(line) - 1);
				}
			}

	        BufferedWriter bw = new BufferedWriter(new FileWriter("resource/testdata/predict_ans.txt"));
			for(Map.Entry<String, Integer> entry : MapforNgram.entrySet()){
				bw.write(entry.getKey() + " ");
				//System.out.print(entry.getKey() + " " );
				if(entry.getValue() > 0) 
					bw.write("pos");//System.out.println("pos");
				else if(entry.getValue() < 0) 
					bw.write("neg");//System.out.println("neg");
				else if(entry.getValue() == 0) 
					bw.write("neu");//System.out.println("neu");
				bw.newLine();
			}
			System.out.println("Finish!");
			bw.close();

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
