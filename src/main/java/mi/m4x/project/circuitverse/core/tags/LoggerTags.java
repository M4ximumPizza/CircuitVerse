package mi.m4x.project.circuitverse.core.tags;

public class LoggerTags {

    private static final String TAG_FORMAT = "\u001B[35;1m{%s}\u001B[0m\u001B[37m";

    public static String createTag(String name) {
        return TAG_FORMAT.formatted(name) + "\u001B[37m";
    }

}
