package postBox;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.ReviewDao;
import dto.ReviewDto;

public class ReviewShowGui extends JFrame implements ActionListener {
	private ReviewDao rd = new ReviewDao();
	private ArrayList<ReviewDto> ard = rd.selectAll();
	private int size = ard.size();
	private JPanel panel = new JPanel(new GridLayout(size, 2));
	private JButton complete = new JButton("완료");

	public ReviewShowGui() {
		setTitle("후기");
		setBounds(100, 100, 300, 600);
		JLabel[] label = new JLabel[size];
		JLabel[] label2 = new JLabel[size];

		for (int i = 0; i < size; i++) {
			label[i] = new JLabel();
			label[i].setText("아이디: " + ard.get(i).getId());
			panel.add(label[i]);

			label2[i] = new JLabel();
			label2[i].setText("내용: " + ard.get(i).getContents());
			panel.add(label2[i]);

		}

		add(panel, "Center");
		panel.setBackground(Color.white);
		add(complete, "South");
		complete.addActionListener(this);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void showbox() {
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(complete)) {
			this.setVisible(false);
		}

	}

}
