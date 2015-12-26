package feature;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Constant;

public class RemovePunctuation {
	Pattern pattern;
	Matcher matcher;

	// public static void main(String)

	public String rmPunctAndNum(String content) {
		content = content.replaceAll("[^a-zA-Z ]", "").toLowerCase();
		content = content.replaceAll("[\\Q][(){},.;!?<>%\\E]", "");		
		
		/*
		String[] pun = Constant.punctuation;
		String newLine = "";
		for (int i = 0; i < content.length(); i++) {
			boolean addChar = true;
			for (int j = 0; j < pun.length; j++) {
				if ((content.charAt(i) + "").equals(pun[j])) {
					if (i != 0 && i != content.length() - 1) {
						newLine += " ";
					}
					addChar = false;
					break;
				}
			}
			if (addChar) {
				newLine += content.charAt(i);
			}
		}
		return newLine.toLowerCase();*/
		return content;
	}

	/*
	 * public String rmPunct(String content){
	 * 
	 * String saveChar = "[^a-zA-Z0-9 ]"; pattern = Pattern.compile(saveChar);
	 * matcher = pattern.matcher(content); content = matcher.replaceAll("");
	 * 
	 * return content; }
	 */
	/*
	 * public String rmNum(String content){
	 * 
	 * String saveChar = "[0-9]"; pattern = Pattern.compile(saveChar); matcher =
	 * pattern.matcher(content); content = matcher.replaceAll("");
	 * 
	 * return content; }
	 */

	/*
	 * public static void main(String[] args){ System.out.println(new
	 * RemovePunctuation
	 * ().getLineWithNoPunctuation("time win. chance win $10 winner announced 3."
	 * )); }
	 */
}
