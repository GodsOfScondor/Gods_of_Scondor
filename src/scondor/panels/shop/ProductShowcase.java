package scondor.panels.shop;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import scondor.components.Card;
import scondor.components.Container;
import scondor.components.Picture;
import scondor.deck.card.CardData;
import scondor.image.Images;
import scondor.inputs.Mouse;

public class ProductShowcase  extends Container {

	private static final int PRIORITY = 2;
	
	private Picture black_fade;
	private Card showcase;
	private Queue<CardData> queue = new LinkedList<>();
	
	public ProductShowcase() {
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		black_fade = new Picture(Images.COLOR_BLACK, false);
		showcase = new Card(null, 500, 300, 3, false);
		
		/*
		 * add components to container
		 */
		
		super.add(black_fade);
		super.add(showcase);
		
		/*
		 *  validate container
		 */
		
		super.validate();
		
	}
	
	public void incomingData(List<CardData> cards, PackType type) {
		queue.clear();
		for (CardData card : cards) queue.add(card);
		showcase.changeData(queue.poll());
		
		black_fade.fade(0, 0.8f, 100);
		showcase.fade(0, 1, 100);
	}

	@Override
	public void refresh() {
		if (Mouse.isButtonTyped(0)) {
			CardData data = queue.poll();
			if (data==null) {
				System.out.println("end of list");
			} else {
				showcase.changeData(data);
				showcase.fade(0, 1, 100);
			}
		}
	}

	@Override
	public int getID() {
		return -1;
	}
	

}
