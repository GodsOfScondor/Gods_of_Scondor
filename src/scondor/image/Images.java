package scondor.image;

public class Images {
	
	public static Texture WALLPAPER_LOBBY;
	public static Texture WALLPAPER_MAIN;
	public static Texture WALLPAPER_SHOP;
	
	public static Texture COLOR_BLACK;
	public static Texture COLOR_WHITE;
	public static Texture COLOR_RED;
	public static Texture COLOR_GREEN;
	public static Texture COLOR_BLUE;
	
	public static Texture LAYOUT_GREEN_COMMON;
	
	public static Texture TEST_IMG;
	
	public static Texture ICON_ARROW;
	
	public static Texture BUTTON_BORDER;
	
	public static Texture DECO_FRAMES;
	
	public static Texture CARD_BACK;
	
	public static void load() {
		
		WALLPAPER_LOBBY = new Texture("lobby");
		WALLPAPER_MAIN = new Texture("bg");
		WALLPAPER_SHOP = new Texture("shop");
		
		COLOR_BLACK = new Texture("colors/black");
		COLOR_WHITE = new Texture("colors/white");
		COLOR_RED = new Texture("colors/red");
		COLOR_GREEN = new Texture("colors/green");
		COLOR_BLUE = new Texture("colors/blue");
		
		LAYOUT_GREEN_COMMON = new Texture("card_gn_common");
		
		TEST_IMG = new Texture("img_test");
		
		ICON_ARROW = new Texture("arrow");
		BUTTON_BORDER = new Texture("button_border");
		
		DECO_FRAMES = new Texture("frame", 1, 2);
		
		CARD_BACK = new Texture("card_back");
	}
	
}
