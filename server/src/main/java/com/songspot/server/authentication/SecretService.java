package com.songspot.server.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SecretService {

    private Map<String, String> secrets = new HashMap<>();

    private final SigningKeyResolver signingKeyResolver = new SigningKeyResolverAdapter() {
        @Override
        public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
            return Decoders.BASE64.decode(secrets.get(header.getAlgorithm()));
        }
    };

    @PostConstruct
    public void setup() {
        refreshSecrets();
    }

    public SigningKeyResolver getSigningKeyResolver() {
        return signingKeyResolver;
    }

    public Map<String, String> getSecrets() {
        return secrets;
    }

    public void setSecrets(Map<String, String> secrets) {
        Objects.requireNonNull(secrets);
        Assert.hasText(secrets.get(SignatureAlgorithm.HS256.getValue()), "HS256 Missing Secret");
        Assert.hasText(secrets.get(SignatureAlgorithm.HS384.getValue()), "HS384 Missing Secret");
        Assert.hasText(secrets.get(SignatureAlgorithm.HS512.getValue()), "HS512 Missing Secret");

        this.secrets = secrets;
    }

    public String getSecretOf(SignatureAlgorithm algorithm) {
        Map<String, String> secrets = getSecrets();
        return secrets.get(algorithm.getValue());
    }

    public byte[] getHS256SecretBytes() {
        return Decoders.BASE64.decode(secrets.get(SignatureAlgorithm.HS256.getValue()));
    }

    public byte[] getHS384SecretBytes() {
        return Decoders.BASE64.decode(secrets.get(SignatureAlgorithm.HS384.getValue()));
    }

    public byte[] getHS512SecretBytes() {
        return Decoders.BASE64.decode(secrets.get(SignatureAlgorithm.HS512.getValue()));
    }

    public Map<String, String> refreshSecrets() {
        SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS256);
        secrets.put(SignatureAlgorithm.HS256.getValue(), Encoders.BASE64.encode(key.getEncoded()));
        key = MacProvider.generateKey(SignatureAlgorithm.HS384);
        secrets.put(SignatureAlgorithm.HS384.getValue(), Encoders.BASE64.encode(key.getEncoded()));
        key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        secrets.put(SignatureAlgorithm.HS512.getValue(), Encoders.BASE64.encode(key.getEncoded()));
        return secrets;
    }
}