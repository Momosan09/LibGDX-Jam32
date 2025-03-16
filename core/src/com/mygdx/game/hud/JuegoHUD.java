package com.mygdx.game.hud;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.utiles.Recursos;

public class JuegoHUD extends ApplicationAdapter {
    private Skin skin;

    private Stage stage;

    public JuegoHUD() {
    	create();
    }
    
    public void create() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal(Recursos.skin));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();

        Label label = new Label("VIDA", skin, "titulo");
        table1.add(label).pad(10.0f).align(Align.top);

        Table table2 = new Table();

        label = new Label("Tiempo", skin, "titulo");
        table2.add(label).padTop(10.0f).padBottom(10.0f);

        table2.row();
        table2.add().grow();

        table2.row();
        Image image = new Image(skin, "botonUp");
        table2.add(image).pad(10.0f);
        table1.add(table2).grow();

        table2 = new Table();
        table2.align(Align.top);

        label = new Label("Ronda", skin, "titulo");
        table2.add(label);

        table2.row();
        label = new Label("60", skin, "titulo");
        table2.add(label);
        table1.add(table2).pad(10.0f).align(Align.top);
        table.add(table1).grow();
        stage.addActor(table);
    }

    public void render() {

        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
