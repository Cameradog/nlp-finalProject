package feature;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveEmoji {
	/*
	public static void main(String[] args){
		System.out.println(new RemoveEmoji().rmEmoji("3 days til christmas!!😊👊🎁🎁👌👌🎉🎉🎶🎶"));
	}*/

	// remove emoji
	public String rmEmoji(String line) {
		String utf8Content = "";

		try {
			byte[] utf8bytes = line.getBytes("UTF-8");
			utf8Content = new String(utf8bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pattern unicodeP = Pattern.compile("[^\\x00-\\x7F]",
				Pattern.UNICODE_CASE | Pattern.CANON_EQ
						| Pattern.CASE_INSENSITIVE);
		Matcher unicodeM = unicodeP.matcher(utf8Content);
		line = unicodeM.replaceAll("");
		return line;
	}
}
