package dk.sdu.mmmi.osgiplayertest;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
     private BundleContext context;

    public void start(BundleContext context) throws Exception {
        // TODO add activation code here
        this.context = context;
      context.registerService(IGamePluginService.class, new PlayerPlugin(), null);

      context.registerService(IEntityProcessingService.class, new PlayerControlSystem(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
