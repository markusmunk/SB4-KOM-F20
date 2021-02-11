package dk.sdu.mmmi.cbse.bulletSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author jcs
 */
public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);

            movingPart.setLeft(false);
            movingPart.setRight(false);
            movingPart.setUp(true);
            
            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            updateShape(bullet);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians - 3.1415f / 8) * 4);
        shapey[0] = (float) (y + Math.sin(radians - 3.1145f / 8) * 4);
        
        shapex[1] = (float) (x + Math.cos(radians + 3.1415f / 8) * 4);
        shapey[1] = (float) (y + Math.sin(radians + 3.1415f / 8) * 4);

        shapex[2] = (float) (x + Math.cos(radians + 7*3.1415f/8 ) * 4);
        shapey[2] = (float) (y + Math.sin(radians + 7*3.1415f/8) * 4);
        
        shapex[3] = (float) (x + Math.cos(radians - 7*3.1415f/8 ) * 4);
        shapey[3] = (float) (y + Math.sin(radians - 7*3.1415f/8) * 4);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
