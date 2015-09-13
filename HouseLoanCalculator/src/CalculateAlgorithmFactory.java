

public class CalculateAlgorithmFactory {
	
	public static CalculateAlgorithm getAlgorithm(LoanData data) {
		
		String calType = data.getCalculateType();
		
		if (LoanData.CAL_TYPE_DENG_E_BEN_JIN.equalsIgnoreCase(calType))
			return new CalculateAlgorithmBenJin();
		else if (LoanData.CAL_TYPE_DENG_E_BEN_XI.equalsIgnoreCase(calType))
			return new CalculateAlgorithmBenXi();

		throw new IllegalArgumentException("Type: " + calType + " is Illegal");
	}
}
