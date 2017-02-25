package data;

public interface MapData {
//	static final String MAP = "$30x30";
//	static final String MAP = "map0";
//	static final String MAP = "map1";
//	static final String MAP = "map2";
//	static final String MAP = "map3";
	static final String MAP = "map4";
//	static final String MAP = "buttonsSmall";
//	static final String MAP = "buttonsMedium2";
//	static final String MAP = "buttonsMedium";
//	static final String MAP = "buttonsLarge";
//	static final String MAP = "largeMapTest";
//	static final String MAP = "largerMapTest";
//	static final String MAP = "1000x1001";
//	static final String MAP = "1050x1050";
//	static final String MAP = "big";
//	static final String MAP = "old0";
//	static final String MAP = "old1";
//	static final String MAP = "105_0";
//	static final String MAP = "small";

	static final boolean EDIT_MODE = false;

	static final int EDGE_TYPE = '1';
	static final int SPAWN_POINT_TYPE = 's', SPAWN_POINT_REPLACEMENT_TYPE = '0';//don't touch
	static final int[] EMPTY_TAGS = {'$', 'x'};//don't touch
	static final int EMPTY_TYPE = '2';

	static final boolean ADD_EDGE = true;//puts and edge around the map
	static final boolean AUTO_DISABLE_ADD_EDGE = true;//disables if it finds an empty tile or if the map area is greater than the value below
	static final int AUTO_DISABLE_ADD_EDGE_THREASHOLD = 10000;//area
}
