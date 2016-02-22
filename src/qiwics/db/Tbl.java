package qiwics.db;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class Tbl<RecT extends Rec> {
	public final UUIDCol Id = uuidCol("id").read((r) -> r.id);
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
	
		public IntCol read(final Reader<RecT, Integer> r) {
			super.read(r);
			return this;
		}

		public IntCol write(final Writer<RecT, Integer> w) {
			super.write(w);
			return this;
		}
	}

	public class StringCol extends Col<RecT, String> {
		public StringCol(final String n) {
			super(n);
		}
	
		public StringCol read(final Reader<RecT, String> r) {
			super.read(r);
			return this;
		}

		public StringCol write(final Writer<RecT, String> w) {
			super.write(w);
			return this;
		}
	}

	public class UUIDCol extends Col<RecT, UUID> {
		public UUIDCol(final String n) {
			super(n);
		}
	
		public UUIDCol read(final Reader<RecT, UUID> r) {
			super.read(r);
			return this;
		}

		public UUIDCol write(final Writer<RecT, UUID> w) {
			super.write(w);
			return this;
		}
	}
}
