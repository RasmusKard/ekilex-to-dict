package com;

import com.converter.classes.DictionaryCreator;

public class Main {

    public static void main(String[] args) {
        String resultFilePath = "";
        String inputJsonDirPath = "";

        DictionaryCreator dictionaryCreator = new DictionaryCreator(inputJsonDirPath);

        dictionaryCreator.createDictionary(resultFilePath);
    }
}
