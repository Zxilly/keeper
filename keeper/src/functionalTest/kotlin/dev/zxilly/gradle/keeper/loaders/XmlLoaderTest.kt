package dev.zxilly.gradle.keeper.loaders

import kotlin.test.Test
import kotlin.test.assertEquals

class XmlLoaderTest {
    @Test
    fun `test load`() {
        val loader = XmlLoader(content)
        assertEquals("1", loader.load("/slideshow/slide/number"))
        assertEquals("Wake up to WonderWidgets!", loader.load("/slideshow/slide/title"))
        assertEquals("1.0", loader.load("/slideshow/slide/float"))
        assertEquals("", loader.load("sadas"))
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