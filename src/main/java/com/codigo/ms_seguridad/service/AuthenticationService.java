package com.codigo.ms_seguridad.service;

import com.codigo.ms_seguridad.aggregates.request.SignUpRequest;
import com.codigo.ms_seguridad.entity.Usuario;
import sun.misc.SignalHandler;

import java.util.List;

public interface AuthenticationService {

    //SIGNUO -> Registrarse
    Usuario signUpUser(SignUpRequest signUpRequest);
    Usuario signUpAdmin(SignUpRequest signUpRequest);
    List<Usuario> todos();

}
