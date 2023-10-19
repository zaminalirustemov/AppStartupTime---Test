package com.lahza.todo.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


class EncryptionViewModel : ViewModel() {

    private lateinit var secretKey: SecretKey

    init {
        generateAESKey()
    }

    private fun generateAESKey() {
        val keyGenerator: KeyGenerator = KeyGenerator.getInstance("AES")
        val random: SecureRandom = SecureRandom()
        keyGenerator.init(256, random)
        secretKey = keyGenerator.generateKey()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encryptData(data: String): String {
        val cipher: Cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes: ByteArray = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decryptData(encryptedData: String): String {
        val cipher: Cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decryptedBytes: ByteArray = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
        return String(decryptedBytes, Charsets.UTF_8)
    }

}