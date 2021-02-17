package dk.sdu.mmmi.cbse.common.astroidSystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.HitBoxPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author jcs
 */
public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            HitBoxPart hitBoxPart = asteroid.getPart(HitBoxPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            //need to be static, but random
            movingPart.setLeft(false);
            movingPart.setRight(false);
            movingPart.setUp(true);
            
            
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            hitBoxPart.process(gameData, asteroid);
            lifePart.process(gameData, asteroid);
            if(lifePart.isIsHit()){
                Entity[] arr = splitAsteroid(asteroid);
                //half the asteroid
                world.removeEntity(asteroid);
                if(arr[0] instanceof Entity){
                   world.addEntity(arr[0]);
                   world.addEntity(arr[1]); 
                }                 
            }
            updateShape(asteroid);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        LifePart lifePart = entity.getPart(LifePart.class);
        float sizing = lifePart.getLife();
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians - 3.1415f / 4) * 8*4/sizing);
        shapey[0] = (float) (y + Math.sin(radians - 3.1145f / 4) * 8*4/sizing);
        
        shapex[1] = (float) (x + Math.cos(radians + 3.1415f / 4) * 8*4/sizing);
        shapey[1] = (float) (y + Math.sin(radians + 3.1415f / 4) * 8*4/sizing);

        shapex[2] = (float) (x + Math.cos(radians + 3*3.1415f/4 ) * 8*4/sizing);
        shapey[2] = (float) (y + Math.sin(radians + 3*3.1415f/4) * 8*4/sizing);
        //System.out.println("Euclid: " + Math.sqrt(Math.pow(shapex[2]-shapex[1], 2)+Math.pow(shapey[2]-shapey[1], 2)));
        
        shapex[3] = (float) (x + Math.cos(radians - 3*3.1415f/4 ) * 8*4/sizing);
        shapey[3] = (float) (y + Math.sin(radians - 3*3.1415f/4) * 8*4/sizing);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
    private Entity[] splitAsteroid(Entity entity){
        Entity[] arr = {null, null};
        LifePart lifePart = entity.getPart(LifePart.class);
        int lifeCount = lifePart.getLife()+1;
        if(lifeCount>3) return arr;
        
        PositionPart positionPart = entity.getPart(PositionPart.class);
        HitBoxPart hitBoxPart = entity.getPart(HitBoxPart.class);
        
        
        float hitBoxHeight = hitBoxPart.getHeight()/2;
        float hitBoxwidth = hitBoxPart.getWidth()/2;
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 100;
        float rotationSpeed = 5;
        float x = positionPart.getX();
        float y = positionPart.getY();
        float[] colors = {0f, 0f, 255f, 1f};
        float random = MathUtils.random(0, 3.1415f*2);
        float radians = positionPart.getRadians();
        float radians1 = radians + (float)(Math.PI/2); 
        float radians2 = radians - (float)(Math.PI/2);
      
        
        Entity asteroid1 = new Asteroid();
        Entity asteroid2 = new Asteroid();
        asteroid1.setColorRgba(colors);
        asteroid1.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid1.add(new PositionPart(x, y, radians1));
        HitBoxPart newHitBox1 = new HitBoxPart(hitBoxwidth, hitBoxHeight, x, y, "asteroid");
        newHitBox1.addIgnore("asteroid");
        asteroid1.add(newHitBox1);
        asteroid1.add(new LifePart(lifeCount,1)); // 1 doesnt matter at the moment
        
        asteroid2.setColorRgba(colors);
        asteroid2.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid2.add(new PositionPart(x, y, radians2));
        HitBoxPart newHitBox2 = new HitBoxPart(hitBoxwidth, hitBoxHeight, x, y, "asteroid");
        newHitBox2.addIgnore("asteroid");
        asteroid2.add(newHitBox2);
        asteroid2.add(new LifePart(lifeCount,1));
        Entity[] array = {asteroid1, asteroid2};
        return array;
    }

}
