package test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.BoardDao;
import test.vo.BoardVo;

@WebServlet("/board/update")
public class UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num=Integer.parseInt(req.getParameter("num"));
		BoardDao dao=new BoardDao();
		BoardVo vo=dao.select(num);
		req.setAttribute("vo",vo);
		req.getRequestDispatcher("/board/update.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int num=Integer.parseInt(req.getParameter("num"));
		String writer=req.getParameter("writer");
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		BoardDao dao=new BoardDao();
		BoardVo vo=new BoardVo(num, writer, title, content, null);
		int n=dao.update(vo);
		if(n>0) {
			resp.sendRedirect("/board/list2");
		}else {
			req.setAttribute("errMsg", "오류로 인하여 수정 실패했습니다");
			req.getRequestDispatcher("/board/list2").forward(req, resp);
		}
	}
}
