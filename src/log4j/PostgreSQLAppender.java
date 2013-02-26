package log4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.jdbc.JDBCAppender;

public class PostgreSQLAppender extends JDBCAppender {

	public PostgreSQLAppender() {
		super();
	}

	protected void execute(String sql) throws SQLException {
		Connection con = null;
		Statement stmt = null;

		//TODO костыльное решение, продумать вопрос об использовании паттерна
		try {
			con = getConnection();

			stmt = con.createStatement();
			sql = sql.replace("'", "''");
			sql = sql.replace("$$", "'");
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			if (stmt != null)
				stmt.close();
			throw e;
		}
		stmt.close();
		closeConnection(con);
	}
}
