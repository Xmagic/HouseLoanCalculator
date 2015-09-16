import java.util.List;
import java.util.Observable;

import javax.swing.SwingWorker;

public class CalculateControler extends Observable {

	private static CalculateControler _instance = null;

	/**
	 * Make constructor private, make sure no one can new this class outside.
	 */
	private CalculateControler() {
	}

	public static CalculateControler getInstance() {
		if (null == _instance)
			_instance = new CalculateControler();
		return _instance;
	}

	public void calculate(final LoanData data) {
		SwingWorker<LoanData, ResultData> worker = new SwingWorker<LoanData, ResultData>() {

			@Override
			protected LoanData doInBackground() throws Exception {
				// do calculate
				CalculateAlgorithm algorithm = CalculateAlgorithmFactory.getAlgorithm(data);
				ResultData result = algorithm.doCalculate(data);
				publish(result); // publish to process(chunks)
				return null;
			}

			@Override
			protected void process(List<ResultData> chunks) {

				for (ResultData resultData : chunks) {
					updateUIWithResult(resultData);
				}
			}
		};
		worker.execute();
	}

	public void updateUIWithResult(ResultData result) {
		this.setChanged();
		this.notifyObservers(result);
		this.clearChanged();
	}

}
