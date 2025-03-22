package com;

import com.converter.classes.DictionaryCreator;

public class Main {

    public static void main(String[] args) {
        DictionaryCreator dictionaryCreator = new DictionaryCreator("/home/rasmus/ekilex-data-grabber/data");

        dictionaryCreator.createDictionary();
    }
}
