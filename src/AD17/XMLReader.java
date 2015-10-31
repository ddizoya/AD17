package AD17;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.print.attribute.standard.PagesPerMinute;
import javax.xml.XMLConstants;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLReader {
	private XMLStreamReader xstrm = null;
	private ArrayList<Product> listap = new ArrayList<>();
	private String[] lecturaXML = new String[9];

	private void leerXML() {
		try {
			xstrm = XMLInputFactory.newInstance()
					.createXMLStreamReader(new FileInputStream("C:\\Users\\David\\Desktop\\products.xml"));

			int x = 0;
			while (xstrm.hasNext()) {
				xstrm.next();
				switch (xstrm.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					if (xstrm.getAttributeCount() == 1){
						lecturaXML[x] = xstrm.getAttributeValue(0);
						x++;
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					lecturaXML[x] = xstrm.getText();
					x++;
					break;
				}
			}		
			
			x = 0;
			while (x < lecturaXML.length) {
				listap.add(new Product(lecturaXML[x], lecturaXML[x+1], Integer.parseInt(lecturaXML[x+2])));
				x += 3;
			}	
			xstrm.close();
			
		} catch (FileNotFoundException | XMLStreamException | FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimirArrayList(){
		
		leerXML();
		int i = 0;
		while (i < listap.size()){
			System.out.println(listap.get(i).getCodigo() + " " + listap.get(i).getDescripcion() + " " + listap.get(i).getPrecio());
			i++;
		}
	}

	public static void main(String[] args) {
		new XMLReader().imprimirArrayList();
	}

}
