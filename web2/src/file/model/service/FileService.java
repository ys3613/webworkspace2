package file.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.JDBCTemplate;
import file.model.dao.FileDao;
import file.model.vo.DataFile;
import file.model.vo.DataFile2;

public class FileService {

	public int uploadFile(DataFile df) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		int result = new FileDao().uploadFile(conn,df);
		if(result==1)
		{
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<DataFile> selectAll() {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		ArrayList<DataFile> list = new FileDao().selectAll(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public DataFile fileSelectDownload(String fileName, Timestamp uploadTime) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		DataFile df = new FileDao().fileSelectDownload(conn,fileName, uploadTime);
		JDBCTemplate.close(conn);
		return df;
	}

	public int uploadFile2(DataFile2 df) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		int result = new FileDao().uploadFile2(conn,df);
		if(result==1)
		{
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<DataFile2> selectAll2() {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		ArrayList<DataFile2> list = new FileDao().selectAll2(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public DataFile2 fileSelectDownload2(String beforeFileName, Timestamp uploadTime) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.connection(conn);
		DataFile2 df = new FileDao().fileSelectDownload2(conn,beforeFileName, uploadTime);
		JDBCTemplate.close(conn);
		return df;
	}
}
