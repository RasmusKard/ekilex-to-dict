package com.converter.util;

import com.converter.classes.Article;
import com.eki.dict.classes.WordDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class JSONToWordDetails {
    public static void main(String[] args) {
        File jsonfile = new File("/home/rasmus/ekilex-to-dict/data/jooksma.json");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Object a = objectMapper.readValue(jsonfile, Object.class);
            // System.out.println(a);

            WordDetails wordDetails = objectMapper.readValue(jsonfile, WordDetails.class);
            Article article = WordDetailsToArticles.main(wordDetails);

            XmlMapper xmlMapper = new XmlMapper();
            String prettyJson = xmlMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(article);
            System.out.println(prettyJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}