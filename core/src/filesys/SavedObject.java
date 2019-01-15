package filesys;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import field.Director;
import field.GameField;
import gameobjects.Border;
import gameobjects.Modifiable;
import input.KeyInput;
import other.Food;
import snake.Snake;

public class SavedObject implements Serializable {
	private Snake snake;
	private Food trueFood;
	private Food falseFood;
	private boolean isEatenFalseFood;
	private int count;
	
	
	public SavedObject(Snake snake, Food trueFood, Food falseFood,
			int count, boolean isEatenFalseFood) {
		this.snake = snake;
		this.trueFood = trueFood;
		this.falseFood = falseFood;
		this.count = count;
		this.isEatenFalseFood = isEatenFalseFood;
	}
	
	public Snake getSnake() {
		return snake;
	}
	
	public Food getTrueFood() {
		return trueFood;
	}
	
	public Food getFalseFood() {
		return falseFood;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean getIsEatenFalseFood() throws Exception{
		return this.isEatenFalseFood;
	}
	
}
