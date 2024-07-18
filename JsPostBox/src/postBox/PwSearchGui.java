package postBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.MemberDao;
import dto.MemberDto;

public class PwSearchGui extends JFrame implements ActionListener {
	private MemberDao md = new MemberDao();

	private JLabel title = new JLabel("비밀번호 찾기");
	private JLabel id = new JLabel("아이디:");
	private JLabel hint = new JLabel("비밀번호 힌트: 기억에 남는 추억의 장소는?");

	private JButton b1 = new JButton("찾기");
	private JPanel[] panel = new JPanel[5];

	private JTextField idtf = new JTextField(10);
	private JTextField httf = new JTextField(10);

	public PwSearchGui() {
		setTitle("JsPostBox");
		setBounds(300, 100, 350, 250);
		this.setLayout(new GridLayout(5, 1));

		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			add(panel[i]);
		}

		panel[0].add(title);
		panel[1].add(id);
		panel[1].add(idtf);
		panel[2].add(hint);
		panel[3].add(httf);
		panel[4].add(b1);
		b1.addActionListener(this);

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void showbox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {
			if (blank()) {
				JOptionPane.showMessageDialog(null, "모든 정보를 입력해주세요");
			} else {
				ArrayList<MemberDto> search = md.searchPw(idtf.getText(), httf.getText());
				if (search == null) {
					JOptionPane.showMessageDialog(null, "정보와 일치하는 비밀번호가 없습니다");
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호: " + search.get(0).getPw());
					idtf.setText("");
					httf.setText("");
				}
			}
		}
	}

	public boolean blank() {
		if (idtf.getText().isEmpty()) {
			return true;
		} else if (httf.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
