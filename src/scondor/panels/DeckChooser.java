package scondor.panels;

import java.util.ArrayList;
import java.util.List;

import scondor.Engine;
import scondor.components.Button;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.deck.DeckData;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;

public class DeckChooser extends Panel {
	
	private List<DeckData> decks = new ArrayList<>();
	private Label titel;
	private Picture arrow;
	
	private static int MAX = 10;
	private static int current;
	private Button[] names = new Button[MAX];

	private int n;
	
	public DeckChooser() {
		super(1);
		super.setBackground(new Texture("lobby"));
		
		titel = new Label("Choose your deck to play:", 50, 50, 4, 1);
		add(titel);
		
		arrow = new Picture(new Texture("arrow"), 275, 420, 20, 20);
		add(arrow);
		
		for (n = 0;n<MAX;n++) {
			names[n] = new Button("", 300, 200+100*(n+current), 4, 1, null).setColor(0.4f, 0.2f, 0.1f);
			names[n].setEffect(new OutlineEffect(0.5f, 0.4f, 0.7f, 2));
			names[n].setDamper(0.5f);
			add(names[n]);
		}
		
		addAction(new Action() {
			public void perform() {
				
				if (isVisible()) {
					if (KeyBoard.isKeyTyped(KeyBoard.KEY_UP)) current++;
					if (KeyBoard.isKeyTyped(KeyBoard.KEY_DOWN)) current--;
					if (Mouse.getDWheel()>0) current--;
					if (Mouse.getDWheel()<0) current++;
					
					current = Math.min(decks.size()-1, current);
					current = Math.max(2-(decks.size()-1), current);
					
					for (n=0;n<MAX;n++) {
						names[n].setXY(names[n].getX(), 200+100*(n+current));
						names[n].setTransparency(1f-(0.3f*Math.abs(2-(n+current))));
					}
					
					if (KeyBoard.isKeyTyped(KeyBoard.KEY_ENTER)) {
						Client.send(new Message("lobby;"+getDeckID(names[current].getText())));
						Panels.show(Panels.LOBBY);
					}
					
				}
				
			}
		});
		
	}
	
	private int getDeckID(String name) {
		for (DeckData deck : decks) if (deck.getName().equalsIgnoreCase(name)) return deck.getID();
		return -1;
	}
	
	private void setContents() {
		
		decks = Engine.getConnection().getContents().getAvaibleDecks();
		
		current = decks.size()/2;
		
		n = 0;
		for (DeckData data : decks) {
			
			names[n].setText(data.getName());
			System.out.println(n);
			n++;
		}
		for (n=n+0;n<MAX;n++) names[n].setText("");
		
	}
	
	@Override
	public void swipeIn() {
		
		setContents();
		
		fade(0, 1, Panels.FADEIN);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
	}

	@Override
	public void swipeOut() {
		fade(1, 0, Panels.FADEOUT);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
	}
	
}
