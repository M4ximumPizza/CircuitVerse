/*
 * Copyright (c) 2024, M4ximumPizza and/or its contributors. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is licensed under the PolyForm Shield License 1.0.0; you can use, modify,
 * and distribute this code for any purpose that does not compete with the software or
 * any product the licensor or any of its affiliates provides using the software.
 * M4ximumPizza designates this particular file as subject to the "Classpath" exception
 * as provided by M4ximumPizza in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the PolyForm Shield License 1.0.0 for more details
 * (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the PolyForm Shield License 1.0.0 along with this work;
 * if not, here is the license [https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt].
 */

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
