package qiwics.db;

public interface Setter<RecT, ValT> {
	public abstract void set(final RecT r, final ValT v);
}
