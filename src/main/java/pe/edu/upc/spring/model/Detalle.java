package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Servicio")
public class Detalle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idfactura", nullable=false)
	private Factura factura;
	
	
	@ManyToOne
	@JoinColumn(name="idexistencia", nullable=false)
	private Existencia existencia;
	
	@ManyToOne
	@JoinColumn(name="idproblema", nullable=false)
	private Problema problema;

	public Detalle(int id, Factura factura, Existencia existencia, Problema problema) {
		super();
		this.id = id;
		this.factura = factura;
		this.existencia = existencia;
		this.problema = problema;
	}

	public Detalle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Existencia getExistencia() {
		return existencia;
	}

	public void setExistencia(Existencia existencia) {
		this.existencia = existencia;
	}

	public Problema getProblema() {
		return problema;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



	
	
	
	
}
