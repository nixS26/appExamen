package pe.com.examen.model;

import lombok.Data;

@Data
public class SerieModel {

	private Integer id;

	private Integer codSerie;
	private String tipoDocumento;
	private String desTipoDocumento;
	private String descripcion;
    private String nroSerie;
    private Integer correlativo;
    private Integer maxcorrelativo;
    private Integer activo;
    private String codigoUsuarioRegistra;
    private String fechaRegistro;
    private String codigoUsuarioModifica;
    private String fechaModificacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodSerie() {
        return codSerie;
    }

    public void setCodSerie(Integer codSerie) {
        this.codSerie = codSerie;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDesTipoDocumento() {
        return desTipoDocumento;
    }

    public void setDesTipoDocumento(String desTipoDocumento) {
        this.desTipoDocumento = desTipoDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public Integer getMaxcorrelativo() {
        return maxcorrelativo;
    }

    public void setMaxcorrelativo(Integer maxcorrelativo) {
        this.maxcorrelativo = maxcorrelativo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getCodigoUsuarioRegistra() {
        return codigoUsuarioRegistra;
    }

    public void setCodigoUsuarioRegistra(String codigoUsuarioRegistra) {
        this.codigoUsuarioRegistra = codigoUsuarioRegistra;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCodigoUsuarioModifica() {
        return codigoUsuarioModifica;
    }

    public void setCodigoUsuarioModifica(String codigoUsuarioModifica) {
        this.codigoUsuarioModifica = codigoUsuarioModifica;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
