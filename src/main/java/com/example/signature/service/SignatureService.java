package com.example.signature.service;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Service
public class SignatureService {
    @Value("${asymetric.public.key}")
    private String publicKey;

    public java.security.PublicKey deConvertPublicKey(String publicKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {//this method to deconvert Base64 into Public Key
        byte[] publicKyeByte = Base64.decode(publicKeyString.getBytes());
        java.security.PublicKey publicKey1= KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKyeByte));
        return publicKey1;
    }

    public boolean verifySignature(String message, byte[] signature, java.security.PublicKey publicKey) throws InvalidKeyException, SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException {//this method to verify signature using public key
        byte[] messageByte= message.getBytes();
        Signature signature1= Signature.getInstance("SHA256withRSA");
        signature1.initVerify(publicKey);
        signature1.update(messageByte);
        return signature1.verify(signature);
    }

    public byte[] deConvertSignature(String signatureString) throws NoSuchAlgorithmException, InvalidKeySpecException {//this method to deconvert signature from Base64
        byte[] signature = Base64.decode(signatureString.getBytes());
        return signature;
    }

    public boolean isValid(String message, String signature) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, SignatureException, InvalidKeyException {
        java.security.PublicKey publicKey = deConvertPublicKey(this.publicKey);
       byte[] byteSignature = deConvertSignature(signature);
       return verifySignature(message, byteSignature, publicKey);
    }
}
