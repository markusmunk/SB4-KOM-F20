package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.FiringPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.entityparts.HitBoxPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        
        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {
        //player attributes
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;
        float[] colors = {0f, 255f, 0f, 1f};
        
        //bullet attributes
        float bulletDeacceleration = 0;
        float bulletAcceleration = 600;
        float bulletMaxSpeed = 500;
        
        //unused
        float bulletStartX = x + MathUtils.cos(radians)*8;
        float bulletStartY = x + MathUtils.sin(radians)*8;
        
        
        Entity playerShip = new Player();
        playerShip.setColorRgba(colors);
        playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        playerShip.add(new PositionPart(x, y, radians));
        playerShip.add(new FiringPart(bulletDeacceleration, bulletAcceleration, bulletMaxSpeed, "player"));
        playerShip.add(new LifePart(1,1)); //1 and 1 doesnt matter at the moment;
        HitBoxPart hitBoxPart = new HitBoxPart(9, 9, x, y, "player");
        hitBoxPart.addIgnore("bullet");
        playerShip.add(hitBoxPart);
        
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
