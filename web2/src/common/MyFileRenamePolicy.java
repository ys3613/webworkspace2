package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		// TODO Auto-generated method stub
		// 업로드된 파일명을 변경하는 로직
		// ex) 파일명.txt 라고 되어 있다면
		// 시간_랜덤수.txt로 변경하도록 할 것임
		// ex) a.txt -> 20180531155428985_98483.txt
		
		// 1.시간값을 가져옴
		long currentTimeValue = Calendar.getInstance().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		String currentTime = sdf.format(new Date(currentTimeValue)); // java.util
		
		// 2. 랜덤 수를 가져옴
		int randomSu = new Random().nextInt(1000000);
		
		// 3. 확장자명 추출
		String oldFileName = oldFile.getName(); // 기존 파일명
		String ext = null; // 현재 확장자명 없음
		int dot = oldFileName.lastIndexOf(".");
		//lastIndexof메소드는 찾으면 index값 리턴
		// 못 찾았다면(없다면) -1 리턴
		
		if(dot>-1)
		{
			ext = oldFileName.substring(dot);
		}
		else
		{
			ext = "";
		}
		
		// 4. 위의 정보를 바탕으로 새로운 파일명을 생성
		String newFileName = currentTime+"_"+randomSu+ext;
		
		File newFile = new File(oldFile.getParentFile(),newFileName);
		return newFile;
	}
	
}
