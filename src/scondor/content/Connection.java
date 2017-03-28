package scondor.content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import scondor.gnet.packet.Packet;
import scondor.packets.Authentication;
import scondor.packets.CardList;
import scondor.packets.DeckList;
import scondor.packets.Verification;
import scondor.panels.Panels;
import scondor.server.Client;
import scondor.util.Messanger;

public class Connection {

	private BufferedReader reader;
	public boolean save_data = false;
	private boolean save = false;
	private BufferedWriter writer;
	private File file;
	private final String PATH = "res/data/auth.data";

	private String username;
	private String password;
	private String license;
	private String line;
	
	private Contents contents;
	
	private final int USER_ALREADY_ONLINE = 0;
	private final int WRONG_PASSWORD = 1;
	private final int LOGIN_SUCCESFULL = 2;
	private final int WRONG_LICENSE = 3;
	private final int USERNAME_ALREADY_EXISTS = 4;

	public void start() {
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

	public String msgFromServer(int code) {

		switch (code) {
		case USER_ALREADY_ONLINE: return Messanger.build("User is already online!", 1, 0, 0);
		case WRONG_PASSWORD: return Messanger.build("Wrong Password!", Panels.LOGIN, 1, 0, 0);
		case LOGIN_SUCCESFULL:
			saveData();
			contents = new Contents();
			contents.load();
			int panel = contents.getAvaibleCards().size()==0?Panels.DECK_STARTER:Panels.MAIN;
			return Messanger.build("Login succesfully!", panel, 0, 1, 0);
		case WRONG_LICENSE: return Messanger.build("License is wrong!", 1, 0, 0);
		case USERNAME_ALREADY_EXISTS: return Messanger.build("Username already exists!", 1, 0, 0);
		}

		return "nopopup";
	}

	public void register(String username, String password, String license) {
		this.username = username;
		this.password = password;
		this.license = license;

		Client.send(new Verification(username, password, license));
	}
	
	public void login(String username, String password) {
		this.username = username;
		this.password = password;
		
		Client.send(new Authentication(username, password));
	}
	
	private void saveData() {

		try {
			
			file = new File(PATH);
			
			if (file.exists()==false) file.createNewFile();
			
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
	
	public void incoming(Packet packet) {
		if (packet instanceof CardList) {
			contents.incoming((CardList)packet);
		} else if (packet instanceof DeckList) {
			contents.incoming((DeckList)packet);
		}
	}

	public void close() {
		if (contents!=null) contents.close();
	}
	
	public Contents getContents() {
		return contents;
	}

}
