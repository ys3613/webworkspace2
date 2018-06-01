package file.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.MyFileRenamePolicy;
import file.model.service.FileService;
import file.model.vo.DataFile;
import file.model.vo.DataFile2;
import member.model.vo.member;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/upload2")
public class FileUpload2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드를 구현하려면 몇가지 정보가 필요함
		// 1. 사용자 계정명(업로드한 사람 정보가 있어야함, session객체 에서 꺼냄)
		HttpSession session = request.getSession();
		String userId = ((member)session.getAttribute("login")).getUserId();
		
		// 2. 최대 업로드 파일 사이즈(설정)
		int fileSizeLimit = 1024*1024*5; //(5mb)
		
		// 3. 업로드 될 경로
		String uploadFilePath = getServletContext().getRealPath("/")+"uploadFile";
		
		// 4. 인코딩 타입(파일 인코딩 타입)
		String encType="UTF-8";
		
		// 5. MultyPartRequest 객체를 생성
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, fileSizeLimit, encType, new MyFileRenamePolicy());
		// 마지막 인자값인 DefaultFileRenamePolicy 객체를 생성하여
		// 넣어줌으로써 파일 중복 처리를 자동으로 해결함
		// ex) a.bmp가 중복으로 업로드 되면 a1.bmp, a2.bmp, a3.bmp ....
		// MultipartRequest 객체가 생성되면 자동으로 파일은 해당 경로로 업로드 됨
		
		

		// 업로드된 파일의 정보를 DB에 기록하여야 함
		
		// 1. 파일이름(filename)
		//getFilesystemName("view의 파라미터 이름");을 하게 되면 해당 업로드 될때의 파일 이름을 가져옴
		String beforeFileName = multi.getOriginalFileName("upfile");
		String afterfileName = multi.getFilesystemName("upfile");
		
		// 2. 업로드 파일의 실제 총 경로(filePath)
		// 총 경로 : filePath+파일이름
		// ex) 업로드된  파일이 a.txt라면
		// 총경로 :C:/Users/user1/git/webworkspace2\web2\WebContent/uploadfile/a.txt
		String fullFilePath = uploadFilePath+"\\"+afterfileName;
		
		System.out.println(fullFilePath);
		
		File file = new File(fullFilePath);
		long fileSize = file.length();
		// 파일 사이즈가 long인 이유
		// 파일사이즈는 byte단위
		System.out.println("파일 사이즈 : "+fileSize );
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = null;
		uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		DataFile2 df = new DataFile2(beforeFileName,afterfileName,fullFilePath, fileSize, userId, uploadTime);
		
		int result = new FileService().uploadFile2(df);
		
		if(result==1)
		{
			response.sendRedirect("/views/file/uploadSuccess.jsp");
		}
		else
		{
			response.sendRedirect("/views/file/uploadError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
