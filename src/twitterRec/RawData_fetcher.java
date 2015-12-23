package twitterRec;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class RawData_fetcher {
	static PrintWriter output,output_with_emoji;
	int counter = 0;
	int tweetsSize = 10000;
	String filename = "tweet_RAW_test";
	Pattern pattern;
	Matcher matcher;
	String hashTag;
	String content;
	int exportCounter = 0;
	
	public static void main(String[] args){
		RawData_fetcher rf = new RawData_fetcher();
		rf.output();
		rf.output_with_emoji();
		rf.twitterStatusListener();
		output.close();
		output_with_emoji.close();
	}
	
	public void twitterStatusListener(){
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		
		//twitter status listener
		StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
                preProcess(status);
                if(dataCounter(status,counter)){
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
	//check if data numbers exceed wanted tweetsSize
		public boolean dataCounter(Status status, int c){
	        counter++;
//	        System.out.println("Counter: "+counter);
	        if(counter>tweetsSize){
	        		output.close();
	        		output_with_emoji.close();
	        		return false;
	        }
	        return true;
		}
	
	//pre-process raw twitter content & status into our format
		public void preProcess(Status status){
			String filterStr = "RT|@|http:|https:|www.|.com";
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
			output_with_emoji.println(hashTag);
			output_with_emoji.println(content);
			output_with_emoji.println();
			output_with_emoji.println("***");
			System.out.println(hashTag);
			System.out.println(content);
			System.out.println("***");
			
			rmEmoji();
			output.println(hashTag);
			output.println(content);
			output.println();
			output.println("***");
			exportCounter++;
			System.out.println("Export: "+exportCounter);
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
