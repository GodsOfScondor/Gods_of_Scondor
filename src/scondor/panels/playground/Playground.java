package scondor.panels.playground;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.image.Images;
import scondor.session.GameType;
import scondor.session.PlayerSideData;

public class Playground extends Container {

	private static final int PRIORITY = 1;

	private PlayerHand player_hand;
	private EnemyHand enemy_hand;
	private Controller controller;
	
	private boolean onturn;
	
	public Playground() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		super.setBackground(Images.WALLPAPER_LOBBY);
		
		/*
		 * create containers
		 */
		
		player_hand = new PlayerHand();
		enemy_hand = new EnemyHand();
		controller = new Controller();
		
		player_hand.fade(0, 1, 0);
		enemy_hand.fade(0, 1, 0);
		controller.fade(0, 1, 0);
		
		/*
		 * validate container
		 */
		
		super.validate();
	}
	
	public boolean isOnTurn() {
		return onturn;
	}
	
	public void setOnTurn(boolean onturn) {
		this.onturn = onturn;
		if (onturn) controller.showMSG("Your turn!");
		else controller.showMSG("Enemys turn!");
	}
	
	public void initData(GameType type, String enemy) {
		
	}
	
	public void updateData(PlayerSideData player, PlayerSideData enemy, String params) {
	
		enemy_hand.updateCards(enemy.getHand().size());
		player_hand.updateCards(player.getHand());

	}

	@Override
	public void refresh() {
		player_hand.update();
		enemy_hand.update();
		controller.update();
	}

	@Override
	public int getID() {
		return Containers.PLAYERGROUND;
	}
	
}
