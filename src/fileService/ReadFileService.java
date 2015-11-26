package fileService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import common.Constant;

import data.Field;
import data.FieldType;
import data.FourField;
import data.FourFieldService;

public class ReadFileService {
	private static ReadFileService readFileService = null;

	// get service and you can use function and you can access this java's
	// function
	// for example:FieldService.getServ().createNewField
	public static ReadFileService getServ() {
		if (readFileService == null) {
			readFileService = new ReadFileService();
		}
		return readFileService;
	}

	// read csv file(training data)
	public void readTrainingData(String path, FieldType ft) {
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new FileReader(path));

			// read file line by line
			while ((line = reader.readLine()) != null) {
				if (ft.equals(FieldType.four)) {
					FourField f = new FourField();
					for (int i = 0; i < getColumnCounts(f); i++) {
						// hash tag
						if (i == 0) {
							for (int j = 0; j < line.split(" ").length; j++) {
								f.hashTag.add(line.split(" ")[j]);
							}
							// content
						} else if (i == 1) {
							f.content = reader.readLine();
						} else if (i == 2) {
							f.polarity = reader.readLine();
						} else if (i == 3) {
							reader.readLine();
						}
					}
					Constant.trainingData.add(f);
					// System.out.println(f.hashTag.get(0) +" A" + f.content
					// +"ABBBB "+ f.polarity +"A");
				}
			}
			// close stream
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void readStopWordFile(String path) {
		BufferedReader reader;
		String word;
		try {
			reader = new BufferedReader(new FileReader(path));
			while ((word = reader.readLine()) != null) {
				//not appear before
				if(Constant.stopwords.get(word) != null){
					//do nothing
				} else {
					Constant.stopwords.put(word, true);
				}
				
			}
			
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getColumnCounts(Field f) {
		return f.columnCounts() + 1;
	}
}
