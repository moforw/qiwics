package qiwics.db;

public interface Getter<RecT, ValT> {
	public abstract ValT get(final RecT r);
}
