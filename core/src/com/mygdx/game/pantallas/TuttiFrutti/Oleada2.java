package com.mygdx.game.pantallas.TuttiFrutti;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entidades.Enemigo;

public class Oleada2 extends MapaTuttiFrutti implements Screen{

	private Enemigo e, e2;
	
	public Oleada2(Game g, World w) {
		super(g, w);
		e = new Enemigo(new Vector2(64,64), 100, w);
		e2 = new Enemigo(new Vector2(64*3,64), 100, w);
		
	}
	
	@Override
	public void dibujarEnemigos() {
	super.dibujarEnemigos();
	e.dibujar();
	e2.dibujar();
	}

}
