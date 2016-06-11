import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.xml.crypto.Data;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class DisplayMainPanel extends JPanel implements Observer {
	private JTextField txtGongJiJinAmount;
	private JTextField txtShangDaiAmount;
	
	private Calculator calculator;

	static Map<String, Double> shangDaiRateMap = new HashMap<String, Double>(){
		{
			put("2015.6.28 * 110%", 5.94);
			put("2015.6.28 * 100%", 5.4);
			put("2015.6.28 * 85%", 4.59);
			put("2015.6.28 * 70%", 3.78);
			
			put( "2015.8.26 * 110%", 5.67);
			put("2015.8.26 * 100%", 5.15);
			put( "2015.8.26 * 85%", 4.38);
			put("2015.8.26 * 70%", 3.61);
			
			put("2015.10.24 * 110%", 5.39);
			put("2015.10.24 * 100%", 4.9);
			put("2015.10.24 * 95%", 4.66);
			put("2015.10.24 * 88%", 4.31);
			put("2015.10.24 * 85%", 4.17);
			put("2015.10.24 * 70%", 3.43);
		}
	};
	
	static Map<String, Double> gongJiJinRateMap = new HashMap<String, Double>(){
		{
			put("2015.6.28 * 110%", 3.85);
			put("2015.6.28 * 100%", 3.5);
			put("2015.6.28 * 85%", 	2.97);
			put("2015.6.28 * 70%", 	2.45);
			
			put("2015.8.26 * 110%", 3.58);
			put("2015.8.26 * 100%", 3.25);
			put("2015.8.26 * 85%", 	2.76);
			put("2015.8.26 * 70%", 	2.28);
			
			put("2015.10.24 * 110%", 3.58);
			put("2015.10.24 * 100%", 3.25);
			put("2015.10.24 * 95%", 3.09);
			put("2015.10.24 * 88%", 2.86);
			put("2015.10.24 * 85%", 2.76);
			put("2015.10.24 * 70%", 2.28);
		}
	};
	static final List<ComboBoxItem> gongJiJinRateList = new ArrayList<>();
	static {
		for(String display: gongJiJinRateMap.keySet())
		{
			gongJiJinRateList.add(new ComboBoxItem(gongJiJinRateMap.get(display), display));
		}
	}
	static final List<ComboBoxItem> shangDaiRateList = new ArrayList<>();
	static {
		for(String display: shangDaiRateMap.keySet())
		{
			shangDaiRateList.add(new ComboBoxItem(shangDaiRateMap.get(display), display));
		}
	}
			
	private JButton button;
	private JComboBox cmbShangDaiRate;
	private JComboBox cmbShangDaiYear;
	private JComboBox cmbGongJiJinYear;
	private JComboBox cmbGongJiJinRate;
	private JTextArea textArea;
	private JComboBox cbLoanType;

	private void doLoanCalculate() {
		calculator.newTask(getGongJiJinLoanData());
		calculator.newTask(getShangDaiData());
	}

	private LoanData getGongJiJinLoanData() {
		LoanData data = new LoanData();
		data.setAmount(Integer.valueOf(txtGongJiJinAmount.getText().isEmpty() ?  "0" : txtGongJiJinAmount.getText()));
		data.setYear(Integer.valueOf(cmbGongJiJinYear.getSelectedItem().toString()));
		
		double rate = ((ComboBoxItem)cmbGongJiJinRate.getSelectedItem()).getValue() / 100;
		data.setRate(String.valueOf(rate));//"0.0515"
		data.setCalculateType((cbLoanType.getSelectedIndex() == 0) ? LoanData.CAL_TYPE_DENG_E_BEN_JIN
				: LoanData.CAL_TYPE_DENG_E_BEN_XI);
		return data;
	}
	
	private LoanData getShangDaiData() {
		LoanData data = new LoanData();
		data.setAmount(Integer.valueOf(txtShangDaiAmount.getText().isEmpty() ?  "0" : txtShangDaiAmount.getText()));
		data.setYear(Integer.valueOf(cmbShangDaiYear.getSelectedItem().toString()));
		
		double rate = ((ComboBoxItem)cmbShangDaiRate.getSelectedItem()).getValue() / 100;
		data.setRate(String.valueOf(rate));//"0.0515"
		data.setCalculateType((cbLoanType.getSelectedIndex() == 0) ? LoanData.CAL_TYPE_DENG_E_BEN_JIN
				: LoanData.CAL_TYPE_DENG_E_BEN_XI);
		return data;
	}

	List<ResultData> resultList = new ArrayList<>(2);
	
	@Override
	public void update(Observable o, Object result) {
//		resultList.add((ResultData) result);
//		
//		if (resultList.size() > 1) {
//			List<BigDecimal> resultListOne = resultList.get(0).getResultList();
//			List<BigDecimal> resultListTwo = resultList.get(1).getResultList();
//			int smallerListSize = Math.min(resultListOne.size(), resultListTwo.size());
//			int biggerListSize = Math.max(resultListOne.size(), resultListTwo.size());
//			
//			ResultData combinedData = new ResultData();
//			for(int i =0; i<smallerListSize; i++)
//			{
//				combinedData.addResult(resultListOne.get(i).add(resultListTwo.get(i)));
//			}
//			
//			for(int i = smallerListSize; i< biggerListSize;i++) {
////				combinedData.addResult(result);
//			}
//			updateResultTextArea(combinedData);
//		}
//		
//		resultList.clear();
		onCalculateDone(result);
	}

	private void onCalculateDone(Object result) {
		updateResultTextArea(result);
	}

	private void updateResultTextArea(Object result) {
		textArea.setText("");
		int i = 0;
		for (BigDecimal item : ((ResultData) result).getResultList()) {
			textArea.append((i + 1) + ": " + item.toPlainString());
			textArea.append("\n");
			i++;
		}
		textArea.setCaretPosition(0);
	}

	/**
	 * Create the panel.
	 */
	public DisplayMainPanel(Calculator calculator) {
		this.calculator = calculator;
		initGui();
		
	}

	private void initGui() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 146, 0, 0, 46, 0, 12, 109, 24, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 2, 21, 21, 22, 18, 2, 2, 23, 218, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
		JLabel label = new JLabel("\u516C\u79EF\u91D1");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		add(label, gbc_label);

		JLabel label_1 = new JLabel("\u5546\u4E1A\u8D37\u6B3E");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 7;
		gbc_label_1.gridy = 0;
		add(label_1, gbc_label_1);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 10;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);

		JLabel label_2 = new JLabel("\u5229\u7387");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		add(label_2, gbc_label_2);

		cmbGongJiJinRate = new JComboBox();
		Collections.sort(gongJiJinRateList, new Comparator<ComboBoxItem>() {

			@Override
			public int compare(ComboBoxItem o1, ComboBoxItem o2) {
				return o1.getDisplay().compareTo(o2.getDisplay());
			}
		});
		cmbGongJiJinRate.setModel( new DefaultComboBoxModel<>(gongJiJinRateList.toArray()));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_comboBox.insets = new Insets(0, 5, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		add(cmbGongJiJinRate, gbc_comboBox);
		
		JLabel lblPercent = new JLabel("%");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 2;
		add(lblPercent, gbc_label_10);

		JLabel label_3 = new JLabel("\u5229\u7387");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 6;
		gbc_label_3.gridy = 2;
		add(label_3, gbc_label_3);

		cmbShangDaiRate = new JComboBox();
		Collections.sort(shangDaiRateList, new Comparator<ComboBoxItem>() {

			@Override
			public int compare(ComboBoxItem o1, ComboBoxItem o2) {
				return o1.getDisplay().compareTo(o2.getDisplay());
			}
		});
		cmbShangDaiRate.setModel( new DefaultComboBoxModel<>(shangDaiRateList.toArray()));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.gridwidth = 2;
		gbc_comboBox_2.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.gridx = 7;
		gbc_comboBox_2.gridy = 2;
		add(cmbShangDaiRate, gbc_comboBox_2);
		
		JLabel label_13 = new JLabel("%");
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.insets = new Insets(0, 0, 5, 0);
		gbc_label_13.gridx = 9;
		gbc_label_13.gridy = 2;
		add(label_13, gbc_label_13);

		JLabel lblNewLabel = new JLabel("\u5E74\u9650");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);

		cmbGongJiJinYear = new JComboBox();
		cmbGongJiJinYear.setModel(new DefaultComboBoxModel(new String[] { "10", "15", "20", "25", "30" }));
		GridBagConstraints gbc_cmbYear = new GridBagConstraints();
		gbc_cmbYear.anchor = GridBagConstraints.NORTH;
		gbc_cmbYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbYear.insets = new Insets(0, 5, 5, 5);
		gbc_cmbYear.gridx = 1;
		gbc_cmbYear.gridy = 3;
		add(cmbGongJiJinYear, gbc_cmbYear);
		
		JLabel lblYear = new JLabel("\u5E74");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 2;
		gbc_label_7.gridy = 3;
		add(lblYear, gbc_label_7);

		JLabel label_4 = new JLabel("\u5E74\u9650");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 6;
		gbc_label_4.gridy = 3;
		add(label_4, gbc_label_4);

		cmbShangDaiYear = new JComboBox();
		cmbShangDaiYear.setModel(new DefaultComboBoxModel(new String[] { "10", "15", "20", "25", "30" }));
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.gridwidth = 2;
		gbc_comboBox_3.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.gridx = 7;
		gbc_comboBox_3.gridy = 3;
		add(cmbShangDaiYear, gbc_comboBox_3);
		
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.insets = new Insets(0, 0, 5, 0);
		gbc_label_11.gridx = 9;
		gbc_label_11.gridy = 3;
		add(lblYear, gbc_label_11);

		JLabel lblAmount = new JLabel("\u91D1\u989D");
		lblAmount.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 4;
		add(lblAmount, gbc_label_5);

		txtGongJiJinAmount = new JTextField();
		txtGongJiJinAmount.setText("100");
		txtGongJiJinAmount.setColumns(10);
		GridBagConstraints gbc_txtAmount = new GridBagConstraints();
		gbc_txtAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmount.anchor = GridBagConstraints.SOUTHWEST;
		gbc_txtAmount.insets = new Insets(0, 5, 5, 5);
		gbc_txtAmount.gridx = 1;
		gbc_txtAmount.gridy = 4;
		add(txtGongJiJinAmount, gbc_txtAmount);

		JLabel lblW = new JLabel("\u4E07");
		GridBagConstraints gbc_lblW = new GridBagConstraints();
		gbc_lblW.anchor = GridBagConstraints.WEST;
		gbc_lblW.insets = new Insets(0, 0, 5, 5);
		gbc_lblW.gridx = 2;
		gbc_lblW.gridy = 4;
		add(lblW, gbc_lblW);

		JLabel label_6 = new JLabel("\u91D1\u989D");
		label_6.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 6;
		gbc_label_6.gridy = 4;
		add(label_6, gbc_label_6);

		txtShangDaiAmount = new JTextField();
		txtShangDaiAmount.setText("100");
		txtShangDaiAmount.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.anchor = GridBagConstraints.NORTH;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 4;
		add(txtShangDaiAmount, gbc_textField_1);
		
