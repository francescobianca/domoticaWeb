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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import model.AttivitaPeriodica;
import model.Regola;
import model.Sensore;
import persistence.DatabaseManager;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class LeggiRegole extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String utente = (String) session.getAttribute("email");

		String tipo = req.getParameter("tipo");

		// restituisco le attivita per il tipo scelto dall'utente
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		// Set delle attivita
		ResultSet setRegole = null;
		try {

			if (tipo.equals("temperatura")) {
				String query = "select * from regola where utente = ? and (tipo = ? or tipo = ?) ";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, "ventilatore");
				statement.setString(3, "riscaldamenti");
				setRegole = statement.executeQuery();
			} else if (tipo.equals("umidità")) {
				String query = "select * from attivitaperiodica where utente = ? and tipo = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, "deumidificatore");
				setRegole = statement.executeQuery();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Regola r = null;
		ArrayList<Regola> regole = new ArrayList<>();
		try {
			if (setRegole != null) {
				while (setRegole.next()) {
					int id = setRegole.getInt("id");
					String nome = setRegole.getString("nome");
					String condizione = setRegole.getString("condizione");
					String tipoSensore = setRegole.getString("tipo");
					float valore = setRegole.getFloat("valoremisurazione");
					Sensore s = new Sensore();
					s.setTipo(tipoSensore);
					s.setStanza("casa");
					r = new Regola();
					r.setId(id);
					r.setNome(nome);
					r.setCondizione(condizione);
					r.setValoreMisurazione(valore);
					r.setSensore(s);
					regole.add(r);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (setRegole != null) {
			String risposta = (new JSONArray(regole).toString());
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.getWriter().print(risposta);
			resp.getWriter().flush();
			resp.getWriter().close();
		}
		resp.getWriter().print("nessunaRegolaMemorizzata");
		resp.getWriter().flush();
		resp.getWriter().close();
	}

}
