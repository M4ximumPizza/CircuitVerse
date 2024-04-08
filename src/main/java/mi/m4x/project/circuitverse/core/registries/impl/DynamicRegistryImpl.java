package mi.m4x.project.circuitverse.core.registries.impl;

import mi.m4x.project.circuitverse.core.registries.RegistryObject;
import mi.m4x.project.circuitverse.core.registries.api.DynamicRegistry;
import mi.m4x.project.circuitverse.core.registries.api.RegistryAccessor;
import mi.m4x.project.circuitverse.core.tags.Identifier;

import java.util.HashMap;

public class DynamicRegistryImpl<T> implements RegistryAccessor<T>, DynamicRegistry<T> {

    private HashMap<Identifier, T> objects;

    public DynamicRegistryImpl() {
        objects = new HashMap<>();
    }

    @Override
    public T get(Identifier identifier) {
        return objects.get(identifier);
    }

    @Override
    public boolean contains(Identifier identifier) {
        return objects.containsKey(identifier);
    }

    @Override
    public Identifier[] getRegisteredNames() {
        return objects.keySet().toArray(new Identifier[0]);
    }

    @Override
    public RegistryObject<T> register(Identifier id, T object) {
        objects.put(id, object);
        return new RegistryObject<>(id, this);
    }
}
