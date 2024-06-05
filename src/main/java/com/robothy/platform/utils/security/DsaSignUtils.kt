package com.robothy.platform.utils.security

import java.security.KeyFactory
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

class DsaSignUtils {

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun sign(data: String, base64EncodedPrivateKey: String): String {
      return sign(
        data.toByteArray(),
        Base64.getDecoder().decode(base64EncodedPrivateKey)
      ).toHexString(HexFormat.Default)
    }

    fun sign(data: ByteArray, privateKeyBytes: ByteArray): ByteArray {
      val signature = Signature.getInstance("SHA256withDSA")
      val privateKey = KeyFactory.getInstance("DSA").generatePrivate(PKCS8EncodedKeySpec(privateKeyBytes))
      signature.initSign(privateKey)
      signature.update(data)
      return signature.sign()
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun verify(data: String, sign: String, base64EncodedPublicKey: String): Boolean {
      return verify(
        data.toByteArray(),
        sign.hexToByteArray(HexFormat.Default),
        Base64.getDecoder().decode(base64EncodedPublicKey)
      )
    }

    fun verify(data: ByteArray, sign: ByteArray, publicKeyBytes: ByteArray): Boolean {
      val signature = Signature.getInstance("SHA256withDSA")
      val publicKey = KeyFactory.getInstance("DSA").generatePublic(X509EncodedKeySpec(publicKeyBytes))
      signature.initVerify(publicKey)
      signature.update(data)
      return signature.verify(sign)
    }
  }


}
