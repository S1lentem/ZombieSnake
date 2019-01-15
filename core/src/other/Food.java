package other;

import java.io.Serializable;

import gameobjects.Border;
import gameobjects.GameObject;
import snake.SegmentSnake;
import snake.Snake;

public class Food extends GameObject implements Serializable {
	private float x;
	private float y;
	private boolean isEaten;
	private final float delta = 32;
	
	public Food(float x, float y) {
		this.x = x;
		this.y = y;
		this.isEaten = true;
	}
	
	public Food(float x, float y, boolean isEaten) {
		this.x = x;
		this.y = y;
		this.isEaten = isEaten;
	}
	
	public Food(boolean isEaten) {
		this.isEaten = isEaten;
	}
	
	@Override
	public float getX() {
		return this.x;
	}
	
	@Override
	public float getY() {
		return this.y;
	}
	
	public void setXY(float x, float  y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isInSnake(Snake snake) {
		if (snake.getHeadX() - delta < this.x && snake.getHeadX() + delta > this.x && 
				snake.getHeadY() - delta < this.y && snake.getHeadY() + delta > this.y) return true;
		for (SegmentSnake segment: snake.getBody()) {
			if (segment.getX() - delta < this.x && segment.getX() + delta > this.x && 
				segment.getY() - delta < this.y && segment.getY() + delta > this.y) return true;
		}
		return false;
	}
	
	public boolean isWasEaten(Snake snake) {
		return this.x - 32 < snake.getHeadX() && this.x + 32 > snake.getHeadX() && 
				this.y - 32 < snake.getHeadY() && this.y + 32 > snake.getHeadY();  
	}

	public boolean isInFood(Food food) {
		return food.getX() - 32 < this.x && food.getX() + 32 > this.x && food.getY() - 32 < this.y && food.getY() + 32 > this.y; 
	}
	
	public void setEdibility(boolean value) {
		this.isEaten = value;
	}
	
	public boolean isEatenFood() {
		return this.isEaten;
	}
	
	public void setEatenFood(boolean value) {
		this.isEaten = value;
	}
}