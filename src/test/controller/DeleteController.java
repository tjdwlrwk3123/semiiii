package test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.BoardDao;

@WebServlet("/board/delete")
public class DeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num=Integer.parseInt(req.getParameter("num"));
		BoardDao dao=new BoardDao();
		int n=dao.delete(num);
		if(n>0) {
			resp.sendRedirect("/board/list2");
		}else {
			req.setAttribute("errMsg", "오류로 인해 삭제하지 못했습니다");
			req.getRequestDispatcher("/board/list2").forward(req, resp);
		}
	}
}
