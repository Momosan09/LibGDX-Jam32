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
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.utiles.Render;

public class Juego implements Screen {

	private Game g;
	
	private Jugador j;
	private EntradasJugador entradasJ;
	private JuegoHUD jHUD;

	// Box2d
	private World world;
	private Box2DDebugRenderer box2Debug;

	// Camaras
	private OrthographicCamera camaraJugador, camaraHud;

	
	public Juego(Game g) {
		this.g = g;
	}
	
	@Override
	public void show() {

		Gdx.input.setInputProcessor(Recursos.muxJuego);

		this.world = new World(new Vector2(0, 0), false);
		world.setContactListener(new ManejadorColisiones());

		// camaras
		camaraJugador = new OrthographicCamera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		camaraJugador.setToOrtho(false);
		camaraJugador.zoom = .4f;

		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		camaraHud.setToOrtho(false);
		camaraHud.zoom = .4f;

		jHUD = new JuegoHUD();

		j = new Jugador(new Vector2(300, 300), 100, world, camaraJugador);
		entradasJ = new EntradasJugador(j);

		this.box2Debug = new Box2DDebugRenderer();

		Recursos.muxJuego.addProcessor(entradasJ);
		
	}

	@Override
	public void render(float delta) {
		dibujarBasico();
	}

	@Override
	public void resize(int width, int height) {
		camaraJugador.viewportWidth = width;
		camaraJugador.viewportHeight = height;
		camaraJugador.update();

		jHUD.resize(width, height);
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

	public void dibujarBasico() {
		world.step(Gdx.graphics.getDeltaTime(), 6, 2); // Actualiza la física
		box2Debug.render(world, camaraJugador.combined);
		camaraHud.update();
		Render.batch.setProjectionMatrix(camaraHud.combined);
		jHUD.render();

		camaraJugador.update();
		Render.batch.setProjectionMatrix(camaraJugador.combined);
		j.actualizar(); // Ahora el jugador se mueve en cada frame
		j.dibujar();

		Enemigo.destruirCuerposPendientes();
	}

}
