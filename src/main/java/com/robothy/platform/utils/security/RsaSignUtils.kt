package com.robothy.platform.utils.security

import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

class RsaSignUtils {

  companion object {

    fun generateKeyPair(): RsaKeyPair {
      val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
      keyPairGenerator.initialize(512)
      val keyPair = keyPairGenerator.genKeyPair()
      val base64 = Base64.getEncoder()
      return RsaKeyPair(
        publicKey = base64.encodeToString(keyPair.public.encoded),
        privateKey = base64.encodeToString(keyPair.private.encoded)
      )
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun sign(data: String, privateKey: String): String {
      return sign(data.toByteArray(), privateKey).toHexString(HexFormat.Default)
    }

    fun sign(data: ByteArray, base64EncodedPrivateKey: String): ByteArray {
      val privateKeyBytes = Base64.getDecoder().decode(base64EncodedPrivateKey)
      val privateKey = KeyFactory.getInstance("RSA").generatePrivate(PKCS8EncodedKeySpec(privateKeyBytes))
      return sign(data, privateKey)
    }

    fun sign(data: ByteArray, privateKey: PrivateKey): ByteArray {
      val signature = Signature.getInstance("SHA256withRSA")
      signature.initSign(privateKey)
      signature.update(data)
      return signature.sign()
    }


    @OptIn(ExperimentalStdlibApi::class)
    fun verify(data: String, sign: String, base64EncodedPublicKey: String): Boolean {
      val publicKeyBytes = Base64.getDecoder().decode(base64EncodedPublicKey)
      val publicKey = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(publicKeyBytes))
      return verify(data.toByteArray(), sign.hexToByteArray(HexFormat.Default), publicKey)
    }

    fun verify(data: ByteArray, sign: ByteArray, publicKey: PublicKey): Boolean {
      val signature = Signature.getInstance("SHA256withRSA")
      signature.initVerify(publicKey)
      signature.update(data)
      return signature.verify(sign)
    }

  }

}
