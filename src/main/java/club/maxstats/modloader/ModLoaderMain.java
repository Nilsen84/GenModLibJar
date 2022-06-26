package club.maxstats.modloader;

import java.net.URLClassLoader;

public class ModLoaderMain {
    private Bus bus;
    private URLClassLoader modClassLoader;
    private static ModLoaderMain instance;

    public static ModLoaderMain getModLoader() { return instance; };
    public Bus getEventBus() { return this.bus; };
    public URLClassLoader getModClassLoader() { return this.modClassLoader; };
}
