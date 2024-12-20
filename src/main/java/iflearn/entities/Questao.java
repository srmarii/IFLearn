package iflearn.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Questao {

	//atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name= "descricao", length = 300)
	private String desc;
	private String explicacao;
	//-
	
	@ManyToOne
	@JoinColumn(name = "id_quiz")
	private Quiz quiz;
	
	@OneToMany(mappedBy = "questao", fetch = FetchType.EAGER)
	private List<Alternativa> alternativas = new ArrayList<>();
	
	
	
	//construtores
	public Questao() {
		super();
	}
	public Questao(Integer id, String desc, String explicacao, Quiz quiz) {
		super();
		this.id = id;
		this.desc = desc;
		this.explicacao=explicacao;
		this.quiz=quiz;
	}
	//-
	
	
	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public List<Alternativa> getAlternativas() {
		return alternativas;
	}
	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public String getExplicacao() {
		return explicacao;
	}
	public void setExplicacao(String explicacao) {
		this.explicacao = explicacao;
	}
	
	
	//-

	
}
