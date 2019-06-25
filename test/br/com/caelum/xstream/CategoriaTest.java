package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller.CircularReferenceException;

public class CategoriaTest {
	
	@Test(expected = CircularReferenceException.class)
	public void deveSerializarUmCiclo() {
		
		String xmlEsperado = "";
		
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		
		esporte.setPai(geral);
		
		
		XStream xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);   // cria novas referencias para os objetos
		xstream.alias("categoria", Categoria.class);
		
		String xmlGerado = xstream.toXML(esporte);
		
		assertEquals(xmlEsperado, xmlGerado);
	}
}
