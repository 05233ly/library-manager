/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.Instore;

/**
 * @author ZouXin
 *2017-5-16
 */
public class InStoreListener implements ActionListener{
    Instore instore;
    public InStoreListener(Instore instore){
    	this.instore=instore;
    }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		MainViewDao dao=new MainViewDao();
		if(command.equals("ȷ��")){
			int select=JOptionPane.showConfirmDialog(instore, "�Ƿ�ȷ�����");
			if(select==0){
				String quantity=instore.getTextField().getText();
//				String library=instore.getComboBox().getSelectedItem().toString().split("_")[1];
//				String room=instore.getComboBox_1().getSelectedItem().toString().split("_")[1];
				String location=instore.getComboBox_2().getSelectedItem().toString().split("_")[1];
				String book=instore.getTextField_1().getText().split("_")[1];
				int s=dao.updateSurplusQuantity(book, location, Integer.parseInt(quantity), 0);
				if(s==1){
					JOptionPane.showMessageDialog(instore, "���ɹ�");
					instore.setVisible(false);
					instore.dispose();
				}else{
					JOptionPane.showMessageDialog(instore, "ϵͳ����");
				}
			}		
		}else if(command.equals("ȡ��")){
			int select=JOptionPane.showConfirmDialog(instore, "�Ƿ�ȷ��ȡ��");
			if(select==0){
				instore.setVisible(false);
				instore.dispose();
			}
		}else if(command.equals("��ѯ")){
			
		}
	}
   
}
