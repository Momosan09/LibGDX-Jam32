package com.mygdx.game.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enums.Direcciones;
import com.mygdx.game.utiles.Recursos;
import com.mygdx.game.utiles.Render;

public class Jugador extends Entidad {

	private OrthographicCamera camara;
	
    public Jugador(Vector2 posicion, float vida, World world, OrthographicCamera camara) {
        super(Recursos.JUGADOR_SPRITESHEET, posicion, vida, world);
        this.camara = camara;
    }

    public void actualizar() {
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

            // Si no se presiona ninguna tecla, el jugador se detiene
            if (movimientoX == 0 && movimientoY == 0) {
                body.setLinearVelocity(0, 0);
                direccionActual = Direcciones.QUIETO;
            } else {
                // Ajuste para evitar velocidad mayor en diagonal
                if (movimientoX != 0 && movimientoY != 0) {
                    movimientoX *= 0.7071f;
                    movimientoY *= 0.7071f;
                }
                body.setLinearVelocity(movimientoX, movimientoY);
            }

            // Actualizar la posición del sprite con la física
            posicion.set(body.getPosition().x, body.getPosition().y + 14);
            movimientoCamara();
        }
    }
    
	private void movimientoCamara() {
		if(camara != null) {
			
            camara.position.set(posicion.x, posicion.y, 0);
            camara.update();
		}
	}

    @Override
    public void dibujar() {
        super.dibujar();
        Render.batch.begin();
        alternarSprites(direccionActual).render();
        Render.batch.end();
    }
}
