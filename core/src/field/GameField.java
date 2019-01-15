package field;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import filesys.SavedObject;
import gameobjects.*;
import input.KeyInput;
import other.Food;
import snake.CommandEat;
import snake.Direction;
import snake.EatFalseFoodCommand;
import snake.EatTrueFoodCommand;
import snake.SegmentSnake;
import snake.Snake;

public class GameField {
	private ArrayList<DisplayObject> objects;
	private SnakeObject snake;
	private Food food;
	private Food falseFood;
	private Border bord;
	private Director director;
	private int count = 0;
	private KeyInput keyinput;
	private CommandEat eat;
	
	public GameField(float width, float height, SnakeObject snake, DisplayObject ... objs) {
		this.bord = new Border(new Texture(Gdx.files.internal("black.jpg")), width, height);
		this.snake = snake;
		this.keyinput = new KeyInput();
		this.director = new Director(this.bord, this.keyinput);
		objects = new ArrayList<DisplayObject>();
		objects.add(bord);
		for(DisplayObject obj : objs) {
			objects.add(obj);
			if (obj instanceof Modifiable) {
				director.addModifiableObject((Modifiable)obj);
			}
		}
		this.eat = new EatTrueFoodCommand(snake.getSnake());
		food = new Food(true);
		falseFood = new Food(false);
	}
	
	public void upgradeGame() {
		director.setRandomObjectModification();
	}
	
	public void setFoodObject(Texture texture, float width, float height) {
		generFood(food);
		falseFood.setEatenFood(false);
		objects.add(new FoodObject(food, texture, width, height));
		objects.add(new FoodObject(falseFood, texture, width, height));
	}
	
	public void addDisplayObject(GameObject object, Texture texture, float width, float height) {
		objects.add(new DisplayObject(object, texture, width, height));
	}
	
	public void drawAllObjects(SpriteBatch batch) {
		snake.draw(batch);
		for(DisplayObject object : objects) {
			object.draw(batch);
		}
	}
	
	public void updateAllObject() {
		this.snake.getSnake().move();
		Direction tempDirect = keyinput.getDirection();
		if (tempDirect != null) {
			this.snake.getSnake().setDirection(tempDirect);
		}
		if (this.isAteFood(food) || (this.isAteFood(falseFood) && falseFood.isEatenFood())) {
			
			count++;
			if (count % 3 == 0) {
				setFalseFood(true);
				generFood(falseFood);
				generFood(falseFood);
				while (falseFood.isInFood(food)) {
					generFood(falseFood);
				}
			}
			if ((count % 3) - 1 == 0) {
				setFalseFood(false);
			}
			if (this.isAteFood(food)) {
				this.eat = new EatTrueFoodCommand(snake.getSnake());
			}
			if (this.isAteFood(falseFood)){
				this.eat = new EatFalseFoodCommand(snake.getSnake());
			}
			this.eat.excecut();
			this.eat = new EatTrueFoodCommand(snake.getSnake());
			if (count % 10 == 0 && count % 20 != 0) {
				director.runObjectModification();
			}
			if (count % 20 == 0) {
				director.resetAllModification();
				director.setRandomObjectModification();
			}
			this.generFood(food);
		}
		
	}
	
	public boolean isSnakeOutside() {
		return bord.isWithinField(snake.getSnake());
	}
	
	public void generFood(Food food) {
		Random rnX = new Random();
		Random rnY = new Random();
		float foodX = this.bord.getLeftBord() + rnX.nextInt((int)this.bord.getRghtBord () - 32 - (int)this.bord.getLeftBord());
		float foodY = this.bord.getDownBoard() + rnY.nextInt((int)this.bord.getTopBoard() - 32 - (int)this.bord.getDownBoard());
		while (isEmptyPlace(foodX, foodY)) {
			foodX = this.bord.getLeftBord() + rnX.nextInt((int)this.bord.getRghtBord () - 32 - (int)this.bord.getLeftBord());
			foodY = this.bord.getDownBoard() + rnY.nextInt((int)this.bord.getTopBoard() - 32 - (int)this.bord.getDownBoard());
		}
		food.setXY(foodX, foodY);
	}
	
	public void setWidth(float width) {
		this.bord.setWidth(width);
	}
	
	public void setsHeight(float height) {
		this.bord.setHeight(height);
	}
	
	public void setState(SavedObject obj) {
		this.food.setXY(obj.getTrueFood().getX(), obj.getTrueFood().getY());
		this.falseFood.setXY(obj.getFalseFood().getX(), obj.getFalseFood().getY());
		this.count = obj.getCount();
		try {
			setFalseFood(obj.getIsEatenFalseFood());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDisplayObject(DisplayObject obj) {
		this.objects.add(obj);
	}
	
	public void reset() {
		this.snake.getSnake().reset(1,1);
		this.director.resetAllModification();
		this.count = 2;
		generFood(food);
	}
	
	public void dispose() {
		snake.dispose();
		for (DisplayObject obj: objects) {
			obj.dispose();
		}
	}
	
	public String getScore() {
		int res = snake.getSnake().getBody().size() * 10;
		return String.valueOf(res);
	}
	
	public String getNextModificationName() {
		return this.director.getInfoModification();
	}
	
	public int getTimeToModification() {
		return 10 - count %10;
	}
		
	public boolean isGameOver() {
		return this.snake.isInItself() || isSnakeOutside();
	}
	
	public boolean isModified() {
		return director.isInstalledModification();
	}
	
	public Border getBord() {
		return this.bord;
	}
	
	public SnakeObject getSnakeObject() {
		return this.snake;
	}
	
	public SavedObject getObjectForSave() {
		return new SavedObject(snake.getSnake(), food, falseFood, count, falseFood.isEatenFood());
	}
	
	public Food getTrueFood() {
		return food;
	}
	
	public Food getFalseFood() {
		return falseFood;
	}
	
	public int getCount() {
		return count;
	}
	
	public Director getDirector() {
		return director;
	}
	
	public KeyInput getInput() {
		return keyinput;
	}
	
	private boolean isEmptyPlace(float xFood, float yFood) {
		if (this.snake.getSnake().getX() > xFood - 32 && this.snake.getSnake().getX() < xFood + 32 && 
				this.snake.getSnake().getY() > yFood - 32 && this.snake.getSnake().getY() < yFood + 32) {
			return true;
		}
		ArrayList<SegmentSnake> body = this.snake.getSnake().getBody();
		for (SegmentSnake segment: body) {
			if (segment.getX() > xFood - 32 && segment.getX() < xFood + 32 && 
					segment.getY() > yFood - 32 && segment.getY() < yFood + 32) {
				return true;
			}
		}
		return false;
	}

	private boolean isAteFood(Food food) {
		Snake snake = this.snake.getSnake();
		boolean temp1 = snake.getX() > food.getX() -24 && snake.getX() < food.getX() + 24;
		boolean temp2 = snake.getY() > food.getY() -24 && snake.getY() < food.getY() + 24;
		return temp1 && temp2;
	}
	
	private void setFalseFood(boolean value) {
		for (DisplayObject obj: objects) {
			if (obj instanceof FoodObject) {
				FoodObject temp = (FoodObject) obj;
				if (temp.getFood() == falseFood) {
					temp.setDisplay(value);
					temp.getFood().setEatenFood(value);
				}
			}
		}
	}
	
}
