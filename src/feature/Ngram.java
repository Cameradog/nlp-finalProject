package feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Constant;

public class Ngram {
	int N = Constant.N;
	String  space =" ";
	
	//return map
	public Map<String, Integer> createNgram(ArrayList<?> datas){
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		for(int i = 0 ; i < datas.size()-N ;i++ ){
			
			String str="";
			for(int j = 1 ; j < N ; j++){
				
				if(j==0){
					str += datas.get(i);
				}
				else if (j > 0){
					str += space +datas.get(i);
				}
			}
			
			if(dataMap.get(str) == null ){
				dataMap.put(str, 1);
			}else{
				dataMap.put(str, dataMap.get(str)+1);
			}
		}
		return dataMap;
	}
}
