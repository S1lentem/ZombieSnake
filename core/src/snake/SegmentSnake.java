package snake;



import java.io.Serializable;

import gameobjects.*;

public class SegmentSnake extends GameObject implements Serializable {
	private float x;
	private float y;
	private Direction direct;
	private static float speed = 2;
	private boolean isCorrect = false;
	
	public SegmentSnake(float x, float y) {
		this.x = x;
		this.y = y;
		this.direct = Direction.Right;
	}
	
	public SegmentSnake(float x, float y, Direction direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	public void Move(Direction direct) {
		if (direct == Direction.Left) {
			this.x-=speed;
		}
		else if (direct == Direction.Up) {
			this.y+=speed;
		}
		else if (direct == Direction.Right) {
			this.x+=speed;
		}
		else {
			this.y-=speed;
		}
		this.direct = direct;
	}
	
	
	public void Move(SegmentSnake prev) {
		boolean temp1 = this.x >= prev.getX() && this.direct == Direction.Right;
		boolean temp2 = this.x <= prev.getX() && this.direct == Direction.Left;
		boolean temp3 = this.y >= prev.getY() && this.direct == Direction.Up;
		boolean temp4 = this.y <= prev.getY() && this.direct == Direction.Down;
		if (temp1 || temp2 || temp3 || temp4) {
			this.direct = prev.direct;
		}
		this.Move(this.direct);
	}
	
	@Override
	public float getX() { 
		return this.x; 
	}

	@Override
	public float getY() {
		return this.y; 
	}
	
	public void setX(float x) { 
		this.x = x; 
	}
	
	public void setY(float y) { 
		this.y = y; 
	}
	
	public static void setSpeed(float value) {
		speed = value;
	}
	
	public static float getSpeed() {
		return speed;
	}
	
	public Direction getDirection() {
		return this.direct;
	}
	
	public void setDirection(Direction direct) {
		this.direct = direct;
	}
}
