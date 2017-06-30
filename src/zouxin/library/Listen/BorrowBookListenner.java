/**
 * 
 */
package zouxin.library.Listen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import zouxin.library.dao.MainViewDao;
import zouxin.library.util.JdbcTemplate;
import zouxin.library.view.MainView;
import zouxin.library.vo.StorageUserInVo;
import zouxin.library.vo.TransactionVo;

/**
 * @author ZouXin
 * 2017-3-26
 */
public class BorrowBookListenner implements ActionListener{
     MainView mainView;
     public BorrowBookListenner(MainView mainView){
    	 this.mainView=mainView;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        MainViewDao dao=new MainViewDao();
		String command = e.getActionCommand();
		JTable table=mainView.getTable();
		if(command.equals("Ԥ��")){
			int selectedRow=mainView.getTable().getSelectedRow();			
			if(selectedRow<0){//�ж��Ƿ�ѡ����
				JOptionPane.showMessageDialog(mainView, "��ѡ����Ҫ���ĵ��鼮");
			}else{
				if(StorageUserInVo.User_YN.equals("N")){
					JOptionPane.showConfirmDialog(mainView, "�Բ�������Ƿ��δ���壬��ǰ���ɷ�");
				}else{
				int borrowNum=dao.selectBorrowBookNum();
				System.out.println("ѧ������ "+borrowNum+"����");
				if(borrowNum<StorageUserInVo.Authority_Borrow_Num){//�ж��Ƿ���Ȩ�޽����鼮
				int remaining=Integer.parseInt(table.getValueAt(selectedRow, 4).toString());
				String library=table.getValueAt(selectedRow, 0).toString();
				String library_Room=table.getValueAt(selectedRow, 1).toString();
				String location=table.getValueAt(selectedRow, 2).toString();
				String loaction_number=table.getValueAt(selectedRow, 11).toString();
				String bookName=table.getValueAt(selectedRow, 3).toString();
				if(remaining<1){//�ж��Ƿ����鼮�ɽ�
					JOptionPane.showMessageDialog(mainView, "��ѡ���"+library+" "+library_Room+" "+location+" "+" "+bookName+"��治��,��ѡ���������һ���������鼮");
				}else{
				int returnFlag=JOptionPane.showConfirmDialog(mainView, "�Ƿ�ȷ��Ԥ���鼮");
				if(returnFlag==0){
				System.out.println(returnFlag);
				String bookNum=table.getValueAt(selectedRow, 10).toString();
				TransactionVo vo=new TransactionVo();
				vo.setBook_Number(bookNum);
				vo.setTransaction_Studnet_num(StorageUserInVo.userId);
				vo.setTransaction_Type("ѧ�������鼮");
				vo.setTransaction_Date(GeneralMethod.conversionDate());
				vo.setTransaction_location(loaction_number);
				int j=dao.updateSurplusQuantityReduce(bookNum, loaction_number);
				if(j==1){
					int i=dao.insertTransaction(vo);
					if(i==1){
						JOptionPane.showMessageDialog(mainView, "Ԥ��ɹ�,��ǰ��ͼ���ȡͼ��");
                        mainView.getSelectValue().doClick();
					}else{
                        dao.updateSurplusQuantityAdd(bookNum, loaction_number);
                        JOptionPane.showMessageDialog(mainView, "�Բ���,ϵͳ����,���Ժ�����,������ϵ����Ա");
					}
				}else{
					JOptionPane.showMessageDialog(mainView, "�Բ���,ϵͳ����,���Ժ�����,������ϵ����Ա");
				}
				}
				}//�ж��Ƿ����鼮���Խ�Ľ���
			}else{
				JOptionPane.showMessageDialog(mainView, "�Բ���������������Ѵ����ޣ��뻹���Ԥ�裬�������Ȩ��");
			}//�ж��Ƿ���Ȩ�޽����鼮����
		    }//�ж��Ƿ�Ƿ��
			}//�ж��Ƿ�ѡ���е�if������
			
			System.out.println(selectedRow+"   ѡ�б������");
		}else if(command.equals("��ѯ")){
			String author=mainView.getTextField_1().getText();//��ȡ��������
			String bookName=mainView.getTextField().getText();//��ȡ�鼮��������
			Object type=mainView.getComboBox().getSelectedItem();//��ȡ�鼮��������
			System.out.println("����:"+author+"    "+"����:"+bookName+"          "+"����:"+type);
			String sql=null;//���ݿ��������
            List<String> sqlList=new ArrayList<String>();//��Ҫ���뵽sql�е�����
            //ͨ����õ�ѡ����Ƿ�ѡ�е����ԣ����ж��Ƿ�ģ����ѯ
			if(mainView.getRdbtnNewRadioButton().isSelected()){
				       sql="select lit.library_name, "+
					       "lrit.library_room_name, "+
					       "lt.location_name, "+
					       "bit.book_name, "+
					       "iit.surplus_quantity, "+
					       "btt.book_type_name, "+
					       "bit.book_author, "+
					       "bit.book_price, "+				       
					       "bit.book_bar_code, "+					       					       
					       "bit.bookr_press, "+
					       "bit.book_number, "+
					       "lt.location_number "+
					  "from Book_Information_Table         bit, "+
					      "Inventory_Information_table    iit, "+
					      "Location_Table                 lt, "+
					       "Library_Room_Information_Table lrit, "+
					       "Library_Information_Table      lit, "+
					       "Book_Type_Table                btt "+
					 "where bit.book_type_num=btt.book_type_num "+
					   "and bit.book_number = iit.book_number "+
					   "and iit.location_number = lt.location_number "+
					   "and lt.library_room_number = lrit.library_room_number "+
					   "and lrit.library_number = lit.library_number ";
				//�ж�ȡ����ֵ�Ƿ�Ϊ�գ���Ϊ��ʱ�޸�sql���ͽ�ֵ����sqlOb��
				if(!type.equals("")){
					sqlList.add(type.toString());
					sql=sql+"and btt.book_type_name =? ";
				}
				if(!author.equals("")){
					sql=sql+"and bit.book_author like '%"+author+"%' ";
				}
				if(!bookName.equals("")){
					sql=sql+"and bit.book_name like '%"+bookName+"%' ";
				}
			}else{
				       sql="select lit.library_name, "+
						       "lrit.library_room_name, "+
						       "lt.location_name, "+
						       "bit.book_name, "+
						       "iit.surplus_quantity, "+
						       "btt.book_type_name, "+
						       "bit.book_author, "+
						       "bit.book_price, "+				       
						       "bit.book_bar_code, "+					       					       
						       "bit.bookr_press, "+
						       "bit.book_number, "+
						       "lt.location_number "+
					  "from Book_Information_Table         bit, "+
					      "Inventory_Information_table    iit, "+
					      "Location_Table                 lt, "+
					       "Library_Room_Information_Table lrit, "+
					       "Library_Information_Table      lit, "+
					       "Book_Type_Table                btt "+
					 "where bit.book_type_num=btt.book_type_num "+
					   "and bit.book_number = iit.book_number "+
					   "and iit.location_number = lt.location_number "+
					   "and lt.library_room_number = lrit.library_room_number "+
					   "and lrit.library_number = lit.library_number ";
				if(!type.equals("")){
					sqlList.add(type.toString());
					sql=sql+"and btt.book_type_name =? ";
				}
				if(!author.equals("")){
					sqlList.add(author);
					sql=sql+"and bit.book_author=? ";
				}
				if(!bookName.equals("")){
					sqlList.add(bookName);
					sql=sql+"and bit.book_name=? ";					
				}

			}
			System.out.println(sql);

            Object[][] rowsData=dao.getBookInf(sql, sqlList);//�洢�е�����
            Object[] cloumnNames=new Object[]{"ͼ���","����","��λ","����","ʣ������","�鼮����","����","����","������","������","�鼮���","��λ���"};//����
            DefaultTableModel dataModel=new DefaultTableModel(rowsData, cloumnNames);//(DefaultTableModel)mainView.getTable().getModel();//new DefaultTableModel(rowsData, cloumnNames);//(DefaultTableModel)mainView.getTable().getModel();
//            dataModel.setDataVector(rowsData, cloumnNames);
            mainView.getTable().setModel(dataModel);
		}
	}
       
}
