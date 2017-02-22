package scondor.panels;

import scondor.components.Button;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.font.effect.GlowEffect;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.util.Action;

public class Lobby extends Panel {
	
	private Button ranked, online, custom, back;
	private Label lobby, desc_ranked, desc_online, desc_custom;
	private Picture sword, upper_frame, lower_frame;
	
	public Lobby() {
		super(1);
		setBackground(new Texture("lobby"));
		OutlineEffect effect = new OutlineEffect(0, 0, 0, 2);
		ranked = new Button("RANKED", 300, 300, 4, 1, null).setColor(0.8f, 0.8f, 0.8f).setDamper(-0.2f).setEffect(effect);
		online = new Button("ONLINE", 300, 400, 4, 1, null).setColor(0.8f, 0.8f, 0.8f).setDamper(-0.2f).setEffect(effect);
		custom = new Button("CUSTOM", 300, 500, 4, 1, null).setColor(0.8f, 0.8f, 0.8f).setDamper(-0.2f).setEffect(effect);
		back = new Button("BACK", 800, 900, 3.5f, 1, new Action() {
			public void perform() {
				Panels.show(Panels.MAIN);
			}
		}).setColor(0.8f, 0.8f, 0.8f).setDamper(-0.2f).setEffect(effect);
		
		lobby = new Label("Lobby", 10, 10, 8, 1).setEffect(new GlowEffect(0.8f, 0.8f, 0.8f, 3));
		desc_ranked = new Label("get ranked into a list of all players.", 600, 300, 3, 1).setLineSize(300);
		desc_online = new Label("play against random opponents without ranking.", 600, 300, 3, 1).setLineSize(300);
		desc_custom = new Label("play against your friends.", 600, 300, 3, 1).setLineSize(300);
		
		sword = new Picture(new Texture("sword"), 250, 300, 200, 40);
		Texture atlas = new Texture("frame", 1, 2);
		upper_frame = new Picture(new Texture(atlas, 0, 0), 270, 190, 200, 60);
		lower_frame = new Picture(new Texture(atlas, 0, 1), 270, 550, 200, 60);
		
		addAction(new Action() {
			public void perform() {
				
				if (!isVisible()) return;
				
				if (ranked.isTargeted()) { desc_ranked.setVisible(true); sword.setY(300); }
				else desc_ranked.setVisible(false);
				if (online.isTargeted()) { desc_online.setVisible(true); sword.setY(400); }
				else desc_online.setVisible(false);
				if (custom.isTargeted()) { desc_custom.setVisible(true); sword.setY(500); }
				else desc_custom.setVisible(false);
				
				if (ranked.isTargeted() || online.isTargeted() || custom.isTargeted()) sword.setVisible(true);
				else sword.setVisible(false);
				
			}
		});
		
		add(ranked);
		add(online);
		add(custom);
		add(back);
		
		add(lobby);
		add(desc_ranked);
		add(desc_online);
		add(desc_custom);
		
		add(sword);
		add(upper_frame);
		add(lower_frame);
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
