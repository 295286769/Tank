package com.huangshang;


import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class Dilireba extends AdvancedRobot {
	/**
	 * run: Huangshang's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		 setColors(Color.red,Color.blue,Color.green); // body,gun,radar
//		setColors(Color.GRAY, Color.blue, Color.ORANGE);
		// Robot main loop
		while(true) {
		
			// Replace the next 4 lines with any behavior you would like
			setAhead(200);
			setTurnLeft(100);
//			setTurnGunRight(50);
			
//			setTurnRight(20);
//			setAhead(100);
//			setAhead(100);
			scan();
//			setBack(100);
//			setTurnGunRight(360);
			execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {//每当雷达扫描到敌人
		// Replace the next line with any behavior you would like
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun =absoluteBearing - getGunHeading();

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (e.getDistance() < 50 && getEnergy() > 50) {
				fire(3);
			} else if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}else {
				fire(1);
			}
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
		else {
//			turnGunRight(bearingFromGun);
			setAhead(150);
		}
		// Generates another scan event if we see a robot.
		// We only need to call this if the gun (and therefore radar)
		// are not turning.  Otherwise, scan is called automatically.
		if (bearingFromGun == 0) {
			scan();
			setAhead(80);
		}
//		if(e.getBearing()>-50&&e.getBearing()<50){
//			if(e.getDistance()<50&&e.getEnergy()>50){
//				fireBullet(3);
//			}else {
//				fireBullet(1);
//			}
//			
//		}else{
////			fireBullet(1);
//		}
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {//每当被子弹击中
		// Replace the next line with any behavior you would like\
		scan();
		setTurnRight(20);
		setAhead(150);
		
		
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {//每当撞墙
		// Replace the next line with any behavior you would like
		setTurnLeft(20);
		setBack(50);
		setTurnRight(20);
		
		
	}	
	public void onHitRobot(HitRobotEvent event) {
		// TODO Auto-generated method stub
		setBack(10);
		fireBullet(2);
		
	}
	public float getTankStep(){
		float step=(float) (getBattleFieldWidth()/10f);
		return step;
	}
}
