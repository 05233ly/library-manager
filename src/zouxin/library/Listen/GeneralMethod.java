/**
 * 
 */
package zouxin.library.Listen;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;

import zouxin.library.util.JdbcTemplate;

/**
 * @author ZouXin
 *2017-3-27
 */
public class GeneralMethod {
	
	/**
	 * ����ǰʱ��ת��Ϊһ��ָ��������
	 * @return һ��ת�����ʱ�䣬ΪString����
	 * @author ZouXin
	 * 2017-3-27
	 */
      public static String conversionDate(){
    	  SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	  String date=sf.format(new Date());
    	  System.out.println("ת����ĵ�ǰʱ��Ϊ"+date);
    	  return date;
      }
      /**
  	 * ����ǰʱ��ת��Ϊһ��ָ��������
  	 * @return һ��ת�����ʱ�䣬ΪString����
  	 * @author ZouXin
  	 * 2017-3-27
  	 */
      public static String conversionDate1(){
    	  SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    	  String date=sf.format(new Date());
    	  System.out.println("ת����ĵ�ǰʱ��Ϊ"+date);
    	  return date;
      }
      /**
       * ����ʱ�� ʱ�����
       * @param month
       * @return String ���͵�ʱ��
       * @author ZouXin
       * 2017-5-14
       */
      public static String CalculationDate(Date date1,int month){
    	  SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
    	  Calendar c = Calendar.getInstance();
    	  c.setTime(date1);
          c.add(Calendar.MONTH, month);
          String date=f.format(c.getTime());
          System.out.println("�����Ļ���ʱ��Ϊ��"+ date);
    	  return date;
      }
      /**
       * 
       * @param imageUrl
       * @return
       * @author ZouXin
       * 2017-3-29
       */
      public static ImageIcon createImage(String imageUrl){
  		URL url=GeneralMethod.class.getResource(imageUrl);
//  		System.out.println(url);
  		if(url!=null){
  			return  new ImageIcon(url);
  		}
  		return null;
      }
      /**
       * ����ַ���
       * @param value
       * @return ��ֺ���ַ��� 
       * @author ZouXin
       * 2017-5-14
       */
      public static String updateString(String value){
    	  String[] s=value.split("_");
    	  return s[1];
      }
      /**
       * ת��ʱ��ͼ���ʱ��
       * @param date
       * @return String ���ͣ����ؼ�����ʱ��
       * @author ZouXin
       * 2017-5-14
       */
      public static String returnDate(String date){
    	  SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
    	  Date date1=null;
    	  String returnDate=null;
    	  try {
			date1=f.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  if(date1!=null){
    		  returnDate =CalculationDate(date1,2);
    	  }
    	  return returnDate;
      }
      /**
       * �Ƚ�ʱ��Ĵ�С
       * @param d1 �黹ʱ��
       * @param d2 ��ǰʱ��
       * @return �Ƿ�黹ʱ��ȵ�ǰʱ��С
       * @author ZouXin
       * 2017-5-14
       */
      public static boolean compareDate(String d1,String d2){
    	  SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
    	  boolean flag=true;
    	  try {
    		Date date2=f.parse(d2);
			Date date1=f.parse(d1);
			if(date2.getTime()>date1.getTime()){
				flag=false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return flag;
      }
      public static void main(String[] args){
//    	  Date date=returnDate("2017-05-03");
//    	  System.out.println(CalculationDate(date,2));
    	 System.out.println(compareDate("2016-01-02","2017-01-03"));
      }
}
