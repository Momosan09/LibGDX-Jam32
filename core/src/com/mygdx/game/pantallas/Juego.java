package com.mygdx.game.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entidades.Enemigo;
import com.mygdx.game.entidades.Jugador;
import com.mygdx.game.entrada.EntradasJugador;
import com.mygdx.game.hud.JuegoHUD;
import com.mygdx.game.proyectiles.ManejadorColisiones;
import com.mygdx.game.utiles.ConfigJuego;
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.utiles.Render;

public class Juego implements Screen{

	
	public Juego(Game juego) {
		this.juego = juego;

	}
	
	@Override
	public void show() {
		
		
		if(ConfigJuego.ronda == 1) {
			this.set
		}
	}

	@Override
	public void render(float delta) {
	   
	    

	    
	    
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
		jHUD.dispose();
	}

}
