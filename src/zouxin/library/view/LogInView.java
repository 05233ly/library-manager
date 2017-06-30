package zouxin.library.view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;

import zouxin.library.Listen.GeneralMethod;
import zouxin.library.Listen.LogInViewListenner;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class LogInView extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public JPasswordField passwordField;
    
	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
    

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(new SubstanceLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
//          SubstanceLookAndFeel.setSkin(new EmeraldDuskSkin());
//          SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
//          SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
//          SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
//            SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
//            SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitePainter());
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInView frame = new LogInView();
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
	
	public LogInView() {
		super.setTitle("԰��ͼ�����ϵͳ");//���ñ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ÿɹر�
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�û���");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(92, 33, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setOpaque(false);
		textField.setBounds(194, 30, 143, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(92, 88, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("��¼");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(65, 166, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ȡ��");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBounds(253, 166, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("��ӭ����**ѧУͼ���");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 12));
		lblNewLabel_2.setBounds(154, 5, 137, 15);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setOpaque(false);
		passwordField.setBounds(194, 85, 143, 21);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {  
	            ImageIcon icon =GeneralMethod.createImage("/images/book1.jpg"); 
	            g.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(),  
	            icon.getImageObserver());  
	            }  
		};
		lblNewLabel_3.setBounds(0, 0, 444, 272);
		contentPane.add(lblNewLabel_3);
		//Ϊ��ť���ע�������
		LogInViewListenner lil=new LogInViewListenner(this);
		btnNewButton.addActionListener(lil);
		btnNewButton_1.addActionListener(lil);
		
		//���ô��ڲ��ɸı��С
		super.setResizable(false);
		//���ô��ھ���
		super.setLocationRelativeTo(null);
		//���ô����ܹ������رգ�ͨ����ť
		super.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing ( WindowEvent e ){
				int showNum=JOptionPane.showConfirmDialog(null, "�Ƿ�ر�ϵͳ", "������ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(showNum==JOptionPane.YES_OPTION){
					System.exit(0);
				}else{
					return;
				}
			}
		});
	}
	
}
