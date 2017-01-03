package NPCs;

import GameBasics.BasicNPC;
import Player.Player;

public class AudioNPC extends BasicNPC
{
	String soundRef;
	
	public AudioNPC(String ref, Player player, String name, float range) 
	{
		super(player, name, range);
		
		this.soundRef = ref;
	}
	
	@Override
	public void action()
	{
		Sound.Sound.playSound(soundRef);
	}

}
