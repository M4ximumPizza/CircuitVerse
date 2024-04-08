package mi.m4x.project.circuitverse.utils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParserUtils {
    public static Document parseXml(File xmlFile) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(xmlFile);

        document.getDocumentElement().normalize();

        return document;
    }
}