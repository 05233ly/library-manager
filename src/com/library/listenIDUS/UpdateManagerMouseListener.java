/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.MainView;
import zouxin.library.view.TeacherMainView;
import zouxin.library.vo.StorageUserInVo;

/**
 * @author ZouXin
 *2017-4-10
 */
public class UpdateManagerMouseListener implements MouseListener{
    TeacherMainView mainView;
    
    
    public UpdateManagerMouseListener(TeacherMainView mainView){
    	this.mainView=mainView;
    }
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String passWord=mainView.getTextField_15().getText();//��ȡ�����ֵ
		System.out.println(passWord);
		String sql="select count(*) from manager_information_table mit where mit.manager_user_id=? and mit.manager_password=?";
		MainViewDao dao=new MainViewDao();
		Object[] obj=new Object[]{StorageUserInVo.userId,passWord};
		int i=dao.selectStu(sql, obj);
		if(i==0){
			mainView.getLblNewLabel_14().setText("�������������������ȷ����");
		}else if(i==1){
			mainView.getLblNewLabel_14().setText("������ȷ");
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 * �����ָ���textfield3���Ƴ�ʱ���ж������Ƿ���ȷ
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
//		String passWord=mainView.getTextField_3().getText();//��ȡ�����ֵ
//		System.out.println(passWord);
//		String sql="select count(*) from student_information_table sit where sit.student_number=? and sit.student_password=?";
//		MainViewDao dao=new MainViewDao();
//		Object[] obj=new Object[]{StorageUserInVo.userId,passWord};
//		int i=dao.selectStu(sql, obj);
//		if(i==0){
//			mainView.getLblNewLabel_9().setText("�������������������ȷ����");
//		}else if(i==1){
//			mainView.getLblNewLabel_9().setText("������ȷ");
//		}
//		System.out.println(i);
		
	}

}