//		JLabel label_12 = new JLabel("\u4E07");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.insets = new Insets(0, 0, 5, 0);
		gbc_label_12.gridx = 9;
		gbc_label_12.gridy = 4;
		add(lblW, gbc_label_12);

		JLabel lblLoanType = new JLabel("\u8FD8\u6B3E\u65B9\u5F0F");
		lblLoanType.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_8.anchor = GridBagConstraints.SOUTH;
		gbc_label_8.insets = new Insets(0, 5, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 6;
		add(lblLoanType, gbc_label_8);

		cbLoanType = new JComboBox();
		cbLoanType.setModel(
				new DefaultComboBoxModel(new String[] { "\u7B49\u989D\u672C\u606F", "\u7B49\u989D\u672C\u91D1" }));
		GridBagConstraints gbc_cbLoanType = new GridBagConstraints();
		gbc_cbLoanType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbLoanType.anchor = GridBagConstraints.NORTHWEST;
		gbc_cbLoanType.insets = new Insets(0, 5, 5, 5);
		gbc_cbLoanType.gridx = 1;
		gbc_cbLoanType.gridy = 6;
		add(cbLoanType, gbc_cbLoanType);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.anchor = GridBagConstraints.NORTH;
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridwidth = 10;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 7;
		add(separator_1, gbc_separator_1);

		button = new JButton("\u8BA1\u7B97");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLoanCalculate();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.gridwidth = 2;
		gbc_button.anchor = GridBagConstraints.NORTHWEST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 7;
		gbc_button.gridy = 8;
		add(button, gbc_button);

		JLabel label_9 = new JLabel("\u8BA1\u7B97\u7ED3\u679C");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = GridBagConstraints.BOTH;
		gbc_label_9.insets = new Insets(0, 5, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 9;
		add(label_9, gbc_label_9);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 9;
		add(scrollPane, gbc_scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	


}

class ComboBoxItem {
	private double value;
	private String display;
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public ComboBoxItem(double value, String display){
		setValue(value);
		setDisplay(display);
	}
	public ComboBoxItem() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return this.value + " ( " + this.display + " ) ";
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
