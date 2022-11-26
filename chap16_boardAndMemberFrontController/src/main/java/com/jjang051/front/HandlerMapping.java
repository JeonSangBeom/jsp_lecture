package com.jjang051.front;

import java.util.HashMap;

import com.jjang051.controller.AbstractController;
import com.jjang051.controller.board.*;
import com.jjang051.controller.member.*;
import com.jjang051.controller.*;

// import com.jjang051.controller.*; 안에 있는 모든 것들을 가져오겠다는 뜻 - 아니면 너무 길어진다
// command에 실려 넘어오는 주소를 Controller에 매핑-연결해 주겠다(jsp에서 넘어갈떄)
public class HandlerMapping {
	private HashMap<String, AbstractController> mappings;
	public HandlerMapping() {
		mappings = new HashMap<String, AbstractController>();
		//board mapping
		mappings.put("/BoardList.do", new BoardListController());
		mappings.put("/BoardView.do", new BoardViewController());
		mappings.put("/BoardWrite.do", new BoardWriteController());
		mappings.put("/BoardWriteProcess.do", new BoardWriteProcessController());
		mappings.put("/BoardReplyWrite.do", new BoardReplyWriteController());
		mappings.put("/BoardReplyWriteProcess.do", new BoardReplyWriteProcessController());
		mappings.put("/BoardDelete.do", new BoardDeleteController());
		mappings.put("/BoardDeleteProcess.do", new BoardDeleteProcessController());
		mappings.put("/BoardUpdate.do", new BoardUpdateController());
		mappings.put("/BoardUpdateProcess.do", new BoardUpdateProcessController());
		mappings.put("/SummerNoteFileUpload.do", new SummerNoteFileUploadController());
		
		//member
		mappings.put("/Login.do", new LoginController());
		mappings.put("/LoginProcess.do", new LoginProcessController());
		mappings.put("/LogOut.do", new LogOutController());
		mappings.put("/MemberList.do", new MemberListController());
		mappings.put("/MemberInfo.do", new MemberInfoController());
		mappings.put("/MemberDelete.do", new MemberDeleteController());
		mappings.put("/MemberDeleteProcess.do", new MemberDeleteProcessController());
		mappings.put("/MemberUpdate.do", new MemberUpdateController());
		mappings.put("/MemberUpdateProcess.do", new MemberUpdateProcessController());
		mappings.put("/MemberJoin.do", new MemberJoinController());
		mappings.put("/MemberJoinProcess.do", new MemberJoinProcessController());
		mappings.put("/IDCheck.do", new IDCheckController());
	}
	//AbstractController를 리턴해주는 getController 만들기 - key에 "/BoardList.do"을 담아 뒤에 값 얻어오기
	public AbstractController getController(String key) {
		return mappings.get(key);
	}
}
