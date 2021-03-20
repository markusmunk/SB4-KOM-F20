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
    private String owner;
    private boolean isFilled;

    public FiringPart(float deceleration, float acceleration, float maxSpeed, String owner, boolean isFilled) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.owner = owner;
        this.bullets = new ArrayList<>();
        this.isFilled = isFilled;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        float[] colors = {0f, 255f, 0f, 1f};
        if(this.fire){
            Entity bullet = new Entity();
            //bullet.setColorRgba(colors);
           
            PositionPart playerPosition = entity.getPart(PositionPart.class);
            
            HitBoxPart hitBoxPart = new HitBoxPart(4,4,playerPosition.getX(), playerPosition.getY(), "bullet");
            hitBoxPart.addIgnore(this.owner);
            MovingPart movingPart = new MovingPart(deceleration, acceleration, maxSpeed,0, false);
            movingPart.setLeft(false);
            movingPart.setRight(false);
            movingPart.setUp(true);
            PositionPart positionPart = new PositionPart(playerPosition.getX(), playerPosition.getY(), playerPosition.getRadians());
            LifePart lifePart = new LifePart(1,1); //1 and 1 doesnt matter at the moment
            bullet.add(lifePart);
            bullet.add(positionPart);
            bullet.add(movingPart);
            bullet.add(hitBoxPart);
            bullets.add(bullet);
        }
        if(!bullets.isEmpty()){
            for(Entity bullet : bullets){
                bullet.getPart(MovingPart.class).process(gameData, bullet);
                bullet.getPart(PositionPart.class).process(gameData, bullet);
                bullet.getPart(HitBoxPart.class).process(gameData, bullet);
                bullet.getPart(LifePart.class).process(gameData, bullet);
            }
        }
        
        
        
    }
     public boolean isFire() {
        return fire;
    }
    public void removeAllBullets(){
        this.bullets = new ArrayList<>();
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
    
    public ArrayList<Entity> getBullets(){
        return this.bullets;
    }
}
