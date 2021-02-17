package dk.sdu.mmmi.cbse.common.astroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.entityparts.HitBoxPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import java.util.ArrayList;

public class AsteroidPlugin implements IGamePluginService {

    private ArrayList<Entity> asteroids;

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        asteroids = new ArrayList<Entity>();
        // Add entities to the world
        for(int i = 0; i<MathUtils.random(2,10); i++){
            Entity asteroid = createPlayerShip(gameData);
            asteroids.add(asteroid);
            world.addEntity(asteroid);
        
        }
    }

    private Entity createPlayerShip(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 50;
        float rotationSpeed = 5;
        float x = MathUtils.random(0,gameData.getDisplayWidth());
        float y = MathUtils.random(0,gameData.getDisplayHeight());
        float[] colors = {0f, 0f, 255f, 1f};
        float random = MathUtils.random(0, 3.1415f*2);
        float radians = random;
      
        
        Entity playerShip = new Asteroid();
        playerShip.setColorRgba(colors);
        playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        playerShip.add(new PositionPart(x, y, radians));
        HitBoxPart newHitBox = new HitBoxPart(37, 37, x, y, "asteroid");
        newHitBox.addIgnore("asteroid");
        playerShip.add(newHitBox);
        playerShip.add(new LifePart(1,1)); //the last 1 doesnt matter at the moment
        
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for(Entity asteroid: asteroids){
            world.removeEntity(asteroid);
        }
    }

}
