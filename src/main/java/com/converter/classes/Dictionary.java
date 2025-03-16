package com.converter.classes;

import java.util.stream.Stream;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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