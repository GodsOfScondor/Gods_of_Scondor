package scondor.panels;

import scondor.components.Component;

public interface EffectAble<TYPE extends Component> {
	
	public TYPE fade(float start, float end, int time);
	public TYPE slideX(int start, int end, int time);
	public TYPE slideY(int start, int end, int time);
	public TYPE stop();
	
}
