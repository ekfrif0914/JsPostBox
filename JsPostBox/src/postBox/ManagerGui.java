package postBox;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerGui extends JFrame implements ActionListener{
	private String id = "manager";
	private String pw = "1234";
	private JLabel mode =new JLabel("관리자 모드");
	private JButton exit =new JButton("나가기");
	private MemberLoginGui address;
	private JPanel panel=new JPanel();
	
	public ManagerGui(MemberLoginGui a){
		address=a;
		setTitle("JsPostBox 관리자 메뉴");
		setBounds(100, 100, 700, 600);
		panel.add(mode);
		mode.setBounds(200, 200, 100, 100);
		add(panel,"Center");
		
		add(exit,"South");
		exit.addActionListener(this);
		
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	 
	public void showbox() {
		this.setVisible(true);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(exit)) {
			this.setVisible(false);
			address.showbox();
		}
	}


}