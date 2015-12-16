package classifer;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import opennlp.maxent.BasicEventStream;
import opennlp.maxent.GIS;
import opennlp.maxent.PlainTextByLineDataStream;
import opennlp.maxent.io.GISModelWriter;
import opennlp.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.model.AbstractModel;
import opennlp.model.EventStream;
import opennlp.model.OnePassRealValueDataIndexer;

//import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class MaxEnt_train {
	public static void main(String[] args) throws IOException{
		training();
	}
	
	public static boolean USE_SMOOTHING = false;
	public static double SMOOTHING_OBSERVATION = 0.1;
	public static void training() throws IOException {
		String dataFileName = new String("training.txt");
		String modelFileName = dataFileName.substring(0, dataFileName.lastIndexOf('.')) + "Model.txt";

		FileReader datafr = new FileReader(new File(dataFileName));
		EventStream es = new BasicEventStream(new PlainTextByLineDataStream(datafr));

		GIS.SMOOTHING_OBSERVATION = SMOOTHING_OBSERVATION;
		AbstractModel model = GIS.trainModel(100, new OnePassRealValueDataIndexer(es, 0), USE_SMOOTHING); // 第一個參數是iterations數

		File outputFile = new File(modelFileName);
		GISModelWriter writer = new SuffixSensitiveGISModelWriter(model, outputFile);
		writer.persist();
	}
	/*public static void maxent(){
		MaxentTagger tagger = new MaxentTagger("models/english-left3words-distsim.tagger");

		String taggedString = tagger.tagString("Here's a tagged string.");
		System.out.println(taggedString);
		//return taggedString;
	}*/
}
