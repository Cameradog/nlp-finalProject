package classifer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import opennlp.maxent.BasicContextGenerator;
import opennlp.maxent.ContextGenerator;
import opennlp.model.GenericModelReader;
import opennlp.model.MaxentModel;

public class MaxEnt_predict {
	static MaxentModel _model;
	ContextGenerator _cg = new BasicContextGenerator();

	public MaxEnt_predict(MaxentModel m) {
		_model = m;
	}

	public static void main(String[] args) {
		/*這裡改訓練資料，和model檔名*/
		String dataFileName = "test.txt", modelFileName = "trainingModel.txt";

		MaxEnt_predict predictor = null;
		try {
			MaxentModel m = new GenericModelReader(new File(modelFileName)).getModel();
			predictor = new MaxEnt_predict(m);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
	        BufferedReader br = new BufferedReader(new FileReader(dataFileName));
	        //一行一行讀
			while (br.ready()) {
				String line = br.readLine();

				String[] contexts = line.split(" ");
				double[] ocs = _model.eval(contexts); //ocs: 每一個instance的top-n結果的信心值
				
				/*輸出在這裡*/
				System.out.println("(" + _model.getBestOutcome(ocs) + ", "+ ocs[ocs.length-1] + ") " + line);	
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Unable to read from specified file: " + modelFileName);
			System.out.println();
			e.printStackTrace();
		}
	}
}
