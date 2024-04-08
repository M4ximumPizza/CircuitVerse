package mi.m4x.project.circuitverse.core.registries.api;

import mi.m4x.project.circuitverse.core.registries.impl.FreezingRegistryImpl;
import mi.m4x.project.circuitverse.core.registries.RegistryObject;
import mi.m4x.project.circuitverse.core.tags.Identifier;

public interface FreezingRegistry<T> {

    void freeze();
    boolean isFrozen();
    RegistryObject<T> register(Identifier id, T object);

    public static <T> FreezingRegistry<T> create() {
        return new FreezingRegistryImpl<>();
    }

}
