package data;

public class Word {
	public String word;
	public String pos;
	public Integer hashNumber;
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Word){
			Word compare = (Word)obj;
			
			return this.word.equals(compare.word) 
					&&this.pos.equals(compare.pos); 
		} 
		return false;
	}
	@Override
	public int hashCode(){
	    return (word+pos).hashCode();
	}
}
