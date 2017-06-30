package zouxin.library.Listen;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.MainView;

public class CardSwitchListenner implements ActionListener{
    MainView mainView;
    public CardSwitchListenner(MainView mainView){
    	this.mainView=mainView;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		CardLayout layout=new CardLayout();
		String command=e.getActionCommand();
		System.out.println(mainView.getTextField().getText());
		if(command.equals("�����鼮")){
			mainView.getCard().show(mainView.getPanel_Card(), "Preemption");
		}else if(command.equals("�����鼮")){
			mainView.getCard().show(mainView.getPanel_Card(), "BackBook");
			MainViewDao dao=new MainViewDao();
	    	Object[][] ob=dao.getBorrow();
	    	Object[] cloumnNames=new Object[]{"�鼮����","����","����ʱ��","�黹ʱ��","ͼ���","����","��λ","�鼮���","���","��λ���"};
//	    	Object[] cloumnNames=new Object[]{"ͼ���","����","��λ","����","ʣ������","�鼮����","����","����","������","������","�鼮���"};//����
	    	DefaultTableModel model=new DefaultTableModel(ob,cloumnNames);
	    	mainView.getTable_3().setModel(model);
		}else if(command.equals("�����鼮")){
			mainView.getCard().show(mainView.getPanel_Card(), "shared");
		}else if(command.equals("�޸�����")){
			mainView.getCard().show(mainView.getPanel_Card(), "Update");
		}
		
	}

}
