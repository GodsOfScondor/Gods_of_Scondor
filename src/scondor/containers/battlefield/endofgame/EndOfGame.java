package scondor.containers.battlefield.endofgame;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.Picture;
import scondor.components.TextButton;
import scondor.font.effect.OutlineEffect;
import scondor.image.Images;
import scondor.util.Action;
import scondor.util.Color;

public class EndOfGame extends Container {

	private static final int PRIORITY = 4;
	
	private Picture black_fade;
	private Label end_msg;
	private TextButton back;
	
	public EndOfGame() {
		super(PRIORITY);
		
		/*
		 * create effects and colors
		 */
		
		Color white = new Color(1, 1, 1);
		OutlineEffect outline = new OutlineEffect(0, 0, 0, 2);
		
		/*
		 * create components
		 */
		
		black_fade = new Picture(Images.COLOR_BLACK, false);
		end_msg = new Label("", 500, 400, 7, 2, false).setColor(white).setEffect(outline);
		back = new TextButton("Back to main menu?", 500, 600, 2, 3, new Action() {
			public void perform() {
				Containers.show(Containers.getMain());
			}
		}, false).setColor(white).setEffect(outline);
		
		/*
		 * add components add container
		 */
		
		super.add(black_fade);
		super.add(end_msg);
		super.add(back);
		
		/*
		 * validata container
		 */
		
		super.validate();
		
		/*
		 * 
		 */
		
		black_fade.fade(1, 0, 0);
		
	}
	
	public void show(EndOfGameType type) {
		
		switch (type) {
		case LOSE:
			break;
		case QUIT:
			black_fade.fade(0, 0.8f, 100);
			end_msg.setText("Enemey disconnected!");
			end_msg.setCompX(500-(end_msg.getCompWidth()/2));
			end_msg.fade(0, 1, 100);
			back.setCompX(500-(back.getCompWidth()/2));
			back.fade(0, 1, 100);
			break;
		case SURRENDER_LOSE:
			break;
		case SURRENDER_WIN:
			break;
		case WIN:
			break;
		}
		
	}
	
	@Override
	protected void discard() {
		end_msg.fade(0.1f, 0, 0);
		back.fade(0.1f, 0, 0);
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public int getID() {
		return -1;
	}

}
