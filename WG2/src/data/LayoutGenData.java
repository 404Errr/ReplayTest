package data;

public interface LayoutGenData {
	int CHUNK_SIZE = 15;
	int UNUSED_TYPE = '&';
	int FLOOR_TYPE = '0';//for map validity

	float SHUFFLEDNESS = 0.5f;

	String CHUNK_PATH = "src/client/mapgen/chunks/";
}
