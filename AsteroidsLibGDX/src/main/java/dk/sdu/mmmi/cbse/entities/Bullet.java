package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObject {
    private float r;


    public Bullet(float x, float y, float radians, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.r = 4;
        this.radians = radians;
        shapex = new float[4];
        shapey = new float[4];
    }
    private void setShape(){
        /*
        //venstre hjørne
        shapex[0] = x;
        shapey[0] = y;

        //højre hjørne
        shapex[1] = x+3;
        shapey[1] = y;

        //højre hjørne top
        shapex[2] = x+3;
        shapey[2] = y+7*MathUtils.sin(radians);;

        //venstre hjørne top
        shapex[3] = x;
        shapey[3] = y+7*MathUtils.sin(radians);


         */


        shapex[0] = x + MathUtils.cos(radians + 3.1415f / 8) * 4;
        shapey[0] = y + MathUtils.sin(radians + 3.1145f / 8) * 4;

        shapex[1] = x + MathUtils.cos(radians - 3.1415f / 8) * 4;
        shapey[1] = y + MathUtils.sin(radians - 3.1145f / 8) * 4;

        shapex[2] = x + MathUtils.cos(radians - 7 * 3.1415f / 8) * 4;
        shapey[2] = y + MathUtils.sin(radians - 7 * 3.1415f / 8) * 4;

        shapex[3] = x + MathUtils.cos(radians + 7 * 3.1415f / 8) * 4;
        shapey[3] = y + MathUtils.sin(radians + 7 * 3.1415f / 8) * 4;


    }
    @Override
    protected void wrap() {
        //should not return on the map
    }
    public void update(float dt) {

        this.dx += MathUtils.cos(radians) * this.speed;// * dt;
        this.dy += MathUtils.sin(radians) * this.speed; //s * dt;


        // set position
        this.x += this.dx;
        this.y += this.dy;
        setShape();
    }
    public void draw(ShapeRenderer sr) {

        sr.setColor(255, 5, 0, 1);

        sr.begin(ShapeRenderer.ShapeType.Filled);

        for(int i = 0, j = shapex.length - 1;
            i < shapex.length;
            j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }
        sr.end();

    }
}
