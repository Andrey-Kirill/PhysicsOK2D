package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame21 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Box2d bx2d;
	OrthographicCamera camera;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		bx2d = new Box2d(this);
		bx2d.create();
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		bx2d.render();
		batch.begin();
		//batch.draw(img, bx2d.rect.getPosition().x+350, bx2d.rect.getPosition().y+190);
		batch.end();


	}


}
