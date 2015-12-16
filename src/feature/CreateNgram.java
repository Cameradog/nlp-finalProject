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

	// return map
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getNgramMap(ArrayList<?> datas, FieldType f,
			int N) {
		if (f == FieldType.four) {
			return getNgramMap((ArrayList<FourField>) datas, N);
		} else {
			return null;
		}
	}

	public Map<String, Integer> getNgramMap(ArrayList<FourField> datas, int N) {
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
					System.out.println("Str: " + str + " val: "
							+ dataMap.get(str));
				} else {
					dataMap.put(str, dataMap.get(str) + 1);
					System.out.println("Str: " + str + " val: "
							+ dataMap.get(str));
				}
			}

		}
		return dataMap;
	}
}
