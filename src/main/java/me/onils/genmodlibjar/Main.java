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

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    @SneakyThrows(IOException.class)
    public static void main(String[] args) {
        JarFile jarFile = new JarFile(Utils.getMinecraftJar());

        Enumeration<? extends JarEntry> entries = jarFile.entries();

        File outputDir = new File(System.getProperty("user.dir"), "out");
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (!entry.getName().endsWith(".class")) {
                continue;
            }

            ClassReader cr = new ClassReader(jarFile.getInputStream(entry));
            ClassWriter cw = new ClassWriter(0);

            Remapper remapper = new me.onils.genmodlibjar.ClassRemapper();
            ClassRemapper classRemapper = new ClassRemapper(cw, remapper);
            cr.accept(classRemapper, ClassReader.SKIP_CODE);


            File newClassFile = new File(outputDir, remapper.map(cr.getClassName())+".class");
            FileUtils.writeByteArrayToFile(newClassFile, cw.toByteArray());
        }
    }
}
