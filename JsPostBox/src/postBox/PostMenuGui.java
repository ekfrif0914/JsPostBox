package postBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.MemberDto;

public class PostMenuGui extends JFrame implements ActionListener {
	private ArrayList<MemberDto> nowUser1;
	private MenuGui menu;
	
 	private JLabel title = new JLabel("JsPostBox");
 	private JButton logout = new JButton("로그 아웃");
 	private JButton res = new JButton("택배 예약");
 	private JButton review = new JButton("이용 후기");
 	private JButton mypage = new JButton("마이 페이지");

 	private JPanel panel[] = new JPanel[4];

 	private FlowLayout f1 = new FlowLayout(FlowLayout.CENTER, 0, 50);
 	private FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 155, 40);
 	private FlowLayout f3 = new FlowLayout(FlowLayout.CENTER, 50, 10);

	public PostMenuGui(MenuGui ad, ArrayList<MemberDto> nowUser) {
		menu=ad;
		nowUser1=nowUser;
		JLabel nowId = new JLabel(nowUser1.get(0).getId() + " 님 로그인");//nowUser.get(0).getId()
		setTitle("JsPostBox");
		setBounds(100, 100, 600, 600);
		this.setLayout(new GridLayout(4, 1));

		panel[0] = new JPanel(f1);
		panel[1] = new JPanel(f2);
		panel[2] = new JPanel(f3);
		panel[3] = new JPanel();

		for (int i = 0; i < panel.length; i++) {
			add(panel[i]);
		}

		panel[0].add(title);
		panel[1].add(nowId);
		panel[1].add(new JLabel(nowUser1.get(0).getPoint()+"point"));
		panel[0].setBackground(Color.cyan);
		panel[1].add(logout);
		panel[2].add(res);
		panel[2].add(review);
		panel[3].add(mypage);

		logout.addActionListener(this);
		res.addActionListener(this);
		review.addActionListener(this);
		mypage.addActionListener(this);

		res.setPreferredSize(new Dimension(160, 90));
		review.setPreferredSize(new Dimension(160, 90));
		mypage.setPreferredSize(new Dimension(200, 90));

		logout.setBackground(Color.pink);
		res.setBackground(Color.white);
		review.setBackground(Color.white);
		mypage.setBackground(Color.white);

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void showbox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(logout)) {
			this.setVisible(false);
			menu.showbox();
		} else if (e.getSource().equals(res)) {
			PostResGui prg=new PostResGui(nowUser1);
			prg.showbox();
		}else if(e.getSource().equals(mypage)) {
			MyPageGui mpg =new MyPageGui(nowUser1);
			mpg.showbox();
			mpg.reArrange();
		}else if(e.getSource().equals(review)) {
			ReviewShowGui rsg=new ReviewShowGui();
			rsg.showbox();
		}
 
	}

}