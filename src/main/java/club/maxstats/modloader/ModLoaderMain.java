package club.maxstats.modloader;

import club.maxstats.modloader.listener.Bus;

public class ModLoaderMain {
    private Bus bus;
    private static ModLoaderMain instance;

    public static ModLoaderMain getModLoader() { return instance; };
    public Bus getEventBus() { return this.bus; };
}
