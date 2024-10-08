package iflearn.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import iflearn.entities.Questao;
import iflearn.entities.Quiz;
import iflearn.entities.Usuario;
import iflearn.repositories.QuestaoRepository;
import iflearn.repositories.QuizRepository;

//quarto crud
@Controller
@RequestMapping("/questao")
public class CrudQuestao {

	@Autowired
	private QuestaoRepository qur;

	@Autowired
	private QuizRepository qir;

	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody Questao qu) {
		if (qu.getDesc() == null || qu.getQuiz() == null) {

			return ResponseEntity.badRequest().body("um dos parâmetros está nulo");
		}

		Questao quNova = qur.save(qu);
		return ResponseEntity.ok(quNova);
	}

	@GetMapping("/read/{id_questao}")
	@ResponseBody
	public ResponseEntity<?> read(@PathVariable(name = "id_questao") Integer id) {
		if (id == null) {
			return ResponseEntity.badRequest().body("o id está nulo");
		}

		Optional<Questao> quExistente = qur.findById(id);
		if (quExistente.isEmpty())
			return ResponseEntity.notFound().build();

		else {
			Questao qu = quExistente.get();
			Quiz qi = qu.getQuiz();
			qi.setUsuario(null);
			qi.setQuestoes(null);
			qi.setPontos(null);

			qu.setQuiz(qi);
			qu.setAlternativas(null);

			return ResponseEntity.ok(qu);
		}
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Questao qu) {
		if (qu.getId() == null || qu.getDesc() == null || qu.getQuiz() == null || qu.getAlternativas() == null) {
			return ResponseEntity.badRequest().body("um dos parâmetros está nulo");
		}

		Optional<Questao> quExistente = qur.findById(qu.getId());
		if (quExistente.isEmpty())
			return ResponseEntity.notFound().build();

		else {
			try {
				Quiz qi = qir.findById(qu.getQuiz().getId()).get();

				Questao quAtualizado = qur.save(qu);

				qi.setUsuario(null);
				qi.setQuestoes(null);
				qi.setPontos(null);

				quAtualizado.setQuiz(qi);
				quAtualizado.setAlternativas(null);

				quAtualizado.setQuiz(qi);

				return ResponseEntity.ok(quAtualizado);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		}
	}

	@DeleteMapping("/delete/{id_questao}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable(name = "id_questao") Integer id) {
		if (id == null) {
			return ResponseEntity.badRequest().body("o id está nulo");
		}

		Optional<Questao> quExistente = qur.findById(id);
		if (quExistente.isEmpty())
			return ResponseEntity.notFound().build();

		else {
			qur.deleteById(id);
			return ResponseEntity.ok().body("questão deletada");
		}
	}

	@GetMapping("/listarTodos")
	@ResponseBody
	public ResponseEntity<List<Questao>> listarTodos() {
		List<Questao> lista = qur.findAll();

		for (Questao qu : lista) {

			if (qu.getQuiz() != null) {
				Quiz qi = qu.getQuiz();
				qi.setPontos(null);
				qi.setQuestoes(null);
				qi.setUsuario(null);
			}

			qu.setAlternativas(null);
		}

		return ResponseEntity.ok(lista);
	}

}
