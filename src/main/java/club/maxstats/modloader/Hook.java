package club.maxstats.modloader;

import org.objectweb.asm.tree.ClassNode;

public abstract class Hook {
    public abstract void inject(ClassNode cNode);
    /* Returns the name of the hooked class */
    public abstract String getHookedClass();
}