/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.LibraryAddAndUpdate;
import zouxin.library.view.LocationAddAndUpdate;
import zouxin.library.view.RoomAddAndUpdate;

/**
 * @author ZouXin
 *2017-5-11
 */
public class LocationAddAndUpdateListener implements ActionListener{
     LocationAddAndUpdate addAndUpdate;
     public LocationAddAndUpdateListener(LocationAddAndUpdate addAndUpdate){
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
		String rlall=addAndUpdate.getComboBox().getSelectedItem().toString();
		String llrall=addAndUpdate.getComboBox_1().getSelectedItem().toString();
		String rnumber=llrall.split("_")[1];
		if(command.equals("ȷ��")){
			if(addAndUpdate.getJudge()==0){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ�����");
				if(select==0){
					String sqll="select count(*) from location_table lt where lt.location_number=?";
					int judge=dao.selectCount(sqll, number);
					if(judge==0){
						String sqlI="insert into location_table values(?,?,?,?)";
						int s=dao.insertUpdateDelete(sqlI, number,rnumber,introduction,name);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "��ӳɹ�");
							if(addAndUpdate.getMainView().getTable_3().getSelectedRow()>=0){
								addAndUpdate.getMainView().setVisible(true);
								addAndUpdate.getMainView().getLocationSelect().doClick();
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
					    String sqlU="update location_table lt set lt.library_room_number=?,lt.location_introduction=?,lt.location_name=? where lt.location_number=?";
						int s=dao.insertUpdateDelete(sqlU,rnumber,introduction,name,number);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "�޸ĳɹ�");
							addAndUpdate.getMainView().setVisible(true);
							addAndUpdate.getMainView().getLocationSelect().doClick();
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
