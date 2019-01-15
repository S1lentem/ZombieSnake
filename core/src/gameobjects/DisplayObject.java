package gameobjects;
import java.io.Serializable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class DisplayObject implements Drawable, Serializable {
	protected GameObject obj;
	protected Texture texture;
	protected float width;
	protected float height;
	protected boolean isDraw = true;
	
	public DisplayObject(GameObject obj, Texture texture, float width, float heigth) {
		this.obj = obj;
		this.texture = texture;
		this.width = width;
		this.height = heigth;
	}
	
	public void draw(SpriteBatch batch) {
		if (isDraw) {
			batch.draw(texture, obj.getX(), obj.getY(), this.width, this.height);
		}
	}
	
	public GameObject getGameObject() {
		return obj;
	}
	
	public void dispose() {
		texture.dispose();
	}
}
