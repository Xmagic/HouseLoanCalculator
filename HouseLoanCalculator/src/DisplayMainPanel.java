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
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class DisplayMainPanel extends JPanel implements Observer {
	private JTextField textField;
	private JTextField textField_1;
	private CalculateControler _controler;
	private JButton button;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private JTextArea textArea;
	private JComboBox cbLoanType;

	private void doLoanCalculate() {
		LoanData data = getCurrentLoanData();
		calculate(data);
	}

	private void calculate(LoanData data) {
		_controler.calculate(data);
	}

	private LoanData getCurrentLoanData() {
		 LoanData data = new LoanData();
		 data.setAmount(100);
		 data.setYear(30);
		 data.setRate("0.0515");
		 data.setCalculateType( (cbLoanType.getSelectedIndex() == 0 ) ? LoanData.CAL_TYPE_DENG_E_BEN_JIN : LoanData.CAL_TYPE_DENG_E_BEN_XI);
		return data;
	}
	
	@Override
	public void update(Observable o, Object result) {
		onCalculateDone(result);
	}

	private void onCalculateDone(Object result) {
		updateResultTextArea(result);
	}

	private void updateResultTextArea(Object result) {
		textArea.setText("");
		int i =0;
		for(BigDecimal item: ((ResultData) result).getResultList())
		{
			this.textArea.append( (i+1) + ": " + item.toPlainString());
			this.textArea.append("\n");
			i++;
		}
	}
	
	/**
	 * Create the panel.
	 */
	public DisplayMainPanel(CalculateControler controler) {
		_controler = controler;
		initGui();
	}

	private void initGui() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{58, 0, 146, 0, 53, 12, 109, 24, 0, 0};
		gridBagLayout.rowHeights = new int[]{15, 2, 21, 21, 22, 18, 2, 2, 23, 218, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
								JLabel label = new JLabel("\u516C\u79EF\u91D1");
								GridBagConstraints gbc_label = new GridBagConstraints();
								gbc_label.anchor = GridBagConstraints.NORTH;
								gbc_label.insets = new Insets(0, 0, 5, 5);
								gbc_label.gridx = 2;
								gbc_label.gridy = 0;
								add(label, gbc_label);
								
								JLabel label_1 = new JLabel("\u5546\u4E1A\u8D37\u6B3E");
								GridBagConstraints gbc_label_1 = new GridBagConstraints();
								gbc_label_1.insets = new Insets(0, 0, 5, 5);
								gbc_label_1.gridx = 6;
								gbc_label_1.gridy = 0;
								add(label_1, gbc_label_1);
						
								JSeparator separator = new JSeparator();
								GridBagConstraints gbc_separator = new GridBagConstraints();
								gbc_separator.anchor = GridBagConstraints.NORTH;
								gbc_separator.fill = GridBagConstraints.HORIZONTAL;
								gbc_separator.insets = new Insets(0, 0, 5, 0);
								gbc_separator.gridwidth = 9;
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
				
						comboBox = new JComboBox();
						GridBagConstraints gbc_comboBox = new GridBagConstraints();
						gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
						gbc_comboBox.insets = new Insets(0, 0, 5, 5);
						gbc_comboBox.gridx = 2;
						gbc_comboBox.gridy = 2;
						add(comboBox, gbc_comboBox);
												
														JLabel label_3 = new JLabel("\u5229\u7387");
														label_3.setHorizontalAlignment(SwingConstants.TRAILING);
														GridBagConstraints gbc_label_3 = new GridBagConstraints();
														gbc_label_3.fill = GridBagConstraints.HORIZONTAL;
														gbc_label_3.anchor = GridBagConstraints.WEST;
														gbc_label_3.insets = new Insets(0, 0, 5, 5);
														gbc_label_3.gridx = 5;
														gbc_label_3.gridy = 2;
														add(label_3, gbc_label_3);
										
												comboBox_2 = new JComboBox();
												GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
												gbc_comboBox_2.gridwidth = 2;
												gbc_comboBox_2.anchor = GridBagConstraints.NORTH;
												gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
												gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
												gbc_comboBox_2.gridx = 6;
												gbc_comboBox_2.gridy = 2;
												add(comboBox_2, gbc_comboBox_2);
								
										JLabel lblNewLabel = new JLabel("\u5E74\u9650");
										lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
										GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
										gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
										gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
										gbc_lblNewLabel.gridx = 0;
										gbc_lblNewLabel.gridy = 3;
										add(lblNewLabel, gbc_lblNewLabel);
								
										comboBox_1 = new JComboBox();
										comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "10", "15", "20", "25", "30" }));
										GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
										gbc_comboBox_1.anchor = GridBagConstraints.NORTH;
										gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
										gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
										gbc_comboBox_1.gridx = 2;
										gbc_comboBox_1.gridy = 3;
										add(comboBox_1, gbc_comboBox_1);
						
								JLabel label_4 = new JLabel("\u5E74\u9650");
								label_4.setHorizontalAlignment(SwingConstants.TRAILING);
								GridBagConstraints gbc_label_4 = new GridBagConstraints();
								gbc_label_4.fill = GridBagConstraints.HORIZONTAL;
								gbc_label_4.anchor = GridBagConstraints.WEST;
								gbc_label_4.insets = new Insets(0, 0, 5, 5);
								gbc_label_4.gridx = 5;
								gbc_label_4.gridy = 3;
								add(label_4, gbc_label_4);
						
								comboBox_3 = new JComboBox();
								comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "10", "15", "20", "25", "30" }));
								GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
								gbc_comboBox_3.gridwidth = 2;
								gbc_comboBox_3.anchor = GridBagConstraints.NORTH;
								gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
								gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
								gbc_comboBox_3.gridx = 6;
								gbc_comboBox_3.gridy = 3;
								add(comboBox_3, gbc_comboBox_3);
				
						JLabel label_5 = new JLabel("\u91D1\u989D");
						label_5.setHorizontalAlignment(SwingConstants.TRAILING);
						GridBagConstraints gbc_label_5 = new GridBagConstraints();
						gbc_label_5.fill = GridBagConstraints.HORIZONTAL;
						gbc_label_5.insets = new Insets(0, 0, 5, 5);
						gbc_label_5.gridx = 0;
						gbc_label_5.gridy = 4;
						add(label_5, gbc_label_5);
						
								textField = new JTextField();
								textField.setText("100");
								textField.setColumns(10);
								GridBagConstraints gbc_textField = new GridBagConstraints();
								gbc_textField.fill = GridBagConstraints.HORIZONTAL;
								gbc_textField.anchor = GridBagConstraints.SOUTHWEST;
								gbc_textField.insets = new Insets(0, 0, 5, 5);
								gbc_textField.gridx = 2;
								gbc_textField.gridy = 4;
								add(textField, gbc_textField);
				
						JLabel lblW = new JLabel("\u4E07");
						GridBagConstraints gbc_lblW = new GridBagConstraints();
						gbc_lblW.anchor = GridBagConstraints.WEST;
						gbc_lblW.insets = new Insets(0, 0, 5, 5);
						gbc_lblW.gridx = 3;
						gbc_lblW.gridy = 4;
						add(lblW, gbc_lblW);
						
								JLabel label_6 = new JLabel("\u91D1\u989D");
								label_6.setHorizontalAlignment(SwingConstants.TRAILING);
								GridBagConstraints gbc_label_6 = new GridBagConstraints();
								gbc_label_6.fill = GridBagConstraints.HORIZONTAL;
								gbc_label_6.anchor = GridBagConstraints.WEST;
								gbc_label_6.insets = new Insets(0, 0, 5, 5);
								gbc_label_6.gridx = 5;
								gbc_label_6.gridy = 4;
								add(label_6, gbc_label_6);
						
								textField_1 = new JTextField();
								textField_1.setText("100");
								textField_1.setColumns(10);
								GridBagConstraints gbc_textField_1 = new GridBagConstraints();
								gbc_textField_1.gridwidth = 2;
								gbc_textField_1.anchor = GridBagConstraints.NORTH;
								gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
								gbc_textField_1.insets = new Insets(0, 0, 5, 5);
								gbc_textField_1.gridx = 6;
								gbc_textField_1.gridy = 4;
								add(textField_1, gbc_textField_1);
				
						JLabel label_7 = new JLabel("\u4E07");
						GridBagConstraints gbc_label_7 = new GridBagConstraints();
						gbc_label_7.anchor = GridBagConstraints.WEST;
						gbc_label_7.insets = new Insets(0, 0, 5, 0);
						gbc_label_7.gridx = 8;
						gbc_label_7.gridy = 4;
						add(label_7, gbc_label_7);
						
								JLabel label_8 = new JLabel("\u8FD8\u6B3E\u65B9\u5F0F");
								label_8.setHorizontalAlignment(SwingConstants.TRAILING);
								GridBagConstraints gbc_label_8 = new GridBagConstraints();
								gbc_label_8.anchor = GridBagConstraints.SOUTHEAST;
								gbc_label_8.insets = new Insets(0, 0, 5, 5);
								gbc_label_8.gridx = 0;
								gbc_label_8.gridy = 6;
								add(label_8, gbc_label_8);
						
						cbLoanType = new JComboBox();
						cbLoanType.setModel(new DefaultComboBoxModel(new String[] {"\u7B49\u989D\u672C\u606F", "\u7B49\u989D\u672C\u91D1"}));
						GridBagConstraints gbc_cbLoanType = new GridBagConstraints();
						gbc_cbLoanType.fill = GridBagConstraints.HORIZONTAL;
						gbc_cbLoanType.anchor = GridBagConstraints.NORTHWEST;
						gbc_cbLoanType.insets = new Insets(0, 0, 5, 5);
						gbc_cbLoanType.gridx = 2;
						gbc_cbLoanType.gridy = 6;
						add(cbLoanType, gbc_cbLoanType);
				
						JSeparator separator_1 = new JSeparator();
						GridBagConstraints gbc_separator_1 = new GridBagConstraints();
						gbc_separator_1.anchor = GridBagConstraints.NORTH;
						gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
						gbc_separator_1.insets = new Insets(0, 0, 5, 0);
						gbc_separator_1.gridwidth = 9;
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
						gbc_button.gridx = 6;
						gbc_button.gridy = 8;
						add(button, gbc_button);
						
						JLabel label_9 = new JLabel("\u8BA1\u7B97\u7ED3\u679C");
						label_9.setHorizontalAlignment(SwingConstants.TRAILING);
						label_9.setVerticalAlignment(SwingConstants.TOP);
						GridBagConstraints gbc_label_9 = new GridBagConstraints();
						gbc_label_9.fill = GridBagConstraints.BOTH;
						gbc_label_9.insets = new Insets(0, 0, 5, 5);
						gbc_label_9.gridx = 0;
						gbc_label_9.gridy = 9;
						add(label_9, gbc_label_9);
						
						JScrollPane scrollPane = new JScrollPane();
						GridBagConstraints gbc_scrollPane = new GridBagConstraints();
						gbc_scrollPane.gridwidth = 6;
						gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
						gbc_scrollPane.fill = GridBagConstraints.BOTH;
						gbc_scrollPane.gridx = 2;
						gbc_scrollPane.gridy = 9;
						add(scrollPane, gbc_scrollPane);
						
						textArea = new JTextArea();
						scrollPane.setViewportView(textArea);
	}

	
}
