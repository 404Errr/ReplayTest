package data;

public interface MapData {
//	String MAP = "$60x60";
//	String MAP = "map0";
//	String MAP = "map1";
//	String MAP = "map2";
//	String MAP = "map3";
//	String MAP = "map4";
//	String MAP = "buttonsSmall";
//	String MAP = "buttonsMedium2";
//	String MAP = "buttonsMedium";
//	String MAP = "buttonsLarge";
//	String MAP = "largeMapTest";
//	String MAP = "largerMapTest";
//	String MAP = "1000x1001";
//	String MAP = "1050x1050";
//	String MAP = "big";
//	String MAP = "old0";
//	String MAP = "old1";
//	String MAP = "105_0";
//	String MAP = "small";
//	String MAP = "AItest1";
//	String MAP = null;

//	String PATH = "src/maps/";
	//---
	String MAP = "edit";

	String PATH = "src/client/mapgen/chunks/";
	//---

	boolean EDIT_MODE = true;
	int EDIT_HISTORY_LIMIT = 666;

	int EDGE_TYPE = '1';
	int SPAWN_POINT_TYPE = 's', SPAWN_POINT_REPLACEMENT_TYPE = '0';//don't touch
	int[] EMPTY_TAGS = {'$', 'x'};//don't touch
	int EMPTY_TYPE = '2';

	boolean ADD_EDGE = true;//puts and edge around the map
	boolean AUTO_DISABLE_ADD_EDGE = true;//disables if it finds an empty tile or if the map area is greater than the value below
	int AUTO_DISABLE_ADD_EDGE_THREASHOLD = 10000;//area
}
