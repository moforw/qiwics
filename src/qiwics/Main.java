package qiwics;

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

//add Table/Column/Index/Record like qiwics/C++
///db package
///use concurrent sets & maps

//add Account class

//generate RSA keys
///generate if not exists and store in settings table

//Add contact class

//add Contact Tile
///used to view/edit a single contact
///name, email, public key
///add contacts/new contact menu item

// add markdown support in messages
/// send markdown and render on other side

// add support for full text search

public class Main {
	public static void main(final String[] args) {
		Context cx = new Context();
		GUI.run(cx);    
	}
}
