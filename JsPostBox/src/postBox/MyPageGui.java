package postBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.PostResDao;
import dto.MemberDto;
import dto.PostResDto;

public class MyPageGui extends JFrame implements ActionListener {
	private ArrayList<MemberDto> nowUser;// ���̵� ����
	private PostResDao prd = new PostResDao();
	private ReviewAddGui rag = new ReviewAddGui(this);

	private DefaultTableModel model;
	private JPanel panel;
	private JTable table;
	private JScrollPane js;
	private JButton up = new JButton("����");
	private JButton del = new JButton("����");
	private JButton print = new JButton("����� ���");
	private JButton review = new JButton("�ı� �ۼ�");
	private FlowLayout flow;

	public void setDefault(DefaultTableModel m) {
		this.model = m;
	}

	public void setTable(JTable t) {
		this.table = t;
	}

	public void setJScroll(JScrollPane q) {
		this.js = q;
	}

	public MyPageGui(ArrayList<MemberDto> a) {
		nowUser = a;
		setTitle("JsPostBox");
		setBounds(100, 100, 700, 700);

		flow = new FlowLayout(FlowLayout.CENTER, 50, 10);
		panel = new JPanel(flow);

		panel.add(up);
		panel.add(del);
		panel.add(print);
		panel.add(review);
		up.addActionListener(this);
		del.addActionListener(this);
		print.addActionListener(this);
		review.addActionListener(this);
		up.setBackground(Color.lightGray);
		del.setBackground(Color.lightGray);
		print.setBackground(Color.lightGray);
		review.setBackground(Color.orange);

		up.setPreferredSize(new Dimension(110, 40));
		del.setPreferredSize(new Dimension(110, 40));
		print.setPreferredSize(new Dimension(110, 40));
		review.setPreferredSize(new Dimension(110, 40));

		panel.setBackground(Color.white);
		add(panel, "South");
		panel.setPreferredSize(new Dimension(500, 460));

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void reArrange() {
		Vector<String> column = new Vector<String>(Arrays.asList("��¥", "ǰ��", "����", "��� ��Ī", "������ ���", "(��)��ȭ��ȣ",
				"(��)�ּ�", "�޴� ���", "(��)��ȭ��ȣ", "(��)�ּ�", "��ۿ�û����", "����� ���"));
		model = new DefaultTableModel(column, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Vector<Vector> list = prd.myList(nowUser.get(0).getId());

		for (int i = 0; i < list.size(); i++) {
			model.addRow(list.get(i));
		}
		table = new JTable(model);
		js = new JScrollPane(table);
		add(js, BorderLayout.CENTER);

		js.setSize(688, 200);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		this.setDefault(model);
		this.setJScroll(js);
		this.setTable(table);

	}

	public void removeScroll() {
		this.remove(js);
	}

	public void showbox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int indexChk = table.getSelectedRow();
		if (indexChk != -1) {
			if (e.getSource().equals(up)) {
				PostResDto exinfo = selectCell();
				if (!exinfo.getPrint().equals("��� �Ϸ�")) {
					UpdateGui ug = new UpdateGui(this);
					ug.showbox();
				} else {
					JOptionPane.showMessageDialog(null, "�̹� ������� ��µǾ� ������ �� �����ϴ�");
				}
			} else if (e.getSource().equals(del)) {
				delete();

			} else if (e.getSource().equals(print)) {
				printChange();
			} else if (e.getSource().equals(review)) {
				rag.showbox();
			}
		}
	}

	public PostResDto selectCell() {
		int row = table.getSelectedRow();
		TableModel data = table.getModel();

		PostResDto select = new PostResDto();
		select.setDate((String) data.getValueAt(row, 0));
		select.setCategory((String)data.getValueAt(row, 1));
		String price = cut((String) data.getValueAt(row, 2));
		select.setpPrice(price);
		select.setpName((String) data.getValueAt(row, 3));
		select.setsName((String) data.getValueAt(row, 4));
		select.setsPhone((String) data.getValueAt(row, 5));
		select.setsAddress((String) data.getValueAt(row, 6));
		select.setgName((String) data.getValueAt(row, 7));
		select.setgPhone((String) data.getValueAt(row, 8));
		select.setgAddress((String) data.getValueAt(row, 9));
		select.setgRequest((String) data.getValueAt(row, 10));
		select.setPrint((String) data.getValueAt(row, 11));
		select.setId(nowUser.get(0).getId());

		return select;

	}

	public String cut(String a) {
		int index = a.indexOf("��");
		String price = a.substring(0, index);
		return price;
	}

	public void delete() {
		int result = JOptionPane.showConfirmDialog(null, "�����ϰڽ��ϱ�?", "����", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.CLOSED_OPTION) {

		} else if (result == JOptionPane.YES_OPTION) {
			PostResDto exinfo = selectCell();
			prd.delete(exinfo);
			removeScroll();
			reArrange();

		} else if (result == JOptionPane.NO_OPTION) {

		}
	}

	public void printChange() {
		PostResDto exinfo = selectCell();
		if (!exinfo.getPrint().equals("��� �Ϸ�")) {
			int result = JOptionPane.showConfirmDialog(null, "����Ͻðڽ��ϱ�?", "���", JOptionPane.YES_NO_CANCEL_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				prd.printChange(exinfo);
				removeScroll();
				reArrange();
				JOptionPane.showMessageDialog(null, "������� ��µǾ����ϴ�");
			}
		} else {
			JOptionPane.showMessageDialog(null, "�̹� ��µǾ����ϴ�");
		}
	}

}