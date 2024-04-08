package mi.m4x.project.circuitverse.core.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.FabricLoaderImpl;

import java.nio.file.Paths;

public class Hooks {

    public static final String InternalName = Hooks.class.getName().replace(".", "/");

    public static void init() {
        FabricLoaderImpl loader = FabricLoaderImpl.INSTANCE;

        loader.prepareModInit(Paths.get("."), loader.getGameInstance());
        loader.invokeEntrypoints("main", ModInitializer.class, ModInitializer::onInitialize);
        loader.invokeEntrypoints("client", ModInitializer.class, ModInitializer::onInitialize);
    }
}
