/**
 * 
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import model.AttivitaPeriodica;
import model.Sensore;
import persistence.DatabaseManager;
import persistence.PersistenceException;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class LeggiAttivita extends HttpServlet{
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		String utente=(String)session.getAttribute("email");
		
		String tipo=req.getParameter("tipo");
		
		if(tipo==null){
			//restituisco tutte le attivita per l'utente
			Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
			// Set delle attivita
			ResultSet setAttivita = null;
			try {
				String query = "select id, nome, giornoinizio, giornofine, orarioinizio, orariofine, "
						+"tipo, stanza from attivitaperiodica where \"utente\"= ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				setAttivita = statement.executeQuery();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			AttivitaPeriodica a=null;
			ArrayList<AttivitaPeriodica> attivita=new ArrayList<>();
			try{
				while(setAttivita.next()){
					long dataInizio=setAttivita.getDate("giornoinizio").getTime();
					long dataFine=setAttivita.getDate("giornofine").getTime();
					long orarioInizio=setAttivita.getTime("orarioinizio").getTime();
					long orarioFine=setAttivita.getTime("orariofine").getTime();
					int id=setAttivita.getInt("id");
					String nome=setAttivita.getString("nome");
					String tipoAttivita=setAttivita.getString("tipo");
					String stanza=setAttivita.getString("stanza");
					Sensore s=new Sensore();
					s.setTipo(tipoAttivita);
					s.setStanza(stanza);
					a=new AttivitaPeriodica();
					a.setId(id);
					a.setGiornoInizio(new java.util.Date(dataInizio));
					a.setGiornoFine(new java.util.Date(dataFine));
					a.setOrarioInizio(new java.util.Date(orarioInizio));
					a.setOrarioFine(new java.util.Date(orarioFine));
					a.setNome(nome);
					a.setSensore(s);
					attivita.add(a);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		
			String risposta=(new JSONArray(attivita).toString());
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().print(risposta);
			resp.getWriter().flush();
			resp.getWriter().close();
			return ;
		}
	}

}
