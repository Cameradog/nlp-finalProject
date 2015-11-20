package fileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import data.Field;
import data.FieldType;
import data.FourField;


public class ReadFile {
	
	//read csv file
	public void readFile(String path, FieldType ft){
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new FileReader(path));
			
			//read file line by line
			while ((line = reader.readLine()) != null) {
				if(ft.equals(FieldType.four)){
					FourField f = new FourField();
					for(int i = 0 ; i <getColumnCounts(f) ; i++){				
						//hash tag
						if(i == 0 ){
							for(int j = 0 ; j < line.split(" ").length ; j++){
								f.hashTag.add(line.split(" ")[j]);
							}
						//content
						} else if ( i == 1){
							f.content = reader.readLine();
						} else if ( i == 2){
							f.polarity = reader.readLine();
						} else if( i == 3){
							reader.readLine();
						}
					}
					//System.out.println(f.hashTag.get(0) +" A" + f.content +"ABBBB "+ f.polarity +"A");
				}
			}		
			//close stream
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getColumnCounts(Field f){
		return f.columnCounts()+1;
	}
}
