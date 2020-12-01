package hu.domparse.cf94so;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;

public class DOMReadCf94so {

	public static void main(String[] args) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse("XMLCf94so.xml");

			Element root = doc.getDocumentElement();
			root.normalize();

			print(root);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void print(Node root) {

		String nodename = root.getNodeName();
		if (!nodename.contains("text")) {
			System.out.println(nodename);
		}

		NodeList children = root.getChildNodes();

		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			boolean isComplex = child.getTextContent().contains("\n");

			if (child.hasAttributes()) {
				NamedNodeMap attributes = child.getAttributes();
				int numAttrs = attributes.getLength();
				for (int j = 0; j < numAttrs; j++) {
					Attr attr = (Attr) attributes.item(j);

					String attrName = attr.getNodeName();
					String attrValue = attr.getNodeValue();

					System.out.println(" " + attrName + " : " + attrValue);
				}
			}

			if (isComplex) {
				print(child);
			} else {
				System.out.print(" " + child.getNodeName());
				System.out.println(": " + child.getTextContent());
			}
		}
	}
}

