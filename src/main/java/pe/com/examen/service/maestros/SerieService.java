package pe.com.examen.service.maestros;

import java.util.List;

import pe.com.examen.model.SerieModel;

public interface SerieService {
	
	List<SerieModel> listarSeries(String datoBuscar, String tipoDocumento) throws Exception;
	void insertarSerie(SerieModel serie) throws Exception;
	boolean existeNroSerie(String nroSerie) throws Exception;


}
