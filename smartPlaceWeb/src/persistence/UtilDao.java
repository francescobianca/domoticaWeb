package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilDao {

	private DataSource dataSource;

	public UtilDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void dropDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "drop SEQUENCE if EXISTS sequenza_id;" + "drop table if exists utente;"
					+ "drop table if exists arduino;" + "drop table if exists attivitaPeriodica;"
					+ "drop table if exists sensore;" + "drop table if exists misurazione;"
					+ "drop table if exists regola;";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			System.out.println("Executed drop database");

		} catch (SQLException e) {

			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void createDatabase() {

		Connection connection = dataSource.getConnection();
		try {

			String create = "create SEQUENCE sequenza_id;" + "create table utente(\"email\" VARCHAR(255) primary key,"
					+ "nome VARCHAR(255),cognome VARCHAR(255)," + "data_nascita DATE, password VARCHAR(255));"

					+ "create table arduino(\"indirizzoIP\" VARCHAR(255) primary key,"
					+ "porta INTEGER, utenteArduino VARCHAR(255) REFERENCES utente(\"email\") );"

					+ "create table sensore(\"arduino_indirizzoIP\" VARCHAR(255) REFERENCES arduino(\"indirizzoIP\"),"
					+ "\"tipo\" VARCHAR(255), \"stanza\" VARCHAR(255),"
					+ "stato INTEGER, PRIMARY KEY(\"arduino_indirizzoIP\",\"tipo\", \"stanza\"));"

					+ "create table attivitaperiodica(id BIGSERIAL primary key,"
					+ "giornoInizio DATE, giornoFine DATE, orarioInizio TIME,"
					+ "orarioFine TIME, nome VARCHAR(255), utente VARCHAR(255) REFERENCES utente(\"email\"),"
					+ "indirizzoIP VARCHAR(255), tipo VARCHAR(255), stanza VARCHAR(255), schedulata boolean,"
					+ "FOREIGN KEY (indirizzoIP,tipo,stanza) REFERENCES sensore(\"arduino_indirizzoIP\",\"tipo\",\"stanza\"));"

					+ "create table misurazione(arduino_indirizzoIP VARCHAR(255), tipo VARCHAR(255), stanza VARCHAR(255),giorno DATE, ora TIME,valore FLOAT, "
					+ "FOREIGN KEY(arduino_indirizzoIP,tipo,stanza) "
					+ "REFERENCES sensore(\"arduino_indirizzoIP\",\"tipo\",\"stanza\"),"
					+ "PRIMARY KEY(arduino_indirizzoIP,tipo,stanza,giorno,ora));"

					+ "create table regola (id BIGSERIAL primary key, "
					+ "nome VARCHAR(255), valoreMisurazione FLOAT, utente VARCHAR(255) REFERENCES utente(\"email\"),"
					+ "indirizzoIP VARCHAR(255), tipo VARCHAR(255), stanza VARCHAR(255), attiva boolean,"
					+ "FOREIGN KEY (indirizzoIP,tipo,stanza) REFERENCES sensore(\"arduino_indirizzoIP\",\"tipo\",\"stanza\"));";

			PreparedStatement statement = connection.prepareStatement(create);

			statement.executeUpdate();
			System.out.println("Executed create database");

		} catch (SQLException e) {

			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void resetDatabase() {

		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM utente";
			PreparedStatement statement = connection.prepareStatement(delete);

			statement.executeUpdate();

			delete = "delete FROM arduino";
			statement = connection.prepareStatement(delete);

			statement.executeUpdate();

			delete = "delete FROM attivitaperiodica";
			statement = connection.prepareStatement(delete);

			statement.executeUpdate();

			delete = "delete FROM sensore";
			statement = connection.prepareStatement(delete);

			statement.executeUpdate();

			delete = "delete FROM misurazione";
			statement = connection.prepareStatement(delete);

			statement.executeUpdate();
			
			delete = "delete FROM regola";
			statement = connection.prepareStatement(delete);

			statement.executeUpdate();

		} catch (SQLException e) {

			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				throw new PersistenceException(e.getMessage());
			}
		}
	}
}