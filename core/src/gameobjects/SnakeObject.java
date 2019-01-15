package gameobjects;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import snake.Direction;
import snake.SegmentSnake;
import snake.Snake;

public class SnakeObject  implements Drawable, Serializable {
	private Snake snake;
	private Texture[] textureRight;
	private Texture[] textureLeft;
	private float width;
	private float height;
	private int item = 0;
	private final int countTexture = 7;
	
	public SnakeObject(Snake snake, Texture[] textureRight, Texture[] textureLeft, float width, float height) {
		this.snake = snake;
		this.textureRight = textureRight;
		this.textureLeft = textureLeft;
		this.width = width;
		this.height = height;
	}
	
	public Snake getSnake() {
		return this.snake;
	}
	
	public void draw(SpriteBatch batch) {
		Texture temp;
		if (snake.getDirection() == Direction.Left || snake.getDirection() == Direction.Up) {
			temp = textureLeft[item/4];
		}
		else {
			temp = textureRight[item/4];
		}
		batch.draw(temp, snake.getX(), snake.getY(), this.width, this.height);
		ArrayList<SegmentSnake> body = snake.getBody();
		for (int i = 0; i < body.size(); i++) {
			SegmentSnake segment = body.get(i);
			if (segment.getDirection() == Direction.Left || segment.getDirection() == Direction.Up) {
				temp = textureLeft[item/4];
			}
			else {
				temp = textureRight[item/4];
			}
			batch.draw(temp, segment.getX(), segment.getY(), this.width, this.height);
		}
		item++;
		if (item > countTexture*4) {
			item = 0;
		}
	}
	
	public void dispose() {
		for (Texture right: textureRight) {
			right.dispose();
		}
//		textureRight.dispose();
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}

	
	public boolean isInItself() {
		float headX = this.snake.getHeadX();
		float headY = this.snake.getHeadY();
		for (SegmentSnake segment: this.snake.getBody()) {
			if (segment.getX() < headX + width/4 && segment.getX() > headX - width/4 &&
					segment.getY() < headY + width/4 && segment.getY() > headY - width/4) {
				return true;
			}
		}
		return false;
	}
}
