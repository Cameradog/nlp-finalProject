package feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classifer.BigramNaive;
import common.Constant;
import data.Field;
import data.FieldType;
import data.FourField;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class CreateNgram {
	String space = " ";
	BigramNaive bn;
	MaxentTagger tagger;
	Map<String, Integer> posMap;
	Map<String, Integer> neuMap;
	Map<String, Integer> negMap;
	
	public CreateNgram() {
		posMap = new HashMap<String, Integer>();
		neuMap = new HashMap<String, Integer>();
		negMap = new HashMap<String, Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getNgramMapWithValue(ArrayList<?> datas, FieldType f,
			int N, String polarity) {
		if (f == FieldType.four) {
			return getNgramMapWithValue((ArrayList<FourField>) datas, N, polarity);
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getNgramMapWithPolarity(ArrayList<?> datas, FieldType f,
			int N) {
		if (f == FieldType.four) {
			return getNgramMapWithPolarity((ArrayList<FourField>) datas, N);
		} else {
			return null;
		}
	}
	
	public Map<String, Integer> getNgramMapWithValue(ArrayList<FourField> datas, int N,String polarity){
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		for (int i = 0; i < datas.size(); i++) {
			for (int k = 0; k < datas.get(i).content.split("\\s+").length - N+1; k++) {
				String str = "";
				for (int j = 1; j <= N; j++) {
					if (j == 1) {
						str += datas.get(i).content.split("\\s+")[k + j - 1];

					} else {
						str += space
								+ datas.get(i).content.split("\\s+")[k + j - 1];
						;
					}
				}
				if (str.equals("")) {
					continue;
				}
				
				String dataPolarity = datas.get(i).polarity;
				if (dataMap.get(str) == null) {
					if(dataPolarity.equals(polarity)){
						dataMap.put(str, 1);
					}
				} else {
					if(dataPolarity.equals(polarity)){
						dataMap.put(str, dataMap.get(str) + 1);
					}
				}
			}

		}
		if(polarity.equals("pos")){
			posMap = dataMap;
		}
		else if(polarity.equals("neu")){
			neuMap = dataMap;
		}
		else{
			negMap = dataMap;
		}
		return dataMap;
	}
	
	//hashmap for pos_tag polarity count
	public Map<String, Integer> getNgramMapWithPosTag(ArrayList<FourField> datas, int N,String polarity){
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		Map<String, Integer> posTagMap = new HashMap<String, Integer>();
		tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
		for (int i = 0; i < datas.size(); i++) {
			for (int k = 0; k < datas.get(i).content.split("\\s+").length - N+1; k++) {
				String str = "";
				String posStr = "";
				for (int j = 1; j <= N; j++) {
					if (j == 1) {
						str += datas.get(i).content.split("\\s+")[k + j - 1];

					} else {
						str += space
								+ datas.get(i).content.split("\\s+")[k + j - 1];
						;
					}
				}
				if (str.equals("")) {
					continue;
				}

				String dataPolarity = datas.get(i).polarity;
				if (dataMap.get(str) == null) {
					if(dataPolarity.equals(polarity)){
						dataMap.put(str, 1);
						
						posStr = posTagProcess(str);
						if(posTagMap.get(posStr) == null){
							posTagMap.put(posStr, 1);
						}
						else{
							posTagMap.put(posStr, posTagMap.get(posStr) + 1);
						}
					}
				} else {
					if(dataPolarity.equals(polarity)){
						dataMap.put(str, dataMap.get(str) + 1);
						
						posStr = posTagProcess(str);
						if(posTagMap.get(posStr) == null){
							posTagMap.put(posStr, 1);
						}
						else{
							posTagMap.put(posStr, posTagMap.get(posStr) + 1);
						}
					}
				}
			}

		}
		if(polarity.equals("pos")){
			posMap = posTagMap;
		}
		else if(polarity.equals("neu")){
			neuMap = posTagMap;
		}
		else{
			negMap = posTagMap;
		}
		return posTagMap;

	}

	public Map<String, Integer> getNgramMapWithValue(ArrayList<FourField> datas, int N) {
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		for (int i = 0; i < datas.size(); i++) {
			for (int k = 0; k < datas.get(i).content.split("\\s+").length - N+1; k++) {
				//System.out.println(datas.get(i).content);
				String str = "";
				for (int j = 1; j <= N; j++) {
					if (j == 1) {
						str += datas.get(i).content.split("\\s+")[k + j - 1];

					} else {
						str += space
								+ datas.get(i).content.split("\\s+")[k + j - 1];
						;
					}
				}
				if (str.equals("")) {
					continue;
				}

				
				if (dataMap.get(str) == null) {
					dataMap.put(str, 1);
//					System.out.println("Str: " + str + " val: "
//							+ dataMap.get(str));
				} else {
					dataMap.put(str, dataMap.get(str) + 1);
//					System.out.println("Str: " + str + " val: "
//							+ dataMap.get(str));
				}
			}

		}
		return dataMap;
	}
	
	public Map<String,Integer> getNgramMapWithPolarity(ArrayList<FourField> datas, int N){
		//Map<String, Integer> dataMap = new HashMap<String, Integer>();
		Map<String, Integer> polarityMap = new HashMap<String, Integer>();
		for (int i = 0; i < datas.size(); i++) {
			String polarity = datas.get(i).polarity;
			for (int k = 0; k < datas.get(i).content.split("\\s+").length - N+1; k++) {
				//System.out.println(datas.get(i).content);
				String str = "";
				for (int j = 1; j <= N; j++) {
					if (j == 1) {
						str += datas.get(i).content.split("\\s+")[k + j - 1];

					} else {
						str += space
								+ datas.get(i).content.split("\\s+")[k + j - 1];
						;
					}
				}
				if (str.equals("")) {
					continue;
				}

				if (polarityMap.get(str) == null && !polarity.equals("none")) {
					if(polarity.equals("pos")){
						polarityMap.put(str, 1);
					} else if(polarity.equals("neg")){
						polarityMap.put(str, -1);
					} else if(polarity.equals("neu")){
					    polarityMap.put(str, 0);
					}
					//System.out.println("Str: " + str +
					//		" "+ polarityMap.get(str));
				} else if(polarityMap.get(str)!=null && !polarity.equals("none")){
					//dataMap.put(str, dataMap.get(str) + 1);
					if(polarity.equals("pos")){
						polarityMap.put(str, polarityMap.get(str) + 1);
					} else if(polarity.equals("neg")){
						polarityMap.put(str, polarityMap.get(str) -1);
					} else if(polarity.equals("neu")){
					    polarityMap.put(str, polarityMap.get(str) + 0);
					}
					//System.out.println("Str: " + str +  " " + polarityMap.get(str));
				}
			}

		}
		return polarityMap;
		//return null;
	}
	
	public String posTagProcess(String word){
		String[] tag = tagger.tagTokenizedString(word).split("_");
	
		return tag[1];
	}
	
	public Map<String, Integer> getNgramMap(String polarity){
		if(polarity.equals("pos")){
			return posMap;
		}
		else if(polarity.equals("neu")){
			return neuMap;
		}
		else{
			return negMap;
		}
	}
	
	
	public void ngramResampling(){
		Map<String, Integer> tempMap = new HashMap<String, Integer>();
		int min = Math.min(posMap.size(), Math.min(neuMap.size(), negMap.size()));
		int counter = 0;
		if(min == negMap.size()){
			counter = posMap.size()-min;
			for(Object key: posMap.keySet()){
				tempMap.put(key.toString(),posMap.get(key));
				if(counter == 1){
					for(Object keyTemp: tempMap.keySet()){
						posMap.remove(keyTemp);
					}
					tempMap.clear();
					break;
				}
				else{
					counter--;
				}
			}
			counter = neuMap.size()-min;
			for(Object key: neuMap.keySet()){
				tempMap.put(key.toString(),neuMap.get(key));
				if(counter == 1){
					for(Object keyTemp: tempMap.keySet()){
						neuMap.remove(keyTemp);
					}
					tempMap.clear();
					break;
				}
				else{
					counter--;
				}
			}
		}
		else if(min == neuMap.size()){
			counter = posMap.size()-min;
			for(Object key: posMap.keySet()){
				tempMap.put(key.toString(),posMap.get(key));
				if(counter == 1){
					for(Object keyTemp: tempMap.keySet()){
						posMap.remove(keyTemp);
					}
					tempMap.clear();
					break;
				}
				else{
					counter--;
				}
			}
			counter = negMap.size()-min;
			for(Object key: negMap.keySet()){
				tempMap.put(key.toString(),negMap.get(key));
				if(counter == 1){
					for(Object keyTemp: tempMap.keySet()){
						negMap.remove(keyTemp);
					}
					tempMap.clear();
					break;
				}
				else{
					counter--;
				}
			}
		}
		else{
			counter = negMap.size()-min;
			for(Object key: negMap.keySet()){
				tempMap.put(key.toString(),negMap.get(key));
				if(counter == 1){
					for(Object keyTemp: tempMap.keySet()){
						negMap.remove(keyTemp);
					}
					tempMap.clear();
					break;
				}
				else{
					counter--;
				}
			}
			counter = neuMap.size()-min;
			for(Object key: neuMap.keySet()){
				tempMap.put(key.toString(),neuMap.get(key));
				if(counter == 1){
					for(Object keyTemp: tempMap.keySet()){
						neuMap.remove(keyTemp);
					}
					tempMap.clear();
					break;
				}
				else{
					counter--;
				}
			}
		}				
	}

}
