/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.ArrayList;

/**
 *
 * @author marku
 */
    public class FiringPart implements EntityPart {
    private boolean fire;
    private ArrayList<Entity> bullets;
    private float deceleration, acceleration;
    private float maxSpeed;

    public FiringPart(float deceleration, float acceleration, float maxSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        
        this.bullets = new ArrayList<>();
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        float[] colors = {0f, 255f, 0f, 1f};
        if(this.fire){
            Entity bullet = new Entity();
            bullet.setColorRgba(colors);
           
            PositionPart playerPosition = entity.getPart(PositionPart.class);
            
            
            MovingPart movingPart = new MovingPart(deceleration, acceleration, maxSpeed,0);
            PositionPart positionPart = new PositionPart(playerPosition.getX(), playerPosition.getY(), playerPosition.getRadians());
            movingPart.setLeft(false);
            movingPart.setRight(false);
            movingPart.setUp(true);
            bullet.add(positionPart);
            bullet.add(movingPart);
            bullets.add(bullet);
        }
        if(!bullets.isEmpty()){
            for(Entity bullet : bullets){
                bullet.getPart(MovingPart.class).process(gameData, bullet);
                bullet.getPart(PositionPart.class).process(gameData, bullet);
            }
        }
        
        
        
    }
     public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
    
    public ArrayList<Entity> getBullets(){
        return this.bullets;
    }
}
