package scondor.panels.authentification;

import scondor.components.CheckBox;
import scondor.components.Container;
import scondor.components.Label;
import scondor.components.TextButton;
import scondor.components.TextField;
import scondor.font.effect.OutlineEffect;
import scondor.image.Images;

public class LoginContainer extends Container {

	private static final int PRIORITY = 1;
	private Label username, password, save;
	private TextField username_field, password_field;
	private CheckBox save_box;
	private TextButton login, exit, no_account;
	
	public LoginContainer() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		super.setBackground(Images.WALLPAPER_LOBBY);
		
		/*
		 * create effect and colors
		 */
		
		OutlineEffect outline_effect = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		username = new Label("Username:", 300, 300, 5, 1, true).setEffect(outline_effect);
		password = new Label("Password:", 300, 400, 5, 1, true).setEffect(outline_effect);
		
		/*
		 * add components to container
		 */
	}

	@Override
	public void refresh() {
		
	}
	
}
