package scondor.containers.battlefield;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Picture;
import scondor.containers.battlefield.endofgame.EndOfGame;
import scondor.containers.battlefield.endofgame.EndOfGameType;
import scondor.image.Images;
import scondor.session.GameType;
import scondor.session.PlayerSideData;
import scondor.util.Maths;

public class Battlefield extends Container {

	private static final int PRIORITY = 1;

	private PlayerHand player_hand;
	private EnemyHand enemy_hand;
	private Controller controller;
	private EndOfGame endofgame;
	private BattleMenu menu;
	
	private Picture battle_bar;
	
	private boolean onturn;
	
	public Battlefield() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		super.setBackground(Images.BATTLEFIELD_FIELD);
		
		/*
		 * create containers
		 */
		
		player_hand = new PlayerHand();
		enemy_hand = new EnemyHand();
		controller = new Controller();
		endofgame = new EndOfGame();
		menu = new BattleMenu();
		
		/*
		 * make containers visible (for updating)
		 */
		
		player_hand.fade(0, 1, 0);
		enemy_hand.fade(0, 1, 0);
		controller.fade(0, 1, 0);
		endofgame.fade(0, 1, 0);
		menu.fade(0, 1, 0);
		
		/*
		 * create components
		 */
		
		battle_bar = new Picture(Images.BATTLEFIELD_BAR, 0, 750, 1000,1+(int) (250/Maths.getScreenRatio()), true).setLayer(0.49f);
		
		/*
		 * add components to container
		 */
		
		super.add(battle_bar);
		
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
		controller.turn(onturn);
	}
	
	public void end(EndOfGameType type) {
		endofgame.show(type);
	}
	
	public void initData(GameType type, String enemy) {
		
	}
	
	public void updateData(PlayerSideData player, PlayerSideData enemy, String params) {
	
		enemy_hand.updateCards(enemy.getHand().size());
		player_hand.updateCards(player.getHand());

	}
	
	@Override
	public void discard() {
		player_hand.discardCardPreview();
		player_hand.discard();
		enemy_hand.discard();
		controller.discard();
		menu.discard();
		endofgame.discard();
	}

	public PlayerHand getPlayerhand() {
		return player_hand;
	}

	public EnemyHand getEnemyhand() {
		return enemy_hand;
	}

	public Controller getController() {
		return controller;
	}

	public EndOfGame getEndOfGame() {
		return endofgame;
	}
	
	public BattleMenu getBattleMenu() {
		return menu;
	}
	
	@Override
	public void refresh() {
		player_hand.update();
		enemy_hand.update();
		controller.update();
		endofgame.update();
		menu.update();
	}

	@Override
	public int getID() {
		return Containers.BATTLEFIELD;
	}
	
}
