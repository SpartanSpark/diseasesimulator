package helpers;

import data.Coordinate;
import data.Entity;

public class SmartAI {

	private double theta, lastTheta;
	private int weights, cycles;
	private float z;
	private Entity m;
	private Coordinate c1,c2,c3,c4,c5;
	
	public SmartAI(Entity m) {
		this.m = m;
		theta = Math.random() * 360;
	}
	
/*
 * t1: money
 * t2: events
 * t3: children
 * */
	public float mx(Entity t1, Entity t2, Entity t3, float strength) {
		cycles++;
		lastTheta = theta;
		theta = 0;
		weights = 5;
		z = strength;
		theta += randAngle() * 5;
		c4 = new Coordinate((int) (m.x+Math.cos(Math.toRadians(theta))*z),(int) (m.y+Math.cos(Math.toRadians(theta))*z));
		weights += 5;
		theta += lastTheta * 5;
		c5 = new Coordinate((int) (m.x+Math.cos(Math.toRadians(lastTheta))*z),(int) (m.y+Math.cos(Math.toRadians(lastTheta))*z));
		if(t1 != null) {
			c1 = new Coordinate(t1.x,t1.y);
			weights+=24;
			if(m.x-t1.x != 0 ) theta += Math.toDegrees(Math.atan((m.y-t1.y)/(m.x-t1.x))*24);
			else {
				if(m.y-t1.y > 0) theta += 90*24;
				else if(m.y-t1.y < 0) theta += 270*24;
			}
		}
		else c1 = null;
		if(t2 != null) {
			c2 = new Coordinate(t2.x,t2.y);
			weights+=12;
			if(m.x-t2.x != 0 ) theta += Math.toDegrees(Math.atan((m.y-t2.y)/(m.x-t2.x))*12);
			else {
				if(m.y-t2.y > 0) theta += 90*12;
				else if(m.y-t2.y < 0) theta += 270*12;
			}
		}
		else c2 = null;
		if(t3 != null) {
			c3 = new Coordinate(t3.x,t3.y);
			weights+=6;
			if(m.x-t3.x != 0 ) theta += Math.toDegrees(Math.atan((m.y-t3.y)/(m.x-t3.x))*6);
			else {
				if(m.y-t3.y > 0) theta += 90*6;
				else if(m.y-t3.y < 0) theta += 270*6;
			}
		}
		else c3 = null;
		theta /= weights;
		return (float) (Math.cos(Math.toRadians(theta))*z);
//		return (float) (Math.cos(Math.toRadians(theta))*z)*720;
	}
	
	public float my() {
		draw();
		return (float) (Math.sin(Math.toRadians(theta))*z);
//		return (float) (Math.cos(Math.toRadians(theta))*z)*720;
	}
	
	private static double randAngle() {
		return Math.random() * 360;
	}
	
	public void draw() {
		if(c1 != null) Artist.DrawLine(m.x, m.y, c1.x, c1.y);
		if(c2 != null) Artist.DrawLine(m.x, m.y, c2.x, c2.y);
		if(c3 != null) Artist.DrawLine(m.x, m.y, c3.x, c3.y);
		if(c4 != null) Artist.DrawLine(m.x, m.y, c4.x, c4.y);
		if(c5 != null) Artist.DrawLine(m.x, m.y, c5.x, c5.y);
	}
	
}
