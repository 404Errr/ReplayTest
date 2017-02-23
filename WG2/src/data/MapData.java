package data;

public interface MapData {
	public static final String MAP = "map0";
//	public static final String MAP = "map1";
//	public static final String MAP = "map2";
//	public static final String MAP = "map3";
//	public static final String MAP = "buttonsSmall";
//	public static final String MAP = "buttonsMedium2";
//	public static final String MAP = "buttonsMedium";
//	public static final String MAP = "buttonsLarge";
//	public static final String MAP = "largeMapTest";
//	public static final String MAP = "largerMapTest";//dont use on school computer
//	public static final String MAP = "1000x1001";
//	public static final String MAP = "1050x1050";
//	public static final String MAP = "big";
//	public static final String MAP = "old0";
//	public static final String MAP = "old1";
//	public static final String MAP = "105_0";
//	public static final String MAP = "Gen 6x4 2017_02_23 12_23_26";

	public static final boolean ADD_EDGE = true;//puts and edge around the map
	public static final char EDGE_TYPE = '1';
	public static final boolean AUTO_DISABLE_ADD_EDGE = true;//disables if it finds an empty tile or if the map area is greater than the value below
	public static final int AUTO_DISABLE_ADD_EDGE_THREASHOLD = 10000;//area
}
