package com.mygdx.game.utiles;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Animator implements ApplicationListener {

	//https://libgdx.com/wiki/graphics/2d/2d-animation
	// Constant rows and columns of the sprite sheet
	private int columnasTotal = 5, filasTotal = 5;
	private int filaDelSpriteSheet;

	// Objects used
	private Animation<TextureRegion> animacion; // Must declare frame type (TextureRegion)
	private Texture spriteSheet;
	private String rutaSpriteSheet;
	
	private Vector2 posicion;
	
	// A variable for tracking elapsed time for the animation
	float stateTime;
	
	public Animator(String rutaSpriteSheet, Vector2 posicion, int filaDelSpriteSheet) {
		this.rutaSpriteSheet = rutaSpriteSheet;
		this.filaDelSpriteSheet = filaDelSpriteSheet;
		this.posicion = posicion;
	}
	
	public Animator(String rutaSpriteSheet, Vector2 posicion, int filaDelSpriteSheet, int filasTotal) {
		this.rutaSpriteSheet = rutaSpriteSheet;
		this.filaDelSpriteSheet = filaDelSpriteSheet;
		this.posicion = posicion;
		this.filasTotal = filasTotal;
	}

	@Override
	public void create() {
		

		spriteSheet = new Texture(rutaSpriteSheet);
		int frameWidth = spriteSheet.getWidth() / columnasTotal;
		int frameHeight = spriteSheet.getHeight() / filasTotal;


		 TextureRegion[][] tmp = TextureRegion.split(spriteSheet, frameWidth, frameHeight);


		TextureRegion[] frames = new TextureRegion[columnasTotal];

			for(int j = 0; j < columnasTotal; j++) {
				frames[j] = tmp[filaDelSpriteSheet][j];//Con filaDelSpriteSheet puedo elegir por constructor que fila es la que se va a utilizar
			}
		


		animacion = new Animation<TextureRegion>(.1f, frames);


		// time to 0
		stateTime = 0f;
	}

	@Override
	public void render() {

		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time


		TextureRegion currentFrame = animacion.getKeyFrame(stateTime, true);
		Render.batch.draw(currentFrame, posicion.x-16, posicion.y-16);
	}
	

	
	public void reset() {
		stateTime = 0;
	}

	@Override
	public void dispose() {
		spriteSheet.dispose();
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
}