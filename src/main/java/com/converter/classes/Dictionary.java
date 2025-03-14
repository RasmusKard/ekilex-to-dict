package com.converter.classes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.eki.dict.classes.Classifier;
import com.eki.dict.classes.Form;
import com.eki.dict.classes.Paradigm;
import com.eki.dict.classes.Usage;
import com.eki.dict.classes.Word;
import com.eki.dict.classes.WordDetails;
import com.eki.dict.classes.WordLexeme;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonRootName(value = "xdxf")
public class Dictionary {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ar")
    private Stream<Article> articles;

    @JacksonXmlProperty(isAttribute = true)
    private final String revision = "034";

    private MetaInfo metaInfo;

    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getRevision() {
        return revision;
    }

    public Stream<Article> getArticles() {
        return articles;
    }

    public void setArticles(Stream<Article> articles) {
        this.articles = articles;
    }
}

// ISSUE - All articles can't be loaded into memory, this will be too expensive

// OPTION 1
// In dictionary instead of storing the ARTICLE class store a list of json files
// or a directory path for the json files.
// write custom serializer for reading the json into class, converting to
// article and then serializing
//
// @@@@@ This seems like a hacky approach since the class will stop representing
// the real shape of the data and instead be representing the paths

// OPTION 2
// First the articles are streamed into a file 1 by 1 and then the other
// dicitonary root/metadata is added to it once finished
//
// @@@ This also doesn't seem like the best approach since the whole articles
// file will still need to be read into memory at the end

// OPTION 3
// have an attribute with the JSON files directory path
// on dictionary construct