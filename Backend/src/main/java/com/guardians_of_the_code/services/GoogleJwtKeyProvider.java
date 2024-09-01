package com.guardians_of_the_code.services;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONArray;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleJwtKeyProvider implements RSAKeyProvider {

    private Map<String, RSAPublicKey> keys = new HashMap<>();
    private static final String GOOGLE_CERTS_URL = "https://www.googleapis.com/oauth2/v3/certs";

    public GoogleJwtKeyProvider() throws JSONException {
        loadKeys();
    }

    private void loadKeys() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(GOOGLE_CERTS_URL, String.class);
        JSONObject json = new JSONObject(response);
        JSONArray keysArray = json.getJSONArray("keys");

        for (int i = 0; i < keysArray.length(); i++) {
            JSONObject key = keysArray.getJSONObject(i);
            String kid = key.getString("kid");
            String n = key.getString("n");
            String e = key.getString("e");

            RSAPublicKey rsaPublicKey = createRsaPublicKey(n, e);
            if (rsaPublicKey != null) {
                keys.put(kid, rsaPublicKey);
            }
        }
    }

    private RSAPublicKey createRsaPublicKey(String modulus, String exponent) {
        try {
            byte[] modulusBytes = java.util.Base64.getUrlDecoder().decode(modulus);
            byte[] exponentBytes = java.util.Base64.getUrlDecoder().decode(exponent);

            RSAPublicKeySpec spec = new RSAPublicKeySpec(
                    new java.math.BigInteger(1, modulusBytes),
                    new java.math.BigInteger(1, exponentBytes)
            );

            java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao criar chave pública RSA", e);
        }
    }

    @Override
    public RSAPublicKey getPublicKeyById(String keyId) {
        return keys.get(keyId);
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }


}