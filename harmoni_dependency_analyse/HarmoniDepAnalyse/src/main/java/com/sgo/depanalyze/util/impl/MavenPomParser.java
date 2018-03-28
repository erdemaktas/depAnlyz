package com.sgo.depanalyze.util.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.util.xml.SimpleNamespaceContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.sgo.depanalyze.util.MavenPomProperties;
import com.sgo.depanalyze.util.intf.IMavenPomParser;

/**
 * The Class MavenPomParser.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Feb 6, 2014 2:11:26 PM
 */
public class MavenPomParser implements IMavenPomParser {
    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IMavenPomParser#parsePomFile(java.util.jar.JarFile, java.util.zip.ZipEntry)
     */
    @Override
    public MavenPomProperties parsePomFile(JarFile jarFile, ZipEntry zipEntry) throws RuntimeException {
        MavenPomProperties result = new MavenPomProperties();
        InputStream input = null;
        try {
            input = jarFile.getInputStream(zipEntry);
            Document document = parserXML(input);
            //
            XPath xpath = XPathFactory.newInstance().newXPath();
            SimpleNamespaceContext nsCtx = new SimpleNamespaceContext();
            xpath.setNamespaceContext(nsCtx);
            nsCtx.bindNamespaceUri("pom", "http://maven.apache.org/POM/4.0.0");
            // parse version
            Node node = (Node)xpath.evaluate("/pom:project/pom:version/text()", document, XPathConstants.NODE);
            if (node == null) {
                node = (Node) xpath.evaluate("/pom:project/pom:parent/pom:version/text()", document, XPathConstants.NODE);
            }
            result.setVersion(node!=null ? node.getTextContent() : "");
            // parse groupId
            node = (Node) xpath.evaluate("/pom:project/pom:groupId/text()", document, XPathConstants.NODE);
            if (node == null) {
                node = (Node) xpath.evaluate("/pom:project/pom:parent/pom:groupId/text()", document, XPathConstants.NODE);
            }
            result.setGroupId(node!=null ? node.getTextContent() : "");
            // parse artifactId
            node = (Node)xpath.evaluate("/pom:project/pom:artifactId/text()", document, XPathConstants.NODE);
            result.setArtifactId(node!=null ? node.getTextContent() : "");
            //
            node = (Node)xpath.evaluate("/pom:project/pom:description/text()", document, XPathConstants.NODE);
            result.setDescription(node!=null ? node.getTextContent() : "");
            //
            node = (Node)xpath.evaluate("/pom:project/pom:name/text()", document, XPathConstants.NODE);
            result.setDescription(node!=null ? node.getTextContent() : "");
        } catch (Exception e) {
            throw new RuntimeException(String.format("Exception occured for jarFile:'%s' zipEntry:'%s'", jarFile.getName(), zipEntry.getName()), e);
        }
        return result;
    }

    private Document parserXML(InputStream input) throws RuntimeException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute("http://xml.org/sax/features/namespaces", true);
        factory.setAttribute("http://xml.org/sax/features/validation", false);
        factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        factory.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setNamespaceAware(true);
        factory.setIgnoringElementContentWhitespace(false);
        factory.setIgnoringComments(false);
        factory.setValidating(false);
        Document document = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(input);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("ParserConfigurationException occured", e);
        } catch (SAXException e) {
            throw new RuntimeException("SAXException occured", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occured", e);
        }
        return document;
    }
}
