package scondor.panels;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.Picture;
import scondor.components.TextButton;
import scondor.font.effect.GlowEffect;
import scondor.font.effect.OutlineEffect;
import scondor.image.Images;
import scondor.image.Texture;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;
import scondor.util.Color;

public class Lobby extends Container {

	private static final int PRIORITY = 1;
	private TextButton ranked, online, custom, back;
	private Picture sword, upper_frame, lower_frame;
	private Label lobby, desc_ranked, desc_online, desc_custom;
	
	private boolean over, before;
	
	public Lobby() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		super.setBackground(Images.WALLPAPER_LOBBY);
		
		/*
		 * create effects and colors
		 */
		
		GlowEffect glow_effect = new GlowEffect(0.8f, 0.8f, 0.8f, 3);
		OutlineEffect outline_effect = new OutlineEffect(0, 0, 0, 2);
		Color gray = new Color(0.8f, 0.8f, 0.8f);
		float damper = -0.2f;
		
		/*
		 * create components
		 */
		
		Action play = new Action() {
			public void perform() {
				if (ranked.isMouseOver()) Client.sendToServer(new Message("lobby;session;ranked"));
				else if (online.isMouseOver()) Client.sendToServer(new Message("lobby;session;online"));
				else if (custom.isMouseOver()) Client.sendToServer(new Message("lobby;session;custom"));
			}
		};
		
		ranked = new TextButton("RANKED", 300, 300, 4, 1, play, true).setColor(gray).setDamper(damper).setEffect(outline_effect);
		online = new TextButton("ONLINE", 300, 400, 4, 1, play, true).setColor(gray).setDamper(damper).setEffect(outline_effect);
		custom = new TextButton("CUSTOM", 300, 500, 4, 1, play, true).setColor(gray).setDamper(damper).setEffect(outline_effect);
		
		back = new TextButton("BACK", 800, 900, 3.5f, 1, new Action() {
			public void perform() {
				Containers.show(Containers.getMain());
				Client.sendToServer(new Message("lobby;exit"));
			}
		}, true).setColor(gray).setDamper(damper).setEffect(outline_effect);
		
		lobby = new Label("Lobby", 10, 10, 8, 1, true).setEffect(glow_effect);
		desc_ranked = new Label("get ranked into a list of all players.", 600, 300, 3, 1, false).setLineSize(300);
		desc_online = new Label("play against random opponents without ranking.", 600, 300, 3, 1, false).setLineSize(300);
		desc_custom = new Label("play against your friends.", 600, 300, 3, 1, false).setLineSize(300);
		
		sword = new Picture(new Texture("sword"), 250, 300, 200, 40, false);
		
		upper_frame = new Picture(new Texture(Images.DECO_FRAMES, 0, 0), 270, 190, 200, 60, true);
		lower_frame = new Picture(new Texture(Images.DECO_FRAMES, 0, 1), 270, 550, 200, 60, true);
		
		/*
		 * add components to container
		 */
		
		super.add(ranked);
		super.add(online);
		super.add(custom);
		super.add(lobby);
		super.add(desc_ranked);
		super.add(desc_online);
		super.add(desc_custom);
		super.add(sword);
		super.add(upper_frame);
		super.add(lower_frame);
		super.add(back);
		
		/*
		 * validate contianer
		 */
		
		super.validate();
		
	}

	@Override
	public void refresh() {
		
		before = !over;
		
		over = (ranked.isMouseOver() || online.isMouseOver() || custom.isMouseOver());
		
		if (before && over) {
			sword.fade(0, 1, 30);
			if (ranked.isMouseOver()) { desc_ranked.fade(0, 1, 50); sword.setCompY(300); }
			if (online.isMouseOver()) { desc_online.fade(0, 1, 50); sword.setCompY(400); }
			if (custom.isMouseOver()) { desc_custom.fade(0, 1, 50); sword.setCompY(500); }
		}
		else if (!before && !over) {
			sword.fade(1, 0, 30);
		}
		
		if (!ranked.isMouseOver()) desc_ranked.fade(1, 0, 0);
		if (!online.isMouseOver()) desc_online.fade(1, 0, 0);
		if (!custom.isMouseOver()) desc_custom.fade(1, 0, 0);
		
	}
	
	@Override
	protected void discard() {
		sword.fade(1f, 0, 0);
	}

	@Override
	public int getID() {
		return Containers.LOBBY;
	}

}
