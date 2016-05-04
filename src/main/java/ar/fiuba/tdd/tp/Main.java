package ar.fiuba.tdd.tp;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        URL[] urls = { new URL("jar:file:" + args[0]+"!/") };
        GameBuilder builder = BuilderLoader.load(file, URLClassLoader.newInstance(urls));
        builder.build();
    }
}
