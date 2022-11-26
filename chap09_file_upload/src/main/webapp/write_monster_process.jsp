<%@page import="com.jjang051.util.ScriptWriter"%>
<%@page import="com.jjang051.model.MarioDto"%>
<%@page import="com.jjang051.model.MarioDao"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");// 한글 넘어오는 거 안깨지게 받아 오고
	
	String savePath = "upload_mario";// 디렉토리에 하나 만들고 / 이미지가 올라갈 경로
	ServletContext context =  this.getServletContext();  // 현재 돌아가는 context(경로)를 얻어 오는 것
	String realPath = context.getRealPath(savePath); //현재 경로에 있는 진짜 경로의 값을 알려준다
	int fileSize = 1024*1024*100;
	String encoding = "UTF-8";
	DefaultFileRenamePolicy fileRenamePolicy = new DefaultFileRenamePolicy(); // 이름이 같은 파일(중복파일)이 올라갔을때 처리방법...
	
	System.out.println(context);
	System.out.println(realPath);
	//C:\Users\jsb43\Desktop\jsp-lecture\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\file_upload 링크 타고 들어가 upload_mario 폴더 생성 해주기
	// 업로드 후에 이 위치로 이미지가 올라가 있어야 한다
	
	MultipartRequest muptipart = new MultipartRequest(request,realPath,fileSize,encoding,fileRenamePolicy);
	//cos.jar 안에 있는 것  (파일 사이즈 잡아 주고 , encding - utf-8 , fileRenamePolicy(파일명이 같으면 어떻게 넣을지) )
	//여기까지가 파일 업로드 되는 것
	
	//request 로 못 받고 multipart로 받아올 수 있다(파일 업로드 하는 경우)
	String user_title = muptipart.getParameter("user_title");
	String user_bg = muptipart.getParameter("user_bg");
	String user_link = muptipart.getParameter("user_link");
	String user_contents = muptipart.getParameter("user_contents");
	String marioImg = muptipart.getOriginalFileName("user_file"); // 이건 진짜 이름 (파일들만 변경해주면 된다)
	String marioRealImg = muptipart.getFilesystemName("user_file"); // 이름이 중복됐을때 들어가는 이름
	
	MarioDao marioDao = new MarioDao();
	MarioDto marioDto = new MarioDto();
	
	marioDto.setTitle(user_title);
	marioDto.setBg(user_bg);
	marioDto.setLink(user_link);
	marioDto.setContents(user_contents);
	marioDto.setMarioImg(marioImg);
	marioDto.setMarioRealImg(marioRealImg);
	
	int result = marioDao.insertMario(marioDto);// dto를 dao에 던져준다
	if(result>0) {
		ScriptWriter.alertAndNext(response, "이미지 업로드가 되었습니다.", "/file_upload");
	} else {
		ScriptWriter.alertAndBack(response, "db오류입니다.");
	}
%>

