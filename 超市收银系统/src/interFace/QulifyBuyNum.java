package interFace;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QulifyBuyNum extends JFrame {

	private JPanel contentPane;
	private JTextField txtNum;
	private JTextField txtID;
	private JButton btn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QulifyBuyNum frame = new QulifyBuyNum();
					frame.setVisible(true);
					frame.getBtn().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String str=frame.getTxtID().getText();
					      //System.out.println();
					      System.out.println("aa"+str);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QulifyBuyNum() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("编号：");
		lblNewLabel.setBounds(76, 67, 81, 21);
		contentPane.add(lblNewLabel);
		
		txtNum = new JTextField();
		txtNum.setBounds(225, 119, 96, 27);
		contentPane.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("购买数量：");
		lblNewLabel_1.setBounds(76, 122, 81, 21);
		contentPane.add(lblNewLabel_1);
		
		txtID = new JTextField();
		txtID.setBounds(225, 64, 96, 27);
		contentPane.add(txtID);
		txtID.setColumns(10);
		btn = new JButton("确定");
		
		
		btn.setBounds(134, 173, 123, 29);
		contentPane.add(btn);
		
	}

	public JButton getBtn() {
		return btn;
	}

	public JTextField getTxtNum() {
		return txtNum;
	}

	public JTextField getTxtID() {
		return txtID;
	}

}
