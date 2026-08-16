package com.converter.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

public class KindleDictionaryWriter {

    private static final int ENTRIES_PER_FILE = 1000;

    private final String indexName;
    private final String inLanguage;
    private final String outLanguage;
    private final String title;
    private final String identifier;

    public KindleDictionaryWriter(String indexName, String inLanguage, String outLanguage, String version) {
        this.indexName = indexName;
        this.inLanguage = inLanguage;
        this.outLanguage = outLanguage;
        this.title = version == null || version.isBlank()
                ? "Estonian Dictionary"
                : "Estonian Dictionary v" + version;
        this.identifier = version == null || version.isBlank()
                ? "ekilex-to-dict"
                : "ekilex-to-dict-v" + version;
    }

    public void write(List<Article> articles, Path outputDir) throws IOException {
        Files.createDirectories(outputDir);

        List<String> contentFileNames = writeContentFiles(articles, outputDir);
        writeCoverImage(outputDir);
        writeOpf(contentFileNames, outputDir);
        writeNcx(contentFileNames, outputDir);
    }

    private void writeCoverImage(Path outputDir) throws IOException {
        BufferedImage cover = new BufferedImage(600, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = cover.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(new Color(0x2b3a55));
        g.fillRect(0, 0, cover.getWidth(), cover.getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 42));
        g.drawString("Eesti sõnastik", 40, 380);
        g.dispose();

        ImageIO.write(cover, "jpg", outputDir.resolve("cover.jpg").toFile());
    }

    private List<String> writeContentFiles(List<Article> articles, Path outputDir) throws IOException {
        List<String> fileNames = new java.util.ArrayList<>();

        for (int start = 0, part = 1; start < articles.size(); start += ENTRIES_PER_FILE, part++) {
            int end = Math.min(start + ENTRIES_PER_FILE, articles.size());
            String fileName = String.format("content-%04d.xhtml", part);
            fileNames.add(fileName);

            StringBuilder body = new StringBuilder();
            for (Article article : articles.subList(start, end)) {
                body.append(buildEntryHtml(article));
            }

            String xhtml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:idx=\"https://kindlegen.s3.amazonaws.com/AmazonKindlePublishingGuidelines.pdf\" xmlns:mbp=\"https://kindlegen.s3.amazonaws.com/AmazonKindlePublishingGuidelines.pdf\">\n"
                    +
                    "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/></head>\n" +
                    "<body>\n<mbp:frameset>\n" + body + "</mbp:frameset>\n</body>\n</html>\n";

            Files.writeString(outputDir.resolve(fileName), xhtml, StandardCharsets.UTF_8);
        }

        return fileNames;
    }

    private String buildEntryHtml(Article article) {
        if (article.getWordValue() != null && article.getWordValue().contains(" ")) {
            System.err.println("warning: multi-word headword unreachable from lookup panel: " + article.getWordValue());
        }

        StringBuilder entry = new StringBuilder();
        entry.append("<idx:entry name=\"").append(indexName).append("\" scriptable=\"yes\" spell=\"yes\">\n");
        entry.append("<div class=\"entry\">\n");
        appendOrthBlock(entry, article);
        appendDefinitionBlocks(entry, article.getDefinitionBlocks());
        entry.append("</div>\n");
        entry.append("</idx:entry>\n<hr/>\n");

        return entry.toString();
    }

    private void appendOrthBlock(StringBuilder entry, Article article) {
        String wordValue = escapeXml(article.getWordValue());

        Set<String> inflections = new LinkedHashSet<>(article.getForms());
        inflections.remove(article.getWordValue());
        inflections.remove("");
        inflections.remove("-");

        entry.append("<idx:orth value=\"").append(wordValue).append("\"><h5>").append(wordValue).append("</h5>\n");

        if (!inflections.isEmpty()) {
            entry.append("<idx:infl>\n");
            for (String form : inflections) {
                entry.append("<idx:iform value=\"").append(escapeXml(form)).append("\"/>\n");
            }
            entry.append("</idx:infl>\n");
        }
        entry.append("</idx:orth>\n");
    }

