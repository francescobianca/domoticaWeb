/**
 * 
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Regola;
import model.Sensore;
import model.Utente;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.RegolaDao;
import persistence.dao.SensoreDao;
import persistence.dao.UtenteDao;

/**
 * @author andre
 *
 */
public class PianificaRegola extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println(" programma una regola ");

		try {

			HttpSession session = req.getSession();

			String utente = (String) session.getAttribute("email");

			String ip = findInfo(utente);

			String nome = req.getParameter("nome");
			float valore = Float.parseFloat(req.getParameter("valore"));
			String sensore = req.getParameter("sensore");
			String condizione = req.getParameter("condizione");
			System.out.println(utente);
			System.out.println(ip);
			System.out.println(nome);
			System.out.println(valore);
			System.out.println(sensore);
			System.out.println(condizione);
			boolean trovaRegola = false;
			Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
			try {
				String query = "select * from regola where utente = ? and nome = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, utente);
				statement.setString(2, nome);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					trovaRegola = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}

			if (!trovaRegola) {
				Regola regola = new Regola(nome);
				regola.setValoreMisurazione(valore);
				regola.setCondizione(condizione);

				Utente u = new Utente();
				UtenteDao uDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				u = uDao.findByPrimaryKey(utente);

				regola.setUtente(u);

				Sensore s = new Sensore();
				SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();

				s = sDao.findByPrimaryKey(ip, sensore, "casa");

				if (s == null) {
					resp.getOutputStream().print("sensoreNonEsiste");
					resp.getOutputStream().flush();
					resp.getOutputStream().close();
					return ;
				}
				regola.setSensore(s);

				RegolaDao regolaDao = DatabaseManager.getInstance().getDaoFactory().getRegolaDAO();

				regolaDao.save(regola);
				resp.getOutputStream().print("salvata");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			} else {
				resp.getOutputStream().print("regolaGiaPresenteConLoStessoNome");
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			}

		} catch (Exception e) {
			resp.getOutputStream().print("errore");
			resp.getOutputStream().flush();
			resp.getOutputStream().close();
		}
	}

	private String findInfo(String utente) {
		String ip = "";
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String query = "select \"indirizzoIP\",porta from arduino where utenteArduino=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				ip = result.getString("indirizzoIP");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return ip;
	}

}
