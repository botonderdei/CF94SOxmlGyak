package saxcf94so;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxCF94SO {

   public static void main(String argv[]) {

    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

	boolean name = false;
	boolean age = false;
	boolean breed = false;

	public void startElement(String uri, String localName,String qName,
                Attributes attributes) throws SAXException {

		System.out.println(qName + " start");

		if (qName.equalsIgnoreCase("NAME")) {
			name = true;
		}

		if (qName.equalsIgnoreCase("AGE")) {
			age = true;
		}

		if (qName.equalsIgnoreCase("BREED")) {
			breed = true;
		}

	}

	public void endElement(String uri, String localName,
		String qName) throws SAXException {

		System.out.println(qName + " end");

	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (name) {
			System.out.println("Name : " + new String(ch, start, length));
			name = false;
		}

		if (age) {
			System.out.println("Age : " + new String(ch, start, length));
			age = false;
		}

		if (breed) {
			System.out.println("Breed : " + new String(ch, start, length));
			breed = false;
		}

	}

     };

       saxParser.parse("/home/botond/Desktop/cats.xml", handler);

     } catch (Exception e) {
       e.printStackTrace();
     }

   }

}