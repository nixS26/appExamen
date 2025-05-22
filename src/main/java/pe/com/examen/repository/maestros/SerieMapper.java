package pe.com.examen.repository.maestros;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.com.examen.model.SerieModel;

@Repository
@Transactional
public interface SerieMapper {
	
	List<SerieModel> listarSerie(Map params);
	void insertarSerie(Map<String, Object> params);
	Integer contarPorNroSerie(Map<String, Object> params) throws Exception;
}
