package dev.zxilly.gradle.keeper.parsers

import kotlin.test.Test
import kotlin.test.assertEquals

class XmlParserTest {
    @Test
    fun `test load`() {
        val loader = XmlParser().apply {
            parse(content)
        }
        assertEquals("1", loader.get("/slideshow/slide/number"))
        assertEquals("Wake up to WonderWidgets!", loader.get("/slideshow/slide/title"))
        assertEquals("1.0", loader.get("/slideshow/slide/float"))
        assertEquals("", loader.get("sadas"))
    }


    private val content = """
<?xml version='1.0' encoding='us-ascii'?>
  <!--  A SAMPLE set of slides  -->
  <slideshow 
    title="Sample Slide Show"
    date="Date of publication"
    author="Yours Truly"
    >
    <!-- TITLE SLIDE -->
    <slide type="all">
      <title>Wake up to WonderWidgets!</title>
      <number>1</number>
      <float>1.0</float>
    </slide>
    <!-- OVERVIEW -->
    <slide type="all">
      <title>Overview</title>
      <item>
        Why 
        <em>WonderWidgets</em>
         are great
      </item>
      <item/>
      <item>
        Who 
        <em>buys</em>
         WonderWidgets
      </item>
    </slide>
  </slideshow>
    """.trimIndent()
}