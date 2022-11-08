package pt.jconduto.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.jconduto.springboot.model.Utilizador;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

	@Query(value = "select u from Utilizador u where lower(trim(u.nome)) like %?1% ")
	List<Utilizador> procurarPorNome(String nome);
}
