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

public class IDSearchGui extends JFrame implements ActionListener {
	private MemberDao md = new MemberDao();
	private JLabel title = new JLabel("���̵� ã��");
	private JLabel name = new JLabel("�̸�");
	private JLabel phone = new JLabel("��ȭ��ȣ");

	private JButton b1 = new JButton("ã��");
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();

	private JTextField nmtf = new JTextField(10);
	private JTextField phtf = new JTextField(10);

	public IDSearchGui() {
		setTitle("JsPostBox");
		setBounds(300, 100, 350, 250);
		this.setLayout(new GridLayout(4, 1));

		p1.add(title);
		p2.add(name);
		p2.add(nmtf);
		p3.add(phone);
		p3.add(phtf);
		p4.add(b1);

		add(p1);
		add(p2);
		add(p3);
		add(p4);

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
				JOptionPane.showMessageDialog(null, "��� ������ �Է����ּ���");
			} else if (phoneChk()) {
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ ������ Ʋ���ϴ�");
			} else {
				ArrayList<MemberDto> search = md.searchId(nmtf.getText(), phtf.getText());
				if (search == null) {
					JOptionPane.showMessageDialog(null, "������ ��ġ�ϴ� ���̵� �����ϴ�");
				}else {
					JOptionPane.showMessageDialog(null, "���̵�: "+ search.get(0).getId());
					nmtf.setText("");
					phtf.setText("");
					
				}
			}
		}
	}

	public boolean blank() {
		if (nmtf.getText().isEmpty()) {
			return true;
		} else if (phtf.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean phoneChk() {
		if (phtf.getText().length() == 13) {
			return false;
		} else {
			return true;
		}
	}

}
