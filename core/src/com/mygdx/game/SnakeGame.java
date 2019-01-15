package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game{
	SpriteBatch batch;
	BitmapFont font;
	BitmapFont shadow;
	
	public final String pathToSave = "";
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("text.fnt"));
		shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
		font.getData().setScale(0.5f);
		shadow.getData().setScale(0.5f);
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		font.dispose();
	}
}
