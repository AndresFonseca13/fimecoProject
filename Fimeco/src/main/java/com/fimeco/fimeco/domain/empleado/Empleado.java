package com.fimeco.fimeco.domain.empleado;

import com.fimeco.fimeco.domain.producto.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Entity(name = "Empleado")
@Table(name = "empleados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "documento", unique = true)
    private String documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "telefono_emergencia")
    private String telefono_emergencia;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;
    @Column(name = "tiempo_servicio")
    private Integer tiempoServicio;
    @Column(name = "usuario", unique = true)
    private String usuario;
    @Column(name = "clave")
    private String clave;
    @Column(name = "activo")
    private boolean activo = true;

    @ManyToMany(mappedBy = "empleados", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Producto> productos;

    public Empleado(DatosRegistroEmpleado datosRegistroEmpleado) {
        this.documento = datosRegistroEmpleado.documento();
        this.nombre = datosRegistroEmpleado.nombre();
        this.apellido = datosRegistroEmpleado.apellido();
        this.fechaNacimiento = LocalDate.parse(datosRegistroEmpleado.fechaNacimiento());
        this.edad = Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
        this.telefono = datosRegistroEmpleado.telefono();
        this.telefono_emergencia = datosRegistroEmpleado.telefonoEmergencia();
        this.email = datosRegistroEmpleado.email();
        this.rol = Rol.valueOf(datosRegistroEmpleado.rol());
        this.fechaIngreso = LocalDate.parse(datosRegistroEmpleado.fechaIngreso());
        this.tiempoServicio = Period.between(this.fechaIngreso, LocalDate.now()).getMonths();
        this.usuario = datosRegistroEmpleado.usuario();
        this.clave = datosRegistroEmpleado.clave();
    }
}
