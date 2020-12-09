package test.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.BoardDao;
import test.vo.BoardVo;

@WebServlet("/board/list2")
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=(String)req.getSession().getAttribute("id");
		if(id==null) {
			resp.sendRedirect("/member/login.jsp");
			return;
		}
		String spageNum=req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		BoardDao dao=new BoardDao();
		ArrayList<BoardVo> list=dao.list(field, keyword,startRow,endRow);
		int pageCount=(int)Math.ceil(dao.getCount(field,keyword)/5.0);
		int startPageNum=((pageNum-1)/5*5)+1;
		int endPageNum=startPageNum+4;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		
		req.setAttribute("list", list);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
	}
}
