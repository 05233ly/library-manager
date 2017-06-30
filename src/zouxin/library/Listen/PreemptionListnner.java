/**
 * 
 */
package zouxin.library.Listen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.MainView;
import zouxin.library.vo.StorageUserInVo;

/**
 * @author ZouXin
 *2017-3-28
 */
public class PreemptionListnner implements ActionListener{
    MainView mainView;
    public PreemptionListnner(MainView mainView){
    	this.mainView=mainView;
    }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		if(command.equals("ȡ��Ԥ��")){
			JTable table=mainView.getTable_1();
			int selectRows=table.getSelectedRow();
			if(selectRows<0){
				JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫȡ��Ԥ����鼮");
			}else{
				int returnFlag=JOptionPane.showConfirmDialog(mainView, "�Ƿ�ȡ��Ԥ��","������ʾ",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(returnFlag==JOptionPane.YES_OPTION){//�ж��Ƿ�ȡ��Ԥ��
				String location_num=table.getValueAt(selectRows, 11).toString();
				System.out.println(location_num);
				String transaction_id=table.getValueAt(selectRows, 10).toString();
				System.out.println(transaction_id);
				String book_name=table.getValueAt(selectRows, 3).toString();
				System.out.println(book_name);
				String sql_t="update Transaction_Table set transaction_YEs_NO='C',transaction_name=? where transaction_id=?";//�����޸�������sql���
				String sql_i="update Inventory_Information_table set Surplus_Quantity=Surplus_Quantity+1 where location_number=? and book_number=(select book_number from Book_Information_Table where book_name=?) ";//�����޸Ŀ���sql���
				MainViewDao dao=new MainViewDao();
				int return_i=dao.updateSurplusQuantityAdd_cancel(sql_i, new Object[]{location_num,book_name});
				System.out.println(return_i+"���ص�״ֵ̬");
				if(return_i==1){
					int return_T=dao.updateSurplusQuantityAdd_cancel(sql_t, new Object[]{StorageUserInVo.userId,transaction_id});
					System.out.println(return_T+"���ص�״ֵ̬");
					if(return_T==1){
						JOptionPane.showMessageDialog(mainView, "ȡ��Ԥ��ɹ�");
						Preemption_CardSwitchListenner.setTable(table, dao);
					}else{
						String sql_i_c="update Inventory_Information_table set Surplus_Quantity=Surplus_Quantity-1 where location_number=? and book_number=(select book_number from Book_Information_Table where book_name=?";
						dao.updateSurplusQuantityAdd_cancel(sql_i_c, new Object[]{location_num,book_name} );
						JOptionPane.showMessageDialog(mainView, "ϵͳ�쳣������ϵ����Ա���Ժ�����");
					}
				}else{
					JOptionPane.showMessageDialog(mainView, "ϵͳ�쳣������ϵ����Ա���Ժ�����");
				}
				
			}//�Ƿ�ȡ��Ԥ��Ľ���
			}
			
		}
	}
   
}
