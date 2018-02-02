package job;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.plaf.SliderUI;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import model.AttivitaPeriodica;
import persistence.DatabaseManager;
import persistence.PersistenceException;
import persistence.dao.AttivitaPeriodicaDao;

public class ActivityJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobDataMap map = arg0.getJobDetail().getJobDataMap();

		int id = map.getInt("id");
		String indirizzoIP = map.getString("indirizzoIP");
		int porta = map.getInt("porta");
		String stanza = map.getString("stanza");
		String tipo = map.getString("tipo");
		String operazione = map.getString("operazione");

		int x = 0; // Valore di apertura finestra

		boolean esegui = false;
		try {

			AttivitaPeriodicaDao aDao = DatabaseManager.getInstance().getDaoFactory().getAttivitaPeriodicaDAO();
			AttivitaPeriodica a = aDao.findByPrimaryKey(id);

			if (a != null)
				esegui = true;

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (esegui) {
			System.out.println("----------------------------------------------------------------------");
			System.out.println(indirizzoIP + " " + porta + " " + stanza + " " + tipo + " " + operazione);
			System.out.println("Data escuzione: " + new Date().toString());
			System.out.println("----------------------------------------");

			BufferedReader in = null;
			PrintStream out = null;
			Socket socket = null;

			try {
				// open a socket connection
				socket = new Socket("localhost", 4000);
				// Apre i canali I/O
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream(), true);

				switch (tipo) {

				case "ventilatore":
					switch (stanza) {
					case "casa":
						switch (operazione) {

						case "accendi":
							out.println("setta led");
							out.println("5");
							out.flush();
							break;
						case "spegni":
							out.println("setta led");
							out.println("4");
							out.flush();
							break;
						default:
							;

						}
						break;
					default:
						;
					}
					break;

				case "luce":
					switch (stanza) {
					case "bagno":
						switch (operazione) {

						case "accendi":
							out.println("setta led");
							out.println("1");
							out.flush();
							break;
						case "spegni":
							out.println("setta led");
							out.println("0");
							out.flush();
							break;
						default:
							;

						}
						break;
					case "cameraLetto":
						switch (operazione) {

						case "accendi":
							out.println("setta led");
							out.println("c");
							out.flush();
							break;
						case "spegni":
							out.println("setta led");
							out.println("d");
							out.flush();
							break;
						default:
							;

						}
						break;
					case "cucina":
						switch (operazione) {

						case "accendi":
							out.println("setta led");
							out.println("a");
							out.flush();
							break;
						case "spegni":
							out.println("setta led");
							out.println("b");
							out.flush();
							break;
						default:
							;

						}
						break;
					case "salone":
						switch (operazione) {

						case "accendi":
							out.println("setta led");
							out.println("3");
							out.flush();
							break;
						case "spegni":
							out.println("setta led");
							out.println("2");
							out.flush();
							break;
						default:
							;

						}
						break;
					default:
						;
					}
					break;

				case "finestra":
					x = leggiStatoFinestra(indirizzoIP, stanza);
					switch (stanza) {
					case "bagno":
						switch (operazione) {
						case "accendi":
							if (x == 180)
								;
							else {
								int j = (180 - x) / 30;
								for (int i = 0; i < j - 1; i++) {
									// open a socket connection
									socket = new Socket("localhost", 4000);
									// Apre i canali I/O
									in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									out = new PrintStream(socket.getOutputStream(), true);
									out.println("apriFinestre");
									out.println("7");
									out.flush();
									Thread.sleep(500);
								}
								x = x + ((j - 1) * 30);
							}
							break;
						case "spegni":
							if (x == 0)
								;
							else {
								int z = x / 30;
								for (int i = 0; i < z - 1; i++) {
									// open a socket connection
									socket = new Socket("localhost", 4000);
									// Apre i canali I/O
									in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									out = new PrintStream(socket.getOutputStream(), true);
									out.println("apriFinestre");
									out.println("8");
									out.flush();
									Thread.sleep(500);
								}
								x = x - ((z - 1) * 30);
							}
							break;
						default:
							;
						}
						break;
					case "cameraLetto":
						switch (operazione) {
						case "accendi":
							;
							break;
						case "spegni":
							;
							break;
						default:
							;
						}
						break;
					case "cucina":
						switch (operazione) {
						case "accendi":
							;
							break;
						case "spegni":
							;
							break;
						default:
							;
						}
						break;
					case "salone":
						switch (operazione) {
						case "accendi":
							;
							break;
						case "spegni":
							;
							break;
						default:
							;
						}
						break;
					default:
						;
					}
					break;

				default:
					;

				}

				if (!tipo.equals("finestra")) {
					if (operazione.equals("accendi"))
						salvaStato(indirizzoIP, stanza, 1, tipo);
					else if (operazione.equals("spegni"))
						salvaStato(indirizzoIP, stanza, 0, tipo);
				} else
					salvaStato(indirizzoIP, stanza, x, tipo);

				out.close();
				in.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
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

	private int leggiStatoFinestra(String indirizzoIP, String stanza) {
		Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
		try {
			String query = "select stato from sensore where \"arduino_indirizzoIP\" = ? and stanza = ? and tipo = ?";
			PreparedStatement Statement = connection.prepareStatement(query);
			Statement.setString(1, indirizzoIP);
			Statement.setString(2, stanza);
			Statement.setString(3, "finestra");

			ResultSet rs = Statement.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getInt("stato"));
				return rs.getInt("stato");
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

		return 0;
	}

}