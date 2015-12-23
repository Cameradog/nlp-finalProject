package feature;

public class Negtation {
	public String mergeDont(String line){
		line = line.replaceAll("do not", "do_not");
		line = line.replaceAll("does not", "does_not");
		line = line.replaceAll("did not", "did_not");
		return line;
	}
}
