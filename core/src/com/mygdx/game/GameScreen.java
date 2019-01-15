package com.mygdx.game;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import snake.SegmentSnake;
import snake.Snake;
import gameobjects.*;
import field.*;
import filesys.SavedObject;
import filesys.SerializationGame;

public class GameScreen implements Screen {
	private OrthographicCamera camera;
	private BitmapFont font, shadow;
	private SpriteBatch batch;
	private GameField field;
	private boolean isGameOver;
	private boolean paused;
	private SnakeGame game;
	private Texture background;
	private Texture[] snakeTexturesRight;
	private Texture[] snakeTexturesLeft;

	private void init() {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		isGameOver = false;
		font = new BitmapFont(Gdx.files.internal("text.fnt"));
		shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
		font.getData().setScale(0.4f);
		shadow.getData().setScale(0.4f);
		background = new Texture(Gdx.files.internal("background.jpg"));
		snakeTexturesRight = new Texture[] {
				new Texture(Gdx.files.internal("z1.png")),
				new Texture(Gdx.files.internal("z2.png")),
				new Texture(Gdx.files.internal("z3.png")),
				new Texture(Gdx.files.internal("z4.png")),
				new Texture(Gdx.files.internal("z5.png")),
				new Texture(Gdx.files.internal("z6.png")),
				new Texture(Gdx.files.internal("z7.png")),
				new Texture(Gdx.files.internal("z8.png")),
		};
		snakeTexturesLeft = new Texture[] {
				new Texture(Gdx.files.internal("r1.png")),
				new Texture(Gdx.files.internal("r2.png")),
				new Texture(Gdx.files.internal("r3.png")),
				new Texture(Gdx.files.internal("r4.png")),
				new Texture(Gdx.files.internal("r5.png")),
				new Texture(Gdx.files.internal("r6.png")),
				new Texture(Gdx.files.internal("r7.png")),
				new Texture(Gdx.files.internal("r8.png")),
		};
	}
	
	public GameScreen (final SnakeGame snakeGame) {
		this.game = snakeGame;
		init();
		Snake sn = new Snake(new SegmentSnake(1,1), 24);
		Texture tx = new Texture(Gdx.files.internal("z1.png"));
		field = new GameField(800, 480, new SnakeObject(sn, snakeTexturesRight, snakeTexturesLeft, 32, 32));
		field.setFoodObject(new Texture(Gdx.files.internal("Banana.png")) , 32, 32);
	}
	
	public GameScreen(final SnakeGame snakeGame, SavedObject obj) {
		this.game = snakeGame;
		init();
		Snake sn = obj.getSnake();
		Texture tx = new Texture(Gdx.files.internal("Zombie.png"));
		field = new GameField(800, 480, new SnakeObject(sn, snakeTexturesRight, snakeTexturesLeft, 32, 32));
		field.setFoodObject(new Texture(Gdx.files.internal("Banana.png")) , 32, 32);
		field.setState(obj);
		paused = true;
		
	}

	@Override
	public void render (float delta){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, 0, 0, 800,480);
		if (!isGameOver) {
			shadow.draw(batch, "Score: " + field.getScore(), field.getBord().getLeftBord(), field.getBord().getTopBoard());
			font.draw(batch, "Score: " + field.getScore(), field.getBord().getLeftBord(), field.getBord().getTopBoard());
			
			if (!field.isModified()) {
				shadow.draw(batch, field.getNextModificationName(), 
						field.getBord().getLeftBord() + 10, field.getBord().getDownBoard() + 50);
				font.draw(batch, field.getNextModificationName(), 
						field.getBord().getLeftBord() + 10, field.getBord().getDownBoard() + 50);
			}
			else {
				shadow.draw(batch, "Reset modification", 
						field.getBord().getLeftBord() + 10, field.getBord().getDownBoard() + 50);
				font.draw(batch, "Reset modification", 
						field.getBord().getLeftBord() + 10, field.getBord().getDownBoard() + 50);
			}
			
			shadow.draw(batch, "After " + field.getTimeToModification(), 
					field.getBord().getLeftBord() + 10, field.getBord().getDownBoard() + 25);
			font.draw(batch, "After " + field.getTimeToModification(), 
					field.getBord().getLeftBord() + 10, field.getBord().getDownBoard() + 25);
			
			field.drawAllObjects(batch);
			isGameOver = field.isGameOver();
			if (paused) {
				shadow.draw(batch, "PAUSED", 350, 240);
				font.draw(batch, "PAUSED", 350, 240);
				
				shadow.draw(batch, "Press 'S' to save the game", 190, 200);
				font.draw(batch, "Press 'S' to save the game", 190, 200);
			}
		}
		else {
			this.game.setScreen(new GameOverScreen(game, field.getScore())); 
			dispose();
			return;
		}
		batch.end();
		try {
			if (paused) {
				if (Gdx.input.isKeyPressed(Input.Keys.S)) {
					System.out.println(field.getTrueFood().getX() + " " +  field.getTrueFood().getY());
					SerializationGame save = new SerializationGame("res", field.getObjectForSave());
					save.serialization();
					JOptionPane.showMessageDialog(null, "The game was successfully saved. ", "Save", JOptionPane.INFORMATION_MESSAGE);
					Thread.sleep(250);		
				}
				if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
					paused = false;
					Thread.sleep(250);
				}
				if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
					this.game.setScreen(new MainMenuScreen(game)); 
					dispose();
					return;
				}
			}
			else {
				field.updateAllObject();
				if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
					paused = true;
					Thread.sleep(500);
				}
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		field.dispose();
		font.dispose();
		shadow.dispose();
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
