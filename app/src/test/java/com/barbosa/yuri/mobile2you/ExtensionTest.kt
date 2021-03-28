package com.barbosa.yuri.mobile2you

import com.barbosa.yuri.mobile2you.utils.releaseDateFormat
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionTest {
    @Test
    fun `Esperado data formata`(){
        assertEquals("2021", "2021-12-01".releaseDateFormat())
    }
}