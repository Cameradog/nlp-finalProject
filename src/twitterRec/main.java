package twitterRec;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.xml.transform.Templates;

import twitter4j.Query;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.FilterQuery;
import twitter4j.api.HelpResources.Language;

public class main {
	static PrintWriter output,output_with_emoji;
	static int counter = 0;
	int exportCounter = 0;
	int posC = 0;
	int neuC = 0;
	int negC = 0;
	int noneC = 0;
	int fileSize = 10000;
	String filename = "tweet_test";
	Pattern pattern;
	Matcher matcher;
	String hashTag;
	String content;
	//our defined emoticon
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
	
	public static void main(String args[]){
		main main = new main();
		main.output();
		main.output_with_emoji();
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		
		//twitter status listener
		StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
                main.disassemble(status);
                if(main.dataCounter(status,counter)){
                		//nothing
                }
                else{
                		twitterStream.shutdown();
                }
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
        };
        twitterStream.addListener(listener);
        twitterStream.sample("en");
	
	}
	
	//re-construct twitter status into our format
	public void disassemble(Status status){
		String filterStr = "RT|@|http:|https:|www.|.com";
		String polarity = "none";
		hashTag = "";
		content = "";
		
		//split each spaces and return
		String[] words = status.getText().split(" |\r|\n|\r\n");
		for(int i=0; i<words.length; i++){
			
			//find and remove redundant words
			pattern = Pattern.compile(filterStr);
			matcher = pattern.matcher(words[i]);
			if(!matcher.find()){
				//hashTag process
				if(words[i].contains("#")){
					String[] temp = words[i].split("#");
					for(int j=0; j<temp.length; j++){
						if(!temp[j].equals("")){
							hashTag = hashTag + temp[j] +" ";
						}
					}
				}
				//content process
				else {
					if(!words[i].equals("")){
						content = content + words[i] + " ";
					}
				}
			}
		}
		rmLastSpace();
		polarity = emojiPole(content);
		if(!polarity.equals("none")){
			output_with_emoji.println(hashTag);
			output_with_emoji.println(content);
			output_with_emoji.println(polarity);
			output_with_emoji.println("***");
		}
		
		if(!polarity.equals("none")){
			rmEmoji();
			rmLastSpace();
			output.println(hashTag);
			output.println(content);
			output.println(polarity);
			output.println("***");
			exportCounter++;
			System.out.println("Counter: "+counter);
			System.out.println("Export: "+exportCounter);
			System.out.println("Pos: "+posC+"\nNeu: "+neuC+"\nNeg: "+negC+"\nNone: "+noneC);
		}
	}
	
	//check if data numbers exceed fileSize
	public boolean dataCounter(Status status, int c){
        counter++;
//        System.out.println("Counter: "+counter);
        if(counter>fileSize){
        		output.close();
        		output_with_emoji.close();
        		return false;
        }
        return true;
	}
	
	//remove last space in each line
	public void rmLastSpace(){
		if(!hashTag.equals("") && hashTag.substring(hashTag.length()-1).equals(" ")){
			String saveStr = hashTag.substring(0,hashTag.length()-1);
			hashTag = saveStr;
		}
		if(!content.equals("") && content.substring(content.length()-1).equals(" ")){
			String saveStr = content.substring(0,content.length()-1);
			content = saveStr;
		}
	}
	
	//check polarity
	public String emojiPole(String content){
		int poleCounter = 0;
		int posCount = 0;
		int neuCount = 0;
		int negCount = 0;
		String returnPole = "";
		
		pattern = Pattern.compile(positivePole);
		matcher = pattern.matcher(content);
		posCount = emojiMatchCount();

		pattern = Pattern.compile(neutralPole);
		matcher = pattern.matcher(content);
		neuCount = emojiMatchCount();
		
		pattern = Pattern.compile(negativePole);
		matcher = pattern.matcher(content);
		negCount = emojiMatchCount();
		
		if(posCount>neuCount && posCount>negCount){
			posC++;
			return "pos";
		}
		else if(neuCount>posCount && neuCount>negCount){
			neuC++;
			return "neu";
		}
		else if(negCount>posCount && negCount>neuCount){
			negC++;
			return "neg";
		}
		else if(posCount==neuCount && posCount>negCount){
			posC++;
			return "pos";
		}
		else if(negCount==neuCount && negCount>posCount){
			negC++;
			return "neg";
		}
		else{
			noneC++;
			return "none";
		}

	}
	public int emojiMatchCount(){
		int matchCount = 0;
		while(matcher.find()){
			matchCount++;
		}
		return matchCount;
	}
	
	public void rmEmoji(){
		String utf8Content = "";
		String utf8Hashtag = "";
		try {
			byte[] utf8bytesHash = hashTag.getBytes("UTF-8");
			byte[] utf8bytes = content.getBytes("UTF-8");
			utf8Hashtag = new String(utf8bytesHash, "UTF-8");
			utf8Content = new String(utf8bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pattern unicodeP = Pattern.compile("[^\\x00-\\x7F]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ
                        | Pattern.CASE_INSENSITIVE);
		Matcher unicodeM = unicodeP.matcher(utf8Hashtag);
		hashTag = unicodeM.replaceAll("");
		unicodeM = unicodeP.matcher(utf8Content);
		content = unicodeM.replaceAll("");
	}
		
	//output file
	public void output(){
		try {
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".txt",false)));
			output.print("");
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".txt",true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void output_with_emoji(){
		try {
			output_with_emoji = new PrintWriter(new BufferedWriter(new FileWriter(filename+"_with_emoji.txt",false)));
			output_with_emoji.print("");
			output_with_emoji = new PrintWriter(new BufferedWriter(new FileWriter(filename+"_with_emoji.txt",true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
