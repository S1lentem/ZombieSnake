package gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import other.Food;

public class FoodObject extends DisplayObject {
	
	private Food food;
	
	public FoodObject(Food obj, Texture texture, float width, float heigth) {
		super(obj, texture, width, heigth);
		this.food = (Food)obj;
		this.isDraw = food.isEatenFood();
	}
	
	public Food getFood() {
		return this.food;
	}
	
	public void setDisplay(boolean value) {
		this.isDraw = value;
	}
}
