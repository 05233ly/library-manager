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
import zouxin.library.view.BookTypeAddAndUpdate;
import zouxin.library.view.LibraryAddAndUpdate;
import zouxin.library.view.LocationAddAndUpdate;
import zouxin.library.view.TeacherMainView;

/**
 * @author ZouXin
 *2017-5-11
 */
public class TeacherTypeListener implements ActionListener{
     TeacherMainView mainView;
     public TeacherTypeListener(TeacherMainView mainView){
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
		String name=mainView.getTextField_6().getText();
		List<String> list=new ArrayList<String>();
		JTable table=mainView.getTable_5();
		int selectRow=table.getSelectedRow();
		System.out.println(selectRow +"�ǺǺǺǺǺ�");
		if(command.equals("typeSelect")){
			String sql="select * from book_type_table btt where 1=1";
			if(!name.equals("")){
				sql=sql+" and btt.book_type_name=?";
				list.add(name);
			}
			Object[][] rowsData=dao.getBookInf(sql, list);
			Object[] colunmNames=new Object[]{"�鼮���ͱ��","��������","����"};
			DefaultTableModel dataModel=new DefaultTableModel(rowsData,colunmNames);
			table.setModel(dataModel);
		}else if(command.equals("typeAdd")){
			new BookTypeAddAndUpdate(0,mainView);
		}else if(command.equals("typeUpdate")){
			if(selectRow<0){
				JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫ�޸ĵ�����");
			}else{
//				int select=JOptionPane.showConfirmDialog(mainView, "�Ƿ�ȷ���޸�");
//				if(select==0){
					String tnumber=table.getValueAt(selectRow, 0).toString();
					String tname=table.getValueAt(selectRow, 1).toString();
					String tint=table.getValueAt(selectRow, 2).toString();
					BookTypeAddAndUpdate lib=new BookTypeAddAndUpdate(1, mainView);
					lib.getTextField().setText(tnumber);
					lib.getTextField_2().setText(tname);
					lib.getTextField_3().setText(tint);
//				}
			}
		}else if(command.equals("typeDelete")){
			if(selectRow<0){
				
				JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫɾ��������");
			}else{
				int selectD=JOptionPane.showConfirmDialog(mainView, "�Ƿ�ȷ��ɾ����������");
				if(selectD==0){
					String tnumber=table.getValueAt(selectRow, 0).toString();
					String sqlbook="select count(*) from book_information_table where book_type_num=?";
					int s=dao.selectCount(sqlbook, tnumber);
					if(s==0){
						String sql="delete book_type_table where book_type_num=?";
						int x=dao.insertUpdateDelete(sql, tnumber);
						if(x==1){
							JOptionPane.showMessageDialog(mainView, "ɾ���ɹ�");
							mainView.getTypeSelect().doClick();
						}else{
							JOptionPane.showMessageDialog(mainView, "ϵͳ����");
						}
					}else{
						JOptionPane.showMessageDialog(mainView, "����ɾ��ʹ�øÿ�λ������");
					}
				}
				
			}
		}
		
	}

}
