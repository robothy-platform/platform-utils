package com.robothy.platform.utils.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RsaSignUtilsTest {

  @Test
  fun test() {
    val keyPair = RsaSignUtils.generateKeyPair()
    val sign = RsaSignUtils.sign("Hello", keyPair.privateKey)
    assertTrue(RsaSignUtils.verify("Hello", sign, keyPair.publicKey))
    val fakeSign = "694e5c0b1a46fd7564becb274a60031e767e55d57020d13de80d7e5f15455122694e5c0b1a46fd7564becb274a60031e767e55d57020d13de80d7e5f15455122"
    assertFalse(RsaSignUtils.verify("Hello", fakeSign, keyPair.publicKey))
  }

}
