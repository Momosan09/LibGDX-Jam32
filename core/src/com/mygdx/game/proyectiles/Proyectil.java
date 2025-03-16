package com.mygdx.game.proyectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entidades.Enemigo;

public class Proyectil {
    private World world;
    private Body body;
    private boolean destruido = false;
    private float tiempoVida = 3f;
    private float daño = 1000f; // Daño que hace el proyectil

    public Proyectil(World world, Vector2 posicion, Vector2 direccion) {
        this.world = world;

        // Crear el cuerpo del proyectil
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.x = posicion.x+40;
        bodyDef.position.y = posicion.y+32;
        bodyDef.bullet = true; 

        body = world.createBody(bodyDef);

        // Crear la forma del proyectil
        CircleShape shape = new CircleShape();
        shape.setRadius(32); // Ajusta el tamaño del proyectil

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        fixtureDef.filter.categoryBits = 0x0002; 
        fixtureDef.filter.maskBits = 0x0004; 

        body.createFixture(fixtureDef);
        shape.dispose();

        // Establecer velocidad
        body.setLinearVelocity(direccion.nor().scl(500f));
        body.setUserData(this);

        // Agregar listener de colisión
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Object a = contact.getFixtureA().getBody().getUserData();
                Object b = contact.getFixtureB().getBody().getUserData();

                if (a instanceof Proyectil && b instanceof Enemigo) {
                    ((Enemigo) b).recibirDaño(daño);
                    ((Proyectil) a).destruir();
                } else if (b instanceof Proyectil && a instanceof Enemigo) {
                    ((Enemigo) a).recibirDaño(daño);
                    ((Proyectil) b).destruir();
                }
            }

            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        });
    }

    private boolean pendienteDestruir = false;

    public void destruir() {
        pendienteDestruir = true;
    }

    public void actualizar() {
        if (pendienteDestruir && body != null) {
            world.destroyBody(body);
            body = null;
        }
    }


    public boolean estaDestruido() {
        return destruido;
    }

    public Vector2 getPosicion() {
        return body.getPosition();
    }
    
    public Body getBody() {
    	return body;
    }
}
