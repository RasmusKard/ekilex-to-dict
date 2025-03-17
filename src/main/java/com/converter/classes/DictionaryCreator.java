package com.converter.classes;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.eki.dict.classes.Classifier;
import com.eki.dict.classes.Form;
import com.eki.dict.classes.Paradigm;
import com.eki.dict.classes.Usage;
import com.eki.dict.classes.Word;
import com.eki.dict.classes.WordDetails;
import com.eki.dict.classes.WordLexeme;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class DictionaryCreator {
    private Dictionary dictionary;

    private Path jsonFilesDirPath;

    private Stream<WordDetails> wordDetailStream;

    public Stream<WordDetails> getWordDetailStream() {
        return wordDetailStream;
    }

    public DictionaryCreator(String jsonFilesDirPath) {
        this.dictionary = new Dictionary();
        this.jsonFilesDirPath = Paths.get(jsonFilesDirPath);
        this.wordDetailStream = createWordDetailsStream();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public Path getJsonFilesDirPath() {
        return jsonFilesDirPath;
    }

    private Stream<WordDetails> createWordDetailsStream() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return Files.list(this.jsonFilesDirPath).filter(Files::isRegularFile).map(path -> {
                try {
                    return objectMapper.readValue(path.toFile(), WordDetails.class);
                } catch (IOException e) {
                    throw new UncheckedIOException("Failed to read file: " + path, e);
                }
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

    public void createDictionary() {

        try {

            Stream<Article> articles = createArticleStreamfromWordDetailsStream(this.wordDetailStream);
            this.dictionary.setArticles(articles);

            ObjectMapper xmlMapper = new XmlMapper().registerModule(new Jdk8Module());
            String prettyJson = xmlMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this.dictionary);
            System.out.println(prettyJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Stream<Article> createArticleStreamfromWordDetailsStream(Stream<WordDetails> wordDetails) {
        return wordDetails.map(wordDetail -> {
            Word word = wordDetail.getWord();
            String wordValue = word.getWordValue();

            Set<String> formValues = new HashSet<>();
            if (word.getParadigms() != null) {
                for (Paradigm paradigm : word.getParadigms()) {
                    for (Form form : paradigm.getForms()) {
                        formValues.add(form.getValue());
                    }
                }
            }

            List<DefinitionBlock> definitionBlocks = new ArrayList<DefinitionBlock>();
            for (WordLexeme lexeme : wordDetail.getLexemes()) {
                String wordType = Optional.ofNullable(lexeme.getPos()).filter(pos -> !pos.isEmpty())
                        .map(pos -> pos.get(0)).map(pos -> pos.getValue()).orElse(null);

                String meaningValue = Optional.ofNullable(lexeme.getMeaning())
                        .map(meaning -> meaning.getDefinitions())
                        .filter(definitions -> !definitions.isEmpty())
                        .map(definitions -> definitions.get(0))
                        .map(definition -> definition.getValue())
                        .orElse(null);

                List<String> usages = new ArrayList<String>();
                for (Usage usage : lexeme.getUsages()) {
                    // keep usages to a maximum of 3
                    if (usages.size() == 3) {
                        break;
                    }
                    usages.add(usage.getValue());
                }

                DefinitionBlock definitionBlock = new DefinitionBlock();

                definitionBlock.setWordType(wordType);
                definitionBlock.setMeaning(meaningValue);
                definitionBlock.setUsages(usages);
                definitionBlocks.add(definitionBlock);
            }

            Article article = new Article();
            article.setWordValue(wordValue);
            article.setForms(formValues);
            article.setDefinitionBlocks(definitionBlocks);

            return article;
        });
    }
}
