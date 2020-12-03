package hu.domparse.cf94so;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class DOMModifyCf94so {
	
public static void main(String[] args) throws TransformerException {
		
		try {
			
			//Document builder inicializálása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse("XMLCf94so.xml");
			String filepath = "C:\\Users\\boton\\eclipse-workspace\\XMLFeladat_CF94SO\\DOMParseCf94so\\XMLCf94so.xml";
	         doc.getDocumentElement().normalize();
	         
	         //Xpath
	         XPath xPath =  XPathFactory.newInstance().newXPath();
	         
	         //A keresett kifejezés
	         String expression = "/nyilvantartas/filmek/film";	        
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
	            doc, XPathConstants.NODESET);

	         for (int i = 0; i < nodeList.getLength(); i++) {
	            Node nNode = nodeList.item(i);
	            
	            //Megkeresi a film ID 0-át
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	              if( Integer.parseInt(eElement.getAttribute("fid")) == 0) {
	            //Beállítja 111 percre a film hosszát majd kiírja az új adatait
	               eElement.getElementsByTagName("hossz").item(0).setTextContent("111 perc");
	  	           System.out.println("\nCurrent Element :" + nNode.getNodeName());
	               System.out.println("Film id :" + eElement.getAttribute("fid"));
	               System.out.println("Cim : " 
	                  + eElement
	                  .getElementsByTagName("cim")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Hossz : " 
	                  + eElement
	                  .getElementsByTagName("hossz")
	                  .item(0)
	                  .getTextContent());
	               //File módosítása
	               TransformerFactory transformerFactory = TransformerFactory.newInstance();
	               Transformer transformer = transformerFactory.newTransformer();
	               DOMSource source = new DOMSource(doc);
	               StreamResult result = new StreamResult(new File(filepath));
	               transformer.transform(source, result);

	               System.out.println("Done");
	              }
	            }
	         }
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
		
	}

}

