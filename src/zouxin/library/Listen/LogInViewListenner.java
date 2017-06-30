/**
 * 
 */
package zouxin.library.Listen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import zouxin.library.dao.LogInViewDao;
import zouxin.library.view.LogInView;
import zouxin.library.view.MainView;
import zouxin.library.view.TeacherMainView;
import zouxin.library.vo.StorageUserInVo;

/**
 * @author ZouXin
 * 2017-3-26
 */
public class LogInViewListenner implements ActionListener{
     LogInView logInView;
     public LogInViewListenner(LogInView logInView){
    	 this.logInView=logInView;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		LogInViewDao inViewDao=new LogInViewDao();
//		System.out.println(inView.getTextField());
		if(command.equals("��¼")){
			String userId=logInView.getTextField().getText();
//			System.out.println(userId);
			String passWord=new String(logInView.getPasswordField().getPassword());
//			System.out.println(passWord);
			int s=inViewDao.decideUser(userId, passWord);
			System.out.println("���ص��ж��ǹ���Ա�����û������û�����������״ֵ̬ : "+s);//0:�����û������������  1:����Ա 2:ѧ��
			if(s==2){
				new MainView();
				logInView.setVisible(false);
				logInView.dispose();
			}else if(s==1){
				if(StorageUserInVo.manager_sf.equals("��������Ա")){
					new TeacherMainView();
				}else if(StorageUserInVo.manager_sf.equals("����Ա")){
					
				}	
				logInView.setVisible(false);
				logInView.dispose();
			}else if(s==0){
				JOptionPane.showMessageDialog(logInView, "�����˺Ż������������ȷ���˺Ż������Ƿ��������");
				logInView.getPasswordField().setText("");
			}
		}else if(command.equals("ȡ��")){
			logInView.setVisible(false);
			logInView.dispose();
		}
	}
     
}
