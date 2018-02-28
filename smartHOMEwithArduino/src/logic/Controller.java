package logic;

import java.io.InputStream;
//import java.io.IOException;
//import java.io.InputStream;
import java.io.PrintWriter;
//import java.util.Scanner;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.fazecast.jSerialComm.SerialPort;

public class Controller {

	private SerialPort sp;
	private PrintWriter output;
	private InputStream input;
	
	private float outputValue;
	
	String line;
	Lock lock = new ReentrantLock();

	public Controller(String port) {
		// TODO Auto-generated constructor stub
		sp = SerialPort.getCommPort(port);
		sp.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		// sp.setComPortTimeouts(SerialPort.ti, , newWriteTimeout);

		sp.openPort();
		output = new PrintWriter(sp.getOutputStream());

		input = sp.getInputStream();

		/*
		 * if (sp.openPort()) { System.out.println("Port is open :)"); } else {
		 * System.out.println("Failed to open port :("); return; }
		 */

		/*
		 * if (sp.closePort()) { System.out.println("Port is closed :)"); } else
		 * { System.out.println("Failed to close port :("); return; }
		 */

	}

	public void changeState(String state) {

		// sp.openPort();
		// PrintWriter output = new PrintWriter(sp.getOutputStream());
		// output.write(state);
		// output.flush();
		// sp.closePort();

		// Scanner sc = new Scanner(System.in);

		// String s = sc.nextLine();
		/*
		 * if (state=="7")
		 * 
		 * try { System.out.print( (char) input.read() ); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// System.out.print( (char) in.read() );
		output.write(state);
		output.flush();

		Thread t = new Thread() {
			public void run() {
				lock.lock();
				Scanner scanner = new Scanner(input);
				while (scanner.hasNextLine()) {
										
					line = scanner.nextLine();

					//outputValue = Float.parseFloat(line);
					//System.out.println(line);
				}
				scanner.close();
				lock.unlock();
			};
		};
		t.start();

		
		
		/*for (int j = 0;; j++) {
			try {
				System.out.print((char) input.read());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/

	}
	
	public float getOutputValue() {
		return outputValue;
	}
	
	public String getLine() {
		return line;
	}

	/*
	 * public static void main(String[] args) { Controller c = new
	 * Controller("COM4"); while (true) c.changeState("1"); }
	 */

}
