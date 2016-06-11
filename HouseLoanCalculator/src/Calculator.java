import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Calculator extends Observable implements Observer{

	private CalculateControler _controler;
	
	List<ResultData> resultDataList = new ArrayList<>();
	
	int taskCount = 0;
	
	public Calculator(CalculateControler controler) {
		_controler = controler;
	}

	public void newTask(LoanData data) {
		_controler.calculate(data);
		taskCount++;
	}
	
	/**
	 * Receive result from CalculateControler, be ready to combine
	 */
	@Override
	public void update(Observable o, Object result) {
		resultDataList.add((ResultData) result);
		
		if (resultDataList.size() >= taskCount) {
			ResultData combinedData = resultDataList.get(0);
			for (int i = 0; i < resultDataList.size(); i++) {
				if (i + 1 < resultDataList.size()) {
					combinedData = combineResult(combinedData, resultDataList.get(i + 1));
				}
			}
			onCombineFinished(combinedData);
			resultDataList.clear();
			taskCount =0;
		}
	}
	
	public ResultData combineResult(ResultData dataOne, ResultData dataTwo) {
		List<BigDecimal> resultListOne = dataOne.getResultList();
		List<BigDecimal> resultListTwo = dataTwo.getResultList();
		int smallerListSize = Math.min(resultListOne.size(), resultListTwo.size());
		int biggerListSize = Math.max(resultListOne.size(), resultListTwo.size());

		ResultData combinedData = new ResultData();
		for (int i = 0; i < smallerListSize; i++) {
			combinedData.addResult(resultListOne.get(i).add(resultListTwo.get(i)));
		}

		for (int i = smallerListSize; i < biggerListSize; i++) {
			List<BigDecimal> biggerList = resultListOne.size() > resultListTwo.size() ? resultListOne : resultListTwo;
			combinedData.addResult(biggerList.get(i));
		}

		return combinedData;
	}
	
	public void onCombineFinished(Object combinedData) {
		this.setChanged();
		this.notifyObservers(combinedData);// Send result to DisplayMainPanel
		this.clearChanged();
//		updateResultTextArea(combinedData);
	}

}
