package classifer;

import java.util.HashMap;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import feature.RemoveEmoji;
import feature.RemovePunctuation;
import feature.RemoveStopwords;

import java.lang.Math;
public class BigramNaive {
        String test = "";
        String[] strArray;
        double UniProbP = 1;
        double UniProbN = 1;
        double UniProbO = 1;
        double TagProbP = 1;
        double TagProbN = 1;
        double TagProbO = 1;
        double countTagPos,countTagObj,countTagNeg;
        double count,count2,count3;//the sum of each word's appearing times in hashmap 
        double V,V2,V3;// the count of words  in hashmap 
        double BiProbP = 1;
		double BiProbN = 1;
		double BiProbO = 1;
		double logPos = 0;
		double logObj = 0;
		double logNeg = 0;
		double theta = -18;
        double countBiPos,countBiNeg,countBiObj;// the sum of bigram's appearing time
        double Entropy;
        
        String Sentiment;
        RemoveStopwords rmvStopwords;
    		RemovePunctuation rmvPun;
    		RemoveEmoji re;
        MaxentTagger tagger;
        double total = 0.0;
	public static void main(String[] args) {
		new BigramNaive();
	}

	public BigramNaive(){
		re = new RemoveEmoji();
		rmvStopwords = new RemoveStopwords();
		rmvPun = new RemovePunctuation();
		tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
	}
	
	public void setTestString(String testStr){
		testStr = re.rmEmoji(testStr);
		testStr = rmvStopwords.getLineWithNoStopwords(testStr);
		testStr = rmvPun.rmPunctAndNum(testStr);
		System.out.println(testStr);
		strArray = testStr.split("\\s+");
		
	}
	
	public String getHandLabel(){
		return strArray[strArray.length-1];
	}
	
	public void countUnihashTable(HashMap<String, Integer> unigram_pos,
									HashMap<String, Integer> unigram_obj,
									HashMap<String, Integer> unigram_neg) {
		count = 0;
		count2 = 0;
		count3 = 0;
		for(Object key: unigram_pos.keySet()){
			count = count + unigram_pos.get(key);
		}
		V = unigram_pos.size();
		for(Object key: unigram_obj.keySet()){
			count2 = count2 + unigram_obj.get(key);
		}
		V2 = unigram_obj.size();
		for(Object key: unigram_neg.keySet()){
			count3 = count3 + unigram_neg.get(key);
		}
		V3 = unigram_neg.size();
	}
	
	public void countBihashTable(HashMap<String, Integer> Bigram_pos,
								HashMap<String, Integer> Bigram_obj,
								HashMap<String, Integer> Bigram_neg) {
		countBiPos = 0;
		countBiObj = 0;
		countBiNeg = 0;
		for(Object key : Bigram_pos.keySet()){
			countBiPos = countBiPos + Bigram_pos.get(key); 
		}
		for(Object key : Bigram_obj.keySet()){
			countBiObj = countBiObj + Bigram_obj.get(key);		
				}
		for(Object key : Bigram_neg.keySet()){
			countBiNeg = countBiNeg + Bigram_neg.get(key);
		}
	}
	
	public void countUniPoshashTable(HashMap<String, Integer> uniPosgram_pos,
			HashMap<String, Integer> uniPosgram_obj,
			HashMap<String, Integer> uniPosgram_neg) {
		countTagPos = 0;
		countTagObj = 0;
		countTagNeg = 0;
		
		for(Object key: uniPosgram_pos.keySet()){
			countTagPos = countTagPos + uniPosgram_pos.get(key);
		}
		
		for(Object key: uniPosgram_obj.keySet()){
			countTagObj = countTagObj + uniPosgram_obj.get(key);
		}
		
		for(Object key: uniPosgram_neg.keySet()){
			countTagNeg = countTagNeg + uniPosgram_neg.get(key);
		}
		
	}



	public String getUniResult() {
		double Prob = Math.max(UniProbP, Math.max(UniProbN, UniProbO));
		if (Prob == UniProbP) {
			Sentiment = "pos";
		} else if (Prob == UniProbN) {
			Sentiment = "neg";
		} else
			Sentiment = "neu";
//		System.out.println("P: "+UniProbP+"\nO: "+UniProbO+"\nN: "+UniProbN);

		return Sentiment;//may changed to -1,0,1
	}

	public String getBiResult() {
		double Prob = Math.max(BiProbP, Math.max(BiProbN, BiProbO));
		if (Prob == BiProbP) {
			Sentiment = "pos";
		} else if (Prob == BiProbN) {
			Sentiment = "neg";
		} else
			Sentiment = "neu";
//		System.out.println("P: "+BiProbP+"\nO: "+BiProbO+"\nN: "+BiProbN);

		return Sentiment;
	}
	
