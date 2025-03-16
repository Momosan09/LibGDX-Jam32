package com.mygdx.game.pantallas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utiles.Recursos;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Mapa {
    
    protected Sprite mapa = new Sprite(new Texture(Recursos.MAPA_0));
    protected Body body;
    protected World w;
    
    public Mapa(World w) {
    	this.w = w;
    	crearColisionDerecha();
    	crearColisionIzquierda();
    	crearColisionArriba();
    	crearColisionAbajo();
    }
    
    
    private void crearColisionDerecha() {
        // Crear el cuerpo del mapa (cuerpo estático)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(-1,mapa.getHeight()/2);

        body = w.createBody(bodyDef);
        
        // Crear la forma de colisión
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2, mapa.getHeight() / 2); // Ajustar el tamaño para que coincida con el mapa

        // Crear fixture para el mapa
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = 0x0001;  // Categoría del mapa
        fixtureDef.filter.maskBits = 0x0004;      // El jugador puede colisionar con el mapa (0x0004 es la categoría del jugador)

        body.createFixture(fixtureDef);
        shape.dispose();  // No olvides liberar la memoria después de usar la forma
    }
    
    private void crearColisionIzquierda() {
        // Crear el cuerpo del mapa (cuerpo estático)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(mapa.getWidth()+1,mapa.getHeight()/2);

        body = w.createBody(bodyDef);
        
        // Crear la forma de colisión
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2, mapa.getHeight() / 2); // Ajustar el tamaño para que coincida con el mapa

        // Crear fixture para el mapa
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = 0x0001;  // Categoría del mapa
        fixtureDef.filter.maskBits = 0x0004;      // El jugador puede colisionar con el mapa (0x0004 es la categoría del jugador)

        body.createFixture(fixtureDef);
        shape.dispose();  // No olvides liberar la memoria después de usar la forma
    }
    
    private void crearColisionArriba() {
        // Crear el cuerpo del mapa (cuerpo estático)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(mapa.getWidth()/2,mapa.getHeight()+1);

        body = w.createBody(bodyDef);
        
        // Crear la forma de colisión
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(mapa.getWidth()/2, 2); // Ajustar el tamaño para que coincida con el mapa

        // Crear fixture para el mapa
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = 0x0001;  // Categoría del mapa
        fixtureDef.filter.maskBits = 0x0004;      // El jugador puede colisionar con el mapa (0x0004 es la categoría del jugador)

        body.createFixture(fixtureDef);
        shape.dispose();  // No olvides liberar la memoria después de usar la forma
    }
    
    private void crearColisionAbajo() {
        // Crear el cuerpo del mapa (cuerpo estático)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(mapa.getWidth()/2,-1);

        body = w.createBody(bodyDef);
        
        // Crear la forma de colisión
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(mapa.getWidth()/2, 2); // Ajustar el tamaño para que coincida con el mapa

        // Crear fixture para el mapa
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = 0x0001;  // Categoría del mapa
        fixtureDef.filter.maskBits = 0x0004;      // El jugador puede colisionar con el mapa (0x0004 es la categoría del jugador)

        body.createFixture(fixtureDef);
        shape.dispose();  // No olvides liberar la memoria después de usar la forma
    }
}
