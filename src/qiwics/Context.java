package qiwics;

import java.nio.file.Path;

public class Context {
	public final jbls.DB db;

	public Context(final Path p) {
		db = new jbls.DB(p);
	}
}