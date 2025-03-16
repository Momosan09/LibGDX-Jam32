package com.mygdx.game.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enums.Direcciones;
import com.mygdx.game.utiles.Animator;
import com.mygdx.game.utiles.Render;

public abstract class Entidad {

	protected String spritesheet;
	protected Vector2 posicion;
	protected float vida;
	protected Direcciones direccionActual = Direcciones.QUIETO;
	protected float velocidad = 1000;
	protected boolean puedeMoverse = true;
	protected Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda;
	
	protected World world;
	protected Body body;

	public Entidad(String textura, Vector2 posicion, float vida, World world) {
		this.spritesheet = textura;
		this.posicion = posicion;
		this.vida = vida;
		this.world = world;
		
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.posicion.x+16,this.posicion.y);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6,2);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
		
		crearAnimaciones();
	}
	
	public void dibujar() {
		Render.batch.begin();
		alternarSprites(direccionActual).render();
		Render.batch.end();
	}
	
	public void movimiento(Direcciones dir) {
		float delta = Gdx.graphics.getDeltaTime();
		direccionActual = dir;
		if(puedeMoverse) {
		switch (dir) {
		case ARRIBA:
			posicion.y += (velocidad*delta);
			break;
			
		case ABAJO:
			posicion.y -= (velocidad*delta);
			break;
			
		case IZQUIERDA:
			posicion.x -= (velocidad*delta);
			break;
			
		case DERECHA:
			posicion.x += (velocidad*delta);
			break;

		default:
			break;
		}

	}
	}
	
	private void crearAnimaciones() {
		animacionAbajo = new Animator(spritesheet, posicion, 0, 6);
		animacionArriba = new Animator(spritesheet, posicion, 1, 6);
		animacionIzquierda = new Animator(spritesheet, posicion, 2, 6);
		animacionDerecha = new Animator(spritesheet, posicion, 3, 6);
		animacionQuieto = new Animator(spritesheet, posicion, 4, 6);
		
		animacionAbajo.create();
		animacionArriba.create();
		animacionIzquierda.create();
		animacionDerecha.create();
		animacionQuieto.create();
	}
	
	private void resetearAnimaciones(Animator ... animaciones) {	//varargs, ya que nose cuantas animaciones voy a usar
	    for (Animator animacion : animaciones) { // for each: tipo del elemento, nombre del elemento, coleccion a recorrer;
	        animacion.reset();
	    }

	}
	
	protected Animator alternarSprites(Direcciones direccion) {
		switch (direccion) {
		case ABAJO:
			return animacionAbajo;
		case ARRIBA:
			return animacionArriba;
		case IZQUIERDA:
			return animacionIzquierda;
		case DERECHA:
			return animacionDerecha;
		case QUIETO:
			return animacionQuieto;
		}
		return null;
	}
}
