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

package mi.m4x.project.circuitverse.core.fabric.modpatcher;

import mi.m4x.project.circuitverse.core.fabric.Hooks;
import net.fabricmc.loader.impl.game.minecraft.MinecraftGameProvider;
import net.fabricmc.loader.impl.game.patch.GamePatch;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.pmw.tinylog.Logger;

import java.util.function.Consumer;
import java.util.function.Function;

import static mi.m4x.project.circuitverse.core.tags.LoggerTags.createTag;

public class BasicModPatch extends GamePatch {

    public static String PreLaunchEntryPoint = "mi.m4x.project.circuitverse.main.Main";
    public static String OnInitEntryPoint = "mi.m4x.project.circuitverse.core.CircuitVerse";

    public static String TAG = createTag("Internal Game Provider");

    @Override
    public void process(FabricLauncher launcher, Function<String, ClassNode> classSource, Consumer<ClassNode> classEmitter) {


        ClassNode mainClass = classSource.apply(OnInitEntryPoint);

        MethodNode initMethod = findMethod(mainClass, (method) -> method.name.equals("onInit"));
        Logger.debug("%s: Found init method %s -> %s".formatted(TAG, OnInitEntryPoint, mainClass.name));
        Logger.debug("%s Patching init method %s%s".formatted(TAG, initMethod.name, initMethod.desc));

        initMethod.instructions.iterator().add(new MethodInsnNode(Opcodes.INVOKESTATIC, Hooks.InternalName, "init", "()V", false));
        classEmitter.accept(mainClass);

    }

}
