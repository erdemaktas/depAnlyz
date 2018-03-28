package com.sgo.depanalyze.util.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.HbmMappingProperties;
import com.sgo.depanalyze.util.intf.IHbmMappingParser;

public class DefaultHbmMappingParser implements IHbmMappingParser {
    private static final String MAP_KEY_TABLE = "table";
    private static final String MAP_KEY_CLASS = "class";
    
    private static final String NODE_CLASS = "class";
    private static final String ATTRIBUTE_TABLE = "table";
    private static final String ATTRIBUTE_NAME = "name";
    
    private static Logger logger = Logger.getLogger(DefaultHbmMappingParser.class);

    @Override
    public HbmMappingProperties parseHbm(InputStream input, String entry) {
        HbmMappingProperties result = null;
        String entryName = entry.replace(Constants.HBM_ROOT_PATH, "");
        if (entryName.indexOf("/") == -1) {
            entryName = "/" + entryName;
        }
        String dataSource = entryName.split("/")[0];
        if (dataSource == null || dataSource.isEmpty()) {
            dataSource = Constants.DATASOURCE_CBS;
        }
         
        try {
            Document doc = parserXML(input);
            Map<String, String> attributes = visit(doc, 0);
            if (attributes != null) {
                if (attributes.get(MAP_KEY_CLASS) != null && attributes.get(MAP_KEY_TABLE) != null) {
                    result = new HbmMappingProperties();
                    result.setClassName(attributes.get(MAP_KEY_CLASS).toString());
                    result.setTableName(attributes.get(MAP_KEY_TABLE).toString());
                    result.setDataSource(dataSource.toUpperCase());
                    result.setEntryName(entryName);
                }
            } else {
                logger.warn("hibernate mapping file cannot be parsed successfully: " + entry);
            }
        } catch (Exception e) {
            logger.error("hibernate mapping file parse exception: " + e.getMessage(), e);
        }
        return result;
    }

    private Document parserXML(InputStream input) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute("http://xml.org/sax/features/namespaces", true);
        factory.setAttribute("http://xml.org/sax/features/validation", false);
        factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setNamespaceAware(true);
        factory.setIgnoringElementContentWhitespace(false);
        factory.setIgnoringComments(false);
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(input);
        return document;
    }

    private Map<String, String> visit(Node node, int level) {
        Map<String, String> attributes = null;
        NodeList nl = node.getChildNodes();
        for (int i = 0, cnt = nl.getLength(); i < cnt; i++) {
            if (nl.item(i).hasAttributes()) {
                attributes = extractAttributes(nl.item(i));
                if (attributes != null && attributes.get(MAP_KEY_CLASS) != null && attributes.get(MAP_KEY_TABLE) != null) {
                    return attributes;
                }
            }
            attributes = visit(nl.item(i), level + 1);
            if (attributes != null && attributes.get(MAP_KEY_CLASS) != null && attributes.get(MAP_KEY_TABLE) != null) {
                return attributes;
            }
        }
        return attributes;
    }

    private Map<String, String> extractAttributes(Node node) {
        Map<String, String> map = new HashMap<String, String>();
        NamedNodeMap attrs = node.getAttributes();
        if (NODE_CLASS.equals(node.getNodeName())) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attribute = (Attr) attrs.item(i);
                if (ATTRIBUTE_NAME.equals(attribute.getName())) {
                    map.put(MAP_KEY_CLASS, attribute.getValue());
                } else if (ATTRIBUTE_TABLE.equals(attribute.getName())) {
                    map.put(MAP_KEY_TABLE, attribute.getValue());
                }
            }
        }
        return map;
    }
}
