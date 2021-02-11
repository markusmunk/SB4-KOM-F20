package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author Markus
 * description: handling post processes on every frame
 * precondition: processing has benn done
 * postcondition: post processing has been done, and gamestate is updated.
 * 
 */
public interface IPostEntityProcessingService  {
        void process(GameData gameData, World world);
}
