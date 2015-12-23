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
	public static String FilePath;
	
	
	//default, lexicon, improve
	public static String rawDataProcess="";
	
	public static boolean removePun = false;
	public static boolean removeUnMeaning = false;
	public static boolean stem = false;
	public static boolean negation = false;
	public static boolean hasStopword = false;
	
	//navie, me
	public static String classifier="";
	
	//uni, bi, unibi, unipo ,no
	public static String classifierFeature=""; 
	
	
}
