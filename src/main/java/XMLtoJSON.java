import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class XMLtoJSON {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> list = parseXML("data.xml");
        String json = CSVtoJSON.listToJson(list);
        writeString(json);
    }

    public static void writeString(String str) {
        try (FileWriter fw = new FileWriter("data2.json")) {
            fw.write(str);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<Employee> parseXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));

        Node root = doc.getDocumentElement();
        NodeList nodelist = root.getChildNodes();

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < nodelist.getLength(); i++) {
            Node node = nodelist.item(i);
            if (Node.ELEMENT_NODE != node.getNodeType()) continue;
            Element employee = (Element) node;
            employees.add(new Employee(Long.parseLong(employee.getElementsByTagName("id").item(0).getTextContent()),
                    employee.getElementsByTagName("firstName").item(0).getTextContent(),
                    employee.getElementsByTagName("lastName").item(0).getTextContent(),
                    employee.getElementsByTagName("country").item(0).getTextContent(),
                    Integer.parseInt(employee.getElementsByTagName("age").item(0).getTextContent())));
        }

        return employees;
    }
}
