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
			//Document builder meghívása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse("XMLCf94so.xml");
			
			//Root normalizálása
			Element root = doc.getDocumentElement();
			root.normalize();

			print(root);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void print(Node root) {

		//Node nevének kiírása
		if (root.getNodeName() != "#text") {
			System.out.println(root.getNodeName());
		}
		
		//Gyerekek Node listába helyezése
		NodeList children = root.getChildNodes();

		
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			boolean isComplex = child.getTextContent().contains("\n");

			//Attribute kiírása
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
			
			//Név és tartalom kíírása
			if (isComplex) {
				print(child);
			} else {
				System.out.print(" " + child.getNodeName());
				System.out.println(": " + child.getTextContent());
			}
		}
	}
}

