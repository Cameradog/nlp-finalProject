package twitterRec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.Constant;
import data.FourField;
import feature.RemoveEmoji;

public class Improved_emoticon {
	// static PrintWriter output,output_with_emoji;
	FileReader file;
	BufferedReader reader;
	static int counter = 0;
	int exportCounter = 0;
	int posC = 0;
	int neuC = 0;
	int negC = 0;
	int noneC = 0;
	// String fileDir = "tweet_RAW.txt";
	// String filename = "tweet_improve_emoticon";
	Pattern pattern;
	Matcher matcher;
	String hashTag;
	String content;
	String polarity;
	// our defined emoticon
	String positivePole = "😙|🎁|❤|🎉|😘|💋|💟|😍|♥|💛|☺|😊|😋|😻|🎂|😀|😃|💪🏽|💪|💪🏿|💪🏾|👏|👏🏾|👏🏽|👋🏼|😎|😌|😉|🎵|😁|😝|✌|✌🏽️|"
			+ "💜|💙|💚|💁|✨|😂|👑|🙃|🙏|🙏🏻️|🙏🏽|❄️|👅|🌟|💫|👌🏻|👌🏼|👌|👌🏾|🤗|👍🏼|👍|👍🏾|💗|🙌🏼|🙌|🙌🏾|💐|🎤|😜|🙈|🌻|💟|🙊|💕|"
			+ "👭|👯|🌈|🎆|🌼|🌸|🌹|💦|🚀|💌|💘|🎧|💎|💍|👙|🍁|🎊|✅|⛄|👼|🏆️|🐾|💖|😺|😽|😼|⭐|💑️|👰|👶🏼|👶|"
			+ "🙋|🎶|🌺|🎷|🎈|☃|🎄|🍾|🌚|🎅🏽|🎅|🤘|✌|👸|🌷|🌌|🍺|⛺|🎸|🐶|🐣|💝|🏈|🌙|🍀|🌨|😇|🏡|😛|💓|🚿|🍦|"
			+ "🎖|⛄️|💞|🎇|🍸|🤑|🍷|😚|😹|🎺|🎮|🙂|😗|☺️";
	String neutralPole = "😏|😮|😈|👊|👊|👊🏾|😬|😅|🔥|😥|💯|😵|😟|😳|😴|✊|✊🏿|✊🏽|✊🏾|✊🏻|🔴|🖖|🖖🏽|❣|🙄|🔙|🔛|🔝|👱|👦🏼|💊|💤|"
			+ "🤔|‼|💸️|👽|💁🏻|💥|🏃|🎬|👻|✍|📣|🐐|🍂|🌎|🎹|🙇🏽|🦄|⚡|✈|💷️|🙆🏻|🎥|🔎|⬆|➡|⬅️️️|⬇|📅|🍫|👚|📖|"
			+ "🌲|🤒|👷|👇|👼🏽|☕|🐸|👧️|👫|🌃|👪|👴|👵|🍃|👹|🏀|🔐|📞|📹|☄|🎌|👀|🔪|💰|💅|🎼|🐝|✋|✋🏽|🍡|"
			+ "📲|☎️|🍑|🌵|👉|🙅🏻|❗|🍝|🍜️|🍎|🚗|🚙|⛽️|👄|🌆|⏰|💨|🎨|💣|🌜|💃🏻|🙆🏽|💲|🐎|📌|⌚️|🍯|📷|🌳|"
			+ "🙋🏼|🦃|⚽|🍪️|🎯|📢|🆘|🕵|🐢|🐕|⌛|🖥️|🤐|💁🏻|🍔|🍟|💁🏼";
	String negativePole = "💢|😱|😰|😧|😲|😭|😨|😫|😖|😷|😡|👎|🔫|😤|😣|😠|😑|😒|😕|👿|😩|💀|🖕🏻|⚰|🙁|💆🏿|😦|😓|💩|"
			+ "😐|😞|💔|☹|😪|😶️|😔|😢";
	RemoveEmoji em;

	public static void main(String[] args) {
		// Improved_emoticon m = new Improved_emoticon();
		// m.output();
		// m.output_with_emoji();
		// System.out.println("Export: "+m.exportCounter);
		// System.out.println("Pos: "+m.posC+"\nNeu: "+m.neuC+"\nNeg: "+m.negC+"\nNone: "+m.noneC);
	}

