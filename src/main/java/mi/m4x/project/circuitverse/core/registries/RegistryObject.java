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
