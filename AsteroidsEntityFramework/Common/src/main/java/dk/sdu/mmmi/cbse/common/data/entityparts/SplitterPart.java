/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author marku
 */
public class SplitterPart implements EntityPart {
    private boolean shouldSplit;
    private float radians1;
    private float radians2;
    private Entity e1;
    private Entity e2;

    public SplitterPart(boolean shouldSplit, float radians1, float radians2) {
        this.shouldSplit = shouldSplit;
        this.radians1 = radians1;
        this.radians2 = radians2;
        this.shouldSplit = false;
    }

    public void setShouldSplit(boolean shouldSplit) {
        this.shouldSplit = shouldSplit;
    }
    
    
    public boolean isShouldSplit() {
        return shouldSplit;
    }

    public float getRadians1() {
        return radians1;
    }

    public float getRadians2() {
        return radians2;
    }

    public Entity getE1() {
        return e1;
    }

    public Entity getE2() {
        return e2;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart position = entity.getPart(PositionPart.class);
        if(this.shouldSplit){
            this.e1 = new Entity();
            this.e2 = new Entity();
            PositionPart position1 = new PositionPart(position.getX(), position.getY(), this.radians1);
            PositionPart position2 = new PositionPart(position.getX(), position.getY(), this.radians2);
        }
    }
    
    
}
