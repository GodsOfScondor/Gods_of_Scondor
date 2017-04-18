package scondor.components;

import scondor.deck.card.CardData;
import scondor.deck.card.troops.ATCardData;
import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Images;
import scondor.inputs.Mouse;
import scondor.util.Maths;

public class BattleCard extends Component {

	private Image image;
	private Image layout;
	private Text attack, life, countdown;

	private boolean over;

	private CardData data;
	private float size;

	public BattleCard(CardData data, int x, int y, float size, boolean depending) {
		super(x, y, 0, 0, depending);

		this.data = data;
		this.size = size;

		layout = new Image(Images.LAYOUT_GREEN_COMMON, 0, 0, 0, 0, -1);
		image = new Image(Images.TEST_IMG, 0, 0, 0, 0, -1);
		attack = new Text("", 0, 0, 0, 1, -1);
		life = new Text("", 0, 0, 0, 1, -1);
		countdown = new Text("", 0, 0, 0, 1, -1);

		setLayer(0.49f);

		layout.setTransparency(0f);
		image.setTransparency(0f);
		attack.setTransparency(0f);
		life.setTransparency(0f);
		countdown.setTransparency(0f);

		changeData(data);
	}

	public boolean isMouseOver() {
		return over;
	}

	public void changeData(CardData data) {
		this.data = data;

		if (data instanceof ATCardData) {

			int attack = ((ATCardData) data).getAttack();
			int life = ((ATCardData) data).getLife();
			int countdown = ((ATCardData) data).getCountdown();

			this.attack.setText("" + attack);
			this.life.setText("" + life);
			this.countdown.setText("" + countdown);
		}

		setValues(x, y, size);
	}

	private void setValues(int x, int y, float size) {

		this.size = size;

		super.setCompX(x);
		super.setCompY(y);
		super.setCompWidth((int) (80 * size));
		super.setCompHeight((int) (120 * size));

		layout.setX(x);
		layout.setY(y);
		layout.setWidth(super.getCompWidth());
		layout.setHeight(super.getCompHeight());

		image.setX(x + (int) (6f * size));
		image.setY(y + (int) (12 * size));
		image.setWidth((int) (super.getCompWidth() * 0.85));
		image.setHeight((int) (super.getCompHeight() * 0.43));

		attack.setSize(size * 1.5f);
		attack.setX(x + (int) (20 * size) - (attack.getWidth() / 2));
		attack.setY(y + (int) (95 * size * Maths.getScreenRatio()));

		life.setSize(size * 1.5f);
		life.setX(x + (int) (42 * size) - (life.getWidth() / 2));
		life.setY(y + (int) (95 * size * Maths.getScreenRatio()));

		countdown.setSize(size * 1.5f);
		countdown.setX(x + (int) (63 * size) - (countdown.getWidth() / 2));
		countdown.setY(y + (int) (95 * size * Maths.getScreenRatio()));
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public void setLayer(float layer) {
		layout.setLayer(layer - 0.001f);
		image.setLayer(layer);
		attack.setLayer(layer - 0.004f);
		life.setLayer(layer - 0.004f);
		countdown.setLayer(layer - 0.004f);
	}

	public boolean hasData() {
		return data != null;
	}

	public CardData getData() {
		return data;
	}

	@Override
	public void fade(float start, float end, int duration) {
		layout.fade(start, end, duration);
		image.fade(start, end, duration);
		attack.fade(start, end, duration);
		life.fade(start, end, duration);
		countdown.fade(start, end, duration);
	}

	@Override
	protected void destroy() {
		layout.destroy();
		image.destroy();
		attack.destroy();
		life.destroy();
		countdown.destroy();
	}

	@Override
	protected void update() {
		over = (Mouse.X >= layout.getX() && Mouse.X <= layout.getX() + width && Mouse.Y >= layout.getY()
				&& Mouse.Y <= layout.getY() + height * Maths.getScreenRatio());
	}

	@Override
	protected void fade(float visibility) {
		if (super.isDepending()) {
			layout.setTransparency(visibility);
			image.setTransparency(visibility);
			attack.setTransparency(visibility);
			life.setTransparency(visibility);
			countdown.setTransparency(visibility);
		}
	}

	@Override
	protected void validate(int priority) {
		layout.validate(priority);
		image.validate(priority);
		attack.validate(priority);
		life.validate(priority);
		countdown.validate(priority);
	}

	@Override
	public void setCompX(int x) {
		setValues(x, y, size);
		super.setCompX(x);
	}

	@Override
	public void setCompY(int y) {
		setValues(x, y, size);
		super.setCompY(y);
	}

}
