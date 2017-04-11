//package scondor.panels.authentification;
//
//import scondor.Engine;
//import scondor.GodsOfScondor;
//import scondor.components.Button;
//import scondor.components.CheckBox;
//import scondor.components.Component;
//import scondor.components.Label;
//import scondor.components.Panel;
//import scondor.components.TextField;
//import scondor.font.effect.OutlineEffect;
//import scondor.image.Texture;
//import scondor.inputs.KeyBoard;
//import scondor.panels.EffectAble;
//import scondor.panels.Panels;
//import scondor.util.Action;
//
//public class Login extends Panel {
//	
//	private Label username, password, save;
//	private TextField username_field, password_field;
//	private CheckBox save_box;
//	private Button login, exit, no_account;
//	private OutlineEffect effect;
//	private Action login_action;
//	
//	
//	public Login() {
//		super(1);
//		setBackground(new Texture("lobby"));
//		
//		effect = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
//		username = new Label("Username:", 300, 300, 5, 1).setEffect(effect);
//		password = new Label("Password:", 300, 400, 5, 1).setEffect(effect);
//		
//		username_field = new TextField(500, 320, 400, 30);
//		password_field = new TextField(500, 420, 400, 30).setHided(true);
//		
//		save_box = new CheckBox(700, 500, 1.2f);
//		save = new Label("Remember me", 730, 500, 3, 1).setEffect(effect).setColor(1, 1, 1);
//		
//		login_action = new Action() {
//			public void perform() {
//				Engine.getConnection().login(username_field.getText(), password_field.getText());
//			}
//		};
//		
//		login = new Button("LOGIN", 500, 700, 5, 1, login_action).setColor(0.3f, 0.3f, 0.3f).setEffect(effect).setDamper(0.5f);
//		exit = new Button("EXIT", 700, 700, 5, 1, new Action() {
//			public void perform() {
//				GodsOfScondor.close();
//			}
//		}).setColor(0.3f, 0.3f, 0.3f).setEffect(effect).setDamper(0.5f);
//		
//		no_account = new Button("Have no account?", 570, 800, 3, 1, new Action() {
//			public void perform() {
//				Panels.show(Panels.REGISTER);
//			}
//		}).setColor(0.3f, 0.3f, 0.3f).setEffect(effect).setDamper(0.5f);
//		
//		addAction(new Action() {
//			public void perform() {
//				if (isVisible()) {
//					if (Panels.isOpen(Panels.LOGIN)) Engine.getConnection().save_data = save_box.isSelected();
//					if (KeyBoard.isKeyTyped(KeyBoard.KEY_ENTER)) {
//						login_action.perform();
//					}
//				}
//			}
//		});
//		
//		add(username);
//		add(password);
//		
//		add(username_field);
//		add(password_field);
//		
//		add(save_box);
//		add(save);
//		
//		add(login);
//		add(no_account);
//		add(exit);
//		
//	}
//
//	@Override
//	public void swipeIn() {
//		fade(0, 1, Panels.FADEIN);
//		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
//	}
//
//
//	@Override
//	public void swipeOut() {
//		fade(1, 0, Panels.FADEOUT);
//		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
//
//	}
//
//}
