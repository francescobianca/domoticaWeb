package test;

import persistence.DAOFactory;
import persistence.UtilDao;

public class MainJDBC {
	
	public static void main(String args[]) {

		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		
		util.dropDatabase();
		
		util.createDatabase();
		
	}
}