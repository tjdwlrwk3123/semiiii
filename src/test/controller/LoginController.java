package test.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.dao.MyUsersDao;

@WebServlet("/member/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		// 아이디와 비밀번호가 일치하면 세션에 id를 저장하고 맞지 않으면 오류메시지를 담아서 login.jsp로 가도록
		MyUsersDao dao=MyUsersDao.getDao();
		HttpSession session=req.getSession();
		int n=dao.isMember(map);
		if(n>0) {
			session.setAttribute("id", id);
			resp.sendRedirect("/index.jsp");
		}else if(n==0) {
			req.setAttribute("errMsg", "아이디 또는 비밀번호가 맞지 않습니다");
			req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
		}else {
			req.setAttribute("errMsg", "내부 오류로 인해 로그인하지 못했습니다.");
			req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
		}
	}
}
