package com.mygdx.game.entrada;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.entidades.Entidad;
import com.mygdx.game.entidades.Jugador;
import com.mygdx.game.enums.Direcciones;

public class EntradasJugador implements InputProcessor{

	private Jugador j;
	
	public EntradasJugador(Jugador j) {
		this.j = j;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.A) {
			j.movimiento(Direcciones.IZQUIERDA);
		}else if(keycode == Keys.D) {
			j.movimiento(Direcciones.DERECHA);
		}
		
		if(keycode == Keys.W) {
			j.movimiento(Direcciones.ARRIBA);
		}else if(keycode == Keys.S) {
			j.movimiento(Direcciones.ABAJO);
	
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		j.movimiento(Direcciones.QUIETO);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}
