package dk.sdu.mmmi.cbse.common.astroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
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

            //need to be static, but random
            movingPart.setLeft(false);
            movingPart.setRight(false);
            movingPart.setUp(true);
            
            
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

         shapex[0] = (float) (x + Math.cos(radians - 3.1415f / 4) * 8);
        shapey[0] = (float) (y + Math.sin(radians - 3.1145f / 4) * 8);
        
        shapex[1] = (float) (x + Math.cos(radians + 3.1415f / 4) * 8);
        shapey[1] = (float) (y + Math.sin(radians + 3.1415f / 4) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3*3.1415f/4 ) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3*3.1415f/4) * 5);
        
        shapex[3] = (float) (x + Math.cos(radians - 3*3.1415f/4 ) * 5);
        shapey[3] = (float) (y + Math.sin(radians - 3*3.1415f/4) * 5);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
