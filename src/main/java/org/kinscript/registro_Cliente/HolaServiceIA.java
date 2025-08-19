package org.kinscript.registro_Cliente;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.UserMessage;

@AiService
public interface HolaServiceIA {

    @UserMessage("""
                Genera un saludo de bienvenida a mi plataforma de Peliculas KNLFilms
                Usa menos de 120 caracteres y hazlo con el estilo Chapin.
            """)
    String generarSaludo();
}
