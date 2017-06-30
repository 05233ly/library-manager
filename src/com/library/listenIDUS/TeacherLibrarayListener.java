/**
 * 
 */
package com.library.listenIDUS;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.LibraryAddAndUpdate;
import zouxin.library.view.TeacherMainView;

/**
 * @author ZouXin
 *2017-5-11
 */
public class TeacherLibrarayListener implements ActionListener{
     TeacherMainView mainView;
     public TeacherLibrarayListener(TeacherMainView mainView){
    	 this.mainView=mainView;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MainViewDao dao=new MainViewDao();
		String command=e.getActionCommand();
		String name=mainView.getTextField_1().getText();
		List<String> list=new ArrayList<String>();
		JTable table=mainView.getTable_1();
		int selectRow=table.getSelectedRow();
		if(command.equals("librarySelect")){
			String sql="select * from library_information_table lit where 1=1 ";
			if(!name.equals("")){
				sql=sql+"and lit.library_name=?";
				list.add(name);
			}
			Object[][] rowsData=dao.getBookInf(sql, list);
			Object[] colunmNames=new Object[]{"ͼ��ݱ��","����","λ��","����","��������","�ݲ�����"};
			DefaultTableModel dataModel=new DefaultTableModel(rowsData,colunmNames);
			table.setModel(dataModel);
		}else if(command.equals("libraryAdd")){
			new LibraryAddAndUpdate(0,mainView);
		}else if(command.equals("libraryUpdate")){
			if(selectRow<0){
				JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫ�޸ĵ�����");
			}else{
//				int select=JOptionPane.showConfirmDialog(mainView, "�Ƿ�ȷ���޸�");
//				if(select==0){
					String lnumber=table.getValueAt(selectRow, 0).toString();
					String lname=table.getValueAt(selectRow, 1).toString();
					String llocation=table.getValueAt(selectRow, 2).toString();
					String lint=table.getValueAt(selectRow, 3).toString();
					String lfw=table.getValueAt(selectRow, 4).toString();
					String lgc=table.getValueAt(selectRow, 5).toString();
					LibraryAddAndUpdate lib=new LibraryAddAndUpdate(1,mainView);
					lib.getTextField().setText(lnumber);
					lib.getTextField().setEditable(false);
					lib.getTextField_1().setText(lname);
					lib.getTextField_2().setText(llocation);
					lib.getTextField_3().setText(lint);
					lib.getTextField_4().setText(lfw);
					lib.getTextField_5().setText(lgc);
//				}
			}
		}else if(command.equals("libraryDelete")){
			if(selectRow<0){
				
				JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫɾ��������");
			}else{
				int selectD=JOptionPane.showConfirmDialog(mainView, "�Ƿ�ȷ��ɾ����������");
				if(selectD==0){
					String lnumber=table.getValueAt(selectRow, 0).toString();
					String sqlroom="select count(*) from library_room_information_table where library_number=?";
					int s=dao.selectCount(sqlroom, lnumber);
					if(s==0){
						String sql="delete library_information_table where library_number=?";
						int x=dao.insertUpdateDelete(sql, lnumber);
						if(x==1){
							JOptionPane.showMessageDialog(mainView, "ɾ���ɹ�");
							mainView.getLibrarySelect().doClick();
						}else{
							JOptionPane.showMessageDialog(mainView, "ϵͳ����");
						}
					}else{
						JOptionPane.showMessageDialog(mainView, "����ɾ��ʹ�ø�ͼ��ݵ�����");
					}
				}
				
			}
		}
		
	}

}
