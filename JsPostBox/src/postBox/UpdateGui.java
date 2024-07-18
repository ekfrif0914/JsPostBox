package postBox;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.PostResDao;
import dto.PostResDto;

public class UpdateGui extends JFrame implements ActionListener {
	private PostResDao prd = new PostResDao();
	private JLabel date1 = new JLabel("날짜:");
	private JLabel date2;
	private JLabel category = new JLabel("품목");// 콤보박스
	private JLabel price = new JLabel("가격:");
	private JLabel won = new JLabel("만원");
	private JLabel pName = new JLabel("등록 명칭:");
	private JLabel sName = new JLabel("보내는 사람:");
	private JLabel sPhone = new JLabel("(보)전화:");
	private JLabel sAddress = new JLabel("(보)주소:");
	private JLabel gName = new JLabel("받는 사람:");
	private JLabel gPhone = new JLabel("(받)전화:");
	private JLabel gAddress = new JLabel("(받)주소:");
	private JLabel gRequest = new JLabel("배송 요청사항:");

	private JTextField ptf = new JTextField(10);
	private JTextField atf = new JTextField(10);
	private JTextField sntf = new JTextField(10);
	private JTextField sptf = new JTextField(10);
	private JTextField satf = new JTextField(10);
	private JTextField gntf = new JTextField(10);
	private JTextField gptf = new JTextField(10);
	private JTextField gatf = new JTextField(10);
	private JTextField grtf = new JTextField(10);

	private JButton nUp = new JButton("수정");

	private String category1[] = { "의류", "가전제품류", "과일류", "곡물류", "식품류", "잡화/서적" };
	private String category2[] = { "가전제품류", "의류", "과일류", "곡물류", "식품류", "잡화/서적" };
	private String category3[] = { "과일류", "의류", "가전제품류", "곡물류", "식품류", "잡화/서적" };
	private String category4[] = { "곡물류", "의류", "가전제품류", "과일류", "식품류", "잡화/서적" };
	private String category5[] = { "식품류", "의류", "가전제품류", "과일류", "곡물류", "잡화/서적" };
	private String category6[] = { "잡화/서적", "의류", "가전제품류", "과일류", "곡물류", "식품류" };
	private JComboBox<String> combo;

	private MyPageGui a;

	public UpdateGui(MyPageGui address) {
		a = address;

		setTitle("예약정보 수정");

		setBounds(100, 100, 600, 500);
		PostResDto info = a.selectCell();
		JPanel p = new JPanel(new GridLayout(11, 2));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ptf.setText(info.getpPrice());
		atf.setText(info.getpName());
		sntf.setText(info.getsName());
		sptf.setText(info.getsPhone());
		satf.setText(info.getsAddress());
		gntf.setText(info.getgName());
		gptf.setText(info.getgPhone());
		gatf.setText(info.getgAddress());
		grtf.setText(info.getgRequest());

		String userCategory = info.getCategory();

		if (userCategory.equals((category1)[0])) {
			combo = new JComboBox<String>(category1);
		} else if (userCategory.equals((category2)[0])) {
			combo = new JComboBox<String>(category2);
		} else if (userCategory.equals((category3)[0])) {
			combo = new JComboBox<String>(category3);
		} else if (userCategory.equals((category4)[0])) {
			combo = new JComboBox<String>(category4);
		} else if (userCategory.equals((category5)[0])) {
			combo = new JComboBox<String>(category5);
		} else if (userCategory.equals((category6)[0])) {
			combo = new JComboBox<String>(category6);
		}
		
		date2 = new JLabel(info.getDate());

		p1.add(ptf);
		p1.add(won);

		p.add(date1);
		p.add(date2);
		p.add(category);
		p.add(combo);
		p.add(price);
		p.add(p1);
		p.add(pName);
		p.add(atf);
		p.add(sName);
		p.add(sntf);
		p.add(sPhone);
		p.add(sptf);
		p.add(sAddress);
		p.add(satf);
		p.add(gName);
		p.add(gntf);
		p.add(gPhone);
		p.add(gptf);
		p.add(gAddress);
		p.add(gatf);
		p.add(gRequest);
		p.add(grtf);

		add(p, BorderLayout.CENTER);
		add(nUp, BorderLayout.SOUTH);

		nUp.addActionListener(this);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void showbox() {
		this.setVisible(true);
	}

	public PostResDto getUpTf() {
		PostResDto newin = new PostResDto();
		newin.setCategory(combo.getSelectedItem().toString());

		newin.setpPrice(ptf.getText());
		newin.setpName(atf.getText());
		newin.setsName(sntf.getText());
		newin.setsPhone(sptf.getText());
		newin.setsAddress(satf.getText());
		newin.setgName(gntf.getText());
		newin.setgPhone(gptf.getText());
		newin.setgAddress(gatf.getText());
		newin.setgRequest(grtf.getText());

		return newin;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(nUp)) {
			PostResDto exinfo = a.selectCell();
			PostResDto newinfo = getUpTf();
			prd.updateCell(newinfo, exinfo);
			JOptionPane.showMessageDialog(null, "수정되었습니다");
			a.removeScroll();
			a.reArrange();
			this.dispose();
		}
	}
}