	public String getUniposResult() {
		double Prob = Math.max(UniProbP*TagProbP, Math.max(UniProbN*TagProbN, UniProbO*TagProbO));
		if (Prob == UniProbP*TagProbP) {
			Sentiment = "pos";
		} else if (Prob == UniProbN*TagProbN) {
			Sentiment = "neg";
		} else
			Sentiment = "neu";
//		System.out.println("P: "+UniProbP*TagProbP+"\nO: "+UniProbO*TagProbO+"\nN: "+UniProbN*TagProbN);

		return Sentiment;
	}
	
	public String getImproveUniposResult(){
		double Prob = Math.max(logPos, Math.max(logObj, logNeg));
		if (Prob == logPos) {
			Sentiment = "pos";
		} else if (Prob == logNeg) {
			Sentiment = "neg";
		} else
			Sentiment = "neu";
//		System.out.println("P: "+logPos+"\nO: "+logObj+"\nN: "+logNeg);

		return Sentiment;
	}

	public void unigramTraining(HashMap<String, Integer> unigram_pos,
								HashMap<String, Integer> unigram_obj,
								HashMap<String, Integer> unigram_neg) {    		
		countUnihashTable(unigram_pos, unigram_obj, unigram_neg);
		UniProbP = 1;
		UniProbO = 1;
		UniProbN = 1;
		
		
        for (int i = 0; i < strArray.length-1; i++) {
            if (unigram_pos.get(strArray[i]) != null) {
                UniProbP = UniProbP * (unigram_pos.get(strArray[i]) + 1) / (count+V)*count/(count+count2+count3);//Laplace smoothing add-1
            } else {
                UniProbP = UniProbP * 1 / (count+V)*count/(count+count2+count3);
            }
            if (unigram_obj.get(strArray[i]) != null) {
                UniProbO = UniProbO * (unigram_obj.get(strArray[i]) + 1) / (count2+V2)*count2/(count+count2+count3);//Laplace smoothing add-1
            } else {
                UniProbO = UniProbO * 1 / (count2+V2)*count2/(count+count2+count3);
            }
            if (unigram_neg.get(strArray[i]) != null) {
                UniProbN = UniProbN * (unigram_neg.get(strArray[i]) + 1) / (count3+V3)*count3/(count+count2+count3);//Laplace smoothing add-1
            } else {
                UniProbN = UniProbN * 1 / (count3+V3)*count3/(count+count2+count3);
            }
        }
        //using Entropy
//        Entropy=UniProbN*Math.log(UniProbN)+UniProbP*Math.log(UniProbP);
        Entropy=-UniProbN*Math.log(UniProbN)-UniProbP*Math.log(UniProbP)-UniProbO*Math.log(UniProbO);
//        Entropy=-Math.log(UniProbN)-Math.log(UniProbP)-Math.log(UniProbO);
//        System.out.println("Entropy: "+Entropy);
        

	}
    
    public String getPosTag(String word){
		String[] tag = tagger.tagTokenizedString(word).split("_");
    		return tag[1];
    }
    
    public void posunigramTraining(HashMap<String, Integer> tag_pos,
    									HashMap<String, Integer> tag_obj,
    									HashMap<String, Integer> tag_neg){
		
		countUniPoshashTable(tag_pos, tag_obj, tag_neg);
		TagProbP = 1;
		TagProbO = 1;
		TagProbN = 1;
    	
        for(int i=0; i<strArray.length-1; i++){
        		String tag = getPosTag(strArray[i]);
            
        		if (tag_pos.get(tag) != null) {
                TagProbP = TagProbP * tag_pos.get(tag) / countTagPos*countTagPos/(countTagPos+countTagObj+countTagNeg);
            } else {
                TagProbP = TagProbP * 1 / countTagPos*countTagPos/(countTagPos+countTagObj+countTagNeg);
            }
            if (tag_obj.get(tag) != null) {
                TagProbO = TagProbO * tag_obj.get(tag) / countTagObj*countTagObj/(countTagPos+countTagObj+countTagNeg);
            } else {
                TagProbO = TagProbO * 1 / countTagObj*countTagObj/(countTagPos+countTagObj+countTagNeg);
            }
            if (tag_neg.get(tag) != null) {
               TagProbN = TagProbN * tag_neg.get(tag) / countTagNeg*countTagNeg/(countTagPos+countTagObj+countTagNeg);//
            } else {
               TagProbN = TagProbN * 1 / countTagNeg*countTagNeg/(countTagPos+countTagObj+countTagNeg);
            }
        
        }			
        
//        System.out.println("TagP: "+Math.log(TagProbP)+"\nTagO: "+Math.log(TagProbO)+"\nTagN: "+Math.log(TagProbN));
	}
       
