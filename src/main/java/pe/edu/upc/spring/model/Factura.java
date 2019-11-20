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
@Table(name="Factura")
public class Factura implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idServicio", nullable=false)
	private Servicio servicio;
	
	private String estado;
	
	private double monto;
	
	private double montoproblemas;

	public Factura(int id, Servicio servicio, String estado, double monto, double montoproblemas) {
		super();
		this.id = id;
		this.servicio = servicio;
		this.estado = estado;
		this.monto = monto;
		this.montoproblemas = montoproblemas;
	}

	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getMontoproblemas() {
		return montoproblemas;
	}

	public void setMontoproblemas(double montoproblemas) {
		this.montoproblemas = montoproblemas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
