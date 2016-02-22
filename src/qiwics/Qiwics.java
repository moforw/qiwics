package qiwics;

import java.time.Instant;

public class Qiwics {
	public static final Instant nullTime = Instant.ofEpochMilli(0);

	public static final int VERSION[] = {0, 0, 1};
	
	public static int version() {
	    return VERSION[0] * 100 + VERSION[1] * 10 + VERSION[2];
	}
	
	private Qiwics() { }
}
