package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
/**
 * 
 * @author markus
 * @Description: Forces the implementer to process the world, with the given gamedata. 
 * preconditions: A world object has been initialized, and the game is rendering.  
 * postcondition: the entities to the implementing service has been updated 
 * 
 */
public interface IEntityProcessingService {

    void process(GameData gameData, World world);
}
