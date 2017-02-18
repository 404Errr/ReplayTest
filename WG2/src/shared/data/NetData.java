package shared.data;

import java.util.Random;

public interface NetData {
	public static final String DEFUALT_USERNAME = "Player"+(1000+new Random().nextInt(9000));//ignore
	public static final String USERNAME = DEFUALT_USERNAME;//username								<<CHANGE USERNAME<<

	public static final String IP = "localhost";
	public static final int PORT = 4040;
}
