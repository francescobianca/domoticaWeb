package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.Misurazione;
import model.Sensore;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.MisurazioneDao;
import persistence.dao.SensoreDao;

public class MyAppServletContextListener implements ServletContextListener {

	private Thread thread;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("Inizializzata");

		thread = new UpdateServer();
		thread.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("Distrutta");
	}

}

class UpdateServer extends Thread {

	public UpdateServer() {

	}

	@Override
	public void run() {

		while (true) {
			try {

				Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
				try {
					//sleep(3600000);
					sleep(60000);
					
					String query = "select * from arduino";
					PreparedStatement statement = connection.prepareStatement(query);

					ResultSet resultSet = statement.executeQuery();

					while (resultSet.next()) {
						String indirizzo = resultSet.getString("indirizzoIP");
						int porta = resultSet.getInt("porta");

						query = "select * from sensore where\"arduino_indirizzoIP\"=?";
						statement = connection.prepareStatement(query);
						statement.setString(1, indirizzo);

						ResultSet r1 = statement.executeQuery();
						while (r1.next()) {
							String tipo_sensore = r1.getString("tipo");
							if (tipo_sensore.equals("temperatura")) {

								Sensore s = new Sensore();
								SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
								s = sDao.findByPrimaryKey(indirizzo, tipo_sensore, "casa");

								float temp = leggiTemperatura(indirizzo, porta);

								Misurazione m = new Misurazione();
								MisurazioneDao mDao = DatabaseManager.getInstance().getDaoFactory().getMisurazioneDAO();

								m.setValore(temp);
								m.setSensore(s);
								Date current = new Date();
								m.setGiorno(current);

								Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),
										Locale.ITALY);
								Date today = calendar.getTime();
								@SuppressWarnings("deprecation")
								Time timeSql = new Time(today.getHours(), today.getMinutes(), today.getSeconds());
								m.setOra(timeSql);

								mDao.save(m);
								
								attivaRegola(m);

							}

							else if (tipo_sensore.equals("umidit�")) {

								Sensore s = new Sensore();
								SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
								s = sDao.findByPrimaryKey(indirizzo, tipo_sensore, "casa");

								float umidita = leggiUmidita(indirizzo, porta);

								System.out.println("umidit�"+umidita);
								
								Misurazione m = new Misurazione();
								MisurazioneDao mDao = DatabaseManager.getInstance().getDaoFactory().getMisurazioneDAO();

								m.setValore(umidita);
								m.setSensore(s);
								Date current = new Date();
								m.setGiorno(current);

								Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),
										Locale.ITALY);
								Date today = calendar.getTime();
								@SuppressWarnings("deprecation")
								Time timeSql = new Time(today.getHours(), today.getMinutes(), today.getSeconds());
								m.setOra(timeSql);

								mDao.save(m);
								
							}

						}
					}

