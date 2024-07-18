package postBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ReviewDao;
import dto.PostResDto;

public class ReviewAddGui extends JFrame implements ActionListener{
	private ReviewDao rvd = new ReviewDao();
	private JTextField rvtf=new JTextField();
	private JButton ok =new JButton("완료");
	private MyPageGui address;
	
	public ReviewAddGui(MyPageGui ad) {
		address=ad;
		setTitle("리뷰작성");
		setBounds(100,100,300,300);

		add(rvtf,"Center");
		add(ok,"South");
		
		ok.addActionListener(this);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}
	
	public void showbox() {
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ok)){
			PostResDto exinfo = address.selectCell();
			String tf=rvtf.getText();
			rvd.insert(exinfo, tf);
			JOptionPane.showMessageDialog(null, "리뷰 작성 완료");
			this.setVisible(false);
		}
		
	}
}
