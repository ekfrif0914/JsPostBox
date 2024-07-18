package postBox;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.MemberDao;
import dto.MemberDto;

public class MemberJoinGui extends JFrame implements ActionListener {
	private MemberDao ma = new MemberDao();
	private boolean idOverlap = false;
	private String label[] = { "ȸ������", "���̵�", "��й�ȣ", "��й�ȣ ��Ʈ", "��￡ ���� �߾��� ��Ҵ�?", "�̸�", "��ȭ��ȣ", "�ּ�" };
	private JLabel yestext = new JLabel();// ��밡���� ���̵��Դϴ�
	private JLabel phoneform = new JLabel("ex) 010-xxxx-xxxx");

	private JLabel la[] = new JLabel[8];

	private JButton yes = new JButton("��밡��");
	private JButton joinlabel = new JButton("����");

	private JTextField idtf = new JTextField(10);
	private JTextField pwtf = new JTextField(10);
	private JTextField httf = new JTextField(10);
	private JTextField nametf = new JTextField(10);
	private JTextField phtf = new JTextField(10);
	private JTextField adtf = new JTextField(50);

	private JPanel p[] = new JPanel[9];

	public MemberJoinGui() {
		setTitle("JsPostBox");
		setBounds(100, 100, 700, 600);
		GridLayout g = new GridLayout(9, 1);
		this.setLayout(g);

		for (int i = 0; i < la.length; i++) {
			la[i] = new JLabel(label[i]);
		}

		for (int i = 0; i < p.length; i++) {
			p[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			add(p[i]);
		}
		yestext.setForeground(Color.red);
		phoneform.setForeground(Color.DARK_GRAY);
		yes.addActionListener(this);
		joinlabel.addActionListener(this);

		p[0].add(la[0]);
		p[1].add(la[1]);
		p[1].add(idtf);
		p[1].add(yes);
		p[1].add(yestext);
		p[2].add(la[2]);
		p[2].add(pwtf);
		p[3].add(la[3]);
		p[4].add(la[4]);
		p[4].add(httf);
		p[5].add(la[5]);
		p[5].add(nametf);
		p[6].add(la[6]);
		p[6].add(phtf);
		p[6].add(phoneform);
		p[7].add(la[7]);
		p[7].add(adtf);
		p[8].add(joinlabel);

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void showbox() {
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(joinlabel)) {
			if (blank()) {
				JOptionPane.showMessageDialog(null, "��� ������ �Է����ּ���");
			} else if (phoneChk()) {
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ ������ Ʋ���ϴ�");
			} else if (idOverlap == true) {
				JOptionPane.showMessageDialog(null, "��밡���� ���̵����� Ȯ���ϼ���");
			} else {
				MemberDto newm = new MemberDto();
				newm.setId(idtf.getText());
				newm.setPw(pwtf.getText());
				newm.setHint(httf.getText());
				newm.setName(nametf.getText());
				newm.setPhone(phtf.getText());
				newm.setAddress(adtf.getText());

				if (ma.insertMember(newm)) {
					JOptionPane.showMessageDialog(null, "���ԵǾ����ϴ�");
					idtf.setText("");
					pwtf.setText("");
					httf.setText("");
					nametf.setText("");
					phtf.setText("");
					adtf.setText("");//���鸸���. ȸ�������� �ٽ� ������ ���ְ� tf�� ��������

				} else {
					JOptionPane.showMessageDialog(null, "��ȿ���� �ʽ��ϴ�","�����߻�",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (e.getSource().equals(yes)) {
			if (ma.selectId(idtf.getText())) {
				System.out.println(ma.selectId(idtf.getText()));
				yestext.setText("�ߺ��� ���̵��Դϴ�");
				idOverlap = true;

			} else {
				System.out.println(ma.selectId(idtf.getText()));
				yestext.setText("��밡���� ���̵��Դϴ�");
				idOverlap = false;
			}
		}
	}

	public boolean blank() {
		if (idtf.getText().isEmpty()) {
			return true;
		} else if (pwtf.getText().isEmpty()) {
			return true;
		} else if (httf.getText().isEmpty()) {
			return true;
		} else if (nametf.getText().isEmpty()) {
			return true;
		} else if (phtf.getText().isEmpty()) {
			return true;
		} else if (adtf.getText().isEmpty()) {
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
