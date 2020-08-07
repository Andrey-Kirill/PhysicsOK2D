package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2d extends Game {
    public World world;
    Box2DDebugRenderer rend;
    OrthographicCamera camera;
    Body rect;
    MyGdxGame21 mn;
    SpriteBatch batch;
    Vector3 touchPos1;
    Vector3 touchPos = new Vector3();
    Box2d(MyGdxGame21 mn){
        this.mn = mn;
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
     world = new World(new Vector2(0,-20),true);
     world.setContactListener(new MyContactListener());
     camera = new OrthographicCamera(800,480);
     camera.position.set(new Vector2(0,0),0);
     rend = new Box2DDebugRenderer();
     touchPos1 = new Vector3();
     createrect();
    }
    public int velocity_x = 0;
    public int velocity_y = 0;
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        rend.render(world, camera.combined);
        world.step(1 / 30F, 6, 6);
        //верх лево

        if(velocity_x > 20)
            velocity_x = 20;
        if(velocity_y > 20)
            velocity_y = 20;
        if(velocity_x < -20)
            velocity_x = -20;
        if(velocity_y < -20)
            velocity_y = -20;

        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if ((touchPos.x >= -400 && touchPos.x <= 0)&& (touchPos.y>=-240 && touchPos.y<=0)) {

                float y = rect.getLinearVelocity().y;
                velocity_x= velocity_x-10;
                velocity_y= velocity_y-10;
                rect.setLinearVelocity(new Vector2(velocity_x, velocity_y));
                 float x = rect.getLinearVelocity().x;
                /*
                int velocity_x = -40;
                int velocity_y = -20;
                rect.setLinearVelocity(new Vector2(velocity_x,velocity_y));
                 */
            }
        }
        //низ лево
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if ((touchPos.x >= -400 && touchPos.x <= 0)&&(touchPos.y >= 0 && touchPos.y <= 240)){

                float x = rect.getLinearVelocity().x;
                velocity_x= velocity_x-10;
                velocity_y= velocity_y+10;
                rect.setLinearVelocity(new Vector2(x, velocity_y));
                float y = rect.getLinearVelocity().y;
                rect.setLinearVelocity(new Vector2(velocity_x, y));
                /*
                int velocity_x = -40;
                int velocity_y = 20;
                rect.setLinearVelocity(new Vector2(velocity_x,velocity_y));
                 */
            }
        }
        //низ право
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if ((touchPos.x >= 0 && touchPos.x <= 400)&&(touchPos.y >= -240 && touchPos.y <= 0)) {

                float x = rect.getLinearVelocity().x;
                velocity_x= velocity_x+10;
                velocity_y= velocity_y-10;
                rect.setLinearVelocity(new Vector2(x, velocity_y));
                float y = rect.getLinearVelocity().y;
                rect.setLinearVelocity(new Vector2(velocity_x, y));
                //rect.applyForceToCenter(new Vector2(100, 0), true);

                /*
                int velocity_x = 40;
                int velocity_y = -20;
                rect.setLinearVelocity(new Vector2(velocity_x,velocity_y));
                 */
            }
        }
        // верх право
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if ((touchPos.x >= 0 && touchPos.x <= 400)&&(touchPos.y >= 0 && touchPos.y <= 240)){

                float x = rect.getLinearVelocity().x;
                velocity_x= velocity_x+10;
                velocity_y= velocity_y+10;
                rect.setLinearVelocity(new Vector2(x, velocity_y));
                float y = rect.getLinearVelocity().y;
                rect.setLinearVelocity(new Vector2(velocity_x, y));
                //rect.applyForceToCenter(new Vector2(100, 0), true);

                /*
                int velocity_x = 40;
                int velocity_y = 20;
                rect.setLinearVelocity(new Vector2(velocity_x,velocity_y));
                 */
            }

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
    public void createrect(){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(0,0);
        //bDef.fixedRotation = true;
        rect = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50,50);
        fDef.shape = shape;
        fDef.density = 100;
        fDef.friction = 0.5F;
        fDef.restitution = 0.5F;
        rect.createFixture(fDef);
        createWall();
        createWall1();
        createWall2();
        createCircle();
    }
    public void createWall(){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(0,0);
        Body w = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(-400,-240),new Vector2(400,-240),new Vector2(400,240),new Vector2(-400,240),new Vector2(-400,-240)});
        fDef.shape = shape;
        fDef.friction = 0.5F;
        w.createFixture(fDef);
    }
    public void createWall1(){

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.KinematicBody;
        bDef.position.set(0,0);
        Body w = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(-100,-100),new Vector2(-100,-90),new Vector2(0,-90),new Vector2(0,-100),new Vector2(-100,-100)});
        fDef.shape = shape;
        fDef.friction = 0.5F;
        fDef.density = 100;
        w.createFixture(fDef);
    }
    public void createWall2(){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(0,0);
        Body w = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(200,100),new Vector2(200,110),new Vector2(350,110),new Vector2(350,100),new Vector2(200,100)});
        fDef.shape = shape;
        fDef.friction = 0.5F;
        w.createFixture(fDef);
    }

    public void createCircle(){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(100,0);
        Body w = world.createBody(bDef);
        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(30);
        fDef.shape = shape;
        fDef.friction = 0.5F;
        w.createFixture(fDef);
    }


}