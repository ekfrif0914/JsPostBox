package postBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuGui extends JFrame implements ActionListener {
	private MemberLoginGui mlg = new MemberLoginGui(this);
	private MemberJoinGui mj = new MemberJoinGui();
	
	private ImageIcon icon = new ImageIcon("D:\\프로그램\\src\\java\\JsPostBox\\image\\postbox.jpg");
	private JLabel image = new JLabel(icon);
	private JButton b1 = new JButton("회원가입");
	private JButton b2 = new JButton("로그인");

	private FlowLayout f = new FlowLayout(FlowLayout.CENTER, 50, 0);
	private JPanel panel = new JPanel(f);

	public MenuGui() {
		setTitle("JsPostBox");
		setBounds(100, 100, 700, 600);
		add(image);

		panel.add(b1);
		b1.setPreferredSize(new Dimension(190, 80));
		b1.setBackground(Color.cyan);
		b1.setForeground(Color.BLUE);
		panel.add(b2);
		b2.setPreferredSize(new Dimension(190, 80));
		b2.setBackground(Color.cyan);
		b2.setForeground(Color.BLUE);
		panel.setPreferredSize(new Dimension(10, 100));
		add(panel, "South");

		b1.addActionListener(this);
		b2.addActionListener(this);

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void showbox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {
			mj.showbox();
		} else if (e.getSource().equals(b2)) {
			mlg.showbox();
		}
 
	}

}