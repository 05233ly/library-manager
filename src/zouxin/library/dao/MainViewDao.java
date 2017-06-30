/**
 * 
 */
package zouxin.library.dao;

import java.util.List;

import oracle.net.aso.l;

import zouxin.library.util.JdbcTemplate;
import zouxin.library.vo.BookTypeVo;
import zouxin.library.vo.StorageUserInVo;
import zouxin.library.vo.TransactionVo;

/**
 * @author ZouXin
 * 2017-3-27
 */
public class MainViewDao {
	JdbcTemplate jdbcTemplate=new JdbcTemplate();
	/*
	 * Return List<BookTypeVo>
	 * @author ZouXin
	 * ���ڲ����鼮������
	 * */
    public <T> List<T> getType(String sql,Class<T> t,Object... objects){
 //  	 String sql="select * from Book_Type_Table";
   	 List<T> list=null;
   	 try {   
   		    if(objects!=null){
   		    	list=jdbcTemplate.queryValues(sql, t,objects);
   		    }else{
   		    	list=jdbcTemplate.queryValues(sql, t);	
   		    }
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 return list;
    }
    /**
     * 
     * @param sql
     * @param list
     * @return object[][]
     * ���ڲ����鼮����Ϣ
     * ZouXin
     * 2017-3-27
     */
    public Object[][] getBookInf(String sql,List<String> list){
  //  	String Sql1="select bit.book_name, bit.book_price, bit.book_bar_code, bit.book_author, bit.bookr_press, iit.surplus_quantity, lt.location_name, lrit.library_room_name, lit.library_name, btt.book_type_name from Book_Information_Table         bit, Inventory_Information_table    iit, Location_Table                 lt, Library_Room_Information_Table lrit, Library_Information_Table      lit, Book_Type_Table                btt where bit.book_type_num=btt.book_type_num and bit.book_number = iit.book_number and iit.location_number = lt.location_number and lt.library_room_number = lrit.library_room_number and lrit.library_number = lit.library_number and bit.book_name like '%����%' ";
    	Object[] ob=new Object[list.size()];
    	for(int i=0;i<list.size();i++){
    		ob[i]=list.get(i);
//    		System.out.println(list.get(i)+i);
    	}
    	List<Object[]> listReturn=jdbcTemplate.selectValue(sql,ob);
    	System.out.println(listReturn.size());
    	if(listReturn.size()==0){
    		return new Object[][]{{}};
    	}else{
    		Object[][] objects=new Object[listReturn.size()][listReturn.get(0).length];
    		for(int j=0;j<listReturn.size();j++){
    			objects[j]=listReturn.get(j);
    		}
    		return objects;
    	}
    }
    /**
     * �������ݵ�Transaction_table��
     * @param vo
     * @return sql���е�״ֵ̬��1Ϊ�ɹ�
     * @author ZouXin
     * 2017-3-27
     */
    public int insertTransaction(TransactionVo vo){
    	String sql="insert into Transaction_Table values(?,?,?,?,Transaction_Table_Sequence.Nextval,?,?,?,?)";
    	int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, new Object[]{vo.getBook_Number(),vo.getTransaction_Date(),vo.getTransaction_Num(),vo.getTransaction_Type(),vo.getTransaction_Name(),vo.getTransaction_Studnet_num(),vo.getTransaction_Yes_No(),vo.getTransaction_location()});
    	return s;
    }
    /**
     * �޸�ʣ������������ʱ
     * @param bookNum
     * @param location
     * @return sql���е�״ֵ̬��1Ϊ�ɹ�
     * @author ZouXin
     * 2017-3-27
     */
    public int updateSurplusQuantityReduce(String bookNum,String location){
    	String sql="update Inventory_Information_table set Surplus_Quantity=Surplus_Quantity-1 where Book_Number=? and Location_Number=?";
    	int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, new Object[]{bookNum,location});
    	return s;
    }
    /**
     * �޸�ʣ������������ʱ
     * @param bookNum
     * @param location
     * @return
     * @author ZouXin
     * 2017-3-27
     */
    public int updateSurplusQuantityAdd(String bookNum,String location){
    	String sql="update Inventory_Information_table set Surplus_Quantity=Surplus_Quantity+1 where Book_Number=? and Location_Number=?";
    	int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, new Object[]{bookNum,location});
    	return s;
    }
    public int updateSurplusQuantity(String bookNum,String location,int quantity,int allQuantity){
    	String sql="update Inventory_Information_table set Surplus_Quantity=Surplus_Quantity+?,All_Quantity=All_Quantity+? where Book_Number=? and Location_Number=?";
    	int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, new Object[]{quantity,allQuantity,bookNum,location});
    	return s;
    }
    public int updateSurplusQuantity1(String bookNum,String location,int quantity,int allQuantity){
    	String sql="update Inventory_Information_table set Surplus_Quantity=Surplus_Quantity-?,All_Quantity=All_Quantity-? where Book_Number=? and Location_Number=?";
    	int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, new Object[]{quantity,allQuantity,bookNum,location});
    	return s;
    }
    /**
     * ���ڲ���ѧ��һ�����˶�����
     * @return ѧ�����˶�����
     * @author ZouXin
     * 2017-3-28
     */
    public int selectBorrowBookNum(){
    	String sql="select count(*) from Transaction_Table where Transaction_Studnet_num=? and Transaction_Yes_No='N' and Transaction_type='ѧ�������鼮'";
    	String sql_B="select count(*) from Student_Borrow_Book_Table where Student_number=? and Whether_Return='N'";
    	int t=Integer.parseInt(jdbcTemplate.selectOne(sql, new Object[]{StorageUserInVo.userId}).toString());
    	int s=Integer.parseInt(jdbcTemplate.selectOne(sql_B, new Object[]{StorageUserInVo.userId}).toString());
    	return t+s;
    }
    /**
     * ����ȡ��Ԥ��ʱ���ڸı���
     * @param bookNum
     * @param location_num
     * @return �Ƿ�ɹ�
     * @author ZouXin
     * 2017-3-29
     */
    public int updateSurplusQuantityAdd_cancel(String sql,Object... objects){
    	//String sql="update Inventory_Information_table set Surplus_Quantity=Surplus_Quantity+1 where Book_Number=? and Location_Number=?";
    	int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, objects);
    	return s;
    }
    /**
     * �����ж��˺������Ƿ���ȷ
     * @param sql
     * @param objects
     * @return ��û��ֵ
     * @author ZouXin
     * 2017-4-12
     */
    public int selectStu(String sql,Object[] objects){
    	int i=Integer.parseInt(jdbcTemplate.selectOne(sql, objects).toString());
    	return i;
    }
    public Object[][] getBorrow(){
	    String sql="select bit.book_name,sbbt.student_borrow_number,sbbt.borrow_time,sbbt.student_borrow_return_time,lit.library_name,lrit.library_room_name,lt.location_name,sbbt.book_number,sbbt.student_borrow_num,sbbt.location from student_borrow_book_table sbbt,book_information_table bit,library_information_table lit,library_room_information_table lrit,location_table lt where sbbt.book_number=bit.book_number and sbbt.location=lt.location_number and lt.library_room_number=lrit.library_room_number and lrit.library_number=lit.library_number and sbbt.student_number=? and sbbt.whether_return='N'";
	    List<Object[]> listReturn=jdbcTemplate.selectValue(sql,StorageUserInVo.userId);
    	System.out.println(listReturn.size()+"���ص��鼮�ĳ���Ϊ");
    	if(listReturn.size()==0){
    		return new Object[][]{{}};
    	}else{
    		Object[][] objects=new Object[listReturn.size()][listReturn.get(0).length];
    		for(int j=0;j<listReturn.size();j++){
    			objects[j]=listReturn.get(j);
    		}
    		return objects;
    	}
  }
    public int selectCount(String sql,Object... objects){
    	int i=Integer.parseInt(jdbcTemplate.selectOne(sql, objects).toString());
    	return i;
    }
    public int insertUpdateDelete(String sql,Object... obj){
    	int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, obj);
    	return s;
    }
    
    
    
    public Object[][] getShareAndB(List<String> list){
    	        String sqlBorrow="select lit.library_name||'_'||lit.library_number,lrit.library_room_name||'_'||lrit.library_room_number,lt.location_name||'_'||lt.location_number,bit.book_name||'_'||bit.book_number,sit.student_name||'_'||sit.student_number,sbbt.borrow_time,sbbt.student_borrow_return_time,sbbt.student_borrow_number,sbbt.student_borrow_num from student_borrow_book_table sbbt,student_information_table sit,book_information_table bit,location_table lt,library_information_table lit,library_room_information_table lrit where lrit.library_number=lit.library_number and lt.library_room_number=lrit.library_room_number and sbbt.location=lt.location_number and sbbt.student_number=sit.student_number and sbbt.book_number=bit.book_number and sbbt.whether_return='N' ";
    	        String sqlShared="select lit.library_name||'_'||lit.library_number,lrit.library_room_name||'_'||lrit.library_room_number,lt.location_name||'_'||lt.location_number,bit.book_name||'_'||bit.book_number,sit.student_name||'_'||sit.student_number,sbit.shared_instore_date,sbit.shared_retrieve_date,sbit.shared_instore_num,sbit.sharedd_numbering  from shared_book_information_table sbit,book_information_table bit,location_table lt,library_information_table lit,library_room_information_table lrit,student_information_table sit where lrit.library_room_number=lt.library_room_number and lrit.library_number=lit.library_number and bit.book_number=sbit.book_number and lt.location_number=sbit.shared_location and sbit.student_number=sit.student_number ";
    	        System.out.println(list.size());
    	        if(list.size()>0){
    	        	sqlBorrow=sqlBorrow+" and sbbt.student_number=?";
    	        	sqlShared=sqlShared+"  and sbit.student_number=?";
    	        }
    	  //  	String Sql1="select bit.book_name, bit.book_price, bit.book_bar_code, bit.book_author, bit.bookr_press, iit.surplus_quantity, lt.location_name, lrit.library_room_name, lit.library_name, btt.book_type_name from Book_Information_Table         bit, Inventory_Information_table    iit, Location_Table                 lt, Library_Room_Information_Table lrit, Library_Information_Table      lit, Book_Type_Table                btt where bit.book_type_num=btt.book_type_num and bit.book_number = iit.book_number and iit.location_number = lt.location_number and lt.library_room_number = lrit.library_room_number and lrit.library_number = lit.library_number and bit.book_name like '%����%' ";
    	    	Object[] ob=new Object[list.size()];
    	    	for(int i=0;i<list.size();i++){
    	    		ob[i]=list.get(i);
//    	    		System.out.println(list.get(i)+i);
    	    	}
    	    	List<Object[]> listBorrow=jdbcTemplate.selectValue(sqlBorrow,ob);
    	    	List<Object[]> listShared=jdbcTemplate.selectValue(sqlShared,ob);
//    	    	System.out.println(listBorrow.size());
    	    	System.out.println(listBorrow.size()+"���ĵ��鼮");
    	    	System.out.println(listShared.size()+"������鼮");
    	    	if(listBorrow.size()==0&&listShared.size()==0){
    	    		return new Object[][]{{}};
    	    	}else{
    	    		Object[][] objects=new Object[listBorrow.size()+listShared.size()][8];
    	    		if(listBorrow.size()!=0){
        	    		for(int j=0;j<listBorrow.size();j++){
        	    			objects[j]=addObject(listBorrow.get(j),"ѧ�������鼮");
        	    		}
    	    		}
    	    		if(listShared.size()!=0){
    	        		for(int i=0;i<listShared.size();i++){
        	    			objects[i+listBorrow.size()]=addObject(listShared.get(i),"ѧ�������鼮");
        	    		}
    	    		}

    	    		return objects;
    	    	}
    	    }
      public Object[] addObject(Object[] obj,String message){
    	  Object[] object=new Object[obj.length+1];
    	  System.arraycopy(obj, 0, object, 0, obj.length);
    	  object[obj.length]=message;
    	  return object;
      }
}
