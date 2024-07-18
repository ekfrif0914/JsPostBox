package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.MemberDto;

public class MemberDao {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public MemberDao() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("로드 성공");
		} catch (Exception e) {
			System.out.println("로드 실패");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "1111");// 사용자명, 비밀번호
			System.out.println("연결 성공");
			return conn;
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		System.out.println("끝");
		return null;
	}

	public boolean insertMember(MemberDto m) {
		PreparedStatement p = null;
		String sql = "insert into member values(?,?,?,?,?,?,?)";
		int a = 0;
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, m.getId());
				p.setString(2, m.getPw());
				p.setString(3, m.getHint());
				p.setString(4, m.getName());
				p.setString(5, m.getPhone());
				p.setString(6, m.getAddress());
				p.setString(7, "100");
				a = p.executeUpdate();
				System.out.println(a + "건 성공");
			} catch (Exception e) {
				System.out.println("입력작업에서 오류 발생");
			}
		}
		if (a == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean selectId(String id) {
		PreparedStatement p = null;
		String sql = "select id from member where id=?";
		int a = 0;
		if (getConnection() != null) {
			try {
				ResultSet r = null;
				p = conn.prepareStatement(sql);
				p.setString(1, id);
				r = p.executeQuery();
				a = p.executeUpdate();
				System.out.println(a + "개 결과값 찾기 성공");
			} catch (Exception e) {
				System.out.println("결과값 찾기 실패");
			}
		}
		if (a == 0) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<MemberDto> searchId(String name, String phone) {
		ArrayList<MemberDto> seari = new ArrayList<>();
		PreparedStatement p = null;
		String sql = "select id from member where name=? and phone=? ";
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, name);
				p.setString(2, phone);
				ResultSet r = p.executeQuery();
				while (r.next()) {
					MemberDto newd = new MemberDto();
					newd.setId(r.getString("id"));
					seari.add(newd);
					return seari;
				}
			} catch (Exception e) {
				System.out.println("아이디 찾기 오류 발생");
			}
		}
		return null;
	}

	public ArrayList<MemberDto> searchPw(String id, String hint) {
		ArrayList<MemberDto> searp = new ArrayList<>();
		PreparedStatement p = null;
		String sql = "select pw from member where id=? and pw_hint=? ";
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, id);
				p.setString(2, hint);
				ResultSet r = p.executeQuery();
				while (r.next()) {
					MemberDto newd = new MemberDto();
					newd.setPw(r.getString("pw"));
					searp.add(newd);
					return searp;
				}
			} catch (Exception e) {
				System.out.println("비밀번호 찾기 오류 발생");
			}
		}
		return null;
	}

	public ArrayList<MemberDto> selectMember(String id, String pw) {
		ArrayList<MemberDto> am = new ArrayList<>();
		PreparedStatement p = null;
		String sql = "select * from member where id=? and pw=?";
		if (getConnection() != null) {
			try {
				ResultSet r = null;
				p = conn.prepareStatement(sql);
				p.setString(1, id);
				p.setString(2, pw);
				r = p.executeQuery();
				while (r.next()) {
					MemberDto newd = new MemberDto();
					newd.setId(r.getString("id"));
					newd.setPw(r.getString("pw"));
					newd.setName(r.getString("name"));
					newd.setPhone(r.getString("phone"));
					newd.setAddress(r.getString("address"));
					newd.setPoint(r.getString("point"));
					am.add(newd);
					return am;
				}
			} catch (Exception e) {
				System.out.println("결과값 찾기 실패");
			}
		}
		return null;
	}
 
}