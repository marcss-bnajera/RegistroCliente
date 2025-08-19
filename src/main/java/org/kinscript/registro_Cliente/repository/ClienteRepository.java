package org.kinscript.registro_Cliente.repository;

import org.kinscript.registro_Cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
