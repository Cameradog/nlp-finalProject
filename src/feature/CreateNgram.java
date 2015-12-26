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
				} else {
					dataMap.put(str, dataMap.get(str) + 1);
				}
			}

		}
		return dataMap;
	}
	
	public Map<String,Integer> getNgramMapWithPolarity(ArrayList<FourField> datas, int N){
		Map<String, Integer> polarityMap = new HashMap<String, Integer>();
		for (int i = 0; i < datas.size(); i++) {
			String polarity = datas.get(i).polarity;
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

				if (polarityMap.get(str) == null && !polarity.equals("none")) {
					if(polarity.equals("pos")){
						polarityMap.put(str, 1);
					} else if(polarity.equals("neg")){
						polarityMap.put(str, -1);
					} else if(polarity.equals("neu")){
					    polarityMap.put(str, 0);
					}
				} else if(polarityMap.get(str)!=null && !polarity.equals("none")){
					if(polarity.equals("pos")){
						polarityMap.put(str, polarityMap.get(str) + 1);
					} else if(polarity.equals("neg")){
						polarityMap.put(str, polarityMap.get(str) -1);
					} else if(polarity.equals("neu")){
					    polarityMap.put(str, polarityMap.get(str) + 0);
					}
				}
			}

		}
		return polarityMap;
	}
}
