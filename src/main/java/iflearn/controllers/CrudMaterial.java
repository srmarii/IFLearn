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

import iflearn.entities.Material;
import iflearn.entities.Usuario;
import iflearn.repositories.MaterialRepository;
import iflearn.repositories.UsuarioRepository;

//segundo crud
@Controller
@RequestMapping("/material")
public class CrudMaterial {

	@Autowired
	private MaterialRepository mr;
	
	@Autowired
	private UsuarioRepository  ur;
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Material> create(@RequestBody Material m) {
		if(m.getNome() == null || m.getTurma() == null 
				|| m.getData() == null
				|| m.getUsuario() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		//try catch pra caso o id não exista
		try {
			//Usuario u = ur.findById(m.getUsuario().getId()).get();
			
			Material mNovo = mr.save(m);
			
//			// nao monta as listas para devolver para o front..
//			u.setQuizzes(null);
//			u.setMateriais(null);
//			u.setPontos(null);
//			
//			// atualiza o objeto material com os dados do usuario para retornar ao front
//			mNovo.setUsuario(u); 
			
			return ResponseEntity.ok(mNovo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build(); 
		}
	}
	
	
	@GetMapping("/read/{id_material}")
	@ResponseBody
	public ResponseEntity<Material> read(@PathVariable(name = "id_material") Integer id) {
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Material> hasUser = mr.findById(id);
		if (hasUser.isEmpty())
			return ResponseEntity.badRequest().build();
		
		else
			return ResponseEntity.ok(hasUser.get());
	}

	//como colocar que não pode trocar o usuario??
	@PutMapping("/update")
    @ResponseBody
    public ResponseEntity<Material> update(@RequestBody Material m) {        
        if (m.getId() == null || m.getNome() == null 
				|| m.getTurma() == null) {
			//	|| m.getUsuario() == null) {
			return ResponseEntity.badRequest().build();
		}

        Optional<Material> hasUser = mr.findById(m.getId());
        if (hasUser.isEmpty())
            return ResponseEntity.badRequest().build();
      
        else {
	        Material mAtualizado = mr.save(m);
	        return ResponseEntity.ok(mAtualizado);
    }
	}

	
	@DeleteMapping("/delete/{id_material}")
	@ResponseBody
	public ResponseEntity<Material> delete(@PathVariable(name = "id_material") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
      
        Optional<Material> hasUser = mr.findById(id);
        if (hasUser.isEmpty())
            return ResponseEntity.badRequest().build();
        
        else {
        	mr.deleteById(id);
    		return ResponseEntity.noContent().build();
    }
	}

	@GetMapping("/listarTodos")
	@ResponseBody
	public ResponseEntity<List<Material>> listarTodos() {
		return ResponseEntity.ok(mr.findAll());
	}
	
}
