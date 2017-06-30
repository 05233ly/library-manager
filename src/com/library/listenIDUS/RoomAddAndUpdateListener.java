/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.LibraryAddAndUpdate;
import zouxin.library.view.RoomAddAndUpdate;

/**
 * @author ZouXin
 *2017-5-11
 */
public class RoomAddAndUpdateListener implements ActionListener{
     RoomAddAndUpdate addAndUpdate;
     public RoomAddAndUpdateListener(RoomAddAndUpdate addAndUpdate){
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
		String location=addAndUpdate.getTextField_3().getText();
		String introduction=addAndUpdate.getTextField_4().getText();
		String rlall=addAndUpdate.getComboBox().getSelectedItem().toString();
		String lnumber=rlall.split("_")[1];
		if(command.equals("ȷ��")){
			if(addAndUpdate.getJudge()==0){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ�����");
				if(select==0){
					String sqll="select count(*) from library_room_information_table lit where lit.library_room_number=?";
					int judge=dao.selectCount(sqll, number);
					if(judge==0){
						String sqlI="insert into library_room_information_table values(?,?,?,?,?)";
						int s=dao.insertUpdateDelete(sqlI, number,lnumber,name,location,introduction);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "��ӳɹ�");
							if(addAndUpdate.getMainView().getTable_2().getSelectedRow()>=0){
								addAndUpdate.getMainView().setVisible(true);
								addAndUpdate.getMainView().getRoomSelect().doClick();
							}
							addAndUpdate.setVisible(false);
							addAndUpdate.dispose();
						}else{
							JOptionPane.showMessageDialog(addAndUpdate, "ϵͳ����");
						}
					}else{
						JOptionPane.showMessageDialog(addAndUpdate, "�ÿ�λ����Ѵ��ڣ�����������");
					}
				}
			}else if(addAndUpdate.getJudge()==1){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ���޸�");
				if(select==0){
					    String sqlU="update library_room_information_table lrit set lrit.library_number=?,lrit.library_room_name=?,lrit.library_room_location=?,lrit.library_room_introduction=? where lrit.library_room_number=?";
						int s=dao.insertUpdateDelete(sqlU,lnumber,name,location,introduction,number);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "�޸ĳɹ�");
							addAndUpdate.getMainView().setVisible(true);
							addAndUpdate.getMainView().getRoomSelect().doClick();
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
