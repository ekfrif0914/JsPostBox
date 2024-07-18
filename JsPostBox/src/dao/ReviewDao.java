package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.PostResDto;
import dto.ReviewDto;

public class ReviewDao {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public ReviewDao() {
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
			// TODO: handle exception
		}
		System.out.println("끝");
		return null;
	}

	public void insert(PostResDto ex, String r) {
		PreparedStatement p = null;
		String sql = "update post_res set review =? where res_date=? and n_id=? and p_name=? and g_name=?";
		if (getConnection() != null) {
			try {
				p = conn.prepareStatement(sql);
				p.setString(1, r);
				p.setString(2, ex.getDate());
				p.setString(3, ex.getId());
				p.setString(4, ex.getpName());
				p.setString(5, ex.getgName());
				int a = p.executeUpdate();
				System.out.println(a + "건 성공");
			} catch (Exception e) {
				System.out.println("리뷰추가 작업 오류");
			}
		}
	}

	public ArrayList<ReviewDto> selectAll() {
		ArrayList<ReviewDto> review = new ArrayList<>();
		Statement p = null;
		String sql = "select review, n_id from post_res";
		if (getConnection() != null) {
			try {
				ResultSet r = null;
				p = conn.createStatement();
				r = p.executeQuery(sql);
				while (r.next()) {
					ReviewDto newr = new ReviewDto();
					newr.setContents(r.getString("review"));
					newr.setId(r.getString("n_id"));
					review.add(newr);
				}

			} catch (Exception e) {
				System.out.println("출력 작업 오류");
			}
		}
		return review;
	}

}
