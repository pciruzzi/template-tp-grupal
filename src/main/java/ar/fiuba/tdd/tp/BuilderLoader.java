package ar.fiuba.tdd.tp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

// TODO: this class should be part of the server
class BuilderLoader {

    private static String escape(String name) {
        if (name.contains("/")) {
            name = name.replaceAll("/", ".");
        }
        if (name.contains("\\")) {
            name = name.replaceAll("\\\\", ".");
        }
        return name;
    }

    private static String processEntry(JarEntry entry) {
        String name = entry.getName();
        return escape(name.substring(0,name.lastIndexOf(".class")));
    }

    private static List<String> scanJar(File file) throws IOException, IllegalArgumentException {
        if (file == null || !file.exists() || !file.getName().endsWith(".jar")) {
            throw new IllegalArgumentException("file");
        }

        List<String> foundClasses = new ArrayList<>();
        try (JarFile jarFile = new JarFile(file)) {
            jarFile.stream()
                    .filter(e -> e.getName().endsWith(".class"))
                    .forEach(e -> foundClasses.add(processEntry(e)));
        }
        return foundClasses;
    }

    static GameBuilder load(File file, ClassLoader loader) throws Exception {
        for (String classFile : scanJar(file)) {
            Class<?> foundClass;
            try {
                if (loader == null) {
                    foundClass = Class.forName(classFile);
                } else {
                    foundClass = Class.forName(classFile, true, loader);
                }

                if (GameBuilder.class.isAssignableFrom(foundClass) && !foundClass.equals(GameBuilder.class)) {
                    return (GameBuilder)foundClass.newInstance();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
