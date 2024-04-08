package mi.m4x.project.circuitverse.core.registries.api;

import mi.m4x.project.circuitverse.core.registries.RegistryObject;
import mi.m4x.project.circuitverse.core.registries.impl.DynamicRegistryImpl;
import mi.m4x.project.circuitverse.core.tags.Identifier;

public interface DynamicRegistry<T> {

    RegistryObject<T> register(Identifier id, T object);

    public static <T> DynamicRegistry<T> create() {
        return new DynamicRegistryImpl<>();
    }

}
