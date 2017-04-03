package scondor.panels.shop;

import java.util.LinkedList;
import java.util.Queue;

import scondor.components.Card;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.deck.card.CardData;
import scondor.image.Texture;
import scondor.util.Action;
import scondor.util.Maths;

public class ProductShowcase extends Panel {

	private static final int PRIORITY = 2;
	private Picture blackfade;
	private Card postview;
	private Queue<CardData> queue = new LinkedList<>();
	
	private CardData current;
	
	public ProductShowcase() {
		super(PRIORITY);
		blackfade = new Picture(new Texture("colors/black"), 0, 0, 1000, 1 + (int) (1000 / Maths.getScreenRatio()));
		blackfade.setLayer(0.3f);// set auf kleiner um picture vor darkscreen zu
									// bringen
		postview = new Card(null, 50, 50, 5).setLayer(0.2f);
		
		addAction(new Action() {
			@Override
			public void perform() {
				if ((current = queue.poll())!=null) {
					postview.setData(current);
					
				}
			}
		});
		
		add(blackfade);
		add(postview);
	}
	
	protected void addData(CardData data) {
		queue.add(data);
	}

	@Override
	public void swipeIn() {
		this.blackfade.fade(0.0f, 0.6f, 200);
		this.postview.fade(0.0f, 1f, 200);
	}

	@Override
	public void swipeOut() {
		this.blackfade.fade(0.6f, 0.0f, 200);
		this.postview.fade(1.0f, 0.0f, 200);
	}
	
}
