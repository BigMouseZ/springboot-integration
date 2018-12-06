package com.integration.mappackage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by ZhangGang on 2018/12/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestXml {

    @Test
    public  void test() {
        try {
            createXML();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        public void createXML() throws FileNotFoundException {
            Document doc = null;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            try {
                DocumentBuilder docBuilder = dbf.newDocumentBuilder();
                doc = docBuilder.newDocument();

                Element country = doc.createElement("country");

                doc.appendChild(country);

                country.appendChild(doc.createTextNode("/n    "));

                Element china = doc.createElement("china");
                country.appendChild(china);

                china.appendChild(doc.createTextNode("/n        "));

                Element city = doc.createElement("city");
                city.appendChild(doc.createTextNode("Beijing"));
                china.appendChild(city);

                china.appendChild(doc.createTextNode("/n        "));

                city = doc.createElement("city");
                city.appendChild(doc.createTextNode("Shanghai"));
                china.appendChild(city);

                china.appendChild(doc.createTextNode("/n    "));

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                File file = new File("E://cities.xml");
                FileOutputStream out = new FileOutputStream(file);
                StreamResult xmlResult = new StreamResult(out);
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(doc), xmlResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
