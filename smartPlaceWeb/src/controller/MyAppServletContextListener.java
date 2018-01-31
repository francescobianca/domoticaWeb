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
					String query = "select * from arduino";
					PreparedStatement statement = connection.prepareStatement(query);

					ResultSet resultSet = statement.executeQuery();

					while (resultSet.next()) {
						String indirizzo = resultSet.getString("indirizzoIP");
						int porta = resultSet.getInt("porta");
						/*Arduino arduino = new Arduino();
						ArduinoDao arduinoDao = DatabaseManager.getInstance().getDaoFactory().getArduinoDAO();
						arduino = arduinoDao.findByPrimaryKey(indirizzo);*/
						
						query = "select * from sensore where\"arduino_indirizzoIP\"=?";
						statement = connection.prepareStatement(query);
						statement.setString(1, indirizzo);

						ResultSet r1 = statement.executeQuery();
						while (r1.next()) {
							String tipo_sensore = r1.getString("tipo");
							if (tipo_sensore.equals("temperatura")) {
								
								Sensore s = new Sensore();
								SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
								s=sDao.findByPrimaryKey(indirizzo, tipo_sensore,"casa");
								
								float temp = leggiTemperatura(indirizzo,porta);
								
								/*Sensore s = new Sensore();
								SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
								sDao.findByPrimaryKey(indirizzoIP, tipo, stanza); */
								
								Misurazione m = new Misurazione();		
								MisurazioneDao mDao = DatabaseManager.getInstance().getDaoFactory().getMisurazioneDAO();
								
								m.setValore(temp);
								m.setSensore(s);
								Date current = new Date();
								m.setGiorno(current);
								//m.setOra(current);
								
								Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
								Date today = calendar.getTime();
								@SuppressWarnings("deprecation")
								Time timeSql = new Time(today.getHours(), today.getMinutes(), today.getSeconds());
								m.setOra(timeSql);
								
								mDao.save(m);
								
							}
							
							else if (tipo_sensore.equals("umidità")) {
																
								Sensore s = new Sensore();
								SensoreDao sDao = DatabaseManager.getInstance().getDaoFactory().getSensoreDAO();
								s=sDao.findByPrimaryKey(indirizzo, tipo_sensore,"casa");
								
								float umidità = leggiUmidità(indirizzo,porta);
								
								Misurazione m = new Misurazione();		
								MisurazioneDao mDao = DatabaseManager.getInstance().getDaoFactory().getMisurazioneDAO();
								
								m.setValore(umidità);
								m.setSensore(s);
								Date current = new Date();
								m.setGiorno(current);
								
								Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
								Date today = calendar.getTime();
								@SuppressWarnings("deprecation")
								Time timeSql = new Time(today.getHours(), today.getMinutes(), today.getSeconds());
								m.setOra(timeSql);
								
								mDao.save(m);
							}
							
						}
					}

					//sleep(60000); // 1 minuto
					sleep(120000);
					
					
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private float leggiTemperatura(String indirizzo,int porta) {

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
	
	private float leggiUmidità(String indirizzo, int porta) {

		BufferedReader in = null;
		PrintStream out = null;
		Socket socket = null;

		float umidità = 0;

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

			umidità = Float.parseFloat(hum);

			out.close();
			in.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return umidità;
	
	}
	

}