package com.robothy.platform.utils.security

import java.security.KeyPairGenerator
import java.util.Base64

data class DsaKeyPair(
  val publicKey: String,
  val privateKey: String
) {

  companion object {

    fun generateKeyPair(): DsaKeyPair {
      KeyPairGenerator.getInstance("DSA").apply {
        initialize(512)
        val keyPair = generateKeyPair()
        return DsaKeyPair(
          publicKey = Base64.getEncoder().encodeToString(keyPair.public.encoded),
          privateKey = Base64.getEncoder().encodeToString(keyPair.private.encoded)
        )
      }
    }

  }


}
