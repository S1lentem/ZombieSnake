package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import gameobjects.SnakeObject;

public class GameOverScreen implements Screen {

	private SnakeGame game;
	private String score;

	private Texture background;
	
	public GameOverScreen(SnakeGame game, String score) {
		this.game = game;
		this.score = score;
		this.background = new Texture(Gdx.files.internal("background.jpg"));
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
		
		game.shadow.draw(game.batch, "GAME OVER", 200, 300);
		game.font.draw(game.batch, "GAME OVER", 200, 300);
		
		game.shadow.draw(game.batch, "Score: " + this.score, 210, 200);
		game.font.draw(game.batch, "Score: " + this.score, 210, 200);
		
		game.shadow.draw(game.batch, "Press enter to continue", 60, 100);
		game.font.draw(game.batch, "Press enter to continue", 60, 100);
		
		game.batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(new MainMenuScreen(game));
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
