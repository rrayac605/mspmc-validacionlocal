package mx.gob.imss.cit.mspmc.validacionlocal.input;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties
public class ValidacionLocalInput {

	@Override
	public String toString() {
		return "ValidacionLocalInput [nss=" + nss + ", rp=" + rp + ", fechaInicio=" + fechaInicio + ", tipoRiesgo="
				+ tipoRiesgo + ", consecuencia=" + consecuencia + ", diasSubsidiados=" + diasSubsidiados
				+ ", procentaje=" + procentaje + ", fechaFin=" + fechaFin + "]";
	}

	@Getter
	@Setter
	private String nss;
	
	@Getter
	@Setter
	private String curp;
	
	@Getter
	@Setter
	private String nombre;
	
	@Getter
	@Setter
	private String primerApellido;
	
	@Getter
	@Setter
	private String segundoApellido;

	@Getter
	@Setter
	private String rp;

	@Getter
	@Setter
	private String fechaInicio;

	@Getter
	@Setter
	private String tipoRiesgo;
	
	@Getter
	@Setter
	private String laudo;

	@Getter
	@Setter
	private String consecuencia;

	@Getter
	@Setter
	private String diasSubsidiados;

	@Getter
	@Setter
	private String procentaje;

	@Getter
	@Setter
	private String fechaFin;

	@Getter
	@Setter
	private String fechaAccidente;
	
	@Getter
	@Setter
	private String fechaAlta;
	
	@Getter
	@Setter
	private Date fecProcesoCarga;

}