    public void biigramTraining(HashMap<String, Integer> unigram_pos,
    								HashMap<String, Integer> unigram_obj,
    								HashMap<String, Integer> unigram_neg,
    								HashMap<String, Integer> bigram_pos,
    								HashMap<String, Integer> bigram_obj,
    								HashMap<String, Integer> bigram_neg) {
    		countBihashTable(bigram_pos, bigram_obj, bigram_neg);
    		BiProbP = 1;
    		BiProbO = 1;
    		BiProbN = 1;
    	
		for (int i = 0; i < strArray.length - 2; i++) {
			String str1 = strArray[i];
			String str2 = strArray[i + 1];
			if (bigram_pos.get(str1 +" "+ str2) != null) {
				if (unigram_pos.get(strArray[i]) != null) {
					BiProbP = BiProbP * (bigram_pos.get(str1 +" "+ str2) + 1)
							/ (unigram_pos.get(strArray[i]) + countBiPos)*countBiPos/(countBiPos+countBiNeg+countBiObj);
				}// Laplace smoothing add-1
				else
					BiProbP = BiProbP * (bigram_pos.get(str1 +" "+ str2) + 1) / countBiPos*countBiPos/(countBiPos+countBiNeg+countBiObj);
			} else {
				if (unigram_pos.get(strArray[i]) != null) {
					BiProbP = BiProbP * 1 / (unigram_pos.get(strArray[i]) + countBiPos)*countBiPos/(countBiPos+countBiNeg+countBiObj);
				} else
					BiProbP = BiProbP * 1 / countBiPos*countBiPos/(countBiPos+countBiNeg+countBiObj);
			}
			// System.out.println(BiProb);
			if (bigram_neg.get(str1 +" "+ str2) != null && unigram_neg.get(strArray[i]) != null) {
				BiProbN = BiProbN * (bigram_neg.get(str1 +" "+ str2) + 1)
						/ (unigram_neg.get(strArray[i]) + countBiNeg)*countBiNeg/(countBiPos+countBiNeg+countBiObj);
			}// Laplace smoothing add-1
			else if (bigram_neg.get(str1 +" "+ str2) != null
					&& unigram_neg.get(strArray[i]) == null) {
				BiProbN = BiProbN * (bigram_neg.get(str1 +" "+ str2) + 1) / countBiNeg*countBiNeg/(countBiPos+countBiNeg+countBiObj);
			} else if (bigram_neg.get(strArray[i]) == null
					&& unigram_neg.get(strArray[i]) != null) {
				BiProbN = BiProbN * 1 / (unigram_neg.get(strArray[i]) + countBiNeg)*countBiNeg/(countBiPos+countBiNeg+countBiObj);
			} else
				BiProbN = BiProbN * 1 / countBiNeg*countBiNeg/(countBiPos+countBiNeg+countBiObj);
	                    
	                    if (bigram_obj.get(str1 +" "+ str2) != null && unigram_obj.get(strArray[i]) != null) {
				BiProbO = BiProbO * (bigram_obj.get(str1 +" "+ str2) + 1)
						/ (unigram_obj.get(strArray[i]) + countBiObj)*countBiObj/(countBiPos+countBiNeg+countBiObj);
			}// Laplace smoothing add-1
			else if (bigram_obj.get(str1 +" "+ str2) != null
					&& unigram_obj.get(strArray[i]) == null) {
				BiProbO = BiProbO * (bigram_obj.get(str1 +" "+ str2) + 1) / countBiObj*countBiObj/(countBiPos+countBiNeg+countBiObj);
			} else if (bigram_obj.get(strArray[i]) == null
					&& unigram_obj.get(strArray[i]) != null) {
				BiProbO = BiProbO * 1 / (unigram_obj.get(strArray[i]) + countBiObj)*countBiObj/(countBiPos+countBiNeg+countBiObj);
			} else
				BiProbO = BiProbO * 1 / countBiObj*countBiObj/(countBiPos+countBiNeg+countBiObj);
		}
	// compare
    }
    
    public void loglikely(){
    		if(Entropy>Math.pow(10, theta)){
    			logPos = Math.log(UniProbP) + Math.log(TagProbP);
    			logObj = Math.log(UniProbO) + Math.log(TagProbO);
    			logNeg = Math.log(UniProbN) + Math.log(TagProbN);
    		}
    		else{
    			logPos = Math.log(TagProbP);
    			logObj = Math.log(TagProbO);
    			logNeg = Math.log(TagProbN);
    		}
    	
    	
    }
    
}

    

