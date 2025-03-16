package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.pantallas.Juego;
import com.mygdx.game.pantallas.Menu;
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.utiles.Render;

public class Principal extends Game {
	SpriteBatch batch;
	Sprite img ;
	
	@Override
	public void create () {
		this.setScreen(new Juego(this));
		img = new Sprite(new Texture(Gdx.files.internal(Recursos.badlogic)));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		Render.batch.begin();
		img.draw(Render.batch);
		Render.batch.end();
		super.render();

	}
	
	@Override
	public void dispose () {

	}
}
