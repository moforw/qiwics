package qiwics.db;

public interface ReaderWriter<RecT, ValT> extends Reader<RecT, ValT>, Writer<RecT, ValT> {
	public ValT getSetVal(final RecT r, ValT v);
 
	@Override
	public default void setVal(final RecT r, ValT v) {
		setVal(r, v);
	}

	@Override
	public default ValT val(final RecT r) {
		return getSetVal(r, null);
	}
}
