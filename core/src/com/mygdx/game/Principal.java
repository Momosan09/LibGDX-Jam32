package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entidades.Enemigo;
import com.mygdx.game.entidades.Jugador;
import com.mygdx.game.entrada.EntradasJugador;
import com.mygdx.game.hud.JuegoHUD;
import com.mygdx.game.pantallas.Juego;
import com.mygdx.game.pantallas.Menu;
import com.mygdx.game.pantallas.TuttiFrutti.Oleada1;
import com.mygdx.game.pantallas.TuttiFrutti.Oleada2;
import com.mygdx.game.proyectiles.ManejadorColisiones;
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.utiles.Render;

public class Principal extends Game {

	
	@Override
	public void create () {
		
		
		this.setScreen(new Juego(this));

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		super.render();

		
	}
	
	@Override
	public void dispose () {

	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

	}
	

}
