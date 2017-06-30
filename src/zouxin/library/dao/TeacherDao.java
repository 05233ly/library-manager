/**
 * 
 */
package zouxin.library.dao;

import javax.swing.JTable;

import zouxin.library.util.JdbcTemplate;
import zouxin.library.vo.SharedBookVo;
import zouxin.library.vo.StorageUserInVo;
import zouxin.library.vo.StudentBorrowVo;

/**
 * @author ZouXin
 *2017-5-10
 */
public class TeacherDao {
     JdbcTemplate jdbcTemplate=new JdbcTemplate();
     /**
      * �������ݵ�ѧ�����ı�
      * @param sql
      * @param vo
      * @return һ���������ж����ݿ�����Ƿ���ȷ
      * @author ZouXin
      * 2017-5-11
      */
     public int insertBorrow(String sql,StudentBorrowVo vo){
    	 int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, new Object[]{vo.getBook_Number(),vo.getStudent_number(),vo.getStudent_Borrow_Number(),vo.getArrear(),vo.getStudent_Borrow_Return_Time(),vo.getBorrow_Time(),vo.getWhether_Return(),vo.getLocation()});
    	 return s;
     }
     /**
      * ���뵽ѧ�������
      * @param sql
      * @param vo
      * @returnһ���������ж����ݿ�����Ƿ���ȷ
      * @author ZouXin
      * 2017-5-11
      */
     public int insertBorrowShare(String sql,SharedBookVo vo){
    	 int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, new Object[]{vo.getBook_Number(),vo.getStudent_number(),vo.getShared_InStore_Date(),vo.getShared_InStore_Num(),vo.getShared_Retrieve_Date(),vo.getShared_Location()});
    	 return s;
     }
     /**
      * ѧ���ɽ���ʱ��
      * @param student_num
      * @author ZouXin
      * 2017-5-11
      */
     public void returnDate(String student_num){
    	 String sql_time="select ait.authority_borrow_time from Authority_Information_Table ait,Student_Information_table sit where sit.student_number=? and sit.authority_num=ait.authority_num";
		 StorageUserInVo.Authority_Borrow_Time=Integer.parseInt(jdbcTemplate.selectOne(sql_time, new Object[]{student_num}).toString());

     }
     /**
      * �޸�����
      * @param sql
      * @param objects
      * @return һ���������ж����ݿ�����Ƿ���ȷ
      * @author ZouXin
      * 2017-5-11
      */
     public int updateValue(String sql,Object... objects){
    	 int s=jdbcTemplate.InsertOrUpdateOrDelete(sql, objects);
    	 return s;
     }
}
