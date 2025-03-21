package com.converter.classes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MetaInfo {
    private Languages languages;

    private final String title = "title";

    private final String description = "description";

    private final String fileVer = "001";

    private final String creationDate = "21-03-2025";

    private final String lastEditedDate = "21-03-2025";

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFileVer() {
        return fileVer;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getLastEditedDate() {
        return lastEditedDate;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }
}
