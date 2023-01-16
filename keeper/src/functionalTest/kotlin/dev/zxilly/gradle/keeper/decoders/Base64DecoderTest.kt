package dev.zxilly.gradle.keeper.decoders

import kotlin.test.Test
import kotlin.test.assertEquals

class Base64DecoderTest {
    @Test
    fun `test decode`() {
        val decoder = Base64Decoder()
        assertEquals(decoder.decode("dGVzdA=="), "test")
    }
}