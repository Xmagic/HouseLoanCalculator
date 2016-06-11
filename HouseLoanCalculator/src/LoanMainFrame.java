import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoanMainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanMainFrame frame = new LoanMainFrame();
					CalculateControler controler = CalculateControler.getInstance();
					Calculator calculator = new Calculator(controler);
					DisplayMainPanel displayPanel = new DisplayMainPanel(calculator);
					controler.addObserver(calculator);//Calculator watches for controler's result
					calculator.addObserver(displayPanel);// DisplayPanel watches result outputs from calculator
					
					
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frame.getContentPane().add(displayPanel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoanMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
