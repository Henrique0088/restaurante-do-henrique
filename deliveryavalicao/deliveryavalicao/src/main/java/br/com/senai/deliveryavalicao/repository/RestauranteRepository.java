package br.com.senai.deliveryavalicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.senai.deliveryavalicao.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    
}
