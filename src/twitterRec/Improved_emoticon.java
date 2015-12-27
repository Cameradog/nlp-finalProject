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
	FileReader file,fileN;
	BufferedReader reader,readerN;
	static int counter = 0;
	int exportCounter = 0;
	// 1M/190000
	// 2M/190000
	int rawDataSize = 1000000;
	int neutralDataSize = 190000;
	int posC = 0;
	int neuC = 0;
	int negC = 0;
	int noneC = 0;
	Pattern pattern;
	Matcher matcher;
	String hashTag;
	String content;
	String polarity;
	// our defined emoticon
//	String positivePole = "😙|🎁|❤|🎉|😘|💋|💟|😍|♥|💛|☺|😊|😋|😻|🎂|😀|😃|💪🏻|💪🏽|💪|💪🏿|💪🏽|💪🏾|👏🏻|👏🏼|👏|👏🏾|👏🏽|👏🏿|👋🏼|😎|😌|😉|🎵|😁|😝|✌️🏻|✌️🏼|✌️🏾|✌️🏿|✌🏽️|"
//			+ "💜|💙|💚|💁|✨|😂|👑|🙃|🙏|🙏🏻️|🙏🏽|🙏🏾|❄️|👅|🌟|💫|👌🏻|👌🏼|👌|👌🏾|🤗|👍🏻|👍🏼|👍|👍🏾|💗|🙌🏼|🙌|🙌🏾|💐|🎤|😜|🙈|🌻|💟|🙊|💕|"
//			+ "👭|👯|🌈|🎆|🌼|🌸|🌹|💦|🚀|💌|💘|🎧|💎|💍|👙|🍁|🎊|✅|⛄|👼|🏆️|🐾|💖|😺|😽|😼|⭐|💑️|👰|👶🏼|👶|"
//			+ "🙋|🎶|🌺|🎷|🎈|☃|🎄|🍾|🌚|🎅🏽|🎅|🤘|✌|👸|🌷|🌌|🍺|⛺|🎸|🐶|🐣|💝|🏈|🌙|🍀|🌨|😇|🏡|😛|💓|🚿|🍦|"
//			+ "🎖|⛄️|💞|🎇|🍸|🤑|🍷|😚|😹|🎺|🎮|🙂|😗|☺️|🎄|🎅🏾|🎅🏿|🎅🏼|🎅🏻|😅";
	String positivePole = "😙|🎁|❤|🎉|😘|💋|💟|😍|♥|💛|☺|😊|😋|😻|🎂|😀|😃|💪🏻|💪🏽|💪|💪🏿|💪🏽|💪🏾|👏🏻|👏🏼|👏|👏🏾|👏🏽|👏🏿|😎|😌|😉|🎵|😁|😝|✌️🏻|✌️🏼|✌️🏾|✌️🏿|✌🏽️|"
			+ "💜|💙|💚|😂|🙃|🙏|🙏🏻️|🙏🏽|🙏🏾|❄️|👌🏻|👌🏼|👌|👌🏾|🤗|👍🏻|👍🏼|👍|👍🏾|💗|🙌🏼|🙌|🙌🏾|😜|🙈|💟|🙊|💕|"
			+ "💌|💘|🎊|⛄|💖|😺|😽|😼|"
			+ "☃|🎄|🎅🏽|🎅|🤘|✌|💝|😇|😛|💓|"
			+ "⛄️|💞|🤑|😚|😹|🎮|🙂|😗|☺️|🎄|🎅🏾|🎅🏿|🎅🏼|🎅🏻|😅";
	String neutralPole = "😏|😮|😈|👊🏻|👊🏼|👊🏽|👊🏿|👊|👊🏾|😬|🔥|😥|💯|😳|😴|✊|✊🏿|✊🏽|✊🏾|✊🏼|✊🏻|🖖|🖖🏻|🖖🏼|🖖🏾|🖖🏿|🖖🏽|🙄|💤|"
			+ "🤔|"
			+ "🌲|☕|👧️|👫|👪|👀|💰|🎼|🐝|✋🏻|🖐🏼|🖐🏾|🖐🏿|✋|✋🏽|"
			+ "📲|️"
			+ "⚽|🍪|🐕|🖥️|🤐|😯|😐|💩|🔔|🕯|🍴";
	String negativePole = "💢|😱|😰|😧|😲|😭|😨|😫|😖|😷|😡|👎🏻|👎🏼|👎🏽|👎🏾|👎🏿|👎|🔫|😤|😣|😠|😑|😒|😕|👿|😩|💀|🖕🏻|🖕🏼|🖕🏾|🖕🏿|🖕|🖕🏽|⚰|🙁|💆🏿|😦|😓|"
			+ "😞|💔|☹|😪|😶️|😔|😢|😔|😕|☹️|😵|😟|🤒|😿|🙍|🗯|🤕|😾|🙀";
	RemoveEmoji em;
	
	public Improved_emoticon() {
		em = new RemoveEmoji();
	}

	public void execute(String path, boolean hasEmoji) {
		readFile(path);
		extract(hasEmoji);
		addNeuTralData();
	}

	public void readFile(String path) {
		try {
			file = new FileReader(path);
			reader = new BufferedReader(file);
			fileN = new FileReader("resource/twitterFile/neutral_RAW.txt");
			readerN = new BufferedReader(fileN);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// extract original paper definition data from raw data
	public void extract(boolean hasEmoji) {
		String line;
		int count = 0;
		System.out.println("emoji extraction...");
		try {
			while ((line = reader.readLine()) != null) {
				if(count == rawDataSize){
					break;
				}
				
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
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addNeuTralData(){
		String line;
		int counter = 0;
		int ranNum = (int) Math.random()*0 +1;
		int dataSize = ranNum*neutralDataSize;
		boolean isWhitespace;
		System.out.println("add neutral raw data...");
		try {
			while((line = readerN.readLine()) != null){
				if(counter == dataSize){
					break;
				}
				
				if(counter%ranNum == 0){
					hashTag = line;
					content = readerN.readLine();
					readerN.readLine();
					readerN.readLine();
					polarity = "neu";
					isWhitespace = content.matches("^\\s*$");
					if(!isWhitespace){	
						addData(hashTag, content, polarity);
					}
					counter++;
				}
				else{
					readerN.readLine();
					readerN.readLine();
					readerN.readLine();
					counter++;
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
}
