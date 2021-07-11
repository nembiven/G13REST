package cl.utem.infb8090.api.persistence.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sismo")
public class Sismo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk", nullable = false)
    private Long id = null;

    @Column(name = "fecha", nullable = false, unique = true)
    private String fecha = null;
    
    
    @Column(name = "fechautc", nullable = false, unique = true)
    private String fechautc = null;
    @Column(name = "latitud", nullable = false)
    private double latitud = 0;

    @Column(name = "longitud", nullable = false)
    private double   longitud = 0;
    
    @Column(name = "profundidad", nullable = false)
    private int profundidad = 0;
   
    @Column(name = "magnitud", nullable = false)
    private double magnitud = 0;
    
    @Column(name = "agencia", nullable = false)
    private String agencia = null;
    
    @Column(name = "referencia_geografica", nullable = false)
    private String referencia_geografica = null;

    public Sismo(String a, String b, String c,String d , String e  , String f,String g, String h)
    {
     
        this.fecha = a;
        this.fechautc=b;
        this.latitud = Double.parseDouble(c);
        this.longitud = Double.parseDouble(d);
        this.profundidad = Math.round(Float.parseFloat(e));
        this.magnitud = Double.parseDouble(f.replaceAll("[^0-9.]", ""));
        this.agencia = g;
        this.referencia_geografica = h;
    }
    public Sismo()
    {
        
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getFechautc() {
        return fechautc;
    }

    public double   getLatitud() {
        return latitud;
    }

    public double   getLongitud() {
        return longitud;
    }

    public int   getProfundidad() {
        return profundidad;
    }

    public double   getMagnitud() {
        return magnitud;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getReferencia_geografica() {
        return referencia_geografica;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setFechautc(String fechautc) {
        this.fechautc = fechautc;
    }

    public void setLatitud(double   latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double   longitud) {
        this.longitud = longitud;
    }

    public void setProfundidad(int   profundidad) {
        this.profundidad = profundidad;
    }

    public void setMagnitud(double   magnitud) {
        this.magnitud = magnitud;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setReferencia_geografica(String referencia_geografica) {
        this.referencia_geografica = referencia_geografica;
    }
    

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.fecha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sismo other = (Sismo) obj;
        return Objects.equals(this.fecha, other.fecha);
    }

    
}
