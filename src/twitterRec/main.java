package twitterRec;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.xml.transform.Templates;

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
	static PrintWriter output;
	static int counter = 0;
	int fileSize = 100;
	String filename = "tweet";
	Pattern pattern;
	Matcher matcher;
	String hashTag;
	String content;
	
	public static void main(String args[]){
		main main = new main();
		main.output();
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        
		//twitter status listener
		StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
//                System.out.println("Counter: "+counter+"  /"+ status.getUser().getScreenName() + " - " + status.getText());
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
//     	FilterQuery filterQuery = new FilterQuery();
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
		output.println(hashTag);
		output.println(content);
		output.println(polarity);
		output.println("***");
	}
	
	//check if data numbers exceed fileSize
	public boolean dataCounter(Status status, int c){
        counter++;
        System.out.println("Counter: "+counter);
        if(counter>fileSize){
        		output.close();
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
		String positivePole = "😙|🎁|❤|🎉|😘|💋|💟|😍|♥|💛|☺|😊|😋|😻|🎂|😀|😃|💪|👏|😎|😌|😉|🎵|😁|😝|✌🏼";
		String neutralPole = "😏|😮|😈|💢|👊|😂|😬|😱|😅|😹|🔥|😥|💯|😵|😟|😳|😢|😴";
		String negativePole = "😰|😧|😲|😭|😨|😫|😖|😷|😡|👎|🔫|😤|😣|😠|😑|😒|😕|👿|😩|💀";
		
		pattern = Pattern.compile(positivePole);
		matcher = pattern.matcher(content);
		if(matcher.find()){
			return "pos";
		}
		
		pattern = Pattern.compile(neutralPole);
		matcher = pattern.matcher(content);
		if(matcher.find()){
			return "neu";
		}
		
		pattern = Pattern.compile(negativePole);
		matcher = pattern.matcher(content);
		if(matcher.find()){
			return "neg";
		}
		return "none";
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
}
