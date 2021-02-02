package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

public class Enemy extends SpaceObject {
    private boolean left;
    private boolean right;
    private boolean up;
    private float maxSpeed;
    private float deacceleration;
    private float acceleration;
    public Enemy() {
        this.x = generateRandomSpot();
        this.y = generateRandomSpot();
        this.acceleration = 50;
        this.shapex = new float[4];
        this.shapey = new float[4];
        radians = 3.1415f / 2;
        rotationSpeed = 3;
        maxSpeed = 100;
        this.deacceleration = 20;

    }
    private void turn(){
        double change = Math.random();
        this.left = false;
        this.right = false;
        this.up = false;

        if(change < 0.15){
            this.left = true;
        }else if( change < 0.30 && change > 0.15 ){
            this.right = true;
        } else {
            this.up = true;
        }
    }
    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 5;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 5;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 3;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 3;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 5;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 5;
    }

    private float generateRandomSpot() {
        double random = Math.random();
        float number=0;
        number = (float) (Math.random()* Game.WIDTH);
        System.out.println(number);
        return number;
    }

    public void update(float dt) {
        turn();
        // turning
        if(left) {
            radians += rotationSpeed * dt;
        }
        else if(right) {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if(up) {
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if(vec > 0) {
            dx -= (dx / vec) * this.deacceleration * dt;
            dy -= (dy / vec) * this.deacceleration * dt;
        }
        if(vec > this.maxSpeed) {
            dx = (dx / vec) * this.maxSpeed;
            dy = (dy / vec) * this.maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        // screen wrap
        wrap();

    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(255, 0, 0, 100);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(this.x, this.y, 10);
        /*
        for(int i = 0, j = shapex.length - 1;
            i < shapex.length;
            j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }
        */
        sr.end();

    }
}
