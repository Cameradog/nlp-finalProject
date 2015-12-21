package data;

import java.util.ArrayList;

import data.Field;

public class FourField implements Field{
	public ArrayList<String> hashTag = new ArrayList<String>();
	public String content;
	public String polarity;
	
	@Override
	public int columnCounts() {
		// TODO Auto-generated method stub
		return 3;
	}


}
