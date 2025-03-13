package com.converter;

import com.eki.dict.classes.WordDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONToClass {
    public static void main(String[] args) {
        File jsonfile = new File("/home/rasmus/ekilex-to-dict/data/õun.json");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Object a = objectMapper.readValue(jsonfile, Object.class);
            // System.out.println(a);
            WordDetails wordDetails = objectMapper.readValue(jsonfile, WordDetails.class);
            String prettyJson = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(wordDetails);
            System.out.println(prettyJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}