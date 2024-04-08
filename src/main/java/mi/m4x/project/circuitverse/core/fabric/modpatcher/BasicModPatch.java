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
