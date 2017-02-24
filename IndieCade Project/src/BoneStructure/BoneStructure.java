package BoneStructure;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import Player.Player;

public class BoneStructure implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -785715115218883562L;
	ArrayList<Bone> Bones;
	
	public boolean flip;
	
	Bone Head;
	public Joint neck;
	
	Bone Neck;
	Bone Shoulder1;
	Bone Shoulder2;
	Bone USpine;
	Bone LSpine;
	Bone Pelvic1;
	Bone Pelvic2;
	public FourWayJoint torso;
	public ThreeWayJoint Pelvic;
	public Joint Spine;
	public Joint PelvicR;
	public Joint PelvicL;
	
	Bone UArm1;
	Bone LArm1;
	Bone Hand1;
	public Joint shoulder1;
	public Joint Elbow1;
	public Joint Wrist1;
	
	Bone UArm2;
	Bone LArm2;
	Bone Hand2;
	public Joint shoulder2;
	public Joint Elbow2;
	public Joint Wrist2;
	
	Bone ULeg1;
	Bone LLeg1;
	Bone Foot1;
	public Joint Knee1;
	public Joint Ankle1;
	
	Bone ULeg2;
	Bone LLeg2;
	Bone Foot2;
	public Joint Knee2;
	public Joint Ankle2;
	
	float size;
	Player player;
	
	public BoneStructure(Player player, Float size)
	{
		Bones = new ArrayList<Bone>();
		
		this.player = player;
		
		Head = new Bone(size * 30);
		Head.setRot(90);
		Head.setX(player.getX());
		Head.setY(player.getY());
		Bones.add(Head);
		
		Neck = new Bone(size * 8);
		Bones.add(Neck);
		
		neck = new Joint(Head, Neck);

		Shoulder1 = new Bone(size * 30);
		Bones.add(Shoulder1);
		Shoulder2 = new Bone(size * 30);
		Bones.add(Shoulder2);
		LSpine = new Bone(size * 20);
		Bones.add(LSpine);
		USpine = new Bone(size * 20);
		Bones.add(USpine);
		torso = new FourWayJoint(Neck, Shoulder1, Shoulder2, USpine);
		
		Spine = new Joint(USpine, LSpine);
		ULeg1 = new Bone(size * 25);
		Bones.add(ULeg1);
		ULeg2 = new Bone(size * 25);
		Bones.add(ULeg2);
		Pelvic1 = new Bone(size * 7.5F);
		Pelvic2 = new Bone(size * 7.5F);
		Pelvic = new ThreeWayJoint(LSpine, Pelvic1, Pelvic2);
		PelvicR = new Joint(Pelvic1, ULeg1);
		PelvicL = new Joint(Pelvic2, ULeg2);
		
		UArm1 = new Bone(size * 5);
		Bones.add(UArm1);
		LArm1 = new Bone(size * 22);
		Bones.add(LArm1);
		Hand1 = new Bone(size * 7);
		Bones.add(Hand1);
		shoulder1 = new Joint(Shoulder1, UArm1);
		Elbow1 = new Joint(UArm1, LArm1);
		Wrist1 = new Joint(LArm1, Hand1);

		UArm2 = new Bone(size * 5);
		Bones.add(UArm2);
		LArm2 = new Bone(size * 22);
		Bones.add(LArm2);
		Hand2 = new Bone(size * 7);
		Bones.add(Hand2);
		shoulder2 = new Joint(Shoulder2, UArm2);
		Elbow2 = new Joint(UArm2, LArm2);
		Wrist2 = new Joint(LArm2, Hand2);
		
		LLeg1 = new Bone(size * 34);
		Bones.add(LLeg1);
		Foot1 = new Bone(size * 20);
		Bones.add(Foot1);
		Knee1 = new Joint(ULeg1, LLeg1);
		Ankle1 = new Joint(LLeg1, Foot1);
		
		LLeg2 = new Bone(size * 34);
		Bones.add(LLeg2);
		Foot2 = new Bone(size * 20);
		Bones.add(Foot2);
		Knee2 = new Joint(ULeg2, LLeg2);
		Ankle2 = new Joint(LLeg2, Foot2);
		

		
		Bones.add(Pelvic1);
		Bones.add(Pelvic2);
		
		torso.rotBone2(-15);
		torso.rotBone3(15);
		shoulder1.rotBone2(40);
		shoulder2.rotBone2(40);
		Elbow1.rotBone2(-60);
		Elbow2.rotBone2(-60);
		Wrist1.rotBone2(60);
		Wrist2.rotBone2(60);
		
		Pelvic.getBone2().setRot(0);
		Pelvic.getBone2().setFlip(true);
		Pelvic.getBone3().setRot(0);
		PelvicR.setBone2Rot(100);
		PelvicL.setBone2Rot(100);
		PelvicL.getBone2().setFlip(true);
		
		Knee1.getBone2().setRot(80);
		Knee2.getBone2().setRot(80);
		Knee1.getBone2().setFlip(true);
		
		Ankle1.getBone2().setRot(0);
		Ankle2.getBone2().setRot(180);
		Ankle2.getBone2().setFlip(true);
		this.size = size;
		
		flip = false;
	}
	
	public void update()
	{
		
		neck.update();
		torso.update();
		Pelvic.update();
		PelvicR.update();
		PelvicL.update();
		Spine.update();
		
		shoulder1.update();
		Elbow1.update();
		Wrist1.update();
		
		shoulder2.update();
		Elbow2.update();
		Wrist2.update();
		
		Knee1.update();
		Ankle1.update();
		
		Knee2.update();
		Ankle2.update();
	}
	
	public void render(Graphics g)
	{
		Head.render(g);
		neck.render(g);
		Neck.render(g);
		torso.render(g);
		Shoulder1.render(g);
		Shoulder2.render(g);
		USpine.render(g);
		LSpine.render(g);
		Pelvic1.render(g);
		Pelvic2.render(g);
		PelvicR.render(g);
		PelvicL.render(g);
		
		UArm1.render(g);
		LArm1.render(g);
		Hand1.render(g);
		
		UArm2.render(g);
		LArm2.render(g);
		Hand2.render(g);
		
		ULeg1.render(g);
		LLeg1.render(g);
		Foot1.render(g);
		
		ULeg2.render(g);
		LLeg2.render(g);
		Foot2.render(g);
		
		Pelvic.render(g);
		Spine.render(g);
		
		shoulder1.render(g);
		Elbow1.render(g);
		Wrist1.render(g);
		
		shoulder2.render(g);
		Elbow2.render(g);
		Wrist2.render(g);
		
		Knee1.render(g);
		Ankle1.render(g);
		
		Knee2.render(g);
		Ankle2.render(g);
		
	}
	
	public Joint getJoint(String name)
	{
		if(name.equals("Neck"))
		{
			return neck;
		}else if(name.equals("Torso"))
		{
			return torso;
		}else if(name.equals("Pelvic"))
		{
			return Pelvic;
		}else if(name.equals("Spine"))
		{
			return Spine;
		}else if(name.equals("Shoulder 1"))
		{
			return shoulder1;
		}else if(name.equals("Shoulder 2"))
		{
			return shoulder2;
		}else if(name.equals("Elbow 1"))
		{
			return Elbow1;
		}else if(name.equals("Elbow 2"))
		{
			return Elbow2;
		}else if(name.equals("Wrist 1"))
		{
			return Wrist1;
		}else if(name.equals("Wrist 2"))
		{
			return Wrist2;
		}else if(name.equals("Knee 1"))
		{
			return Knee1;
		}else if(name.equals("Knee 2"))
		{
			return Knee2;
		}else if(name.equals("Ankle 1"))
		{
			return Ankle1;
		}else if(name.equals("Ankle 2"))
		{
			return Ankle2;
		}else 
		{
			return null;
		}
	}
	
	public ArrayList<Bone> getBones()
	{
		return Bones;
	}
	
	public float getSize()
	{
		return size;
	}
	
	public void flip()
	{
		//derp
		//Legs flip incorrectly, could be rotational error. 
		//Something may be up with the multiple images for the leg, should fix that
		
		for(Bone bone: Bones)
		{
			if(bone.flip)
			{
				bone.setFlip(false);
			}else
			{
				bone.setFlip(true);
			}
		}
		
		if(flip)
		{
			flip = false;
		}else
		{
			flip = true;
		}
	}
}
