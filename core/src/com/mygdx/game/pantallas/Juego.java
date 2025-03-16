package com.mygdx.game.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entidades.Jugador;
import com.mygdx.game.entrada.EntradasJugador;
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.utiles.Render;

public class Juego implements Screen{
	
	private Game juego;
	private Jugador j;
	private EntradasJugador entradasJ;
	
	
	//Box2d
	private World world;
	private Box2DDebugRenderer box2Debug;
	
	//Camaras
	private OrthographicCamera camaraJugador, camaraHud;
	
	public Juego(Game juego) {
		this.juego = juego;
		Gdx.input.setInputProcessor(Recursos.muxJuego);
	}
	
	@Override
	public void show() {
		this.world = new World(new Vector2(0,0), false);
		
		//camaras
		camaraJugador = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		camaraJugador.setToOrtho(false);
		camaraJugador.zoom = .4f;
		
		j = new Jugador(new Vector2(0,0), 100, world, camaraJugador);
		entradasJ = new EntradasJugador(j);
		
		Recursos.muxJuego.addProcessor(entradasJ);
	}

	@Override
	public void render(float delta) {
	    world.step(delta, 6, 2); // Actualiza la física
	    
		camaraJugador.update();
		Render.batch.setProjectionMatrix(camaraJugador.combined);
	    j.actualizar(); // Ahora el jugador se mueve en cada frame
	    j.dibujar();
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

}