    private void appendDefinitionBlocks(StringBuilder entry, List<DefinitionBlock> blocks) {
        if (blocks == null) {
            return;
        }

        for (DefinitionBlock block : blocks) {
            entry.append("<p>");
            if (block.getWordType() != null) {
                entry.append("<i>").append(escapeXml(block.getWordType())).append("</i> ");
            }
            if (block.getMeaning() != null) {
                entry.append(escapeXml(block.getMeaning()));
            }
            entry.append("</p>\n");

            if (block.getUsages() != null) {
                for (String usage : block.getUsages()) {
                    entry.append("<p class=\"example\">").append(escapeXml(usage)).append("</p>\n");
                }
            }
        }
    }

    private static String escapeXml(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
                .replace("'", "&apos;");
    }

    private void writeOpf(List<String> contentFileNames, Path outputDir) throws IOException {
        StringBuilder manifest = new StringBuilder();
        StringBuilder spine = new StringBuilder();
        for (String fileName : contentFileNames) {
            String id = fileName.replace(".xhtml", "");
            manifest.append("<item id=\"").append(id).append("\" href=\"").append(fileName)
                    .append("\" media-type=\"application/xhtml+xml\"/>\n");
            spine.append("<itemref idref=\"").append(id).append("\"/>\n");
        }

        String firstFile = contentFileNames.isEmpty() ? "content-0001.xhtml" : contentFileNames.get(0);

        String opf = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<package xmlns=\"http://www.idpf.org/2007/opf\" unique-identifier=\"BookId\" version=\"2.0\">\n" +
                "<metadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:opf=\"http://www.idpf.org/2007/opf\">\n"
                +
                "<dc:title>" + title + "</dc:title>\n" +
                "<dc:language>" + inLanguage + "</dc:language>\n" +
                "<dc:identifier id=\"BookId\">" + identifier + "</dc:identifier>\n" +
                "<meta name=\"cover\" content=\"cover-image\"/>\n" +
                "<x-metadata>\n" +
                "<DictionaryInLanguage>" + inLanguage + "</DictionaryInLanguage>\n" +
                "<DictionaryOutLanguage>" + outLanguage + "</DictionaryOutLanguage>\n" +
                "<DefaultLookupIndex>" + indexName + "</DefaultLookupIndex>\n" +
                "</x-metadata>\n" +
                "</metadata>\n" +
                "<manifest>\n" +
                "<item id=\"ncx\" href=\"toc.ncx\" media-type=\"application/x-dtbncx+xml\"/>\n" +
                "<item id=\"cover-image\" href=\"cover.jpg\" media-type=\"image/jpeg\" properties=\"cover-image\"/>\n" +
                manifest +
                "</manifest>\n" +
                "<spine toc=\"ncx\">\n" + spine + "</spine>\n" +
                "<guide>\n" +
                "<reference type=\"index\" title=\"Index\" href=\"" + firstFile + "\"/>\n" +
                "</guide>\n" +
                "</package>\n";

        Files.writeString(outputDir.resolve("content.opf"), opf, StandardCharsets.UTF_8);
    }

    private void writeNcx(List<String> contentFileNames, Path outputDir) throws IOException {
        String firstFile = contentFileNames.isEmpty() ? "content-0001.xhtml" : contentFileNames.get(0);

        String ncx = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<ncx xmlns=\"http://www.daisy.org/z3986/2005/ncx/\" version=\"2005-1\">\n" +
                "<head>\n" +
                "<meta name=\"dtb:uid\" content=\"" + identifier + "\"/>\n" +
                "</head>\n" +
                "<docTitle><text>" + title + "</text></docTitle>\n" +
                "<navMap>\n" +
                "<navPoint id=\"navpoint-1\" playOrder=\"1\">\n" +
                "<navLabel><text>Dictionary</text></navLabel>\n" +
                "<content src=\"" + firstFile + "\"/>\n" +
                "</navPoint>\n" +
                "</navMap>\n" +
                "</ncx>\n";

        Files.writeString(outputDir.resolve("toc.ncx"), ncx, StandardCharsets.UTF_8);
    }
}
