package mi.m4x.project.circuitverse.core.registries.api;

import mi.m4x.project.circuitverse.core.tags.Identifier;

public interface RegistryAccessor<T> {

    T get(Identifier identifier);
    boolean contains(Identifier identifier);
    Identifier[] getRegisteredNames();

}
