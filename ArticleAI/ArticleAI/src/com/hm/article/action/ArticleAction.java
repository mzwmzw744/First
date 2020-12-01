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
 * 响应关键字搜索 API（）
 * 
 * @author Administrator 我具有响应请求功能，为了让大家都知道我存在，我向外公布我的信息
 *
 *
 */
public class ArticleAction extends HttpServlet {

	/**
	 * 处理用户请求 JSP servlet同一回事
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 中文乱码（（编码）二进制 解码 )
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 解析请求关键字
		String articleKey = req.getParameter("articleKey");
		// 知识库，人物
		ArticleService articleService = new ArticleService();
		Article article = articleService.getArticle(articleKey);
		// 响应浏览器 请求作用域
		req.setAttribute("article", article);
		req.getRequestDispatcher("/showArticle.jsp").forward(req, resp);		
	}

}
