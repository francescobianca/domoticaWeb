package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.Categoria;
import model.Domanda;
import model.Utente;
import persistence.DatabaseManager;

@SuppressWarnings("serial")
public class ApriQuestion extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection=DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		LinkedList<Domanda> domande=new LinkedList<>();
		try{
			Domanda domanda;
			PreparedStatement statement;
			String query = "select * from domanda,utente as u where u.email = \"utente\" order by id DESC LIMIT 10";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				domanda = new Domanda();
				domanda.setId(result.getInt("id"));
				domanda.setTitolo(result.getString("titolo"));
				domanda.setTesto(result.getString("testo"));

				Categoria categoria = new Categoria(result.getString("categoria"));
				domanda.setCategoria(categoria);

				Utente utente = new Utente();
				utente.setEmail(result.getString("email"));
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				long secs = result.getDate("data_nascita").getTime();
				utente.setDataNascita(new java.util.Date(secs));

				domanda.setUtente(utente);

				domande.add(domanda);

			}
			
			req.setAttribute("domande", domande);
			RequestDispatcher dispatcher=req.getRequestDispatcher("Question.jsp");
			dispatcher.forward(req, resp);
		}catch(Exception e){
			
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text=req.getParameter("text").toLowerCase();
		String[] parole=text.split(" ");
		LinkedList<Domanda> domande=(LinkedList<Domanda>) DatabaseManager.getInstance().getDaoFactory().getDomandaDAO().findAll();
		
		ArrayList<Domanda> domandeFiltrate = new ArrayList<>();
				
		for (Domanda d : domande) {
			
			boolean trovato=false;
			for(int i=0;i<parole.length;i++){
				if((d.getTitolo().toLowerCase()).contains(parole[i]))
					trovato=true;
			}
			
			/*if(!trovato){
				domande.remove(d);
			}*/
		
			if (trovato)
				domandeFiltrate.add(d);
		}
		
		String domandeFiltro = (new JSONArray(domandeFiltrate).toString());
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.getWriter().print(domandeFiltro);
	}
	
}
