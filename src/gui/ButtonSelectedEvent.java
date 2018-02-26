package gui;

import java.util.EventObject;


/////////////////////////////////// Represents an object passed from InnerPanel to MainFrame ///////////////

public class ButtonSelectedEvent extends EventObject {

	private String character;

	public ButtonSelectedEvent(Object source) {
		super(source);
	}

	public ButtonSelectedEvent(Object source, String character) {
		super(source);
		this.character = character;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

}
