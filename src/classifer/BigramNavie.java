package classifer;

import java.util.HashMap;
import java.util.Set;

public class BigramNavie {

	public void execute() {
		String Sentiment = "";
		// TODO code application logic here
		/*
		 * abc pos adf sadf asdfas pos
		 */
		String s = "abcbaadfdgdeiivnielo";// positive
		String s2;
		String s3 = "abcdefghijklmn";// negative
		String s4;
		String s5 = "abcopqrtuvwxy";
		String s6;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();// positive
																			// unigram
																			// hashmap
		char c;
		for (int i = 0; i < s.length() - 1; i++) {
			c = s.charAt(i);
			if (map.get(c) == null) {
				map.put(c, 1);
			} else {
				Integer in = map.get(c);
				map.put(c, in.intValue() + 1);
			}
		}
		HashMap<Character, Integer> map3 = new HashMap<Character, Integer>();// negative
																				// unigram
																				// hashmap
		for (int i = 0; i < s3.length() - 1; i++) {
			c = s3.charAt(i);
			if (map3.get(c) == null) {
				map3.put(c, 1);
			} else {
				Integer in = map3.get(c);
				map3.put(c, in.intValue() + 1);
			}
		}

		HashMap<Character, Integer> map5 = new HashMap<Character, Integer>();// negative
																				// unigram
																				// hashmap
		for (int i = 0; i < s5.length(); i++) {
			c = s5.charAt(i);
			if (map5.get(c) == null) {
				map5.put(c, 1);
			} else {
				Integer in = map5.get(c);
				map5.put(c, in.intValue() + 1);
			}
			System.out.println(c + "$$  " + map5.get(c));
		}

		HashMap<String, Integer> map2 = new HashMap<String, Integer>();// positive
																		// bigram
																		// hashmap

		// char c;
		char[] charArray = s.toCharArray();
		for (int i = 0; i < charArray.length - 1; i++) {
			s2 = "";
			s2 += charArray[i];
			s2 += charArray[i + 1];
			if (map2.get(s2) == null) {
				map2.put(s2, 1);
			} else {
				Integer in = map2.get(s2);
				map2.put(s2, in.intValue() + 1);
			}
		}

		Set<String> set = map2.keySet();
		int count = 0;
		int V = 0;
		for (String ch : set) {
			System.out.println(ch + " " + map2.get(ch));
			count = count + map2.get(ch);
			V = V + 1;

		}
		// negative bigram hashmap
		HashMap<String, Integer> map4 = new HashMap<String, Integer>();// positive
																		// bigram
																		// hashmap

		// char c;
		char[] charArray2 = s3.toCharArray();
		for (int i = 0; i < charArray2.length - 1; i++) {
			s4 = "";
			s4 += charArray2[i];
			s4 += charArray2[i + 1];
			if (map4.get(s4) == null) {
				map4.put(s4, 1);
			} else {
				Integer in = map4.get(s4);
				map4.put(s4, in.intValue() + 1);
			}
		}

		Set<String> set2 = map4.keySet();
		int count2 = 0;
		int V2 = 0;
		for (String ch : set2) {
			System.out.println(ch + " " + map4.get(ch));
			count2 = count2 + map4.get(ch);
			V2 = V2 + 1;

		}

		HashMap<String, Integer> map6 = new HashMap<String, Integer>();// positive
																		// bigram
																		// hashmap

		// char c;
		char[] charArray3 = s5.toCharArray();
		for (int i = 0; i < charArray3.length - 1; i++) {
			s6 = "";
			s6 += charArray3[i];
			s6 += charArray3[i + 1];
			if (map6.get(s6) == null) {
				map6.put(s6, 1);
			} else {
				Integer in = map6.get(s6);
				map6.put(s6, in.intValue() + 1);
			}
		}

		Set<String> set3 = map6.keySet();
		int count3 = 0;
		int V3 = 0;
		for (String ch : set3) {
			System.out.println(ch + " " + map6.get(ch));
			count3 = count3 + map6.get(ch);
			V3 = V3 + 1;

		}

		double BiProb = 1;
		String test = "a b c";
		double BiProb2 = 1;
		double BiProb3 = 1;
		String[] strArray = test.split(" ");
		for (int i = 0; i < strArray.length - 1; i++) {
			String str1 = strArray[i];
			String str2 = strArray[i + 1];
			if (map2.get(str1 + str2) != null) {
				if (map.get(strArray[i]) != null) {
					BiProb = BiProb * (map2.get(str1 + str2) + 1)
							/ (map.get(strArray[i]) + V);
				}// Laplace smoothing add-1
				else
					BiProb = BiProb * (map2.get(str1 + str2) + 1) / V;
			} else {
				if (map.get(strArray[i]) != null) {
					BiProb = BiProb * 1 / (map.get(strArray[i]) + V);
				} else
					BiProb = BiProb * 1 / V;
			}
			// System.out.println(BiProb);
			if (map4.get(str1 + str2) != null && map3.get(strArray[i]) != null) {
				BiProb2 = BiProb2 * (map4.get(str1 + str2) + 1)
						/ (map3.get(strArray[i]) + V);
			}// Laplace smoothing add-1
			else if (map4.get(str1 + str2) != null
					&& map3.get(strArray[i]) == null) {
				BiProb2 = BiProb2 * (map4.get(str1 + str2) + 1) / V;
			} else if (map3.get(strArray[i]) != null
					&& map3.get(strArray[i]) != null) {
				BiProb2 = BiProb2 * 1 / (map3.get(strArray[i]) + V);
			} else
				BiProb2 = BiProb2 * 1 / V;
		}
		// compare
		System.out.println(BiProb3);
		double Prob = Math.max(BiProb, Math.max(BiProb2, 0));
		if (Prob == BiProb) {
			Sentiment = "Postive";
		} else if (Prob == BiProb2) {
			Sentiment = "Negative";
		} else
			Sentiment = "Objective";

		System.out.println(Sentiment);
	}

	public static void main(String[] args) {
		new BigramNavie().execute();
	}

	public void training() {
		// unigramTraining(unigram_pos, unigram_obj, unigram_neg);
		// bigramTraining(bigram_pos, bigram_obj,bigram_neg);
	}

	public String getUniResult(String line) {
		return "NULL";
	}

	public String getBiResult(String line) {
		return "result";
	}

	public void unigramTraining(HashMap<String, Integer> unigram_pos,
			HashMap<String, Integer> unigram_obj,
			HashMap<String, Integer> unigram_neg) {

	}

}
