package postBox;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.PostResDao;
import dto.MemberDto;
import dto.PostResDto;

public class PostResGui extends JFrame implements ActionListener {
//nowid�� ������ db���� �����;� �Ѵ�.
	private ArrayList<MemberDto> nowUser;
	private PostResDao prd = new PostResDao();

	private JLabel title = new JLabel("�ù� ����");
	private JLabel info = new JLabel("��ǰ ����");
	private JTextField num = new JTextField(3);// ���ڸ� �Է��ϵ���
	private JLabel price = new JLabel("����");
	private JLabel adminName = new JLabel("��� ��Ī ex)ģ�� ����");
	private JTextField adtf = new JTextField(7);
	private JLabel sendInfo = new JLabel("�����º� ����");
	private JLabel sendName = new JLabel("�̸�");
	private JTextField sntf = new JTextField(10);// nowUser.get(0).getName()
	private JLabel sendphone = new JLabel("����ó");
	private JLabel phoneform1 = new JLabel("ex) 010-xxxx-xxxx");
	private JLabel phoneform2= new JLabel("ex) 010-xxxx-xxxx");
	private JTextField sptf = new JTextField(10);// nowUser.get(0).getPhone()
	private JLabel sendaddress = new JLabel("�ּ�");
	private JTextField satf = new JTextField(50);// nowUser.get(0).getAddress()

	private JLabel getInfo = new JLabel("�޴º� ����");
	private JLabel getName = new JLabel("�̸�");
	private JTextField gntf = new JTextField(10);
	private JLabel getphone = new JLabel("����ó");
	private JTextField gptf = new JTextField(10);
	private JLabel getaddress = new JLabel("�ּ�");
	private JTextField gatf = new JTextField(50);
	private JLabel request = new JLabel("��� ��û����");
	private JTextField grtf = new JTextField(25);

	private JButton go = new JButton("�ù� �����ϱ�");
	private String category[] = { "�Ƿ�", "������ǰ��", "���Ϸ�", "���", "��ǰ��", "��ȭ/����" };
	private JComboBox<String> combo;
	private JPanel panel[] = new JPanel[14];

	private FlowLayout f = new FlowLayout(FlowLayout.LEFT);

	public PostResGui(ArrayList<MemberDto> ad) {
		nowUser = ad;
		setTitle("JsPostBox");
		setBounds(100, 100, 700, 600);
		combo = new JComboBox<String>(category);
		this.setLayout(new GridLayout(14, 1));

		for (int i = 0; i < panel.length; i++) {
			if (i > 0 && i <= 12) {
				panel[i] = new JPanel(f);
			} else {
				panel[i] = new JPanel();
			}
			add(panel[i]);
		}

		panel[0].add(title);
		panel[1].add(info);
		panel[1].add(combo);
		panel[2].add(num);
		panel[2].add(price);
		panel[3].add(adminName);
		panel[3].add(adtf);
		panel[4].add(sendInfo);
		panel[5].add(sendName);
		sntf.setText(nowUser.get(0).getName());
		panel[5].add(sntf);
		panel[6].add(sendphone);
		sptf.setText(nowUser.get(0).getPhone());
		panel[6].add(sptf);
		panel[6].add(phoneform1);
		panel[7].add(sendaddress);
		satf.setText(nowUser.get(0).getAddress());
		panel[7].add(satf);
		panel[8].add(getInfo);
		panel[9].add(getName);
		panel[9].add(gntf);
		panel[10].add(getphone);
		panel[10].add(gptf);
		panel[10].add(phoneform2);
		panel[11].add(getaddress);
		panel[11].add(gatf);
		panel[12].add(request);
		panel[12].add(grtf);
		panel[13].add(go);

		go.addActionListener(this);
		combo.addActionListener(this);

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	public void showbox() {
		this.setVisible(true);
	}

	public boolean blank() {
		if (num.getText().isEmpty()) {
			return true;
		} else if (adtf.getText().isEmpty()) {
			return true;
		} else if (sntf.getText().isEmpty()) {
			return true;
		} else if (sptf.getText().isEmpty()) {
			return true;
		} else if (satf.getText().isEmpty()) {
			return true;
		} else if (gntf.getText().isEmpty()) {
			return true;
		} else if (gptf.getText().isEmpty()) {
			return true;
		} else if (gatf.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean numChk(String num) {
		try {
			int number = Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
 
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(go)) {
			if (blank()) {
				JOptionPane.showMessageDialog(null, "�ʼ� ������ �Է����ּ���");
			} else if (!numChk(num.getText())) {
				JOptionPane.showMessageDialog(null, "��ǰ ���ݿ��� ���ڸ� �Է��� �� �ֽ��ϴ�");
			}else if(phoneChk(sptf.getText())){
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ ������ Ʋ���ϴ�");
			}else if(phoneChk(gptf.getText())){
				JOptionPane.showMessageDialog(null, "��ȭ��ȣ ������ Ʋ���ϴ�");
			} else {
				PostResDto newp = new PostResDto();

				newp.setId(nowUser.get(0).getId());// nowUser.get(0).getId()

				newp.setCategory(combo.getSelectedItem().toString());
				newp.setpPrice(num.getText());

				newp.setpName(adtf.getText());
				newp.setsName(sntf.getText());
				newp.setsPhone(sptf.getText());
				newp.setsAddress(satf.getText());
				newp.setgName(gntf.getText());
				newp.setgPhone(gptf.getText());
				newp.setgAddress(gatf.getText());
				newp.setgRequest(grtf.getText());
				newp.setPrint("0");
				newp.setReview("");

				prd.insert(newp);
				JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�");
				num.setText("");
				adtf.setText("");
				sntf.setText("");
				sptf.setText("");
				satf.setText("");
				gntf.setText("");
				gptf.setText("");
				gatf.setText("");
				grtf.setText("");
			}
		}
	}
	
	public boolean phoneChk(String phone) {
		if (phone.length() == 13) {
			return false;
		} else {
			return true;
		}
	}

}