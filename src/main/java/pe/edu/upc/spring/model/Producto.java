package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
	private static final long serialVersionUID =1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	
	private String nombre;
	

	private String codigo;

	private int stock;
	
	
	
	private String link;

	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}





	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Producto(int id, String nombre, String codigo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	




}
