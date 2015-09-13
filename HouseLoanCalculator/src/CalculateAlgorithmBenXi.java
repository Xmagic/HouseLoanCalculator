import java.math.BigDecimal;

public class CalculateAlgorithmBenXi implements CalculateAlgorithm {

	@Override
	public ResultData doCalculate(LoanData data) {
		try {
			BigDecimal rateYear = new BigDecimal(data.getRate());

			BigDecimal rateMon = rateYear.divide(new BigDecimal("12"), BigDecimal.ROUND_HALF_UP);
			BigDecimal amount = new BigDecimal(data.getAmount() * 10000);
			BigDecimal monthCount = new BigDecimal(data.getYear() * 12);

			BigDecimal upSide = amount.multiply(rateMon).multiply(    rateMon.add(new BigDecimal(1)).pow(monthCount.intValue()));
			BigDecimal downSide = rateMon.add(new BigDecimal(1)).pow(monthCount.intValue()).subtract(new BigDecimal(1));

			BigDecimal result = upSide.divide(downSide, BigDecimal.ROUND_HALF_UP);
			
			ResultData resultData = new ResultData();
			resultData.addResult(result);
			return resultData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
