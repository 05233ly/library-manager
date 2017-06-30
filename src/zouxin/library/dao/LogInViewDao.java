/**
 * 
 */
package zouxin.library.dao;

import java.util.ArrayList;
import java.util.List;

import zouxin.library.util.JdbcTemplate;
import zouxin.library.vo.BookTypeVo;
import zouxin.library.vo.StorageUserInVo;
import zouxin.library.vo.UserIdVo;

/**
 * @author ZouXin
 * 2017-3-26
 */
public class LogInViewDao {
	JdbcTemplate jdbcTemplate=new JdbcTemplate();
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @return int
	 * �����ж��˺������Ƿ���ȷ���ж���ʲô��ݣ����ز�һ��������
	 * @author ZouXin
	 * 2017-3-27
	 */
     public int decideUser(Object userName,Object passWord){
    	 String sql_Student="select student_name,student_number from Student_Information_table where student_number=? and student_password=?";
    	 String sql_manager="select Manager_User_Id,Manager_name from Manager_Information_Table where Manager_User_Id=? and Manager_PassWord=?";
    	 int s=0;
    	 ArrayList<UserIdVo> list_Student=null;
    	 ArrayList<UserIdVo> list_Manager=null;
		try {
			list_Student = jdbcTemplate.queryValues(sql_Student, UserIdVo.class,new Object[]{userName,passWord});
			list_Manager=jdbcTemplate.queryValues(sql_manager, UserIdVo.class,new Object[]{userName,passWord});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 if(list_Student.size()!=0){
    		 StorageUserInVo.userId=list_Student.get(0).getStudent_Number();//����ѯ�������û�ID����ھ�̬������
    		 StorageUserInVo.userName=list_Student.get(0).getStudent_Name();//����ѯ�������û����ƴ���ھ�̬������
    		 String sql="select ait.authority_borrow_num from Authority_Information_Table ait,Student_Information_table sit where sit.student_number=? and sit.authority_num=ait.authority_num";
    		 StorageUserInVo.Authority_Borrow_Num=Integer.parseInt(jdbcTemplate.selectOne(sql, new Object[]{list_Student.get(0).getStudent_Number()}).toString());//����ѯ�������û��ɽ����鼮��������ھ�̬������
    		 String sql_Y_N="select sit.student_okorno from student_information_table sit where sit.student_number=?";
    		 StorageUserInVo.User_YN=jdbcTemplate.selectOne(sql_Y_N, new Object[]{list_Student.get(0).getStudent_Number().toString()}).toString();
       		 s=2;
    		 System.out.println("�û�ID : "+StorageUserInVo.userId+"�û�����  : "+StorageUserInVo.userName+"�û��ɽ��鼮���� : "+StorageUserInVo.Authority_Borrow_Num+"�û��Ƿ���Խ����鼮"+StorageUserInVo.User_YN);
    	 }
    	 if(list_Manager.size()!=0){
    		 StorageUserInVo.userId=list_Manager.get(0).getManager_User_Id();
    		 StorageUserInVo.userName=list_Manager.get(0).getManager_name();
    		 String sqlSF="select mit.manager_sf from manager_information_table mit where mit.manager_user_id=?";
    		 StorageUserInVo.manager_sf=jdbcTemplate.selectOne(sqlSF, list_Manager.get(0).getManager_User_Id()).toString();
    		 s=1;
    	 }
           return s;
     }
}
