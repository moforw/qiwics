package qiwics;

import java.util.UUID;

import qiwics.db.*;

public class Account extends Rec {
	public static class T extends Tbl<Account> {		
		public final StringCol Email =    stringCol("email")
			.get((a)    -> a.email)
			.set((a, v) -> a.email = v);
		public final StringCol Password = stringCol("password")
			.get((a)    -> a.password)
			.set((a, v) -> a.password = v);
		public final StringCol ImapHost = stringCol("imapHost")
			.get((a)    -> a.imapHost)
			.set((a, v) -> a.imapHost = v);
		public final IntCol ImapPort =    intCol(   "imapPort")
			.get((a)    -> a.imapPort)
			.set((a, v) -> a.imapPort = v);
		public final StringCol SmtpHost = stringCol("smtpHost")
			.get((a)    -> a.smtpHost)
			.set((a, v) -> a.smtpHost = v);
		public final IntCol SmtpPort =    intCol(   "smtpPort")
			.get((a)    -> a.smtpPort)
			.set((a, v) -> a.smtpPort = v);
		
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
	public String password;
	public String imapHost;
	public int imapPort;
	public String smtpHost;
	public int smtpPort;
}
