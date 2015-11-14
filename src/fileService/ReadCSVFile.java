package fileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadCSVFile {
	
	//read csv file
	public void readCSVFile(String path){
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new FileReader(path));
			
			//read file line by line
			while ((line = reader.readLine()) != null) {

			}		
			
			//close stream
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
