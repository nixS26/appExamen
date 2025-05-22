
package pe.com.examen.controlador;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.com.examen.model.ComboModel;
import pe.com.examen.model.SerieModel;
import pe.com.examen.service.GenericService;
import pe.com.examen.service.maestros.SerieService;
import pe.com.examen.util.Constante;

@Controller
public class PrincipalController {
	
	private static final Logger logger = LogManager.getLogger(PrincipalController.class);
		
	@Autowired
	private GenericService genericService;

	@Autowired
	private SerieService serieService;
	@Autowired
    HttpSession session;
	
	@GetMapping({"/", "/index", "/login", "/principal"})
    public String principal(Model model) {
		String retorno;
		try {
			logger.info("entrando al método principal");
			List<ComboModel> listaTipoDocumento = genericService.cargarCombo(Constante.CATALOGO_TIPO_DOC_SUNAT);
			model.addAttribute("listaTipoDocumentoModal", listaTipoDocumento);
			model.addAttribute("listaTipoDocumento", listaTipoDocumento);

			List<SerieModel> listaSeries = serieService.listarSeries("", ""); // vacío para listar TODO
			model.addAttribute("listaSeries", listaSeries);

			retorno = Constante.PAGINA_MANTENIMIENTO_SERIE;
			logger.info("saliendo del método principal");
		}catch (Exception e) {
			// TODO: handle exception
			retorno = Constante.PAGINA_ERROR;
			model.addAttribute("mensajeError", e.toString());
		}
		return retorno;
    }
	@GetMapping("/api/tipos-documento")
	@ResponseBody
	public List<ComboModel> obtenerTiposDocumento() throws Exception {
		return genericService.cargarCombo(Constante.CATALOGO_TIPO_DOC_SUNAT);
	}

	@GetMapping("/add-serie")
	public String mostrarFormularioNuevaSerie(Model model) {
		try {
			List<ComboModel> listaTipoDocumento = genericService.cargarCombo(Constante.CATALOGO_TIPO_DOC_SUNAT);
			model.addAttribute("listaTipoDocumento", listaTipoDocumento);
			model.addAttribute("serie", new SerieModel()); // Para vincular con el formulario
			return "/paginas/maestros/add-serie"; // Thymeleaf buscará el archivo add-serie.html
		} catch (Exception e) {
			model.addAttribute("mensajeError", e.toString());
			return Constante.PAGINA_ERROR;
		}
	}

	@PostMapping("/add-serie")
	public String insertarSerie(@ModelAttribute SerieModel serie, Model model) {
		try {
			serieService.insertarSerie(serie);
			model.addAttribute("mensaje", "Serie insertada correctamente.");
		} catch (Exception e) {
			model.addAttribute("error", "Error al insertar serie: " + e.getMessage());
		}
		return "redirect:/principal"; // o la vista que uses
	}







}
