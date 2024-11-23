package com.codigo.ms_seguridad.service.impl;

import com.codigo.ms_seguridad.service.JwtService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

@Service
@Log4j2
public class JwtServiceImpl implements JwtService {

    @Value("${key.signature}")
    private String keySignature;

    @Override
    public String extractUsername(String token) {
        return "";
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return "";
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        return null;
    }

    //GENERAR UNA SERIE DE METODOS DE APOYO PARA INTERACTURA O GENERAR EL TOKEN

    private Key getSignKey(){
        log.info("CLAVE CON LA QUE VAMOS A EJECUTAR: " + keySignature);
        byte[] key = Decoders.BASE64.decode(keySignature);
        log.info("Key con la que vamos a firmar: " + Keys.hmacShaKeyFor(key));
        return Keys.hmacShaKeyFor(key);
    }
}
