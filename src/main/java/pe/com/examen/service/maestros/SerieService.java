package pe.com.examen.service.maestros;

import java.util.List;

import pe.com.examen.model.SerieModel;

public interface SerieService {
	
	List<SerieModel> listarSeries(String datoBuscar, String tipoDocumento) throws Exception;
}
