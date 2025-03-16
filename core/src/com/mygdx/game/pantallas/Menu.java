package com.mygdx.game.pantallas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.utiles.Recursos;

public class Menu implements Screen {
    private Skin skin;

    private Stage stage;

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

	@Override
	public void show() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal(Recursos.skin));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.align(Align.left);
        table.setFillParent(true);

        Table table1 = new Table();

        Label label = new Label("EL NOMBRE DEL JUEGO", skin);
        table1.add(label);

        table1.row();
        TextButton textButton = new TextButton("JUGAR", skin);
        table1.add(textButton);

        table1.row();
        textButton = new TextButton("Ajustes", skin);
        table1.add(textButton);

        table1.row();
        textButton = new TextButton("Salir", skin);
        table1.add(textButton);
        table.add(table1).align(Align.left);
        stage.addActor(table);
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}
}
