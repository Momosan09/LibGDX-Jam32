package com.mygdx.game.entidades;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.utiles.Recursos;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.physics.box2d.Body;

public class Enemigo extends Entidad {

    // Lista para registrar cuerpos a destruir
    private static List<Body> cuerposParaDestruir = new ArrayList<>();	
	
    public Enemigo(Vector2 posicion, float vida, World world) {
        super(Recursos.ENEMIGO_SPRITESHEET, posicion, vida, world);
        
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(this.posicion.x,this.posicion.y+16);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16,32);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = 0x0004; // Categoría del enemigo
        fixtureDef.filter.maskBits = 0x0002; // Puede colisionar con proyectiles
        body.createFixture(fixtureDef);
        shape.dispose();
		


		
		body.setUserData(this); // Para identificar a los enemigos

    }

    @Override
    public void recibirDaño(float daño) {
        super.recibirDaño(daño);
        System.out.println("Enemigo recibió daño. Vida restante: " + vida);
    }

    @Override
    public void morir() {
        if (world != null && body != null) {
            System.out.println("Enemigo eliminado"); // Verifica que se llama
            estaMuerto = true;
            cuerposParaDestruir.add(body);
            body = null; // Evitar referencias a un body destruido
        }
    }

    // Método estático para destruir cuerpos pendientes
    public static void destruirCuerposPendientes() {
        for (Body body : cuerposParaDestruir) {
            if (body != null && body.getWorld() != null) {
                body.getWorld().destroyBody(body); // Destruir el cuerpo en el mundo
            }
        }
        cuerposParaDestruir.clear(); // Limpiar la lista después de destruir
    }
    
}