	public Improved_emoticon() {
		em = new RemoveEmoji();
	}

	public void execute(String path, boolean hasEmoji) {
		readFile(path);
		extract(hasEmoji);
	}

	public void readFile(String path) {
		try {
			file = new FileReader(path);
			reader = new BufferedReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// extract original paper definition data from raw data
	public void extract(boolean hasEmoji) {
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				hashTag = line;
				content = reader.readLine();
				//System.out.println(content);
				reader.readLine();
				reader.readLine();
				polarity = mode_improve();

				if (!polarity.equals("none") && hasEmoji) {
					boolean isWhitespace = content.matches("^\\s*$");
					if(!isWhitespace){	
						addData(hashTag, content, polarity);
					}
				}
				if (!polarity.equals("none") && !hasEmoji) {
					content =em.rmEmoji(content);
					boolean isWhitespace = content.matches("^\\s*$");
					if(!isWhitespace){
						addData(hashTag, content, polarity);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addData(String hashTag , String content, String polarity){
		FourField f = new FourField();
		ArrayList<String> hashTagALi = new ArrayList<String>();
		for(int i = 0 ; i < hashTag.split("\\s").length; i++){
			hashTagALi.add(hashTag.split("\\s")[i]);
		}
		f.hashTag = hashTagALi;
		f.content = this.content;
		f.polarity = this.polarity;
		Constant.trainingData.add(f);	
	}

	public String mode_improve() {
		int posCount = 0;
		int neuCount = 0;
		int negCount = 0;

		pattern = Pattern.compile(positivePole);
		matcher = pattern.matcher(content);
		posCount = emojiMatchCount();

		pattern = Pattern.compile(neutralPole);
		matcher = pattern.matcher(content);
		neuCount = emojiMatchCount();

		pattern = Pattern.compile(negativePole);
		matcher = pattern.matcher(content);
		negCount = emojiMatchCount();

		if (posCount > neuCount && posCount > negCount) {
			posC++;
			return "pos";
		} else if (neuCount > posCount && neuCount > negCount) {
			neuC++;
			return "neu";
		} else if (negCount > posCount && negCount > neuCount) {
			negC++;
			return "neg";
		} else if (posCount == neuCount && posCount > negCount) {
			posC++;
			return "pos";
		} else if (negCount == neuCount && negCount > posCount) {
			negC++;
			return "neg";
		} else {
			noneC++;
			return "none";
		}
	}

	public int emojiMatchCount() {
		int matchCount = 0;
		while (matcher.find()) {
			matchCount++;
		}
		return matchCount;
	}

	// remove emoji
	// public void rmEmoji(){
	// String utf8Content = "";
	// String utf8Hashtag = "";
	// try {
	// byte[] utf8bytesHash = hashTag.getBytes("UTF-8");
	// byte[] utf8bytes = content.getBytes("UTF-8");
	// utf8Hashtag = new String(utf8bytesHash, "UTF-8");
	// utf8Content = new String(utf8bytes, "UTF-8");
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// Pattern unicodeP = Pattern.compile("[^\\x00-\\x7F]",
	// Pattern.UNICODE_CASE | Pattern.CANON_EQ
	// | Pattern.CASE_INSENSITIVE);
	// Matcher unicodeM = unicodeP.matcher(utf8Hashtag);
	// hashTag = unicodeM.replaceAll("");
	// unicodeM = unicodeP.matcher(utf8Content);
	// content = unicodeM.replaceAll("");
	// }

	// output file
	// public void output(){
	// try {
	// output = new PrintWriter(new BufferedWriter(new
	// FileWriter(filename+".txt",false)));
	// output.print("");
	// output = new PrintWriter(new BufferedWriter(new
	// FileWriter(filename+".txt",true)));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// public void output_with_emoji(){
	// try {
	// output_with_emoji = new PrintWriter(new BufferedWriter(new
	// FileWriter(filename+"_with_emoji.txt",false)));
	// output_with_emoji.print("");
	// output_with_emoji = new PrintWriter(new BufferedWriter(new
	// FileWriter(filename+"_with_emoji.txt",true)));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
