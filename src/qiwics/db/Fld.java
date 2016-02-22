package qiwics.db;

public class Fld<RecT, ValT> {
	public final String name;
	
	public Fld(final String n) {
		name = n;
	}
	
	public Fld<RecT, ValT> get(Getter<RecT, ValT> g) {
		getter = g;
		return this;
	}

	public ValT get(final RecT r) {
		return getter.get(r);
	}
	
	public Fld<RecT, ValT> set(Setter<RecT, ValT> s) {
		setter = s;
		return this;
	}

	public void set(final RecT r, final ValT v) {
		setter.set(r, v);
	}
	
	private Getter<RecT, ValT> getter;
	private Setter<RecT, ValT> setter;	
}
