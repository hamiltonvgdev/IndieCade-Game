package Tiles;

import org.newdawn.slick.Color;

import Form.Form;
import Player.Player;

public class FormTile extends InteractTile
{
	Form form;
	boolean empty;
	
	public FormTile(String name, Player player, Color Id)
	{
		super(name, player, Id);
		empty = false;
	}
	
	public FormTile setForm(Form form)
	{
		this.form = form;
		return this;
	}
	
	public void action()
	{
		if(!empty)
		{
			player.giveForm(form);
		}
		
		empty = true;
	}
}
