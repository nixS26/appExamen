package pe.com.examen.serviceImpl.maestros;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.examen.exception.ErrorControladoException;
import pe.com.examen.model.SerieModel;
import pe.com.examen.repository.maestros.SerieMapper;
import pe.com.examen.service.maestros.SerieService;
import pe.com.examen.util.Constante;

@Service
public class SerieServiceImpl implements SerieService {

	private static final Logger logger = LogManager.getLogger(SerieServiceImpl.class);

	@Autowired
	private SerieMapper serieMapper;
	
	@Override
	public List<SerieModel> listarSeries(String datoBuscar, String tipoDocumento) throws Exception {

		Map<String, Object> params = new HashMap();
		params.put(Constante.PARAM_SP_DATO_BUSCAR, datoBuscar);
		params.put(Constante.PARAM_SP_COD_TIPO_DOCUMENTO, tipoDocumento);
		
		logger.info("params ===> " + params);

		List<SerieModel> perfilList = serieMapper.listarSerie(params);
		
		String flagResultado = (String) params.get(Constante.PARAM_FLAG_RESULTADO);
		String mensajeResultado = (String) params.get(Constante.PARAM_MENSAJE_RESULTADO);

		logger.info("flagResultado ===> " + flagResultado);
		logger.info("mensajeResultado ===> " + mensajeResultado);

		if (flagResultado.equals(Constante.RESULTADO_EXITOSO)) {
			logger.info("listarSeries ===> " + perfilList.toString());

		} else if (flagResultado.equals(Constante.RESULTADO_ALTERNATIVO)) {
			throw new ErrorControladoException(mensajeResultado);

		} else {
			throw new Exception(mensajeResultado);

		}

		return perfilList;
	}

	@Override
	public void insertarSerie(SerieModel serie) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("p_V_COD_TIPO_DOCUMENTO", serie.getTipoDocumento());
		params.put("p_V_DESCRIPCION", serie.getDescripcion());
		params.put("p_V_NRO_SERIE", serie.getNroSerie());
		params.put("p_N_CORRELATIVO", serie.getCorrelativo());
		params.put("p_N_MAX_CORRELATIVO", serie.getMaxcorrelativo());

		serieMapper.insertarSerie(params);
	}

	@Override
	public boolean existeNroSerie(String nroSerie) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("p_V_NRO_SERIE", nroSerie);

		// Asumiendo que el mapper devuelve la cantidad (int) de registros que tienen ese nroSerie
		Integer cantidad = serieMapper.contarPorNroSerie(params);

		return cantidad != null && cantidad > 0;
	}

}
