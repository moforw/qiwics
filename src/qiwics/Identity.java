package qiwics;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import jbls.*;

public class Identity extends BasicRec {
	public static class T extends Tbl<Identity> {		
		public final StrCol<Identity> Name =     strCol("name")
			.read((i)     -> i.name)
			.write((i, v) -> i.name= v);
		public final KeyCol<Identity> privateKey = privKeyCol("privateKey")
			.read((i)     -> i.privateKey)
			.write((i, v) -> i.privateKey = v);
		public final KeyCol<Identity> publicKey = pubKeyCol("publicKey")
			.read((i)     -> i.privateKey)
			.write((i, v) -> i.privateKey = v);

		public T(final String n) {
			super(n);
		}
		
		@Override
		protected Identity newRec(final UUID id) {
			return new Identity(id);
		}
	}
	
	public String name;
	public Key privateKey;
	public Key publicKey;

	public static final T table = new T("identities");

	public Identity(final UUID id) {
		super(id);
		initKeys();
	}
	
	private void initKeys() {
		final KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
		} catch (final NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		kpg.initialize(4096);
		final KeyPair kp = kpg.genKeyPair();
		privateKey = kp.getPrivate();
		publicKey = kp.getPublic();
	}
}
