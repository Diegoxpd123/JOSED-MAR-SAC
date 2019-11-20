package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Race")
public class Race implements Serializable{

	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRace;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "nameRace", length=60, nullable=false)
	private String nameRace;

	
	public Race() {
		super();
	}
	
	public Race(int idRace,
			String nameRace) {
		super();
		this.idRace = idRace;
		this.nameRace = nameRace;
	}

	public int getIdRace() {
		return idRace;
	}

	public void setIdRace(int idRace) {
		this.idRace = idRace;
	}

	public String getNameRace() {
		return nameRace;
	}

	public void setNameRace(String nameRace) {
		this.nameRace = nameRace;
	}
	
	
}
