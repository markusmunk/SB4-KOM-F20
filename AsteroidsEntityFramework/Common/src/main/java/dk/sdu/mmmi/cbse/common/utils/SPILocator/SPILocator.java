/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.utils.SPILocator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/**
 *
 * @author marku
 */
public class SPILocator {
    
    private static Map<Class, ServiceLoader> mapOfLoaders = new HashMap<>();

    public SPILocator() {
    }
    
    public static <T> List<T> locateAll(Class<T> service){
        ServiceLoader<T> loader = mapOfLoaders.get(service);

        boolean printStatement = false;

        if (loader == null) {
            loader = ServiceLoader.load(service);
            mapOfLoaders.put(service, loader);
            printStatement = true;
        }

        List<T> list = new ArrayList<T>();

        if (loader != null) {
            try {
                for (T instance : loader) {
                    list.add(instance);
                }
            } catch (ServiceConfigurationError serviceError) {
                serviceError.printStackTrace();
            }
        }

        if (printStatement) {
            System.out.println("Found " + list.size() + " implementations for interface: " + service.getName());
        }

        return list;
        
    } 
    
    
}
