package gameobjects;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import snake.Snake;

public class Border extends DisplayObject implements Modifiable, Serializable {
	private float deltaX;
	private float deltaY;
	
	public Border(Texture texture, float width, float height) {
		super(new GameObject(){ 
			public float getX() {
				return 0;
			}
			public float getY() {
				return 0;
			}
		}, texture, width, height);
		this.deltaX = this.deltaY = 0;
	}

	public void setModification() {
		this.deltaX = this.width/8;
		this.deltaY = this.height/8;
	}

	public void resetModification() {
		this.deltaX = this.deltaY = 0;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getLeftBord() {
		return this.obj.getX() + this.deltaX;
	}
	
	public float getRghtBord() {
		return this.width - this.deltaX;
	}
	
	public float getTopBoard() {
		return this.height - this.deltaY;
	}
	
	public float getDownBoard() {
		return this.obj.getY() + this.deltaY;
	}
	
	public boolean isWithinField(Snake snake) {
		float headX = snake.getX();
		float headY = snake.getY();
		float leftBord = this.obj.getX() + this.deltaX;
		float rightBord = this.width - this.deltaX;
		float upBord = this.height - this.deltaY;
		float downBord = this.obj.getY() + this.deltaY;
		return (headX < leftBord || headY < downBord || headX > rightBord - 32 || headY > upBord - 32);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(this.texture, this.obj.getX(), this.obj.getY(), this.width, deltaY);
		batch.draw(this.texture, this.obj.getX(), this.obj.getY(), deltaX, this.height);
		batch.draw(this.texture, this.obj.getX(), this.height, this.width, -deltaY);
		batch.draw(this.texture, this.width, this.obj.getY(), -deltaX, this.height);
	}

	@Override
	public String getDescriptionModification() {
		return "resize field";
	}
}
