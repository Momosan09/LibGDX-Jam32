package com.mygdx.game.pantallas.TuttiFrutti;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.pantallas.Mapa;
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.utiles.Render;
import com.mygdx.game.utiles.Tiempo;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public abstract class MapaTuttiFrutti extends Mapa implements Screen{

	private Game g;

	
	public MapaTuttiFrutti(Game g, World w) {
		super(w);
		this.g = g;
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Render.batch.begin();
		mapa.draw(Render.batch);
		Render.batch.end();
		dibujarEnemigos();
		Tiempo.contarSegundos();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}
	
	public void dibujarEnemigos() {
		
	}

}
