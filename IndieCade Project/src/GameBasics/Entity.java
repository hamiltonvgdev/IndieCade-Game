package GameBasics;

import java.io.Serializable;

import org.newdawn.slick.Graphics;

import Geo.Quad;
import Render.AnimationSet;
import Render.BasicImage;

public abstract class Entity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1335939607570570293L;
	
	//Horizontal movement
	float x;
	float Vx;
	float Vy;
	float Ax;
	float Ay;
	float acceleration;
	
	//Vertical movement
	float y;
	float jumpV;
	int jump;
	int maxJumps;
	long jumpTick;
	boolean jumping;
	
	//Combat statistics
	float maxHealth;
	float health;
	float damage;
	float defense;
	float speed;
	float tenacity;
	float basespeed;
	
	//Combat values
	float Stun;
	float Speed;
	
	
}
