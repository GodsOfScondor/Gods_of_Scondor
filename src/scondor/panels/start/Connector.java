package scondor.panels.start;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import scondor.packets.Authentication;
import scondor.packets.Verification;
import scondor.panels.Panels;
import scondor.server.Client;
import scondor.util.Messanger;

public class Connector {

	private static BufferedReader reader;
	public static boolean save_data = false;
	private static boolean save = false;
	private static BufferedWriter writer;
	private static File file;
	private static final String PATH = "res/data/auth.data";

	private static String username;
	private static String password;
	private static String license;
	private static String line;
	
	private static final int USER_ALREADY_ONLINE = 0;
	private static final int WRONG_PASSWORD = 1;
	private static final int LOGIN_SUCCESFULL = 2;
	private static final int WRONG_LICENSE = 3;
	private static final int USERNAME_ALREADY_EXISTS = 4;

	public static void init() {
		try {

			file = new File(PATH);

			if (file.exists()) {
				
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

				while ((line = reader.readLine()) != null) {
					if (line.startsWith("save=")) save = Boolean.parseBoolean(line.split("=")[1]);
					if (line.startsWith("license=")) license = line.split("=")[1];
					if (line.startsWith("name=")) username = line.split("=")[1];
					if (line.startsWith("password=")) password = line.split("=")[1];
				}

				if (save) Client.send(new Authentication(username, password));
				else Panels.show(Panels.LOGIN);

				reader.close();
				
			} else {

				Panels.show(Panels.REGISTER);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String msgFromServer(int code) {

		switch (code) {
		case USER_ALREADY_ONLINE: return Messanger.build("User is already online!", 1, 0, 0);
		case WRONG_PASSWORD: return Messanger.build("Wrong Password!", Panels.LOGIN, 1, 0, 0);
		case LOGIN_SUCCESFULL:
			saveData();
			return Messanger.build("Login succesfully!", Panels.MAIN, 0, 1, 0);
		case WRONG_LICENSE: return Messanger.build("License is wrong!", 1, 0, 0);
		case USERNAME_ALREADY_EXISTS: return Messanger.build("Username already exists!", 1, 0, 0);
		}

		return "nopopup";
	}

	public static void register(String username, String password, String license) {
		Connector.username = username;
		Connector.password = password;
		Connector.license = license;

		Client.send(new Verification(username, password, license));
	}
	
	public static void login(String username, String password) {
		Connector.username = username;
		Connector.password = password;
		
		Client.send(new Authentication(username, password));
	}
	
	private static void saveData() {

		try {
			
			file = new File(PATH);
			
			if (!save) {
			
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			
			
				
				writer.write("save="+(save_data||save)); writer.newLine();
				
				if (save_data) {
					writer.write("name=" + username); writer.newLine();
					writer.write("password=" + password); writer.newLine();
				}
				
				writer.write("license=" + license);

			writer.close();
			
			} else {
				
				// TODO switch user
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
