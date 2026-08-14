package com;

import com.converter.classes.DictionaryCreator;

public class Main {

    public static void main(String[] args) {
        String format = args.length > 0 ? args[0] : "xdxf";
        String inputJsonDirPath = "/Users/rasmus.kard/ekilex-to-dict/data";

        DictionaryCreator dictionaryCreator = new DictionaryCreator(inputJsonDirPath);

        switch (format) {
            case "xdxf" -> dictionaryCreator.createDictionary("result.xdxf");
            case "mobi" -> dictionaryCreator.createKindleDictionarySource("kindle-dict");
            default -> throw new IllegalArgumentException("Unknown format: " + format + " (expected xdxf or mobi)");
        }
    }
}
