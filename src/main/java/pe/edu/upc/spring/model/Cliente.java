package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID =1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	
	private String nombre;
	

	private String apellido;
	
	
	private int edad;
	

	
	private String correo;
	
	private String direccion;
	
	private int telefono;
	private int dni;
	
	private String estado;
	
	private String nombre_usuario;
	
	private String contra;
	private String sexo;
	
	
	


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	private int n_compras;
	public Cliente() {
		super();
	}

	
	public Cliente(int id, String nombre, String apellido, int edad, String correo, String direccion, int telefono,
			int dni, String estado, String nombre_usuario, String contra, int is_cliente, int n_compras) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
		this.estado = estado;
		this.nombre_usuario = nombre_usuario;
		this.contra = contra;

		this.n_compras = n_compras;
	}

	public int getN_compras() {
		return n_compras;
	}

	public void setN_compras(int n_compras) {
		this.n_compras = n_compras;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}



}
