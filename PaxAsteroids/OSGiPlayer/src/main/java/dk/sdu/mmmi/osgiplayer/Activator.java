package dk.sdu.mmmi.osgiplayer;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {
    
    
    public void start(BundleContext context) throws Exception {
        // TODO add activation code here
        PlayerControlSystem pcs = new PlayerControlSystem();
        context.registerService(IEntityProcessingService.class, pcs, null);
        PlayerPlugin pp = new PlayerPlugin();
        context.registerService(IGamePluginService.class, pp, null);
    }    

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
