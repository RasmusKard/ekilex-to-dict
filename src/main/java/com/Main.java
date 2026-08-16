package com;

import com.converter.classes.DictionaryCreator;

public class Main {

    public static void main(String[] args) {
        String format = args.length > 0 ? args[0] : "xdxf";
        String version = args.length > 1 ? args[1] : null;
        String outputDir = args.length > 2 ? args[2] : "kindle-dict";
        String inputJsonDirPath = "/Users/rasmus.kard/ekilex-to-dict/data";

        DictionaryCreator dictionaryCreator = new DictionaryCreator(inputJsonDirPath);

        switch (format) {
            case "xdxf" -> dictionaryCreator.createDictionary("result.xdxf");
            case "mobi" -> dictionaryCreator.createKindleDictionarySource(outputDir, version);
            default -> throw new IllegalArgumentException("Unknown format: " + format + " (expected xdxf or mobi)");
        }
    }
}
