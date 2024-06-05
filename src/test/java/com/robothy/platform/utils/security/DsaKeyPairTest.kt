package com.robothy.platform.utils.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DsaKeyPairTest {

  @Test
  fun test() {
    val keyPair = DsaKeyPair.generateKeyPair()
    val sign = DsaSignUtils.sign("Hello", keyPair.privateKey)
    assertTrue(DsaSignUtils.verify("Hello", sign, keyPair.publicKey))
    val fake = "302d0215008fbd3aac3b63d9a79a6f0f9f81508bef40ee86ad02144389ad415ef65b056bc11922b290f50912d49101"
    assertFalse(DsaSignUtils.verify("Hello", fake, keyPair.publicKey))
  }

}
