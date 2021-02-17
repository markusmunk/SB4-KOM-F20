package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.FiringPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.HitBoxPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {
    
    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            FiringPart firingPart = player.getPart(FiringPart.class);
            HitBoxPart hitBoxPart = player.getPart(HitBoxPart.class);
            LifePart lifePart = player.getPart(LifePart.class);
            movingPart.setLeft(gameData.getKeys().isDown(LEFT));
            movingPart.setRight(gameData.getKeys().isDown(RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(UP));
            firingPart.setFire(gameData.getKeys().isPressed(SPACE));
            movingPart.process(gameData, player);
            lifePart.process(gameData, player);
            positionPart.process(gameData, player);
            firingPart.process(gameData, player);
            hitBoxPart.process(gameData, player);
            for(Entity bullet: firingPart.getBullets()){
                if(world.getEntity(bullet.getID()) instanceof Entity){}
                else {
                    world.addEntity(bullet);
                }
                LifePart lp = bullet.getPart(LifePart.class);
                if(lp.isIsHit()){
                    world.removeEntity(bullet);
                }
                updateBulletShape(bullet);
            }
            if(lifePart.isIsHit()){
                world.removeEntity(player);
                for(Entity bullet: firingPart.getBullets()){
                    world.removeEntity(bullet);
                }
            }
            updateShape(player);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
    private void updateBulletShape(Entity entity){
        
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
