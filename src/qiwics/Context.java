package qiwics;

import java.nio.file.Path;

import jbls.DB;

public class Context {
	public final DB db;

	public Context(final Path p) {
		db = new DB(p);
	}
}