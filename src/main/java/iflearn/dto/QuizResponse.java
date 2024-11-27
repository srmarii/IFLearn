package iflearn.dto;

import java.time.LocalDateTime;

import iflearn.entities.Quiz;

public record QuizResponse(Integer id, 
		String desc, 
		String nome, 
		LocalDateTime dataCriacao,
		UsuarioResponse usuario) {

	public QuizResponse(Quiz qi) {
		this(qi.getId(), 
				qi.getDesc(), 
				qi.getNome(), 
				qi.getDataCriacao(),
				new UsuarioResponse(qi.getUsuario()));
	}

}