package saxcf94so;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class XsdValidation {
	
	
static File schemaFile = new File("/home/botond/Desktop/cats.xsd");
static Source xmlFile = new StreamSource(new File("/home/botond/Desktop/cats.xml"));
static SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	

public static void main(String argv[]) {
		   
	try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			System.out.println(xmlFile.getSystemId() + " is valid");
			} catch (SAXException e) {
			System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
			} catch (IOException e) {}
	   
	   }
}
