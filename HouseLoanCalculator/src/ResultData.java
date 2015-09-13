import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResultData {
private double result = 0;
private List<BigDecimal> resultList = new ArrayList<BigDecimal>();
	
	public ResultData(double doubleValue) {
		// TODO Auto-generated constructor stub
		setResult(doubleValue);
	}

	public ResultData() {
		// TODO Auto-generated constructor stub
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
	
	public void addResult(BigDecimal result){
		resultList.add(result);
	}
	
	public  List<BigDecimal> getResultList()
	{
		return resultList;
	}

}
