package dev.zxilly.gradle.keeper.parsers

import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathFactory

class XmlParser : Parser {
    private val instance = XPathFactory.newInstance()
    private lateinit var document: Document

    override fun parse(content: String) {
        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(content.byteInputStream()).let {
            document = it
        }
    }

    override fun get(key: String): String? {
        return instance.newXPath().evaluate(key, document)
    }
}