package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.LinkedList;

public class Enemy extends SpaceObject {
    private boolean left;
    private boolean right;
    private boolean up;
    private float maxSpeed;
    private float deacceleration;
    private float acceleration;
    private float radius;
    private boolean shoot;
    private LinkedList<Bullet> bullets;


    public Enemy() {
        this.x = MathUtils.random(50, 400);
        this.y = MathUtils.random(50, 400);
        this.acceleration = MathUtils.random(75,150);
        this.shapex = new float[4];
        this.shapey = new float[4];
        radians = 3.1415f / 2;
        rotationSpeed = 3;
        maxSpeed = 100;
        this.deacceleration = 20;
        this.radius = 20;
        this.shoot = false;
        this.bullets = new LinkedList<Bullet>();


    }
    private void setShape() {
    }


    public void update(float dt) {
        // set position
        x += MathUtils.random(-1f, 1f) * acceleration * dt;
        y += MathUtils.random(-1f, 1f) * acceleration * dt;

        if(MathUtils.random()>= 0.99){
            this.shoot = true;
        }
        if(this.shoot){
            float randomRadians = MathUtils.random(0f, 3.14f * 2.0f);

            this.bullets.add(new Bullet(this.x + MathUtils.cos(randomRadians)*this.radius, this.y+MathUtils.sin(randomRadians)*this.radius, randomRadians, 1) );
            if(bullets.size() > 5){
                this.bullets.remove();
            }

        }
        for(Bullet bullet: bullets){
            bullet.update(dt);
        }
        this.shoot = false;

        // set shape
        setShape();

        // screen wrap
        wrap();

    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(255, 0, 0, 100);

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.circle(this.x, this.y, this.radius-10);
        sr.end();
        sr.setColor(0, 0, 255, 100);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.circle(this.x, this.y, this.radius);
        sr.end();
        for(Bullet bullet: bullets){
            bullet.draw(sr);
        }




    }
}
