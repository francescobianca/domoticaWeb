package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.Categoria;
import model.Domanda;
import model.Risposta;
import model.Utente;
import persistence.DatabaseManager;

@SuppressWarnings("serial")
public class RestituisciRisposte extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id=Integer.parseInt(req.getParameter("id"));
		
		Connection connection=DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try{
			String query = "select r.id as idRisposta,r.testo as testoRisposta,domanda,r.utente as utenteRisposta,"
					+ "d.id as idDomanda, d.utente as utenteDomanda, d.testo as testoDomanda, d.titolo as titoloDomanda, categoria,"
					+ "u1.nome as nomeUtenteRisposta, u1.cognome as cognomeUtenteRisposta, u1.email as emailUtenteRisposta, u1.data_nascita as dataNascitaRisposta,"
					+ "u2.nome as nomeUtenteDomanda, u2.email as emailUtenteDomanda, u2.cognome as cognomeUtenteDomanda, u2.data_nascita as dataNascitaDomanda"
					+ " from risposta as r,domanda as d,utente as u1,utente as u2 where u1.email = r.utente and r.domanda = d.id and "
					+ "d.utente=u2.email and r.domanda=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			Risposta risposta=null;
			ResultSet result=statement.executeQuery();
			ArrayList<Risposta> risposte=new ArrayList<>();
			while(result.next()){
				risposta = new Risposta();
				risposta.setId(result.getInt("idRisposta"));
				risposta.setTesto(result.getString("testoRisposta"));
				Utente utenteRisposta = new Utente();
				utenteRisposta.setEmail(result.getString("emailUtenteRisposta"));
				utenteRisposta.setNome(result.getString("nomeUtenteRisposta"));
				utenteRisposta.setCognome(result.getString("cognomeUtenteRisposta"));
				long secsUtenteRisposta = result.getDate("dataNascitaRisposta").getTime();
				utenteRisposta.setDataNascita(new java.util.Date(secsUtenteRisposta));
				risposta.setUtente(utenteRisposta);

				Domanda domanda = new Domanda();
				Utente utenteDomanda = new Utente();
				utenteDomanda.setEmail(result.getString("emailUtenteDomanda"));
				utenteDomanda.setNome(result.getString("nomeUtenteDomanda"));
				utenteDomanda.setCognome(result.getString("cognomeUtenteDomanda"));
				long secsUtenteDomanda = result.getDate("dataNascitaDomanda").getTime();
				utenteDomanda.setDataNascita(new java.util.Date(secsUtenteDomanda));
				domanda.setUtente(utenteDomanda);
				Categoria categoria = new Categoria(result.getString("categoria"));
				domanda.setCategoria(categoria);
				domanda.setId(result.getInt("idDomanda"));
				domanda.setTitolo(result.getString("titoloDomanda"));
				domanda.setTesto(result.getString("testoDomanda"));
				risposta.setDomanda(domanda);

				risposte.add(risposta);
			}
			
			String risposteString = (new JSONArray(risposte).toString());
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().print(risposteString);
			
		}catch(Exception e){
			resp.getWriter().print("errore");
			resp.getWriter().flush();
			resp.getWriter().close();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
