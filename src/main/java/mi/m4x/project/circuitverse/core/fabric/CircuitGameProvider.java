package mi.m4x.project.circuitverse.core.fabric;

import com.google.common.collect.ImmutableList;
import mi.m4x.project.circuitverse.core.fabric.modpatcher.BasicModPatch;
import mi.m4x.project.circuitverse.core.info.CVConstants;
import mi.m4x.project.circuitverse.main.Main;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.game.GameProvider;
import net.fabricmc.loader.impl.game.GameProviderHelper;
import net.fabricmc.loader.impl.game.patch.GameTransformer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.metadata.BuiltinModMetadata;
import net.fabricmc.loader.impl.metadata.ContactInformationImpl;
import net.fabricmc.loader.impl.util.Arguments;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.util.ASMifier;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CircuitGameProvider implements GameProvider {

    public static final String[] ENTRYPOINTS = new String[]{"mi.m4x.project.core.CircuitVerse"};
    private static final GameTransformer TRANSFORMERS = new GameTransformer() {
        @Override
        public byte[] transform(String className) {
            return null;
        }
    };
    private static final Path GameJar;
    private static Arguments args = new Arguments();

    static {
        try {
            GameJar = Paths.get(new File(
                    Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()
            ).getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Path> jars;

    @Override
    public String getGameId() {
        return "circuit_verse";
    }

    @Override
    public String getGameName() {
        return "Circuit Verse";
    }

    @Override
    public String getRawGameVersion() {
        return CVConstants.GameVersion;
    }

    @Override
    public String getNormalizedGameVersion() {
        return CVConstants.GameVersion;
    }

    @Override
    public Collection<BuiltinMod> getBuiltinMods() {
        HashMap<String, String> M4xContactMap = new HashMap<>();
        M4xContactMap.put("", "");

        HashMap<String, String> ZombiiContactMap = new HashMap<>();
        ZombiiContactMap.put("", "");

        HashMap<String, String> GameContactMap = new HashMap<>();
        GameContactMap.put("", "");

        BuiltinModMetadata.Builder metaData = new BuiltinModMetadata.Builder(getGameId(), getNormalizedGameVersion())
                .setName(getGameName())
                .addAuthor("M4x", M4xContactMap)
                .addAuthor("MrZombii", ZombiiContactMap)
                .setContact(new ContactInformationImpl(GameContactMap))
                .setDescription("The base game")
                ;

        return Collections.singletonList(new BuiltinMod(
                Collections.singletonList(GameJar),
                metaData.build()
            )
        );
    }

    @Override
    public String getEntrypoint() {
        return BasicModPatch.PreLaunchEntryPoint;
    }

    @Override
    public Path getLaunchDirectory() {
        File runDir = new File(String.valueOf(Paths.get("run")));
        if (!runDir.exists()) runDir.mkdirs();
        return Paths.get("run");
    }

    @Override
    public boolean isObfuscated() {
        return false;
    }

    @Override
    public boolean requiresUrlClassLoader() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean locateGame(FabricLauncher launcher, String[] args) {
        CircuitGameProvider.args.parse(args);

        GameProviderHelper.findFirst(List.of(new Path[]{
                Path.of(String.valueOf(GameJar))
        }), new HashMap<>(), true, ENTRYPOINTS);

//        this.jars = new ArrayList<>();
//        jars.addAll(launcher.getClassPath());
        return true;
    }

    @Override
    public void initialize(FabricLauncher launcher) {
        try {
            launcher.setValidParentClassPath(ImmutableList.of(
                    Path.of(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()),
                    Path.of(MixinBootstrap.class.getProtectionDomain().getCodeSource().getLocation().toURI()),
                    Path.of(FabricLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI()),
                    Path.of(AnnotationVisitor.class.getProtectionDomain().getCodeSource().getLocation().toURI()),
                    Path.of(AbstractInsnNode.class.getProtectionDomain().getCodeSource().getLocation().toURI()),
                    Path.of(Analyzer.class.getProtectionDomain().getCodeSource().getLocation().toURI()),
                    Path.of(ASMifier.class.getProtectionDomain().getCodeSource().getLocation().toURI()),
                    Path.of(AdviceAdapter.class.getProtectionDomain().getCodeSource().getLocation().toURI())
            ));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        TRANSFORMERS.locateEntrypoints(launcher, List.of(GameJar));
    }

    @Override
    public GameTransformer getEntrypointTransformer() {
        return TRANSFORMERS;
    }

    @Override
    public void unlockClassPath(FabricLauncher launcher) {
        launcher.addToClassPath(GameJar);
//        for (Path jar : jars) {
//            launcher.addToClassPath(jar);
//        }
    }

    @Override
    public void launch(ClassLoader loader) {
        try {
            Class<?> main = loader.loadClass(BasicModPatch.PreLaunchEntryPoint);
            Method method = main.getMethod("main", String[].class);
            method.invoke(null, (Object) getArguments().toArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Arguments getArguments() {
        return args;
    }

    @Override
    public String[] getLaunchArguments(boolean b) {
        return args == null ? new String[0] : args.toArray();
    }
}
