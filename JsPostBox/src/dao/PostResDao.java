package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import dto.PostResDto;

public class PostResDao {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public PostResDao() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("�ε� ����");
		} catch (Exception e) {
			System.out.println("�ε� ����");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "1111");// ����ڸ�, ��й�ȣ
			System.out.println("���� ����");
			return conn;
		} catch (Exception e) {
			System.out.println("���� ����");
		}
		System.out.println("��");
		return null;
	}

	public void insert(PostResDto r) {
		PreparedStatement p = null;
		String sql = "insert into post_res values (sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		int a = 0;
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);

				p.setString(1, r.getId());
				p.setString(2, r.getCategory());
				p.setString(3, r.getpPrice());
				p.setString(4, r.getpName());
				p.setString(5, r.getsName());
				p.setString(6, r.getsPhone());
				p.setString(7, r.getsAddress());
				p.setString(8, r.getgName());
				p.setString(9, r.getgPhone());
				p.setString(10, r.getgAddress());
				p.setString(11, r.getgRequest());
				p.setString(12, r.getPrint());
				p.setString(13, r.getReview());

				a = p.executeUpdate();
				System.out.println(a + "�� ����");
			} catch (Exception e) {
				System.out.println("�Է��۾����� ���� �߻�");
			}
		}
	}

	public Vector<Vector> myList(String nowId) {
		Vector<Vector> setRow = new Vector<>();
		String sql = "select res_date, p_category, p_price, p_name , s_name, s_phone,"
				+ " s_address , g_name, g_phone , g_address, g_request, track_print" + " from post_res where n_id =?";
		PreparedStatement p = null;
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, nowId);
				ResultSet r = p.executeQuery();
				while (r.next()) {

					Vector<String> row = new Vector<String>();
					row.addElement(r.getString("res_date"));
					row.addElement(r.getString("p_category"));
					row.addElement(r.getString("p_price") + "����");
					row.addElement(r.getString("p_name"));
					row.addElement(r.getString("s_name"));
					row.addElement(r.getString("s_phone"));
					row.addElement(r.getString("s_address"));
					row.addElement(r.getString("g_name"));
					row.addElement(r.getString("g_phone"));
					row.addElement(r.getString("g_address"));
					row.addElement(r.getString("g_request"));
					if (r.getString("track_print").equals("0")) {
						row.addElement("          -");
					} else {
						row.addElement("��� �Ϸ�");// 12
					}
					setRow.addElement(row);
				}
			} catch (Exception e) {
				System.out.println("��� �۾� ����");
			}
		}
		return setRow;
	}

	public void updateCell(PostResDto newinfo, PostResDto ex) {
		String sql = "update post_res set p_category=? , p_price =? , p_name =? ,s_name=? , s_phone=? , s_address=?,"
				+ " g_name=?, g_phone=? , g_address=? , g_request=? "
				+ " where res_date=? and n_id=? and p_name=? and g_name=?";
		PreparedStatement p = null;
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, newinfo.getCategory());
				p.setString(2, newinfo.getpPrice());
				p.setString(3, newinfo.getpName());
				p.setString(4, newinfo.getsName());
				p.setString(5, newinfo.getsPhone());
				p.setString(6, newinfo.getsAddress());
				p.setString(7, newinfo.getgName());
				p.setString(8, newinfo.getgPhone());
				p.setString(9, newinfo.getgAddress());
				p.setString(10, newinfo.getgRequest());
				p.setString(11, ex.getDate());
				p.setString(12, ex.getId());
				p.setString(13, ex.getpName());
				p.setString(14, ex.getgName());

				int a = p.executeUpdate();
				System.out.println(a + "�� ����");
			} catch (Exception e) {
				System.out.println("���� �۾� ����");
			}
		}
	}

	public void delete(PostResDto ex) {
		String sql = "delete from post_res where res_date=? and n_id=? and p_name=? and g_name=?";
		PreparedStatement p = null;
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, ex.getDate());
				p.setString(2, ex.getId());
				p.setString(3, ex.getpName());
				p.setString(4, ex.getgName());
				int a = p.executeUpdate();
				System.out.println(a + "�� ����");
			} catch (Exception e) {
				System.out.println("���� �۾� ����");
			}
		}
	}
	
	public void printChange(PostResDto ex) {
		String sql="update post_res set track_print = '1' where res_date=? and n_id=? and p_name=? and g_name=?";
		PreparedStatement p = null;
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, ex.getDate());
				p.setString(2, ex.getId());
				p.setString(3, ex.getpName());
				p.setString(4, ex.getgName());
				int a = p.executeUpdate();
				System.out.println(a + "�� ����");
			}catch(Exception e) {
				System.out.println("����� �۾� ����");
			}
		}
	}
}
