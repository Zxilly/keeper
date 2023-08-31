package dev.zxilly.gradle.keeper.decoders

import kotlin.test.Test
import kotlin.test.assertEquals

class Base64SecretDecoderTest {
    @Test
    fun `test decode`() {
        val decoder = Base64SecretDecoder()
        assertEquals(decoder.decode("dGVzdA=="), "test")
        assertEquals(decoder.decode(null), null)
    }
}