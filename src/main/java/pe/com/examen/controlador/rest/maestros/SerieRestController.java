package pe.com.examen.controlador.rest.maestros;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.examen.model.SerieModel;
import pe.com.examen.service.maestros.SerieService;
import pe.com.examen.util.Constante;

@RestController
public class SerieRestController {

	private static final Logger logger = LogManager.getLogger(SerieRestController.class);
	
	@Autowired
	SerieService serieService;
	@Autowired
    HttpSession session;
	
	@GetMapping ("/listarSeries/")
    public ResponseEntity<List<SerieModel>> listarSeries(@RequestParam(Constante.PARAM_DATO_BUSCAR) String datoBuscar, 
    													 @RequestParam(Constante.PARAM_TIPO_DOCUMENTO) String tipoDoc) throws Exception {
        
        	logger.info("Inicio listarSeries.......");
            
            List<SerieModel> serieList = serieService.listarSeries(datoBuscar, tipoDoc);
            
            logger.info("Fin listarSeries.......");

            return new ResponseEntity<List<SerieModel>>(serieList, HttpStatus.OK);   
    }
}
