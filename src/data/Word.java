package data;

public class Word {
	public String word;
	public String pos;
	public Integer hashNumber;
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Word){
			Word compare = (Word)obj;
			boolean posIsOK =false;
			//System.out.println(this.pos +" " + compare.pos);
			if(this.pos.equals("anypos") || compare.pos.equals("anypos") || this.pos.equals(compare.pos)){
				posIsOK= true;
			}
			return this.word.equals(compare.word) 
					&&posIsOK; 
		} 
		return false;
	}
	@Override
	public int hashCode(){
	    return (word).hashCode();
	}
}
