
import java.math.BigDecimal;

public class CalculateAlgorithmBenJin implements CalculateAlgorithm {

	@Override
	public ResultData doCalculate(LoanData data) {
		BigDecimal rateYear = new BigDecimal(data.getRate());

		BigDecimal rateMon = rateYear.divide(new BigDecimal("12"), BigDecimal.ROUND_HALF_UP);
		BigDecimal amount = new BigDecimal(data.getAmount() * 10000);
		BigDecimal monthCount = new BigDecimal(data.getYear() * 12);
		
		ResultData result = new ResultData();
		
		
		for (int i = 0; i < data.getYear() * 12; i++) {
			BigDecimal interest = rateMon.multiply(amount.subtract( amount.divide(monthCount , BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(i))));
			BigDecimal loan = amount.divide(monthCount, BigDecimal.ROUND_HALF_UP);
			result.addResult(interest.add(loan));
		}
		
		return result;
//		System.out.println(resultList);
	}

}
