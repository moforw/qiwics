package qiwics.db;

public interface Writer<RecT, ValT> {
	public void setVal(final RecT r, final ValT v);
}
