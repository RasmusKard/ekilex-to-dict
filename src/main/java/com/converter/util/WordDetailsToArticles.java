package com.converter.util;

import com.eki.dict.classes.WordDetails;
import com.eki.dict.classes.WordLexeme;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.converter.classes.Article;
import com.converter.classes.DefinitionBlock;
import com.eki.dict.classes.Classifier;
import com.eki.dict.classes.Form;
import com.eki.dict.classes.Paradigm;
import com.eki.dict.classes.Usage;
import com.eki.dict.classes.Word;

public class WordDetailsToArticles {
    public static Article main(WordDetails wordDetails) {
        Word word = wordDetails.getWord();
        String wordValue = word.getWordValue();

        Set<String> formValues = new HashSet<>();
        for (Paradigm paradigm : word.getParadigms()) {
            for (Form form : paradigm.getForms()) {
                formValues.add(form.getValue());
            }
        }

        List<DefinitionBlock> definitionBlocks = new ArrayList<DefinitionBlock>();
        for (WordLexeme lexeme : wordDetails.getLexemes()) {
            List<Classifier> pos = lexeme.getPos();
            String wordType = pos == null || pos.isEmpty() ? null : pos.get(0).getValue();
            String meaningValue = lexeme.getMeaning().getDefinitions().get(0).getValue();

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
    }
}
