package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.Categoria;
import persistence.DatabaseManager;

@SuppressWarnings("serial")
public class AllCategories extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		LinkedList<Categoria> categorie = (LinkedList<Categoria>) DatabaseManager.getInstance().getDaoFactory().getCategoriaDAO().findAll();
		
		String categories = (new JSONArray(categorie).toString());
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.getWriter().print(categories);
		
	}

}
