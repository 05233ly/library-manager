/**
 * 
 */
package zouxin.library.Listen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.MainView;

/**
 * @author ZouXin
 *2017-5-14
 */
public class RenewBookListener implements ActionListener{
     MainView mainView;
     public RenewBookListener(MainView mainView){
    	 this.mainView=mainView;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int selectRows=mainView.getTable_2().getSelectedRow();
		if(selectRows<0){
			JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫ������鼮");
		}else{
			int select=JOptionPane.showConfirmDialog(mainView, "�Ƿ��������");
			if(select==0){
				String number=mainView.getTable_2().getValueAt(selectRows, 8).toString();
				String returnDate=mainView.getTable_2().getValueAt(selectRows, 3).toString();
				String sql="update student_borrow_book_table sbbt set sbbt.student_borrow_return_time=? where sbbt.student_borrow_num=?";
				MainViewDao dao = new MainViewDao();
				String sqlR="select sbbt.renewnum from student_borrow_book_table sbbt where sbbt.student_borrow_num=?";
				int renewN=dao.selectCount(sqlR, number);
				if(renewN<=1){
					if(GeneralMethod.compareDate(returnDate, GeneralMethod.conversionDate1())){
						int s=dao.insertUpdateDelete(sql, GeneralMethod.returnDate(returnDate),number);
						if(s==1){
							JOptionPane.showMessageDialog(mainView, "����ɹ�");
							String sqlUpdate="update student_borrow_book_table sbbt set sbbt.renewnum=sbbt.renewnum+1 where sbbt.student_borrow_num=?";
							dao.insertUpdateDelete(sqlUpdate, number);
							Object[][] ob=dao.getBorrow();
					    	Object[] cloumnNames=new Object[]{"�鼮����","����","����ʱ��","�黹ʱ��","ͼ���","����","��λ","�鼮���","���","��λ���"};
//					    	Object[] cloumnNames=new Object[]{"ͼ���","����","��λ","����","ʣ������","�鼮����","����","����","������","������","�鼮���"};//����
					    	DefaultTableModel model=new DefaultTableModel(ob,cloumnNames);
					    	mainView.getTable_2().setModel(model);
						}else{
							JOptionPane.showMessageDialog(mainView, "ϵͳ����");
						}

					}else{
						JOptionPane.showMessageDialog(mainView, "��������鼮�Ѿ�����,���ɽ�������,��ǰ������");
					}
				}else{
					JOptionPane.showMessageDialog(mainView, "����������Ѵ�����,�����Լ�������,��黹�鼮���������");
				}
				
			}
			
			}
		
	}
     
}
