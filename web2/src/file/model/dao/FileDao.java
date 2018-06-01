package file.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.JDBCTemplate;
import file.model.vo.DataFile;
import file.model.vo.DataFile2;

public class FileDao {

	public int uploadFile(Connection conn, DataFile df) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into fileTbl values(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, df.getFileName());
			pstmt.setString(2, df.getFilePath());
			pstmt.setLong(3, df.getFileSize());
			pstmt.setString(4, df.getFileUser());
			pstmt.setTimestamp(5, df.getUploadTime());
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result=1;
			}
			else
			{
				result=0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<DataFile> selectAll(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select* from filetbl";
		ArrayList<DataFile> list = new ArrayList<DataFile>();
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			
			while(rset.next())
			{
				DataFile df = new DataFile();
				df.setFileName(rset.getString("filename"));
				df.setFilePath(rset.getString("filepath"));
				df.setFileSize(rset.getLong("filesize"));
				df.setFileUser(rset.getString("fileuser"));
				df.setUploadTime(rset.getTimestamp("uploadtime"));
				list.add(df);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public DataFile fileSelectDownload(Connection conn, String fileName, Timestamp uploadTime) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select* from filetbl where fileName=? and uploadTime=?";
		DataFile df = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileName);
			pstmt.setTimestamp(2, uploadTime);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				df = new DataFile();
				df.setFileName(rset.getString("filename"));
				df.setFilePath(rset.getString("filepath"));
				df.setFileSize(rset.getLong("filesize"));
				df.setFileUser(rset.getString("fileuser"));
				df.setUploadTime(rset.getTimestamp("uploadtime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return df;
	}

	public int uploadFile2(Connection conn, DataFile2 df) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into fileTbl2 values(?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, df.getBeforeFileName());
			pstmt.setString(2, df.getAfterFileName());
			pstmt.setString(3, df.getFilePath());
			pstmt.setLong(4, df.getFileSize());
			pstmt.setString(5, df.getFileUser());
			pstmt.setTimestamp(6, df.getUploadTime());
			
			result = pstmt.executeUpdate();
			if(result>0)
			{
				result=1;
			}
			else
			{
				result=0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<DataFile2> selectAll2(Connection conn) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select* from filetbl2";
		ArrayList<DataFile2> list = new ArrayList<DataFile2>();
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			
			while(rset.next())
			{
				DataFile2 df = new DataFile2();
				df.setBeforeFileName(rset.getString("beforefilename"));
//				df.setFilePath(rset.getString("filepath"));
				df.setFileSize(rset.getLong("filesize"));
				df.setFileUser(rset.getString("fileuser"));
				df.setUploadTime(rset.getTimestamp("uploadtime"));
				list.add(df);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

}
