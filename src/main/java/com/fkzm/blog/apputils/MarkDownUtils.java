package com.fkzm.blog.apputils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkDownUtils {
    /**
     * markdown 格式转成成 HTML
     * @param markdownText
     * @return
     */
    public static String markdownToHtml(String markdownText){
        Parser parser=Parser.builder().build();
        Node document = parser.parse(markdownText);
        HtmlRenderer renderer=HtmlRenderer.builder().build();
        return renderer.render(document);

    }
    /**
     * 增加扩展[标题锚点,表格生成]
     * Markdown 转换 HTML
     * @param markdownText
     * @return
     */
    public static String markdownToHtmlExtension(String markdownText){
        //对每个标题生成 Id
        Set<Extension> headingAnchorExtension= Collections.singleton(HeadingAnchorExtension.create());
        //table表格转换成 HTML
        List<Extension> tableExtension= Arrays.asList(TablesExtension.create());
        Parser parser=Parser.builder().extensions(tableExtension).build();
        Node document =parser.parse(markdownText);
        HtmlRenderer renderer=HtmlRenderer.builder().extensions(headingAnchorExtension)
                .extensions(tableExtension)
                .attributeProviderFactory(e -> new CustomAttributeProvider()).build();

         return renderer.render(document);
    }

    private static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            if(node instanceof Link){
                map.put("target", "_blank");
            }
            if(node instanceof TableBlock){
                map.put("class","ui celled table");
            }
        }
    }
}
