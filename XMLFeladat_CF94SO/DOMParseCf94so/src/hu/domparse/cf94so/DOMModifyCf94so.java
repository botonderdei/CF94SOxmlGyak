package hu.domparse.cf94so;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOMModifyCf94so {
	
public static void main(String[] args) {
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse("XMLCf94so.xml");
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();

	         String expression = "/nyilvantartas/filmek/film";	        
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
	            doc, XPathConstants.NODESET);

	         for (int i = 0; i < nodeList.getLength(); i++) {
	            Node nNode = nodeList.item(i);
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	              if( Integer.parseInt(eElement.getAttribute("fid")) == 0) {
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

