package twitterRec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemovePunctuation {
	Pattern pattern;
	Matcher matcher;
	
	public String rmPunct(String content){
		
		String saveChar = "[^a-zA-Z0-9 ]";
		pattern = Pattern.compile(saveChar);
		matcher = pattern.matcher(content);
		content = matcher.replaceAll("");
		
		return content;
	}
	
	public String rmNum(String content){
		
		String saveChar = "[0-9]";
		pattern = Pattern.compile(saveChar);
		matcher = pattern.matcher(content);
		content = matcher.replaceAll("");
		
		return content;
	}
}
