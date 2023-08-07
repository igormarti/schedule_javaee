package model;

import java.io.Serializable;
import java.util.Objects;

public class JavaBeans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idcon;
	private String nome;
	private String fone;
	private String email;
	
	
	
	public JavaBeans(Integer idcon, String nome, String fone, String email) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	
	public JavaBeans() {
		super();
	}
	
	public Integer getIdcon() {
		return idcon;
	}
	public void setIdcon(Integer idcon) {
		this.idcon = idcon;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, idcon, nome, fone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JavaBeans other = (JavaBeans) obj;
		return Objects.equals(email, other.email) && Objects.equals(idcon, other.idcon)
				&& Objects.equals(nome, other.nome) && Objects.equals(fone, other.fone);
	}

	@Override
	public String toString() {
		return "JavaBeans [idcon=" + idcon + ", nome=" + nome + ", fone=" + fone + ", email=" + email + "]";
	}
	
}
