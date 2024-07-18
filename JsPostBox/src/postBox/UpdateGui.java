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
	private JLabel date1 = new JLabel("��¥:");
	private JLabel date2;
	private JLabel category = new JLabel("ǰ��");// �޺��ڽ�
	private JLabel price = new JLabel("����:");
	private JLabel won = new JLabel("����");
	private JLabel pName = new JLabel("��� ��Ī:");
	private JLabel sName = new JLabel("������ ���:");
	private JLabel sPhone = new JLabel("(��)��ȭ:");
	private JLabel sAddress = new JLabel("(��)�ּ�:");
	private JLabel gName = new JLabel("�޴� ���:");
	private JLabel gPhone = new JLabel("(��)��ȭ:");
	private JLabel gAddress = new JLabel("(��)�ּ�:");
	private JLabel gRequest = new JLabel("��� ��û����:");

	private JTextField ptf = new JTextField(10);
	private JTextField atf = new JTextField(10);
	private JTextField sntf = new JTextField(10);
	private JTextField sptf = new JTextField(10);
	private JTextField satf = new JTextField(10);
	private JTextField gntf = new JTextField(10);
	private JTextField gptf = new JTextField(10);
	private JTextField gatf = new JTextField(10);
	private JTextField grtf = new JTextField(10);

	private JButton nUp = new JButton("����");

	private String category1[] = { "�Ƿ�", "������ǰ��", "���Ϸ�", "���", "��ǰ��", "��ȭ/����" };
	private String category2[] = { "������ǰ��", "�Ƿ�", "���Ϸ�", "���", "��ǰ��", "��ȭ/����" };
	private String category3[] = { "���Ϸ�", "�Ƿ�", "������ǰ��", "���", "��ǰ��", "��ȭ/����" };
	private String category4[] = { "���", "�Ƿ�", "������ǰ��", "���Ϸ�", "��ǰ��", "��ȭ/����" };
	private String category5[] = { "��ǰ��", "�Ƿ�", "������ǰ��", "���Ϸ�", "���", "��ȭ/����" };
	private String category6[] = { "��ȭ/����", "�Ƿ�", "������ǰ��", "���Ϸ�", "���", "��ǰ��" };
	private JComboBox<String> combo;

	private MyPageGui a;

	public UpdateGui(MyPageGui address) {
		a = address;

		setTitle("�������� ����");

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
			JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");
			a.removeScroll();
			a.reArrange();
			this.dispose();
		}
	}
}
