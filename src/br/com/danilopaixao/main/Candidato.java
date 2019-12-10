package br.com.danilopaixao.main;

public class Candidato {
	
	private String nome;
	private Integer nota;
	private Integer notaObjetiva;
	private Integer notaDiscursiva;
	private Integer notaTitulos;
	private boolean aprovadoNegro;

	public Candidato(String nome, Integer notaObjetiva,
			Integer notaDiscursiva,
			Integer notaTitulos,
			boolean aprovadoNegro) {
		super();
		this.nome = nome;
		this.notaDiscursiva = notaDiscursiva;
		this.notaObjetiva = notaObjetiva;
		this.notaTitulos = notaTitulos;
		this.nota = notaObjetiva + notaDiscursiva + notaTitulos;
		this.aprovadoNegro = aprovadoNegro;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNota() {
		return nota;
	}
	
	public Integer getNotaObjetiva() {
		return notaObjetiva;
	}

	public Integer getNotaDiscursiva() {
		return notaDiscursiva;
	}

	public Integer getNotaTitulos() {
		return notaTitulos;
	}
	
	public boolean isAprovadoNegro() {
		return aprovadoNegro;
	}

	public void setAprovadoNegro(boolean aprovadoNegro) {
		this.aprovadoNegro = aprovadoNegro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		return true;
	}

}
