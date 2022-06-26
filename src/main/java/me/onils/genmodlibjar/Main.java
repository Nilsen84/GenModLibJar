package me.onils.genmodlibjar;

import lombok.SneakyThrows;
import lombok.ToString;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    @SneakyThrows(IOException.class)
    public static void main(String[] args) {
        JarFile mcJar = new JarFile(Utils.getMinecraftJar());
        Enumeration<? extends JarEntry> entries = mcJar.entries();

        File outputDir = new File(System.getProperty("user.dir"), "out");
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (!entry.getName().endsWith(".class")) {
                continue;
            }

            ClassReader cr = new ClassReader(mcJar.getInputStream(entry));
            ClassWriter cw = new ClassWriter(0);

            Remapper remapper = new me.onils.genmodlibjar.ClassRemapper();
            ClassRemapper classRemapper = new ClassRemapper(cw, remapper);
            cr.accept(classRemapper, 0);

            File newClassFile = new File(outputDir, remapper.map(cr.getClassName())+".class");
            FileUtils.writeByteArrayToFile(newClassFile, cw.toByteArray());
        }

        JarFile modLoaderJar = new JarFile(new File(System.getProperty("user.dir") + "/build/libs", "GenModLibJar-1.0.jar"));
        entries = modLoaderJar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (!entry.getName().endsWith(".class"))
                continue;

            /* When obfuscated, all classes are put into "club.maxstats.modloader" package */
//            String obfuscatedName = "club/maxstats/modloader/" + entry.getName().substring(entry.getName().lastIndexOf("/") + 1, entry.getName().indexOf(".")) + ".class";

            File newClassFile = new File(outputDir, entry.getName());
            FileUtils.writeByteArrayToFile(newClassFile, modLoaderJar.getInputStream(entry).readAllBytes());
        }
    }

//    @SneakyThrows(IOException.class)
//    public static void main(String[] args) {
//        JarFile mcJar = new JarFile(Utils.getMinecraftJar());
//        Enumeration<? extends JarEntry> entries = mcJar.entries();
//
//        File outFile = new File(System.getProperty("user.dir"), "out.txt");
//        FileWriter writer = new FileWriter(outFile);
//
//        while (entries.hasMoreElements()) {
//            JarEntry entry = entries.nextElement();
//            if (!entry.getName().endsWith(".class"))
//                continue;
//
//            InputStream entryStream = mcJar.getInputStream(entry);
//
//            ClassReader classReader = new ClassReader(entryStream.readAllBytes());
//            ClassNode cn = new ClassNode();
//            classReader.accept(cn, 0);
//
//            String mcpName = Mappings.getMappedClassName(cn.name);
//            String mcpSuperName = Mappings.getMappedClassName(cn.superName);
//
//            if (cn.superName.startsWith("java/lang"))
//                continue;
//
//            writer.write(String.format("SC: %s %s\n", mcpName, mcpSuperName));
//        }
//
//        writer.close();
//    }
}
