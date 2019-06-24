package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class ProdutoTest {
	
	@Test
	public void deveGerarXMLComNomePrecoEDescricaoAdequados() {
		
		String xmlEsperado = "<produto codigo=\"1587\">\n" + 
				"  <nome>geladeira</nome>\r\n" + 
				"  <preco>1000.0</preco>\r\n" + 
				"  <descrição>geladeira duas portas</descrição>\r\n" + 
				"</produto>";
		
		Produto geladeira = new Produto(1587, "geladeira", 1000.0, "geladeira duas portas");
		
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.aliasField("descrição", Produto.class, "descricao"); // novo nome do campo, classe e atributo da classe
		xstream.useAttributeFor(Produto.class, "codigo");
		String xmlGerado = xstream.toXML(geladeira);
		
		
		assertEquals(xmlEsperado, xmlGerado);
	}
}


















