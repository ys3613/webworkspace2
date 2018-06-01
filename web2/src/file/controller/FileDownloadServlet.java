package file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import file.model.service.FileService;
import file.model.vo.DataFile;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/fileDown")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		String fileName = request.getParameter("fileName");
		Timestamp uploadTime = Timestamp.valueOf(request.getParameter("uploadTime"));
		
		System.out.println(uploadTime);
		
		DataFile df = new FileService().fileSelectDownload(fileName,uploadTime);
		System.out.println("df : "+ df);
		if(df!=null)
		{
			File file = new File(df.getFilePath());
			
			String endFileName = new String(df.getFileName().getBytes(),"ISO-8859-1");
			
			response.setContentType("application/octet-stream");
			response.setContentLengthLong(file.length());
			response.setHeader("Content-Disposition", "attachment;filename="+endFileName);
			
			FileInputStream fileIn = new FileInputStream(file);
			
			ServletOutputStream out = response.getOutputStream();
			
			byte[] outputByte = new byte[4096];
			
			while(fileIn.read(outputByte,0,4096)!=-1)
			{
				out.write(outputByte,0,4096);
			}
			fileIn.close();
			out.close(); 
		}
		else
		{
			response.sendRedirect("/views/file/fileError.html");
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
