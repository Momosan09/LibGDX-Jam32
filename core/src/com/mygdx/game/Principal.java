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
	
	private Jugador j;
	private EntradasJugador entradasJ;
	private JuegoHUD jHUD;
	
	
	//Box2d
	private World world;
	private Box2DDebugRenderer box2Debug;
	
	//Camaras
	private OrthographicCamera camaraJugador, camaraHud;
	
	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(Recursos.muxJuego);
		
		this.world = new World(new Vector2(0,0), false);
		world.setContactListener(new ManejadorColisiones());

		
		//camaras
		camaraJugador = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		camaraJugador.setToOrtho(false);
		camaraJugador.zoom = .4f;
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		camaraHud.setToOrtho(false);
		camaraHud.zoom = .4f;
		
		jHUD = new JuegoHUD();
		
		j = new Jugador(new Vector2(300,300), 100, world, camaraJugador);
		entradasJ = new EntradasJugador(j);

		
		this.box2Debug = new Box2DDebugRenderer();
		
		Recursos.muxJuego.addProcessor(entradasJ);
		
		this.setScreen(new Oleada2(this, world));

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		super.render();
		dibujarBasico();
		
	}
	
	@Override
	public void dispose () {

	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		camaraJugador.viewportWidth = width;
		camaraJugador.viewportHeight = height;
		camaraJugador.update();	
		
		jHUD.resize(width, height);
	}
	
	public void dibujarBasico(){
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
