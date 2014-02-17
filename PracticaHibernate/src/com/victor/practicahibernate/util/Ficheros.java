package com.victor.practicahibernate.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.victor.practicahibernate.Circuitos;
import com.victor.practicahibernate.Coches;
import com.victor.practicahibernate.Equipos;
import com.victor.practicahibernate.Pilotos;
import com.victor.practicahibernate.Ruedas;
import com.victor.practicahibernate.Sponsors;

/**
 * Clase que contiene los métodos para leer y escribir
 * ficheros en XML
 * @author Victor
 *
 */
public class Ficheros {
	
	/*
	 TODO
	
public static void escribirCircuitosXML(ArrayList<Circuitos> circuitos, String rutaFichero) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation dom = builder.getDOMImplementation();
			documento = dom.createDocument(null,  "xml", null);
			
			Element raiz = documento.createElement("Circuitos");
			documento.getDocumentElement().appendChild(raiz);
			
			Element nodoPersona = null, nodoDatos = null;
			Text texto = null;
			for (Circuitos circuito : circuitos) {
				nodoPersona = documento.createElement("Circuito");
				raiz.appendChild(nodoPersona);
				
				nodoDatos = documento.createElement("nombre");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(circuito.getNombre());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("ubicacion");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(circuito.getUbicacion());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("distancia");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(String.valueOf(circuito.getDistancia()));
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("pilotos");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(circuito.getPilotoses());
				nodoDatos.appendChild(texto);
			}
			
			Source source = new DOMSource(documento);
			Result resultado = new StreamResult(new File(rutaFichero));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, resultado);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}
	
	*/
	/**
	 * Método para escribir los datos de los coches a XML
	 * @param coches ArrayList con los datos de los coches
	 * @param rutaFichero ruta donde almacenamos el XML
	 */
public static void escribirCochesXML(ArrayList<Coches> coches, String rutaFichero) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation dom = builder.getDOMImplementation();
			documento = dom.createDocument(null,  "xml", null);
			
			Element raiz = documento.createElement("Coches");
			documento.getDocumentElement().appendChild(raiz);
			
			Element nodoPersona = null, nodoDatos = null;
			Text texto = null;
			for (Coches coche : coches) {
				nodoPersona = documento.createElement("Coche");
				raiz.appendChild(nodoPersona);
				
				nodoDatos = documento.createElement("nombre");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(coche.getNombre());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("color");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(coche.getColor());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("peso");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(String.valueOf(coche.getPeso()));
				nodoDatos.appendChild(texto);
				/*
				nodoDatos = documento.createElement("piloto");
				nodoPersona.appendChild(nodoDatos);
				
				texto = documento.createTextNode(coche.getPilotos());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("equipo");
				nodoPersona.appendChild(nodoDatos);

				texto = documento.createTextNode(coche.getEquipos());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("ruedas");
				nodoPersona.appendChild(nodoDatos);

				texto = documento.createTextNode(coche.getRuedases());
				nodoDatos.appendChild(texto);*/
			}
			
			Source source = new DOMSource(documento);
			Result resultado = new StreamResult(new File(rutaFichero));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, resultado);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}

