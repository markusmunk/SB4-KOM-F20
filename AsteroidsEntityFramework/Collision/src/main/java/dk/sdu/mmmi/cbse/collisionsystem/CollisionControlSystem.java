package dk.sdu.mmmi.cbse.collisionsystem;

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
public class CollisionControlSystem implements IPostEntityProcessingService {
    
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for(Entity entity1 : world.getEntities()){
                if(entity.equals(entity1)){
                } else{ 
                //collsion logic
                    collisionDetection(entity, entity1);
                }
            }
        }
    }
    
    private void collisionDetection(Entity entity, Entity entity1){
        HitBoxPart hitBox;
        HitBoxPart hitBox1;
        if(entity.getPart(HitBoxPart.class) instanceof HitBoxPart){
            hitBox = entity.getPart(HitBoxPart.class);
            if(entity1.getPart(HitBoxPart.class) instanceof HitBoxPart){
                hitBox1 = entity1.getPart(HitBoxPart.class);
                            
                float entityX = hitBox.getX();
                float entityY = hitBox.getY();
                float entityWidth = hitBox.getWidth();
                float entityHeight = hitBox.getHeight();
                    
                float entity1X = hitBox1.getX();
                float entity1Y = hitBox1.getY();
                float entity1Width = hitBox1.getWidth();
                float entity1Height = hitBox1.getHeight();
        
                if(entityX < entity1X + entity1Width && 
                    entityX + entityWidth > entity1X &&
                    entityY < entity1Y + entity1Height &&
                    entityHeight + entityY > entity1Y){
                      actionOnCollision(entity, entity1);
                }
            }
        }
    }
    
    private void actionOnCollision(Entity entity, Entity entity1){
        HitBoxPart hitBox = entity.getPart(HitBoxPart.class);
        HitBoxPart hitBox1 = entity1.getPart(HitBoxPart.class);
        PositionPart positionPart = entity.getPart(PositionPart.class);
        PositionPart positionPart1 = entity1.getPart(PositionPart.class);
        boolean hitable = true;
        for(String s: hitBox.getIgnorations()){
            if(s == hitBox1.getOwner()){
                hitable = false;
            }
        }
        if(hitable){
            LifePart lifePart;
            LifePart lifePart1;
            if(entity.getPart(LifePart.class) instanceof LifePart){
                lifePart = entity.getPart(LifePart.class);
                lifePart.setIsHit(true);
                hitBox.setHitRadians(positionPart1.getRadians());
            }
            if(entity1.getPart(LifePart.class) instanceof LifePart){
                lifePart1 = entity1.getPart(LifePart.class);
                lifePart1.setIsHit(true);
                hitBox1.setHitRadians(positionPart.getRadians());
            }  
        }   
    }
}
