package com.huangshang;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class Gulinazha extends Robot {
	public void run() {
		while (true) {
			ahead(100);
			turnRight(20);
			ahead(100);
			turnGunLeft(20);
			
		}
		
	}
public void onScannedRobot(ScannedRobotEvent e) {
	// TODO Auto-generated method stub
	if(e.getBearing()>-50&&e.getBearing()<50){
		if(e.getDistance()<50&&e.getEnergy()>50){
			fire(3);
		}else {
			fire(1);
		}
		
	}else{
//		fireBullet(1);
	}
}
/**
 * onHitByBullet: What to do when you're hit by a bullet
 */
public void onHitByBullet(HitByBulletEvent e) {//每当被子弹击中
	// Replace the next line with any behavior you would like
	back(100);
	
}

/**
 * onHitWall: What to do when you hit a wall
 */
public void onHitWall(HitWallEvent e) {//每当撞墙
	// Replace the next line with any behavior you would like
	back(10);
}	
public void onHitRobot(HitRobotEvent event) {
	// TODO Auto-generated method stub
	back(10);
	fire(2);
	
}
public float getTankStep(){
	float step=(float) (getBattleFieldWidth()/10f);
	return step;
}

}
