package iflearn.controllers;

import java.util.List;

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

import iflearn.entities.Quiz;
import iflearn.services.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService qis;

	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody Quiz qi) {
		return qis.create(qi);
	}

	@GetMapping("/read/{id_quiz}")
	@ResponseBody
	public ResponseEntity<?> read(@PathVariable(name = "id_quiz") Integer id) {
		return qis.read(id);
	}

	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Quiz qi) {
		return qis.update(qi);
	}

	@DeleteMapping("/delete/{id_quiz}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable(name = "id_quiz") Integer id) {
		return qis.delete(id);
	}

	@GetMapping("/listarTodos")
	@ResponseBody
	public ResponseEntity<List<Quiz>> listarTodos() {
		return qis.listarTodos();
	}

	///////////////////////////////////////////////////////////////////

	@PostMapping("/calculaPontosQi")
	@ResponseBody
	public ResponseEntity<?> calculaPontosQi(@RequestBody Quiz qi) {
		return qis.calculaPontosQi(qi);
	}

	@PostMapping("/calculaPontosU/{id_usuario}")
	@ResponseBody
	public ResponseEntity<?> calculaPontosTotalPorU(@PathVariable(name = "id_usuario") Integer id) {
		return qis.calculaPontosTotalPorU(id);
	}

	@GetMapping("/ranking")
	@ResponseBody
	public ResponseEntity<?> ranking() {
		return qis.ranking();
	}

}
