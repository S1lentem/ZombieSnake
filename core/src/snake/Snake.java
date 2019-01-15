package snake;

import java.io.Serializable;
import java.util.ArrayList;
import gameobjects.*;

public class Snake extends GameObject implements Serializable{
	private SegmentSnake head;
	private ArrayList<SegmentSnake> body = new ArrayList<SegmentSnake>();
	private Direction direct;
	private final int delayTurn;
	private int currentDelay = 0;
	
	public Snake(SegmentSnake head, int delay) {
		this.head = head;
		this.direct = Direction.Right;
		this.delayTurn = delay;
		addChild();
	}
	
	public Snake(int delay) {
		this.head = new SegmentSnake(1,1);
		this.delayTurn = delay;
		this.direct = Direction.Right;
		addChild();
	}
	
	public void move() {
		this.head.Move(this.direct);
		SegmentSnake prev = this.head;
		for (SegmentSnake elem: body) {
			elem.Move(prev);
			prev = elem;
		}
		currentDelay++;
	}
	
	public float getHeadX() {
		return head.getX();
	}
	
	public float getHeadY() {
		return head.getY();
	}
	
	@Override
	public float getX() {
		return getHeadX();
	}
	
	@Override
	public float getY() {
		return getHeadY();
	}
	
	public int getLenSnake() {
		return this.body.size() + 1;
	}
	
	public Direction getDirection() {
		return this.direct;
	}
	
	public void setDirection(Direction direct) {
		if (currentDelay * SegmentSnake.getSpeed() > delayTurn) {
			int delta = this.direct.ordinal() - direct.ordinal();
			if (delta != 2 && delta != -2) {
				this.direct = direct;		
			}
			currentDelay = 0;
		}
	}
	
	public void addChild() {
		float tempX, tempY;
		Direction direct;
		if (!body.isEmpty()) {
			int last = body.size() - 1;
			tempX = body.get(last).getX();
			tempY = body.get(last).getY();
			direct = body.get(last).getDirection();
		}
		else {
			tempX = head.getX();
			tempY = head.getY();
			direct = head.getDirection();
		}
		if (direct == Direction.Down) {
			tempY += delayTurn;
		}
		else if (direct == Direction.Up) {
			tempY -= delayTurn;
		}
		else if (direct == Direction.Left) {
			tempX +=  delayTurn;
		}
		else {
			tempX -= delayTurn;
		}
		SegmentSnake tail = new SegmentSnake(tempX, tempY, direct);
		body.add(tail);
	}
	
	public void removeChild() {
		this.body.remove(this.body.size() - 1);
	}

	public ArrayList<SegmentSnake> getBody() {
		return body;
	}
	
	public void speedUp() {
		SegmentSnake.setSpeed(SegmentSnake.getSpeed() + 2);
		//System.out.println(SegmentSnake.getSpeed());
	}
	
	public void resetSpeed() {
		SegmentSnake.setSpeed(2);
		
	}
	
	public void reset(int resetX, int resetY) {
		this.head.setX(resetX);
		this.head.setY(resetY);
		this.direct = Direction.Right;
		this.head.setDirection(Direction.Right);
		this.body = new ArrayList<SegmentSnake>();
		addChild();
		this.resetSpeed();
	}
	
	public boolean isIntersectionWithYourself(float delta) {
		for (SegmentSnake segment: this.body) {
			if (segment != body.get(0)) {
				boolean checkedX = segment.getX() + delta > this.head.getX() && segment.getX() - delta < this.head.getX();
				boolean checkedY = segment.getY() + delta > this.head.getY() && segment.getY() - delta < this.head.getY();
				if (checkedX && checkedY) {
					return false;
				}
			}
		}
		return true;
	}
}
