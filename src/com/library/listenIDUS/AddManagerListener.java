/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.TeacherMainView;

/**
 * @author ZouXin
 *2017-5-14
 */
public class AddManagerListener implements ActionListener{
     TeacherMainView mainView;
     public AddManagerListener(TeacherMainView mainView){
    	 this.mainView=mainView;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		String username=mainView.getTextField_10().getText();
		String password=mainView.getTextField_11().getText();
		String name=mainView.getTextField_12().getText();
		String age=mainView.getTextField_13().getText();
		String card=mainView.getTextField_14().getText();
		String telphone=mainView.getTextField_18().getText();
		String sex="";
		if(mainView.getRadioMan().isSelected()){
			sex="��";
		}
		if(mainView.getRadioWuman().isSelected()){
			sex="Ů";
		}
		MainViewDao dao=new MainViewDao();
		if(command.equals("addManager")){
			if(!username.equals("")&&!password.equals("")&&!name.equals("")&&!age.equals("")&&!card.equals("")&&!telphone.equals("")&&!sex.equals("")){
				int select = JOptionPane.showConfirmDialog(mainView, "�Ƿ���ӹ���Ա");
				if(select==0){
					String sqlcount="select count(*) from manager_information_table mit where mit.manager_user_id=?";
					int count=dao.selectCount(sqlcount, username);
					if(count>0){
						JOptionPane.showMessageDialog(mainView, "���˺��Ѵ���,����������");
					}else{
						String sql="insert into manager_information_table values(?,?,?,?,?,?,'����Ա')";
						int s=dao.insertUpdateDelete(sql, username,password,name,sex,age,card);
						if(s==1){
							JOptionPane.showMessageDialog(mainView, "����Ա��ӳɹ�");
							mainView.getTextField_10().setText("");
							mainView.getTextField_11().setText("");
							mainView.getTextField_12().setText("");
							mainView.getTextField_13().setText("");
							mainView.getTextField_14().setText("");
							mainView.getTextField_18().setText("");
						}else{
							JOptionPane.showMessageDialog(mainView, "ϵͳ����");
						}
					}
				}
			}else{
				JOptionPane.showMessageDialog(mainView, "����δ��д��ֵ�������ƿո�");
			}
		}else if(command.equals("resetAdd")){
			mainView.getTextField_10().setText("");
			mainView.getTextField_11().setText("");
			mainView.getTextField_12().setText("");
			mainView.getTextField_13().setText("");
			mainView.getTextField_14().setText("");
			mainView.getTextField_18().setText("");
		}
	}

}