/*
 TODO

public static void escribirEquiposXML(ArrayList<Equipos> equipos, String rutaFichero) {
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	Document documento = null;
	
	try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation dom = builder.getDOMImplementation();
		documento = dom.createDocument(null,  "xml", null);
		
		Element raiz = documento.createElement("Equipos");
		documento.getDocumentElement().appendChild(raiz);
		
		Element nodoPersona = null, nodoDatos = null;
		Text texto = null;
		for (Equipos equipo : equipos) {
			nodoPersona = documento.createElement("Equipo");
			raiz.appendChild(nodoPersona);
			
			nodoDatos = documento.createElement("nombre");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(equipo.getNombre());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("fundador");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(equipo.getFundador());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("presidente");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode((equipo.getPresidente());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("pilotos");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(equipo.getPilotoses());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("coches");
			nodoPersona.appendChild(nodoDatos);

			texto = documento.createTextNode(equipo.getCocheses());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("sponsors");
			nodoPersona.appendChild(nodoDatos);

			texto = documento.createTextNode(equipo.getSponsorses());
			nodoDatos.appendChild(texto);
		}
		
		Source source = new DOMSource(documento);
		Result resultado = new StreamResult(new File(rutaFichero));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, resultado);
		
	} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	} catch (TransformerConfigurationException tce) {
		tce.printStackTrace();
	} catch (TransformerException te) {
		te.printStackTrace();
	}
}

public static void escribirPilotosXML(ArrayList<Pilotos> pilotos, String rutaFichero) {
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	Document documento = null;
	
	try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation dom = builder.getDOMImplementation();
		documento = dom.createDocument(null,  "xml", null);
		
		Element raiz = documento.createElement("Pilotos");
		documento.getDocumentElement().appendChild(raiz);
		
		Element nodoPersona = null, nodoDatos = null;
		Text texto = null;
		for (Pilotos piloto : pilotos) {
			nodoPersona = documento.createElement("Piloto");
			raiz.appendChild(nodoPersona);
			
			nodoDatos = documento.createElement("nombre");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(piloto.getNombre());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("nacionalidad");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(piloto.getNacionalidad());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("dorsal");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(String.valueOf(piloto.getDorsal()));
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("equipo");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(piloto.getEquipos());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("coches");
			nodoPersona.appendChild(nodoDatos);

			texto = documento.createTextNode(piloto.getCocheses());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("circuitos");
			nodoPersona.appendChild(nodoDatos);

			texto = documento.createTextNode(piloto.getCircuitoses());
			nodoDatos.appendChild(texto);
		}
		
		Source source = new DOMSource(documento);
		Result resultado = new StreamResult(new File(rutaFichero));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, resultado);
		
	} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	} catch (TransformerConfigurationException tce) {
		tce.printStackTrace();
	} catch (TransformerException te) {
		te.printStackTrace();
	}
}

public static void escribirRuedasXML(ArrayList<Ruedas> ruedas, String rutaFichero) {
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	Document documento = null;
	
	try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation dom = builder.getDOMImplementation();
		documento = dom.createDocument(null,  "xml", null);
		
		Element raiz = documento.createElement("Ruedas");
		documento.getDocumentElement().appendChild(raiz);
		
		Element nodoPersona = null, nodoDatos = null;
		Text texto = null;
		for (Ruedas rueda : ruedas) {
			nodoPersona = documento.createElement("Rueda");
			raiz.appendChild(nodoPersona);
			
			nodoDatos = documento.createElement("pulgadas");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(String.valueOf(rueda.getPulgadas()));
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("tipo_neumatico");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(rueda.getTipoNeumatico());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("peso");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(String.valueOf(rueda.getPeso()));
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("coche");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(rueda.getCoches());
			nodoDatos.appendChild(texto);
		}
		
		Source source = new DOMSource(documento);
		Result resultado = new StreamResult(new File(rutaFichero));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, resultado);
		
	} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	} catch (TransformerConfigurationException tce) {
		tce.printStackTrace();
	} catch (TransformerException te) {
		te.printStackTrace();
	}
}

public static void escribirSponsorsXML(ArrayList<Sponsors> sponsors, String rutaFichero) {
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	Document documento = null;
	
	try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation dom = builder.getDOMImplementation();
		documento = dom.createDocument(null,  "xml", null);
		
		Element raiz = documento.createElement("Sponsors");
		documento.getDocumentElement().appendChild(raiz);
		
		Element nodoPersona = null, nodoDatos = null;
		Text texto = null;
		for (Sponsors sponsor : sponsors) {
			nodoPersona = documento.createElement("Sponsor");
			raiz.appendChild(nodoPersona);
			
			nodoDatos = documento.createElement("nombre");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(sponsor.getNombre());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("presidente");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(sponsor.getPresidente());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("web");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(sponsor.getWeb());
			nodoDatos.appendChild(texto);
			
			nodoDatos = documento.createElement("equipos");
			nodoPersona.appendChild(nodoDatos);
			
			texto = documento.createTextNode(sponsor.getEquiposes());
			nodoDatos.appendChild(texto);
		}
		
		Source source = new DOMSource(documento);
		Result resultado = new StreamResult(new File(rutaFichero));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, resultado);
		
	} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	} catch (TransformerConfigurationException tce) {
		tce.printStackTrace();
	} catch (TransformerException te) {
		te.printStackTrace();
	}
}
	
	*/
	/*
	 * Lee un fichero XML y muestra el contenido por pantalla
	 * TODO Almacenar el contenido en vez de mostrarlo por pantalla
	 */
	public void leerFicheroXML() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File("coches.xml"));
			
			// Recorre cada uno de los nodos 'Persona'
			NodeList personas = documento.getElementsByTagName("Coche");
			for (int i = 0; i < personas.getLength(); i++) {
				Node persona = personas.item(i);
				Element elemento = (Element) persona;

				System.out.print(elemento.getElementsByTagName("modelo").item(0).
						getChildNodes().item(0).getNodeValue());
				System.out.print(" ");
				
				System.out.print(elemento.getElementsByTagName("tipoTransmision").item(0).
						getChildNodes().item(0).getNodeValue());
				System.out.print(" ");
				
				System.out.print(elemento.getElementsByTagName("fechaCompra").item(0).
						getChildNodes().item(0).getNodeValue());
				System.out.print(" ");
				
				System.out.print(elemento.getElementsByTagName("caballos").item(0).
						getChildNodes().item(0).getNodeValue());
				System.out.print(" ");
				System.out.print(elemento.getElementsByTagName("peso").item(0).
						getChildNodes().item(0).getNodeValue());
				System.out.println();
			}
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		}
	}

}
