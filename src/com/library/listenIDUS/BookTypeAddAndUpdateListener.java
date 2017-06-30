/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.BookTypeAddAndUpdate;
import zouxin.library.view.LibraryAddAndUpdate;
import zouxin.library.view.LocationAddAndUpdate;
import zouxin.library.view.RoomAddAndUpdate;

/**
 * @author ZouXin
 *2017-5-11
 */
public class BookTypeAddAndUpdateListener implements ActionListener{
     BookTypeAddAndUpdate addAndUpdate;
     public BookTypeAddAndUpdateListener(BookTypeAddAndUpdate addAndUpdate){
    	 this.addAndUpdate=addAndUpdate;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MainViewDao dao=new MainViewDao();
		String command=e.getActionCommand();
		String number=addAndUpdate.getTextField().getText();
		String name=addAndUpdate.getTextField_2().getText();
		String introduction=addAndUpdate.getTextField_3().getText();
		if(command.equals("ȷ��")){
			if(addAndUpdate.getJudge()==0){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ�����");
				if(select==0){
					String sqll="select count(*) from book_type_table btt where btt.book_type_num=?";
					int judge=dao.selectCount(sqll, number);
					if(judge==0){
						String sqlI="insert into book_type_table values(?,?,?)";
						int s=dao.insertUpdateDelete(sqlI, number,name,introduction);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "��ӳɹ�");
							if(addAndUpdate.getMainView().getTable_5().getSelectedRow()>=0){
								addAndUpdate.getMainView().setVisible(true);
								addAndUpdate.getMainView().getTypeSelect().doClick();
							}
							addAndUpdate.setVisible(false);
							addAndUpdate.dispose();
						}else{
							JOptionPane.showMessageDialog(addAndUpdate, "ϵͳ����");
						}
					}else{
						JOptionPane.showMessageDialog(addAndUpdate, "���鼮���ͱ���Ѵ��ڣ�����������");
					}
				}
			}else if(addAndUpdate.getJudge()==1){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ���޸�");
				if(select==0){
					    String sqlU="update book_type_table btt set btt.book_type_name=?,btt.book_type_introduction=? where btt.book_type_num=?";
						int s=dao.insertUpdateDelete(sqlU,introduction,name,number);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "�޸ĳɹ�");
							addAndUpdate.getMainView().setVisible(true);
							addAndUpdate.getMainView().getTypeSelect().doClick();
							addAndUpdate.setVisible(false);
							addAndUpdate.dispose();
							
						}else{
							JOptionPane.showMessageDialog(addAndUpdate, "ϵͳ����");
						}
					
				}
			}
		}else if(command.equals("ȡ��")){
			addAndUpdate.setVisible(false);
			addAndUpdate.dispose();
		}
	}
      
}
