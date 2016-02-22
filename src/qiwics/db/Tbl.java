package qiwics.db;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class Tbl<RecT extends Rec> {
	public final UUIDCol Id = uuidCol("id").get((r) -> r.id);
	public final String name;
	
	public Tbl(final String n) {
		name = n;
	}
	
	public IntCol intCol(final String n) {
		return new IntCol(n);
	}

	public RecT newRec() {
		final RecT r = newRec(UUID.randomUUID());
		recs.put(r.id, r);
		offs.put(r.id, -1);
		return r;
	}

	public StringCol stringCol(final String n) {
		return new StringCol(n);
	}

	public UUIDCol uuidCol(final String n) {
		return new UUIDCol(n);
	}

	protected abstract RecT newRec(final UUID id);

	private final Map<UUID, Integer> offs = new ConcurrentSkipListMap<>();
	private final Map<UUID, RecT> recs = new ConcurrentSkipListMap<>();

	public class IntCol extends Col<RecT, Integer> {
		public IntCol(final String n) {
			super(n);
		}
	
		public IntCol get(final Getter<RecT, Integer> g) {
			super.get(g);
			return this;
		}

		public IntCol set(final Setter<RecT, Integer> s) {
			super.set(s);
			return this;
		}
	}

	public class StringCol extends Col<RecT, String> {
		public StringCol(final String n) {
			super(n);
		}
	
		public StringCol get(final Getter<RecT, String> g) {
			super.get(g);
			return this;
		}

		public StringCol set(final Setter<RecT, String> s) {
			super.set(s);
			return this;
		}
	}

	public class UUIDCol extends Col<RecT, UUID> {
		public UUIDCol(final String n) {
			super(n);
		}
	
		public UUIDCol get(final Getter<RecT, UUID> g) {
			super.get(g);
			return this;
		}

		public UUIDCol set(final Setter<RecT, UUID> s) {
			super.set(s);
			return this;
		}
	}
}
