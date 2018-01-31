package job;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import persistence.DatabaseManager;

public class ActivityJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		JobDataMap map = arg0.getJobDetail().getJobDataMap();

		String indirizzoIP = map.getString("indirizzoIP");
		int porta = map.getInt("porta");
		String stanza = map.getString("stanza");
		String tipo = map.getString("tipo");
		String operazione = map.getString("operazione");

		boolean esegui = false;
		try {
			Connection connection = DatabaseManager.getInstance().getDaoFactory().getDataSource().getConnection();
			// fare ricerca per chiave primaria
			String query = "select * from attivitaperiodica where tipo = ? and stanza = ? and \"indirizzoip\" = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, tipo);
			statement.setString(2, stanza);
			statement.setString(3, indirizzoIP);
			ResultSet set = statement.executeQuery();
			if (set.next())
				esegui = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (esegui) {
			System.out.println(indirizzoIP + " " + porta + " " + stanza + " " + tipo + " " + operazione);

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

				/*
				 * case "finestra": switch (stanza) { case "bagno": switch
				 * (operazione) { case "accendi": ; break; case "spegni": ;
				 * break; default: ; } break; case "cameraLetto": switch
				 * (operazione) { case "accendi": ; break; case "spegni": ;
				 * break; default: ; } break; case "cucina": switch (operazione)
				 * { case "accendi": ; break; case "spegni": ; break; default: ;
				 * } break; case "salone": switch (operazione) { case "accendi":
				 * ; break; case "spegni": ; break; default: ; } break; default:
				 * ; } break;
				 */
				default:
					;

				}

				out.close();
				in.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}