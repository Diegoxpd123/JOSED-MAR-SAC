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
public class Servicio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="idcliente", nullable=false)
	private Cliente cliente;
	
	
	@ManyToOne
	@JoinColumn(name="idadministrador", nullable=false)
	private Administrador administrador;
	
	private String estado;
	
	private String fecha_entrega;
	
	private String fecha_registro;
	
	private String fecha_devolucion;

	public Servicio(int id, Cliente cliente, Administrador administrador, String estado, String fecha_entrega,
			String fecha_registro, String fecha_devolucion) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.administrador = administrador;
		this.estado = estado;
		this.fecha_entrega = fecha_entrega;
		this.fecha_registro = fecha_registro;
		this.fecha_devolucion = fecha_devolucion;
	}

	public Servicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
	
	
}
