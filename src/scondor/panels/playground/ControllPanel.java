package scondor.panels.playground;

import scondor.components.Button;
import scondor.components.Component;
import scondor.components.Panel;
import scondor.packets.Message;
import scondor.panels.EffectAble;
import scondor.panels.Panels;
import scondor.server.Client;
import scondor.util.Action;

public class ControllPanel extends Panel {

	public ControllPanel() {
		super(1);
		
		Button button_switch = new Button("SWITCH", 600, 900, 4, 3, new Action() {
			public void perform() {
				Client.sendToServer(new Message("fight;action;switch"));
			}
		});
		
		add(button_switch);
		
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
