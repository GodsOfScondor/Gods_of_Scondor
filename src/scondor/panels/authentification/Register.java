package scondor.panels.authentification;

import scondor.Engine;
import scondor.GodsOfScondor;
import scondor.components.Button;
import scondor.components.CheckBox;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.TextField;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.panels.EffectAble;
import scondor.panels.Panels;
import scondor.util.Action;

public class Register extends Panel {
	
	private Label license, username, password, save;
	private TextField license_field, username_field, password_field;
	private CheckBox save_box;
	private Button login, exit, already;
	private OutlineEffect effect;
	
	public Register() {
		super(1);
		setBackground(new Texture("lobby"));
		
		effect = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		license = new Label("License:", 200, 200, 5, 1).setEffect(effect);
		username = new Label("Username:", 200, 300, 5, 1).setEffect(effect);
		password = new Label("Password:", 200, 400, 5, 1).setEffect(effect);
		
		license_field = new TextField(350, 220, 550, 30);
		username_field = new TextField(500, 320, 400, 30);
		password_field = new TextField(500, 420, 400, 30);
		
		save_box = new CheckBox(700, 510, 1.2f);
		save = new Label("Remember me", 730, 500, 3, 1).setEffect(effect).setColor(1, 1, 1);
		
		login = new Button("REGISTER", 400, 700, 5, 1, new Action() {
			public void perform() {
				Engine.getConnection().register(username_field.getText(), password_field.getText(), license_field.getText());
			}
		}).setEffect(effect).setDamper(0.2f);
		exit = new Button("EXIT", 700, 700, 5, 1, new Action() {
			public void perform() {
				GodsOfScondor.close();
			}
		}).setEffect(effect).setDamper(0.2f);
		already = new Button("Already have an account?", 500, 850, 3, 1, new Action() {
			public void perform() {
				Panels.show(Panels.LOGIN);
			}
		}).setEffect(effect).setDamper(0.2f);
		
		addAction(new Action() {
			public void perform() {
				if (Panels.isOpen(Panels.REGISTER)) Engine.getConnection().save_data = save_box.isSelected();
			}
		});
		
		add(license);
		add(username);
		add(password);
		
		add(license_field);
		add(username_field);
		add(password_field);
		
		add(save_box);
		add(save);
		
		add(login);
		add(exit);
		add(already);
		
	}

	@Override
	public void swipeIn() {
		fade(0, 1, Panels.FADEIN);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
	}


	@Override
	public void swipeOut() {
		fade(1, 0, Panels.FADEOUT);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
	}

}