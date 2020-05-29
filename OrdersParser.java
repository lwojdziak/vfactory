
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdersParser {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;

    public OrdersParser() {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<Order> parseOrder(String order) throws IOException, SAXException {
        List<Order> orders = new ArrayList<>();

        Document document = builder.parse(new ByteArrayInputStream(order.getBytes()));
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        NodeList nList = document.getElementsByTagName("item");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String vehicleType = eElement.getAttribute("type");
                orders.add(new Order(VehicleType.fromString(vehicleType)));
            }
        }
        return orders;
    }
}
