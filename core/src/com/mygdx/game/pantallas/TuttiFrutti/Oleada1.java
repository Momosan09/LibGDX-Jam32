package com.mygdx.game.pantallas.TuttiFrutti;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entidades.Enemigo;
import com.mygdx.game.hud.JuegoHUD;

public class Oleada1 extends MapaTuttiFrutti implements Screen{

	private Enemigo e;
	protected int tiempo = 30;
	protected int ronda = 1;

	
	public Oleada1(Game g, World w, JuegoHUD hud) {
		super(g, w, hud);
		hud.getTiempo().setText(tiempo);
		hud.getRonda().setText(ronda);
		e = new Enemigo(new Vector2(64,64), 100, w);
	}
	
	@Override
	public void dibujarEnemigos() {
	super.dibujarEnemigos();
	e.dibujar();
	actualizarContador(tiempo);
	System.out.println(tiempoRestante);
	if(tiempoRestante == 0) {
	terminarRonda();
		
	}
	}
	
	

}
