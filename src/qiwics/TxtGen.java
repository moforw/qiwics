package qiwics;

import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Stream;

import org.junit.Test;

public class TxtGen {
	public Set<Character> punct = new HashSet<>();
	
	public static String genKey(final List<String> p) {
		return String.join(" ", p);
	}
	
	public TxtGen(final int pl) {
		rnd.setSeed(Instant.now().toEpochMilli());
		prefixLen = pl;
		punct.add('.');
		punct.add('?');
		punct.add('?');
		punct.add('!');
	}
	
	public void train(final StringReader r) {
		final LinkedList<String> p = new LinkedList<>();

		try (final Scanner s = new Scanner(r)) {
			s.useDelimiter(" ");
			while (s.hasNext()) {		
				final String k = genKey(p);
				Set<String> cks = chain.get(k);
				if (cks == null) {
					cks = new ConcurrentSkipListSet<>();
					chain.put(k, cks);
				}
				final String ss = s.next();
				cks.add(ss);
				if (p.size() == prefixLen) {
					p.removeFirst();
				}
				p.addLast(ss);
			}
		}
	}

	public Stream<String> getSufs(final String pref) {
		final Set<String> ss = chain.get(pref);
		return (ss == null) ? Stream.empty() : ss.stream();
	}
	
	public ArrayList<String> gen(final int n) {
		final LinkedList<String> p = new LinkedList<>();
		final ArrayList<String> ws = new ArrayList<>();		
				
		for (int i = 0; i < n; i++) {
			Set<String> sfs = chain.get(genKey(p));
			
			if (sfs == null) {
				if (p.size() > 0) {
					p.removeFirst();
					i--;
					continue;
				}
				
				return null;
			}
			
			Stream<String> sfss = sfs.stream();
			
			if (sfs.size() > 1) {
				final int sc = rnd.nextInt(sfs.size());
				if (sc > 0) {
					sfss = sfss.skip(sc);
				}
			}
			
			final String w = sfss.findFirst().get();
			
			ws.add(w);

			if (p.size() == prefixLen) {
				p.removeFirst();
			}
			
			p.addLast(w);
			
			if (i == n - 1 && !p.isEmpty()) {
				final String pl = p.getLast();
				if (!punct.contains(pl.charAt(pl.length() - 1))) {
					i--;
				}
			}
		}
		
		return ws;
	}
		
	private final int prefixLen;
	private final Map<String, Set<String>> chain = new ConcurrentSkipListMap<>();
	private final Random rnd = new Random();
	
	public static class Tests {		
		@Test
		public void testSimple() {
			TxtGen g = new TxtGen(2);
			g.train(new StringReader("I am not a number! I am a free man!"));
			assertTrue(g.gen(2).size() >= 2);
			System.out.println(g.gen(100));
		}	

		@Test
		public void testMumboJumbo() {
			TxtGen g = new TxtGen(2);
			g.train(new StringReader("The default whitespace delimiter used by a scanner is as recognized by Character.isWhitespace.\n" +
			"The reset() method will reset the value of the scanner's delimiter to the default whitespace delimiter regardless of whether " +
			"it was previously changed.\nA scanning operation may block waiting for input. The next() and hasNext() methods and their " +
			"primitive-type companion methods (such as nextInt() and hasNextInt()) first skip any input that matches the delimiter pattern, " +
			"and then attempt to return the next token.\nBoth hasNext and next methods may block waiting for further input.\nWhether a hasNext " +
			"method blocks has no connection to whether or not its associated next method will " +
			"block. The findInLine(java.lang.String), findWithinHorizon(java.lang.String, int), and skip(java.util.regex.Pattern) methods operate " +
			"independently of the delimiter pattern.\nThese methods will attempt to match the specified pattern with no regard to delimiters in the " +
			"input and thus can be used in special circumstances where delimiters are not relevant.\nThese methods may block waiting for more input.\n" +
			"Abstract class for reading character streams. The only methods that a subclass must implement are read(char[], int, int) and close().\n" +
			"Most subclasses, however, will override some of the methods defined here in order to provide higher efficiency, additional functionality, or both.\n" +
			"Closes this resource, relinquishing any underlying resources. This method is invoked automatically on objects managed by the try-with-resources statement.\n" +
			"While this interface method is declared to throw Exception, implementers are strongly encouraged to declare concrete implementations of the close method to throw more specific exceptions, or to throw no exception at all if the close operation cannot fail.\n" +
			"Implementers of this interface are also strongly advised to not have the close method throw InterruptedException.\n" +
			"This exception interacts with a thread's interrupted status, and runtime misbehavior is likely to occur if an InterruptedException is suppressed.\n" +
			"More generally, if it would cause problems for an exception to be suppressed, the AutoCloseable.close method should not throw it.\n" +
			"Note that unlike the close method of Closeable, this close method is not required to be idempotent.\n" +
			"In other words, calling this close method more than once may have some visible side effect, unlike Closeable.close which is required to have no effect if called more than once.\n" +
			"However, implementers of this interface are strongly encouraged to make their close methods idempotent."));
			
			System.out.println(String.join(" ", g.gen(10000)));
		}			
	}
}
