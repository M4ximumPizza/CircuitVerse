package mi.m4x.project.circuitverse.core.registries;

import mi.m4x.project.circuitverse.core.registries.api.DynamicRegistry;
import mi.m4x.project.circuitverse.core.registries.api.FreezingRegistry;
import mi.m4x.project.circuitverse.core.registries.api.RegistryAccessor;
import mi.m4x.project.circuitverse.core.tags.Identifier;

public class RegistryObject<T> {

    Identifier objectId;
    RegistryAccessor<T> registryAccess;

    public RegistryObject(Identifier id, FreezingRegistry<T> referencedRegistry) {
        objectId = id;
        registryAccess = (RegistryAccessor<T>) referencedRegistry;
    }

    public RegistryObject(Identifier id, DynamicRegistry<T> referencedRegistry) {
        objectId = id;
        registryAccess = (RegistryAccessor<T>) referencedRegistry;
    }

    public T get() {
        if (registryAccess.contains(objectId)) {
            return registryAccess.get(objectId);
        }
        throw new RuntimeException("REGISTRY OBJECT NOT IN REGISTRY: FAILED TO FIND/REGISTER \""+objectId+"\"");
    }

}
