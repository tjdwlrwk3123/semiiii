package test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.BoardDao;
import test.vo.BoardVo;

@WebServlet("/board/insert")
public class InsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String writer=req.getParameter("writer");
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		BoardDao dao=new BoardDao();
		int num=dao.getMaxNum()+1;
		BoardVo vo=new BoardVo(num, writer, title, content, null);
		int n=dao.insert(vo);
		String code="fail";
		if(n>0) {
			code="success";
		}
		req.setAttribute("code", code);
		req.getRequestDispatcher("/board/result.jsp").forward(req, resp);
	}
}
