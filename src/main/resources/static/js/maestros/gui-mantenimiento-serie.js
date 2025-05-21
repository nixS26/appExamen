// campos de formulario principal
var formSerie;
var campoBuscar;
var tipoDocumento;
// botones
var btnLimpiar;
var btnNuevo;
// tablas
var tablaSerie;
var dataTableSerie;


/**************** CARGA INICIAL DE FORMULARIO ****************************************************
 *************************************************************************************************/
$(document).ready(function() {
	inicializarVariables();
	inicializarComponentes();
	inicializarPantalla();
});

function inicializarVariables(){
	formSerie = $('#formSerie');
	campoBuscar = $('#campoBuscar');
	tipoDocumento = $('#tipoDocumento');
	btnLimpiar = $('#btnLimpiar');
	btnNuevo = $('#btnNuevo');
	tablaSerie = $('#tablaSerie');
}

function inicializarComponentes(){
	habilitarAnimacionAcordion();
	inicializarEventos();
	inicializarTabla();
}

function inicializarPantalla(){	
	campoBuscar.focus();
}

function inicializarEventos(){
	campoBuscar.on('keyup', function (e) {
		buscar();
	});
			
	tipoDocumento.on('click', function (e) {
		buscar();
	});
}


function inicializarTabla(){
	
	dataTableSerie = tablaSerie.DataTable({
        "ajax": {
			// se pasa la data de esta forma para poder reinicializar luego sólo la llamada ajax sin tener que dibujar de nuevo toda la tabla
			data: function ( d ) {
				d.datoBuscar 	= campoBuscar.val().trim();
				d.tipoDoc		= (tipoDocumento.val() == CADENA_VACIA) ? 0 : tipoDocumento.val();
		    },
            url: '/appExamen/listarSeries/',
                dataSrc: function (json) {
				console.log("listarSeries...success");
            	return json;
            },
            error: function (xhr, error, code){
				
            }
        },
            "responsive"	: false,
	        "scrollCollapse": false,
			"ordering"      : true,
	        "dom"			: '<ip<rt>lp>',
        	"lengthMenu"	: [[15, 30, 45, -1], [15, 30, 45, "Todos"]],
	        "deferRender"   : true,
	        "autoWidth"		: false,
	        "sortClasses"	: false,
		    "columnDefs"    : [
                {
                    "width": "3px",
                    "targets": 0,
                    "data": "id"
                },
                {
                    "width": "25px",
                    "targets": [1],
                    "data": "codSerie"
                },				
				{
                    "width": "35px",
                    "targets": [2],
                    "data": "desTipoDocumento"                    
                },
                {
                    "width": "35px",
                    "targets": [3],
                    "data": "nroSerie"
                },
                {
                    "width": "35px",
                    "targets": [4],
                    "data": "correlativo"
                },
				{
                    "width": "35px",
                    "targets": [5],
                    "data": "maxcorrelativo"
                },				
				{
                    "width": "3px",
                    "targets": [6],
                    "data": "activo",
                    "className": "dt-body-center text-center",
                    "render": function (data, type, row) {
                        let checked = data == 1 ? 'checked' : '';
                        return `<input type="checkbox" disabled ${checked}>`;
                    }
            	},
                {
                    "width": "20px",
                    "targets": [7],
                    "className": "dt-body-center",
                    "orderable": false,
					"render":
                    function (data, type, row ) {
                    	return  "<div style='display:flex;justify-content:space-around;'>" +
                        			"<button title='Ver' class='btn-view btn btn-info btn-xs'>" +
							                    "<span><i class=\"fas fa-eye\"></i></span>" +
							                "</button>" +											
							                "<button title='Modificar' class='btn-edit btn btn-primary btn-xs'>" +
                                                "<span><i class=\"fas fa-edit\"></i></span>" +
                                            "</button>" +
				                "</div>";
                    }
                }
             ],
             "fnRowCallback":
                 function(row, aData, iDisplayIndex, iDisplayIndexFull){
                    var index = iDisplayIndexFull + 1;
                    $('td:eq(0)', row).html(index);
									
					// modificando el tamaño de los caracteres del listado
					$(row).addClass("listado-tam-caracteres");

                    //coloreamos y ponemos en negrita si es activo == 0
                     if (aData.activo == 0) {
                         $(row).css("background-color", "#FFD580"); // naranja claro
                         $(row).find("td").css({
                             "color": "red",
                             "font-weight": "bold"
                         });
                     }

                    return row;
                 },
             "language"  : {
                "url": "/appExamen/language/Spanish.json"
            }
    });
	
}



/**************** FUNCIONES DE SOPORTE ***********************************************************
 *************************************************************************************************/

function buscar(){
	var form1 = formSerie;

	if ( $.fn.dataTable.isDataTable(tablaSerie)) {
		dataTableSerie.clear(); // usamos esta instrucción para limpiar la tabla sin que haya parpadeo
		dataTableSerie.ajax.reload(null, true);
	}
	form1.addClass('was-validated');
}


