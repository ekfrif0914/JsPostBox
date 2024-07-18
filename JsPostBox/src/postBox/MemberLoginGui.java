package postBox;

import java.awt.Color;
import java.awt.FlowLayout;
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

public class MemberLoginGui extends JFrame implements ActionListener {
	private IDSearchGui isg = new IDSearchGui();
	private PwSearchGui pwg = new PwSearchGui();
	private MemberDao md = new MemberDao();
	private ManagerGui mg = new ManagerGui(this);

	private MenuGui address;

	private JLabel login = new JLabel("ȸ�� �α���");
	private JLabel id = new JLabel("id");
	private JLabel pw = new JLabel("pw");
	private JTextField idtf = new JTextField(10);
	private JTextField pwtf = new JTextField(10);

	private JButton b1 = new JButton("���̵� ã��");
	private JButton b2 = new JButton("��й�ȣ ã��");
	private JButton b3 = new JButton("�α���");

	private FlowLayout f = new FlowLayout(FlowLayout.LEFT);

	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel(f);
	private JPanel p3 = new JPanel(f);
	private JPanel p4 = new JPanel();

	public MemberLoginGui(MenuGui a) {
		address = a;
		setTitle("JsPostBox");
		setBounds(100, 100, 400, 300);
		this.setLayout(new GridLayout(4, 1));
		b1.setBackground(Color.pink);
		b2.setBackground(Color.pink);
		p1.add(login);
		p2.add(id);
		p2.add(idtf);
		p3.add(pw);
		p3.add(pwtf);
		p4.add(b1);
		p4.add(b2);
		p4.add(b3);

		add(p1);
		add(p2);
		add(p3);
		add(p4);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void showbox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {
			isg.showbox();
		} else if (e.getSource().equals(b2)) {
			pwg.showbox();
		} else if (e.getSource().equals(b3)) {

			if (blank()) {
				JOptionPane.showMessageDialog(null, "��� ������ �Է����ּ���");
			} else if (idtf.getText().equals(mg.getId()) && pwtf.getText().equals(mg.getPw())) {
				this.setVisible(false);
				mg.showbox();
				idtf.setText("");
				pwtf.setText("");
			} else {
				ArrayList<MemberDto> nowUser = md.selectMember(idtf.getText(), pwtf.getText());// �ּҰ�
				if (nowUser != null) {
					address.setVisible(false);
					this.setVisible(false);
					idtf.setText("");
					pwtf.setText("");
					PostMenuGui pmg = new PostMenuGui(address, nowUser);
					pmg.showbox();

				} else {
					JOptionPane.showMessageDialog(null, "�α��� ����");
				}
			}
		}
	}

	public boolean blank() {
		if (idtf.getText().isEmpty()) {
			return true;
		} else if (pwtf.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}