package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * 
 * @author marku
 * @start
 * description: initialize entities
 * precondition: the world has been created and the gamedata object
 * postcondition: the startconditions of the implementing entity service has been set.
 * 
 * @stop
 * description: removes entities from the world on game ending
 * precondition: the game is running with entities in the world
 * postcondition: the entities is removed from the world. 
 */
public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
