package com.mygdx.game.proyectiles;


import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entidades.Entidad;

public class ManejadorColisiones implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
	    System.out.println("Colisión detectada");

	    Fixture fixtureA = contact.getFixtureA();
	    Fixture fixtureB = contact.getFixtureB();

	    if (esProyectilContraEnemigo(fixtureA, fixtureB)) {
	        System.out.println("Proyectil impactó a un enemigo");

	        Fixture fixtureProyectil = esProyectil(fixtureA) ? fixtureA : fixtureB;
	        Fixture fixtureEnemigo = (fixtureProyectil == fixtureA) ? fixtureB : fixtureA;

	        Proyectil proyectil = (Proyectil) fixtureProyectil.getBody().getUserData();
	        Entidad enemigo = (Entidad) fixtureEnemigo.getBody().getUserData();

	        enemigo.recibirDaño(10);
	        proyectil.destruir();  // Ahora solo marca el proyectil para destruir
	    }
	}

    @Override
    public void endContact(Contact contact) {
        // No necesitamos hacer nada cuando termina la colisión
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Se puede modificar la respuesta a la colisión aquí (opcional)
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Se puede reaccionar después de la colisión (opcional)
    }

    private boolean esProyectilContraEnemigo(Fixture a, Fixture b) {
        return (esProyectil(a) && esEnemigo(b)) || (esProyectil(b) && esEnemigo(a));
    }

    private boolean esProyectil(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof Proyectil;
    }

    private boolean esEnemigo(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof Entidad;
    }
}