					// sleep(60000); // 1 minuto
					//sleep(120000);
					//sleep(10000);
					
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				} finally {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new PersistenceException(e.getMessage());
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private float leggiTemperatura(String indirizzo, int porta) {

		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;

		float temperatura = 0;

		try {
			// open a socket connection
			socket = new Socket(indirizzo, porta);
			// Apre i canali I/O
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream(), true);

			// Invio nuovo stato da impostare al server
			out.println("readData"); // da modificare
			out.println("9");
			out.flush();

			String s = in.readLine();

			String[] date = s.split("/");
			String temp = date[1];

			temperatura = Float.parseFloat(temp);

			out.close();
			in.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return temperatura;

	}

	private float leggiUmidita(String indirizzo, int porta) {

		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;

		float umidita = 0;

		try {
			// open a socket connection
			socket = new Socket(indirizzo, porta);
			// Apre i canali I/O
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream(), true);

			// Invio nuovo stato da impostare al server
			out.println("readData"); // da modificare
			out.println("h");
			out.flush();

			String s = in.readLine();

			String[] date = s.split("/");
			String hum = date[0];

			umidita = Float.parseFloat(hum);

			out.close();
			in.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return umidita;

	}

	private void attivaRegola(Misurazione m) {
		
		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;

		ResultSet rs = null;
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			
			String query = "";
			PreparedStatement statement;
			
			if (m.getSensore().getTipo().equals("temperatura")) {

				query = "select * from regola where indirizzoIP = ? and stanza = ? and (tipo = ? or tipo = ?)  and attiva=false";
				statement = connection.prepareStatement(query);
				statement.setString(1, m.getSensore().getArduino().getIndirizzoIP());
				statement.setString(2, m.getSensore().getStanza());
				statement.setString(3, "ventilatore");
				statement.setString(4, "riscaldamento");
				rs = statement.executeQuery();
				
			}
			
			else if (m.getSensore().getTipo().equals("umidit�")) {
				query = "select * from regola where indirizzoIP = ? and stanza = ? and tipo = ?  and attiva=false";
				statement = connection.prepareStatement(query);
				statement.setString(1, m.getSensore().getArduino().getIndirizzoIP());
				statement.setString(2, m.getSensore().getStanza());
				statement.setString(3, "deumidificatore");
				rs = statement.executeQuery();
			}
			
			
			int porta = m.getSensore().getArduino().getPorta();

			while (rs.next()) {
				
				System.out.println("valore della misurazione: "+m.getValore()+"valore nostro: "+rs.getFloat("valoreMisurazione"));
				

				if (rs.getString("condizione").equals("maggiore")) {

					if (m.getValore() > rs.getFloat("valoreMisurazione")) {
						System.out.println("sto attivando un sensore");
						query = "update regola set attiva=true where id = ?";
						statement = connection.prepareStatement(query);
						statement.setInt(1, rs.getInt("id"));
						statement.executeUpdate();
						
						// open a socket connection
						socket = new Socket(m.getSensore().getArduino().getIndirizzoIP(), porta);
						// Apre i canali I/O
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintStream(socket.getOutputStream(), true);
						
						switch (rs.getString("tipo")) {
						case "ventilatore":
							out.println("setta led");
							out.println("5");
							out.flush();
							
							salvaStato(m.getSensore().getArduino().getIndirizzoIP(), "casa", 1 , "ventilatore");
							break;
						case "riscaldamento":
							;
							break;
						case "deumidificatore":
							;
							break;
						default:
							break;
						}
						
						
						out.close();
						in.close();
					}
				}

				else if (rs.getString("condizione").equals("minore")) {

					if (m.getValore() < rs.getFloat("valoreMisurazione")) {
						System.out.println("sto attivando un sensore minore");
						query = "update regola set attiva=true where id = ?";
						statement = connection.prepareStatement(query);
						statement.setInt(1, rs.getInt("id"));
						statement.executeUpdate();
						
						// open a socket connection
						socket = new Socket(m.getSensore().getArduino().getIndirizzoIP(), porta);
						// Apre i canali I/O
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintStream(socket.getOutputStream(), true);
						
						switch (rs.getString("tipo")) {
						case "ventilatore":
							out.println("setta led");
							out.println("5");
							out.flush();
							
							salvaStato(m.getSensore().getArduino().getIndirizzoIP(), "casa", 1 , "ventilatore");
							break;
						case "riscaldamento":
							;
							break;
						case "deumidificatore":
							;
							break;
						default:
							break;
						}
						
						out.close();
						in.close();
					}
				}
				
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

	}
	
	private void salvaStato(String indirizzoIP, String stanza, int stato, String tipo) {
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String update = "update sensore set stato = ? where \"arduino_indirizzoIP\" = ? and stanza = ? and tipo = ?";
			PreparedStatement updateStatement = connection.prepareStatement(update);
			updateStatement.setInt(1, stato);
			updateStatement.setString(2, indirizzoIP);
			updateStatement.setString(3, stanza);
			updateStatement.setString(4, tipo);
			updateStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}