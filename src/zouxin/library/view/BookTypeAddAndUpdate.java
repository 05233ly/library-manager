/**
 * 
 */
package zouxin.library.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.library.listenIDUS.BookTypeAddAndUpdateListener;

/**
 * @author ZouXin
 *2017-5-11
 */
public class BookTypeAddAndUpdate extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public JTextField textField_2;
	public JTextField textField_3;
    public int judge;
    public TeacherMainView mainView;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BookTypeAddAndUpdate frame = new BookTypeAddAndUpdate();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public BookTypeAddAndUpdate(int judge,TeacherMainView mainView) {
		this.judge=judge;
		this.mainView=mainView;
		setBounds(100, 100, 330, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("���");
		lblNewLabel.setBounds(35, 26, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(144, 23, 136, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("����");
		label_1.setBounds(35, 93, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_4 = new JLabel("����");
		label_4.setBounds(35, 170, 54, 15);
		contentPane.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(144, 90, 136, 21);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(144, 167, 136, 21);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("ȷ��");
		btnNewButton.setBounds(23, 229, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ȡ��");
		btnNewButton_1.setBounds(169, 229, 93, 23);
		contentPane.add(btnNewButton_1);
		
		
		BookTypeAddAndUpdateListener bt=new BookTypeAddAndUpdateListener(this);
		btnNewButton.addActionListener(bt);
		btnNewButton_1.addActionListener(bt);
		
		
		
		
		//���ô���Ϊ�ɼ�
		   super.setVisible(true);
		   //���ô��ھ���
		   super.setLocationRelativeTo(null);
		   //���ô��ڲ����Ըı��С
		   super.setResizable(false);
		   //���ô����ܹ������رգ�ͨ����ť
		   super.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
			//Ϊ��������һ�����������������û��Ƿ�ر�ϵͳ
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	public int getJudge() {
		return judge;
	}

	public void setJudge(int judge) {
		this.judge = judge;
	}

	public TeacherMainView getMainView() {
		return mainView;
	}

	public void setMainView(TeacherMainView mainView) {
		this.mainView = mainView;
	}
	
}
