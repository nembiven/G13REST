package cl.utem.infb8090.api.rest.vo;
import cl.utem.infb8090.api.persistence.model.Sismo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

@ApiModel(value = "sismo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SismoVO {

    private String Fecha= null;
    private double Latitud = 0;
    private double  Longitud = 0;
    private int Profundidad = 0;
    private double Magnitud = 0;
    private String Referencia_Geografica = null;


    public SismoVO() {
    }

    public SismoVO(Sismo sismo) {
        this.Fecha = sismo.getFecha();
        this.Latitud = sismo.getLatitud();
        this.Longitud = sismo.getLongitud();
        this.Profundidad = sismo.getProfundidad();
        this.Magnitud = sismo.getMagnitud();
        this.Referencia_Geografica = sismo.getReferencia_geografica();
        
        
    }

    @ApiModelProperty(value = "Fecha Local",
            required = true, example = "2021/07/09 14:01:54")
    public String getFecha() {
        return Fecha;
    }
@ApiModelProperty(value = "Latitud",
            required = true, example = "-18.222")
    public double getLatitud() {
        return Latitud;
    }
    @ApiModelProperty(value = "Longitud",
            required = true, example = "-70.405")
    public double getLongitud() {
        return Longitud;
    }
    @ApiModelProperty(value = "Profundidad",
            required = true, example = "37")
    public int getProfundidad() {
        return Profundidad;
    }
   @ApiModelProperty(value = "Magnitud",
            required = true, example = "3.0")
    public double getMagnitud() {
        return Magnitud;
    }
    @ApiModelProperty(value = "Referencia Geográfica",
            required = true, example = "28 km al SO de Tacna")
    public String getReferencia_Geografica() {
        return Referencia_Geografica;
    }
    

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    public void setLatitud(double latitud) {
        this.Latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.Longitud = longitud;
    }

    public void setProfundidad(int profundidad) {
        this.Profundidad = profundidad;
    }

    public void setMagnitud(double magnitud) {
        this.Magnitud = magnitud;
    }

    public void setReferencia_Geografica(String ref) {
        this.Referencia_Geografica = ref;
    }
   
    
    
}
