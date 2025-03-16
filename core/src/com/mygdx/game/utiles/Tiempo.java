package com.mygdx.game.utiles;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;

public class Tiempo {

    private long tiempoInicio;
    private boolean contadorActivo;

    // Constructor que inicializa el contador
    public Tiempo() {
        this.contadorActivo = false;
    }

    // Método para iniciar el conteo
    public void iniciarContador() {
        tiempoInicio = TimeUtils.nanoTime(); // Guardamos el tiempo de inicio en nanosegundos
        contadorActivo = true;
    }

    // Método para obtener los segundos transcurridos
    public long obtenerSegundos() {
        if (!contadorActivo) {
            throw new IllegalStateException("El contador no ha sido iniciado.");
        }
        
        // Calcular el tiempo transcurrido en segundos (convertimos de nanosegundos a segundos)
        return (TimeUtils.nanoTime() - tiempoInicio) / 1000000000; // Nanosegundos a segundos
    }

    // Método para detener el contador
    public void detenerContador() {
        contadorActivo = false;
    }

    // Método para reiniciar el contador
    public void reiniciarContador() {
        tiempoInicio = TimeUtils.nanoTime();
        contadorActivo = true;
    }

    // Método para saber si el contador está activo
    public boolean estaContando() {
        return contadorActivo;
    }

    
    public static void actorEsperar(final Actor actor, int tiempo) {
	    // Añade una secuencia de acciones:
	    // - Espera 2 segundos (Actions.delay(2))
	    // - Luego oculta el label (Actions.visible(false))
    	actor.addAction(Actions.sequence(
	        Actions.delay(tiempo),        // Espera 
	        Actions.fadeOut(.5f)

	   ));
	    actor.getColor().a = 1;
    }

}