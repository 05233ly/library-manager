/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.LibraryAddAndUpdate;

/**
 * @author ZouXin
 *2017-5-11
 */
public class LbraryAddAndUpdateListener implements ActionListener{
     LibraryAddAndUpdate addAndUpdate;
     public LbraryAddAndUpdateListener(LibraryAddAndUpdate addAndUpdate){
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
		String name=addAndUpdate.getTextField_1().getText();
		String location=addAndUpdate.getTextField_2().getText();
		String introduction=addAndUpdate.getTextField_3().getText();
		String fw=addAndUpdate.getTextField_4().getText();
		String gc=addAndUpdate.getTextField_5().getText();
		if(command.equals("ȷ��")){
			if(addAndUpdate.getJudge()==0){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ�����");
				if(select==0){
					String sqll="select count(*) from library_information_table lit where lit.library_number=?";
					int judge=dao.selectCount(sqll, number);
					if(judge==0){
						String sqlI="insert into library_information_table values(?,?,?,?,?,?)";
						int s=dao.insertUpdateDelete(sqlI, number,name,location,introduction,fw,gc);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "��ӳɹ�");
							if(addAndUpdate.getMainView().getTable_1().getSelectedRow()>=0){
								addAndUpdate.getMainView().setVisible(true);
								addAndUpdate.getMainView().getLibrarySelect().doClick();
							}
							addAndUpdate.setVisible(false);
							addAndUpdate.dispose();
						}else{
							JOptionPane.showMessageDialog(addAndUpdate, "ϵͳ����");
						}
					}else{
						JOptionPane.showMessageDialog(addAndUpdate, "��ͼ��ݱ���Ѵ��ڣ�����������");
					}
				}
			}else if(addAndUpdate.getJudge()==1){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "�Ƿ�ȷ���޸�");
				if(select==0){
					    String sqlU="update library_information_table lit set lit.library_name=?,lit.library_location=?,lit.library_introduction=?,lit.library_service_content=?,lit.library_collection_type=? where lit.library_number=?";
						int s=dao.insertUpdateDelete(sqlU,name,location,introduction,fw,gc,number);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "�޸ĳɹ�");
							addAndUpdate.getMainView().setVisible(true);
							addAndUpdate.getMainView().getLibrarySelect().doClick();
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
