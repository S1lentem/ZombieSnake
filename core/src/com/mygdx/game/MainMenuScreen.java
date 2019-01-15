package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import filesys.SerializationGame;

public class MainMenuScreen implements Screen{

	final SnakeGame game; 	
	final float width;
	final float heigth;
	
	private Texture background;	
	
	public MainMenuScreen(SnakeGame game) {
		this.game = game;
		this.width = 800f;
		this.heigth = 480f;
		background = new Texture(Gdx.files.internal("background.jpg"));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		
		game.batch.draw(background, 0, 0, 800, 480);
		
		game.shadow.draw(game.batch, "Press 'Enter' to", 10, this.heigth - 75);
		game.font.draw(game.batch, "Press 'Enter' to", 10, this.heigth - 75);
		
		game.shadow.draw(game.batch, "Start New Game", this.width/3, this.heigth/3*2);
		game.font.draw(game.batch, "Start New Game", this.width/3, this.heigth/3*2);
		
		game.shadow.draw(game.batch, "Press 'L' to" , 10, this.heigth/2);
		game.font.draw(game.batch, "Press 'L' to", 10, this.heigth/2);
		
		game.shadow.draw(game.batch, "Load Game", this.width/2, this.heigth/3);
		game.font.draw(game.batch, "Load Game", this.width/2, this.heigth/3);
		
		game.batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.L)) {
			SerializationGame temp = new SerializationGame("res");
			try {
				temp.deserialization();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			game.setScreen(new GameScreen(game, temp.getSaveObject()));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int width, int height) {
		
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

	@Override
	public void dispose() {
		//game.dispose();
		
	}

}
