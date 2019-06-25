package br.com.caelum.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class CompraTest {
	
	@Test
	public void deveGerarCadaUmDosProdutosDeUmaCompra() {
		
		String xmlEsperado = "<compra>\r\n" + 
				"  <id>15</id>\r\n" + 
				"  <produtos>\r\n" + 
				"    <produto codigo=\"1587\">\r\n" + 
				"      <nome>geladeira</nome>\r\n" + 
				"      <preco>1000.0</preco>\r\n" + 
				"      <descrição>geladeira duas portas</descrição>\r\n" + 
				"    </produto>\r\n" + 
				"    <produto codigo=\"1588\">\r\n" + 
				"      <nome>ferro de passar</nome>\r\n" + 
				"      <preco>100.0</preco>\r\n" + 
				"      <descrição>ferro com vaporizador</descrição>\r\n" + 
				"    </produto>\r\n" + 
				"  </produtos>\r\n" + 
				"</compra>";
		
		XStream xstream = new XStream();
		xstream.alias("compra", Compra.class);
		xstream.alias("produto", Produto.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		
		List<Produto> produtos = new ArrayList<>();
		
		Produto geladeira = new Produto(1587, "geladeira", 1000.0, "geladeira duas portas");
		Produto ferro = new Produto(1588, "ferro de passar", 100.0, "ferro com vaporizador");
				
		produtos.add(geladeira);
		produtos.add(ferro);
		Compra compra = new Compra(15, produtos);
		
		String xmlGerado = xstream.toXML(compra);
		
		assertEquals(xmlEsperado, xmlGerado);
	}
	
	
	@Test
	public void deveGerarUmaCompraComCadaUmDosProdutosDoXML() {
		
		String xmlDeOrigem = "<compra>\r\n" + 
				"  <id>15</id>\r\n" + 
				"  <produtos>\r\n" + 
				"    <produto codigo=\"1587\">\r\n" + 
				"      <nome>geladeira</nome>\r\n" + 
				"      <preco>1000.0</preco>\r\n" + 
				"      <descrição>geladeira duas portas</descrição>\r\n" + 
				"    </produto>\r\n" + 
				"    <produto codigo=\"1588\">\r\n" + 
				"      <nome>ferro de passar</nome>\r\n" + 
				"      <preco>100.0</preco>\r\n" + 
				"      <descrição>ferro com vaporizador</descrição>\r\n" + 
				"    </produto>\r\n" + 
				"  </produtos>\r\n" + 
				"</compra>";
		
		XStream xstream = new XStream();
		xstream.alias("compra", Compra.class);
		xstream.alias("produto", Produto.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		
		List<Produto> produtos = new ArrayList<>();
		
		Produto geladeira = new Produto(1587, "geladeira", 1000.0, "geladeira duas portas");
		Produto ferro = new Produto(1588, "ferro de passar", 100.0, "ferro com vaporizador");
				
		produtos.add(geladeira);
		produtos.add(ferro);
		
		Compra compraEsperada = new Compra(15, produtos);
		
		Compra compraDesserializada = (Compra) xstream.fromXML(xmlDeOrigem);
		
		assertEquals(compraEsperada, compraDesserializada);
		
	}
	
	
	
	@Test
	public void deveSerializarDuasGeladeirasIguais() {
		
		String xmlEsperado = "<compra>\r\n" + 
				"  <id>15</id>\r\n" + 
				"  <produtos>\r\n" + 
				"    <produto codigo=\"1587\">\r\n" + 
				"      <nome>geladeira</nome>\r\n" + 
				"      <preco>1000.0</preco>\r\n" + 
				"      <descrição>geladeira duas portas</descrição>\r\n" + 
				"    </produto>\r\n" + 
				"    <produto codigo=\"1587\">\r\n" + 
			    "      <nome>geladeira</nome>\r\n" + 
				"      <preco>1000.0</preco>\r\n" + 
				"      <descrição>geladeira duas portas</descrição>\r\n" + 
				"    </produto>\r\n" + 
				"  </produtos>\r\n" + 
				"</compra>";
		
		Produto geladeira = new Produto(1587, "geladeira", 1000.0, "geladeira duas portas");
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(geladeira);
		
		Compra compra = new Compra(15, produtos);
		
		XStream xstream = new XStream();
		xstream.alias("compra", Compra.class);
		xstream.alias("produto", Produto.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		
		xstream.setMode(XStream.NO_REFERENCES);
		
		String xmlGerado = xstream.toXML(compra);
		
		assertEquals(xmlEsperado, xmlGerado);
		
	}
}


















