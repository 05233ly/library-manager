/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.net.aso.s;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.TeacherMainView;
   
/**
 * @author ZouXin
 *2017-5-15
 */
public class ShareAndBorrowListener implements ActionListener{
     TeacherMainView mainView;
     public ShareAndBorrowListener(TeacherMainView mainView){
    	 this.mainView=mainView;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		String studentNum=mainView.getTextField_9().getText();
		JTable table=mainView.getTable_7();
		List<String> list=new ArrayList<String>();
		MainViewDao dao=new MainViewDao();
		if(command.equals("borrowSelect")){
			if(!studentNum.equals("")){
				list.add(studentNum);
			}
			
			Object[][] rowsData=dao.getShareAndB(list);
			Object[] colunmNames=new Object[]{"ͼ���","����","��λ","�鼮","ѧ��","��������ʱ��","ȡ�ػ���ʱ��","����","���","����"};
			DefaultTableModel dataModel=new DefaultTableModel(rowsData,colunmNames);
			table.setModel(dataModel);
		}else if(command.equals("borrowOK")){
			int selectRow=table.getSelectedRow();
			String type=table.getValueAt(selectRow, 9).toString();
			String number=table.getValueAt(selectRow, 8).toString();
			String quantity=table.getValueAt(selectRow, 7).toString();
			String bookNum=table.getValueAt(selectRow, 3).toString().split("_")[1];
			String locationNum=table.getValueAt(selectRow, 2).toString().split("_")[1];
			if(selectRow<0){
				JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫ���������");
			}else{
				if(type.equals("ѧ�������鼮")){
					String sqlU="update student_borrow_book_table sbbt set sbbt.whether_return='Y' where sbbt.student_borrow_num=?";
					int ass=dao.updateSurplusQuantity1(bookNum, locationNum, Integer.parseInt(quantity),0);
					if(ass==1){
						int us=dao.insertUpdateDelete(sqlU, number);
						if(us==1){
							JOptionPane.showMessageDialog(mainView, "����ɹ�");
						}else{
							JOptionPane.showMessageDialog(mainView, "����ʧ��");
							
						}
					}else{
						JOptionPane.showMessageDialog(mainView, "ϵͳ����");
					}
					
				}else if(type.equals("ѧ�������鼮")){
					String sqlDelete="delete shared_book_information_table sbit where sbit.sharedd_numbering=? ";
					int as=dao.updateSurplusQuantity1(bookNum, locationNum, Integer.parseInt(quantity),Integer.parseInt(quantity));
					if(as==1){
						int ds=dao.insertUpdateDelete(sqlDelete, number);
						if(ds==1){
							JOptionPane.showMessageDialog(mainView, "����ɹ�");
						}else{
							JOptionPane.showMessageDialog(mainView, "����ʧ��");
						}
					}else{
						JOptionPane.showMessageDialog(mainView, "ϵͳ����");
					}

				}
			}
			
		}
	}
         
}
