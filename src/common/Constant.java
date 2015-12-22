package common;

import java.util.ArrayList;
import java.util.HashMap;

import data.FourField;
import data.Word;

public class Constant {
	public static ArrayList<FourField> trainingData = new ArrayList<FourField>();
	public static HashMap<String, Boolean> stopwords= new HashMap<String, Boolean>();
	public static String[] punctuation = {",",".","!","_","+","=","(",")","@","#","'","\"",":","?"};
	public static HashMap<Word, String> lexicon = new HashMap<Word,String>();
}
