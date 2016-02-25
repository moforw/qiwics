package qiwics;

import java.nio.file.FileSystems;

//add Message class

//gen markov text using TextGen for Message subject and body
///select next suffix based on byte value, byte for byte
///needs min byte value alts, else back up
////will replay the same on other side

//gen magic byte combo 0123456789 as subject
///use to trigger imap fetch / process / decrypt

//add Contact class

//add KeyPad class/Tbl
///contact ref

///contains the TxtGen training material
///use to train contact.txtGen()
///add Contact.initKeyPads() method to generate new pads from existing emails in accounts
////store complete message bodies

//add Identity class
///name, publicKey & privateKey

//gen keys on Identity creation
///use JSch / web example

//tiling interface
///add Tile class, extend Composite
////add label
////add edit to get something to focus on
///up to 8 tiles
///inc cols first, then rows
///use gridlayout
///unified menubar
////each tile has it's own menus/items
///dim inactive tiles
///ctrl|1-8 to switch tile

//add account Tile
///used to view/edit a single account
///email, password, imap_host/port, smtp_host/port
///add accounts/new account menu item

//add Identity class
///add name

//add Identity tile with name and keys
///generate RSA keys
///and store in settings table

//Add contact class

//add Contact Tile
///used to view/edit a single contact
///name, email, public key
///add contacts/new contact menu item

// add markdown support in threads
/// use html view for viewing and richtext for editing

// add support for full text search
/// link ngrams to entities
/// add each ngram has a weight, text and entity

public class Main {
	public static void main(final String[] args) {
		Context cx = 
			new Context(FileSystems.getDefault().getPath("commit.log"));
		
		GUI.run(cx);    
	}
}
