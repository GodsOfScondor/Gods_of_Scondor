package scondor.containers.authentification;

import scondor.Engine;
import scondor.GodsOfScondor;
import scondor.components.CheckBox;
import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.PasswordField;
import scondor.components.TextButton;
import scondor.components.TextField;
import scondor.font.effect.OutlineEffect;
import scondor.image.Images;
import scondor.util.Action;
import scondor.util.Color;

public class Register extends Container {

	private static final int PRIORITY = 1;

	private Label license, username, password, save;
	private TextField license_field, username_field;
	private PasswordField password_field;
	private CheckBox save_box;
	private TextButton login, exit, already;
	
	public Register() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		super.setBackground(Images.WALLPAPER_LOBBY);
		
		/*
		 * create effects and colors
		 */
		
		OutlineEffect outline_effect = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		Color white  = new Color(1, 1, 1);
		
		/*
		 * create components
		 */
		
		license = new Label("License:", 200, 200, 5, 1, true).setEffect(outline_effect);
		username = new Label("Username:", 200, 300, 5, 1, true).setEffect(outline_effect);
		password = new Label("Password:", 200, 400, 5, 1, true).setEffect(outline_effect);
		
		license_field = new TextField(350, 220, 550, 30, true);
		username_field = new TextField(500, 320, 400, 30, true);
		password_field = new PasswordField(500, 420, 400, 30, true);
		
		save_box = new CheckBox(700, 510, 1f, true);
		save = new Label("Remember me", 730, 500, 3, 1, true).setEffect(outline_effect).setColor(white);
		
		login = new TextButton("REGISTER", 400, 700, 5, 1, new Action() {
			public void perform() {
				Engine.getConnection().register(username_field.getText(), password_field.getText(), license_field.getText());
			}
		}, true).setEffect(outline_effect).setDamper(0.5f);
		exit = new TextButton("EXIT", 700, 700, 5, 1, new Action() {
			public void perform() {
				GodsOfScondor.close();
			}
		}, true).setEffect(outline_effect).setDamper(0.5f);
		already = new TextButton("Already have an account?", 500, 850, 3, 1, new Action() {
			public void perform() {
				Containers.show(Containers.getLogin());
			}
		}, true).setEffect(outline_effect).setDamper(0.5f);
		
		/*
		 * add components to container
		 */
		
		super.add(license);
		super.add(username);
		super.add(password);
		
		super.add(license_field);
		super.add(username_field);
		super.add(password_field);
		
		super.add(save_box);
		super.add(save);
		
		super.add(login);
		super.add(exit);
		super.add(already);
		
		/*
		 * validate container
		 */
		super.validate();
		
	}

	@Override
	public void refresh() {
//		if (Panels.isOpen(Panels.REGISTER)) Engine.getConnection().save_data = save_box.isSelected();
	}

	@Override
	public int getID() {
		return Containers.REGISTER;
	}

}
