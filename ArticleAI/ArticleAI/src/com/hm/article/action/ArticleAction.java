package com.hm.article.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hm.article.entity.Article;
import com.hm.article.service.ArticleService;

/**
 * ��Ӧ�ؼ������� API����
 * 
 * @author Administrator �Ҿ�����Ӧ�����ܣ�Ϊ���ô�Ҷ�֪���Ҵ��ڣ������⹫���ҵ���Ϣ
 *
 *
 */
public class ArticleAction extends HttpServlet {

	/**
	 * �����û����� JSP servletͬһ����
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �������루�����룩������ ���� )
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// ��������ؼ���
		String articleKey = req.getParameter("articleKey");
		// ֪ʶ�⣬����
		ArticleService articleService = new ArticleService();
		Article article = articleService.getArticle(articleKey);
		// ��Ӧ����� ����������
		req.setAttribute("article", article);
		req.getRequestDispatcher("/showArticle.jsp").forward(req, resp);		
	}

}
