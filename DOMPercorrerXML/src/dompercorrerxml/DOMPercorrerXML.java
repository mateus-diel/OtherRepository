/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dompercorrerxml;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dionatan.k
 */
public class DOMPercorrerXML {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Instancia o parser
        DocumentBuilderFactory b = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = b.newDocumentBuilder();

        //aplica o parser no documento
        Document doc = builder.parse("produto.xml");

        //Recupera o elemento raiz
        Element raiz = doc.getDocumentElement();

        percorreArvoreXML((Node) raiz);

        System.out.println("");
    }

    /**
     * Esse método pode ser melhorado para atender outros tipos de Node
     * Exemplo: Node.ATTRIBUTE_NODE
     */
    private static void percorreArvoreXML(Node node) {
        int i;

        // verifica se o Node é do tipo Element e mostra o nome 
        // Exemplo: <Produto>
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.print("<" + node.getNodeName() + ">");
        }

        // verifica se o Node é do tipo Text e mostra o valor 
        if (node.getNodeType() == Node.TEXT_NODE) {
            System.out.print(node.getNodeValue());
        }

        if (node.hasChildNodes()) {
            NodeList filhos = node.getChildNodes();
            for (i = 0; i < filhos.getLength(); i++) {
                percorreArvoreXML(filhos.item(i));
            }
        }

        // verifica se o Node é do tipo Element e fecha a tag
        // Exemplo: </Produto>
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.print("</" + node.getNodeName() + ">");
        }
    }

}
