package pt.jconduto.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pt.jconduto.springboot.model.Utilizador;
import pt.jconduto.springboot.repository.UtilizadorRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private UtilizadorRepository utilizadorRepository;

	@RequestMapping(value = "/auth/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Spring Boot API " + name + "!";
	}

	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {

		Utilizador utilizador = new Utilizador();
		utilizador.setNome(nome);

		utilizadorRepository.save(utilizador);

		return "Olá mundo " + nome;
	}

	@GetMapping(value = "listatodos")
	@ResponseBody
	public ResponseEntity<List<Utilizador>> listarUtilizadores() {
		List<Utilizador> utilizadores = utilizadorRepository.findAll();

		return new ResponseEntity<List<Utilizador>>(utilizadores, HttpStatus.OK);
	}

	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Utilizador> salvar(@RequestBody Utilizador utilizador) {
		Utilizador u = utilizadorRepository.save(utilizador);

		return new ResponseEntity<Utilizador>(u, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "apagar")
	@ResponseBody
	public ResponseEntity<String> apagar(@RequestParam Long id) {
		utilizadorRepository.deleteById(id);

		return new ResponseEntity<String>("Apagado com sucesso.", HttpStatus.OK);
	}

	@GetMapping(value = "pesquisar")
	@ResponseBody
	public ResponseEntity<Utilizador> pesquisar(@RequestParam Long id) {
		Utilizador u = utilizadorRepository.findById(id).get();

		return new ResponseEntity<Utilizador>(u, HttpStatus.OK);
	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Utilizador utilizador) {
		if (utilizador.getId() == null) {
			return new ResponseEntity<String>("Utilizador não foi informado.", HttpStatus.OK);

		}

		Utilizador u = utilizadorRepository.saveAndFlush(utilizador);
		return new ResponseEntity<Utilizador>(u, HttpStatus.OK);
	}

	@GetMapping(value = "pesquisarPorNome")
	@ResponseBody
	public ResponseEntity<List<Utilizador>> pesquisarPorNome(@RequestParam(name = "nome") String nome) {
		List<Utilizador> utilizadores = utilizadorRepository.procurarPorNome(nome.trim().toLowerCase());

		return new ResponseEntity<List<Utilizador>>(utilizadores, HttpStatus.OK);
	}
}
