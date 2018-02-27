/**
 * 
 */
package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;
import persistence.DatabaseManager;
import persistence.dao.UtenteDao;

/**
 * @author andre
 *
 */
@SuppressWarnings("serial")
public class ResetPassword extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String utente = req.getParameter("email");
		try {
			Utente user = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO().findByPrimaryKey(utente);
			if (user != null) {
				char[] caratteriMinusc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
				char[] caratteriMaiusc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
				char[] numeri = "0123456789".toCharArray();
				char[] all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
				Random random = new Random();
				String sb = "";
				for (int i = 0; i < 2; i++) {
					char c = caratteriMaiusc[random.nextInt(caratteriMaiusc.length)];
					sb += c;
				}
				for (int i = 0; i < 2; i++) {
					char c = caratteriMinusc[random.nextInt(caratteriMinusc.length)];
					sb += c;
				}
				for (int i = 0; i < 2; i++) {
					char c = numeri[random.nextInt(numeri.length)];
					sb += c;
				}
				for (int i = 0; i < 4; i++) {
					char c = all[random.nextInt(all.length)];
					sb += c;
				}
				System.out.println(sb);
				DatabaseManager.getInstance().getDaoFactory().getUtenteDAO().setPassword(user, sb);
				resp.getWriter().print(sb);
				resp.getWriter().flush();
				resp.getWriter().close();
			} else {
				resp.getWriter().print("utenteNonRegistrato");
				resp.getWriter().flush();
				resp.getWriter().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
