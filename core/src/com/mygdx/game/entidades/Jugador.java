package com.mygdx.game.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enums.Direcciones;
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.proyectiles.Proyectil;
import java.util.ArrayList;
import java.util.Iterator;
import com.mygdx.game.utiles.Render;

public class Jugador extends Entidad {

    private OrthographicCamera camara;
    private ArrayList<Proyectil> proyectiles = new ArrayList<>();
    
    public Jugador(Vector2 posicion, float vida, World world, OrthographicCamera camara) {
        super(Recursos.JUGADOR_SPRITESHEET, posicion, vida, world);
        this.camara = camara;
        
        // Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.posicion.x, this.posicion.y);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6, 2);  // Ajusta el tamaño del jugador

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        
        // Asignar categorías de colisión para el jugador
        fixtureDef.filter.categoryBits = 0x0004;  // Categoría del jugador
        fixtureDef.filter.maskBits = 0x0001;      // El jugador puede colisionar con el mapa (0x0001 es la categoría del mapa)

        body.createFixture(fixtureDef);
        shape.dispose();  // No olvides liberar la memoria después de usar la forma
    }

    public void actualizar() {
        // Movimiento
        float movimientoX = 0;
        float movimientoY = 0;

        if (puedeMoverse) {
            if (Gdx.input.isKeyPressed(Keys.W)) {
                movimientoY += velocidad;
                direccionActual = Direcciones.ARRIBA;
            } else if (Gdx.input.isKeyPressed(Keys.S)) {
                movimientoY -= velocidad;
                direccionActual = Direcciones.ABAJO;
            }

            if (Gdx.input.isKeyPressed(Keys.A)) {
                movimientoX -= velocidad;
                direccionActual = Direcciones.IZQUIERDA;
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                movimientoX += velocidad;
                direccionActual = Direcciones.DERECHA;
            }

            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) { // Disparo con ESPACIO
                disparar();
            }

            // Configuración de movimiento
            if (movimientoX == 0 && movimientoY == 0) {
                body.setLinearVelocity(0, 0);
                direccionActual = Direcciones.QUIETO;
            } else {
                if (movimientoX != 0 && movimientoY != 0) {
                    movimientoX *= 0.7071f;  // Ajustar diagonal
                    movimientoY *= 0.7071f;
                }
                body.setLinearVelocity(movimientoX, movimientoY);
            }

            posicion.set(body.getPosition().x, body.getPosition().y + 14);
            movimientoCamara();
        }

        // Actualizar proyectiles
        Iterator<Proyectil> iter = proyectiles.iterator();
        while (iter.hasNext()) {
            Proyectil proyectil = iter.next();
            proyectil.actualizar();
            if (proyectil.estaDestruido()) {
                iter.remove();
            }
        }
    }

    private void movimientoCamara() {
        if (camara != null) {
            camara.position.set(posicion.x, posicion.y, 0);
            camara.update();
        }
    }

    private void disparar() {
        Vector2 direccion = new Vector2(0, 0);

        switch (direccionActual) {
            case ARRIBA:
                direccion.set(0, 1);
                break;
            case ABAJO:
                direccion.set(0, -1);
                break;
            case IZQUIERDA:
                direccion.set(-1, 0);
                break;
            case DERECHA:
                direccion.set(1, 0);
                break;
            default:
                return;
        }

        Proyectil proyectil = new Proyectil(world, new Vector2(posicion.x, posicion.y), direccion);
        proyectiles.add(proyectil);
    }

    @Override
    public void dibujar() {
        super.dibujar();
        Render.batch.begin();
        for (Proyectil proyectil : proyectiles) {
            if (proyectil.getBody() != null) {
                // Dibujar cada proyectil en su posición
                Render.batch.draw(new Texture(Recursos.PROYECTIL), proyectil.getPosicion().x - 32, proyectil.getPosicion().y - 32, 64, 64);
            }
        }
        Render.batch.end();
    }
}
