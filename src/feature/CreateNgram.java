package feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Constant;
import data.Field;
import data.FieldType;
import data.FourField;

public class CreateNgram {
	String space = " ";

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getNgramMapWithValue(ArrayList<?> datas, FieldType f,
			int N) {
		if (f == FieldType.four) {
			return getNgramMapWithValue((ArrayList<FourField>) datas, N);
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
					//System.out.println("Str: " + str + " val: "
					//		+ dataMap.get(str));
				} else {
					dataMap.put(str, dataMap.get(str) + 1);
					//System.out.println("Str: " + str + " val: "
					//		+ dataMap.get(str));
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
	/*
	int[] array = new int[3];
	if(str.equals("pos")){
		array[0] +=1;
	} else if(str.equals("neu")){
		array[1] +=1;
	} else{
		array[2]+=1;
	}
	
	for(int i = 0 ; i< array.length;i++){
		if(array[0] == array[1]  && array[1]== array[2]){
			
		} else{
			if(array[0] > array[1] && array[0] > array[2]){
				
			}
		}
	}*/
}
