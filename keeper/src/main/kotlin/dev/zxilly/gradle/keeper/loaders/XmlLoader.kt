package dev.zxilly.gradle.keeper.loaders

import dev.zxilly.gradle.keeper.constraints.Loader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

class XmlLoader(content: String) : Loader {
    private val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(content.byteInputStream())

    override fun load(key: String): String {
        val result = XPathFactory.newInstance().newXPath().evaluate(key, document, XPathConstants.STRING)
        return result as String
    }
}