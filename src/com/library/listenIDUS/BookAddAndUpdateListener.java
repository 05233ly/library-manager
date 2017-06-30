/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.BookAddAndUpdate;
import zouxin.library.view.LibraryAddAndUpdate;
import zouxin.library.view.RoomAddAndUpdate;

/**
 * @author ZouXin
 *2017-5-11
 */
public class BookAddAndUpdateListener implements ActionListener{
     BookAddAndUpdate addAndUpdate;
     public BookAddAndUpdateListener(BookAddAndUpdate addAndUpdate){
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
		String bnumber=addAndUpdate.getTextField().getText();
		String bname=addAndUpdate.getTextField_2().getText();
		String btype=addAndUpdate.getComboBox().getSelectedItem().toString();
		String price=addAndUpdate.getTextField_3().getText();
		String bbarcode=addAndUpdate.getTextField_4().getText();
		String bauther=addAndUpdate.getTextField_5().getText();
		String bcbs=addAndUpdate.getTextField_1().getText();
		String typenumber=btype.split("_")[1];
		if(command.equals("ȷ��")){
			if(addAndUpdate.getJudge()==0){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ�����");
				if(select==0){
					String sqll="select count(*) from book_information_table bit where bit.book_number=?";
					int judge=dao.selectCount(sqll, bnumber);
					if(judge==0){
						String sqlI="insert into book_information_table values(?,?,?,?,?,'1',?,?)";
						int s=dao.insertUpdateDelete(sqlI, bnumber,typenumber,bname,price,bbarcode,bauther,bcbs);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "��ӳɹ�");
							if(addAndUpdate.getMainView().getTable_4().getSelectedRow()>=0){
								addAndUpdate.getMainView().setVisible(true);
								addAndUpdate.getMainView().getBookSelect().doClick();
							}
							addAndUpdate.setVisible(false);
							addAndUpdate.dispose();
						}else{
							JOptionPane.showMessageDialog(addAndUpdate, "ϵͳ����");
						}
					}else{
						JOptionPane.showMessageDialog(addAndUpdate, "���鼮����Ѵ��ڣ�����������");
					}
				}
			}else if(addAndUpdate.getJudge()==1){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ���޸�");
				if(select==0){
					    String sqlU="update book_information_table bit set bit.book_type_num=?,bit.book_name=?,bit.book_price=?,bit.book_bar_code=?,bit.book_author=?,bit.bookr_press=? where bit.book_number=? ";
						int s=dao.insertUpdateDelete(sqlU,typenumber,bname,price,bbarcode,bauther,bcbs,bnumber);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "�޸ĳɹ�");
							addAndUpdate.getMainView().setVisible(true);
							addAndUpdate.getMainView().getBookSelect().doClick();
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
