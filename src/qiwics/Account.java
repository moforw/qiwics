package qiwics;

import java.util.UUID;

import jbls.*;

public class Account extends BasicRec {
	public static class T extends Tbl<Account> {		
		public final StringCol Email =    stringCol("email")
			.read((a)     -> a.email)
			.write((a, v) -> a.email = v);
		public final StringCol ImapHost = stringCol("imapHost")
			.read((a)     -> a.imapHost)
			.write((a, v) -> a.imapHost = v);
		public final IntCol ImapPort =    intCol(   "imapPort")
			.read((a)     -> a.imapPort)
			.write((a, v) -> a.imapPort = v);
		public final StringCol Password = stringCol("password")
			.read((a)     -> a.password)
			.write((a, v) -> a.password = v);
		public final StringCol SmtpHost = stringCol("smtpHost")
			.read((a)     -> a.smtpHost)
			.write((a, v) -> a.smtpHost = v);
		public final IntCol SmtpPort =    intCol(   "smtpPort")
			.read((a)     -> a.smtpPort)
			.write((a, v) -> a.smtpPort = v);
		
		public T(final String n) {
			super(n);
		}
		
		@Override
		protected Account newRec(final UUID id) {
			return new Account(id);
		}
	}
	
	public static final T table = new T("accounts");

	public Account (UUID id) {
		super(id);
	}
	
	public String email;
	public String imapHost;
	public int    imapPort;
	public String password;
	public String smtpHost;
	public int    smtpPort;
}
