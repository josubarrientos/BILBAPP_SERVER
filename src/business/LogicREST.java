package business;

import java.util.ArrayList;
import java.util.List;

/*import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

*/

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import business.json.*;
import model.*;


import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

//imports necesarios
//import eus.ehu.INTEL901_504021.TTA1617.utils.FileUtils;

@Singleton//Anotación de EJB compatible con Web Service
@Path("/Bilbapp")
public class LogicREST {
    @Context
    private javax.servlet.http.HttpServletRequest hsr;
    
    @PersistenceContext
    EntityManager em;
	
	public LogicREST() {
	}

	/*@PostConstruct
	private void buildUploadFolderTree() {
		String contextName=hsr.getContextPath().substring(1);
		String folderNames[]={"audio", "compressed", "docs", "files", "img", "video"};
		
		//Crear la carpeta contextName con las subcarpetas indicadas en folderNames, dentro de la ubicación por defecto
		FileUtils.generateFolderTree(contextName, folderNames);
	}
	
	@Path("/uploadFile")//Nombre del metodo a traves de URL/metodo_name
	@POST	//Anotación de método para REST
    //Anotación del tipo de mensaje HTTP-req consumido
	@Consumes("multipart/form-data")
    public Response uploadFile(MultipartFormDataInput input) {
        System.out.println("uploadFile: "+hsr.getRemoteAddr());

        Response httpResponse=FileUtils.uploadFile(input);//Recoger el fichero recibido como contenido multipart y escribirlo en la ubicación por defecto
		
        return httpResponse;		
    }*/
/*
	@SuppressWarnings("unchecked")
	//Anotación de método para REST
	//Anotación del tipo de datos producido
    @Path("/requestCalification")
	public Response requestCalification(@QueryParam("solutionName") String solutionName) {
		System.out.println("requestCalification: "+hsr.getRemoteAddr());
		Response response;
		String exerciseCode=solutionName.substring(solutionName.indexOf("E"),solutionName.indexOf("_"));
		String login=solutionName.substring(solutionName.indexOf("_")+1);
		
		List<Solution> solutionList=//Consultar la lista de soluciones calificadas con código de ejercicio=exerciseCode y login=login
		//Si la lista es de 1 elemento (sólo puede haber un registro así en la tabla)
			response=Response.status(200).entity(solutionList.get(0).getCalification()).build();
		//si no
			response=//Construir HTTP-RESPONSE con contenido "NONE"

		return response;
	}	
*/
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/requestOpciones")	
	public OpcionesJSON OpcionJSON() {
		System.out.println("requestOpciones: "+hsr.getRemoteAddr());
		
		System.out.println("DENTRO");
		
		List<Opcion> opcionesList=(List<Opcion>)em.createNamedQuery("Opcion.findAll").getResultList();//Consultar la lista de todas las lecciones
		
		System.out.println("SALIDA");
		
		OpcionesJSON opcionesJSON=new OpcionesJSON();
		List<OpcionJSON> opcionJSONList=new ArrayList<OpcionJSON>();
		
		
		System.out.println(opcionesList.size());
		
		
		for(int i=0;i<opcionesList.size();i++){//Para cada lección de la lista
			Opcion l=opcionesList.get(i);
			OpcionJSON lJSON=new OpcionJSON(l.getIdOpcion(),l.getOpcion());//Crear objeto LessonJSON, copiando lessonCode y title
			opcionJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		
		System.out.println(opcionJSONList.size());
		
		opcionesJSON.setOpciones(opcionJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON
		
		return opcionesJSON;
		
	}

	/*
	@SuppressWarnings("unchecked")
	//Anotación de método para REST
	//Anotación del tipo de datos producido
	@Path("/requestInitialData")
	public InitialDataJSON requestInitialData(@QueryParam("login") String login, @QueryParam("lessonCode") String lessonCode) {
		System.out.println("requestInitialData: "+hsr.getRemoteAddr());
		
		InitialDataJSON initialDataJSON=new InitialDataJSON();
		
		List<Student> students=//Consultar la lista de estudiantes con login=login

		if(students.size()==1) {
			ExercisesJSON exercisesJSON=new ExercisesJSON(login,lessonCode);
			SolutionsJSON solutionsJSON=new SolutionsJSON(login,lessonCode);

			List<Exercise> exerciseList=//Consultar la lista de ejercicios con lessonCode=lessonCode (ejercicios de una lección concreta)
		
			List<ExerciseJSON> exerciseJSONList=new ArrayList<ExerciseJSON>();
			List<SolutionJSON> solutionJSONList=new ArrayList<SolutionJSON>();
			
			{//Para cada ejercicio de la lista
				Exercise e=exerciseList.get(i);
				//Crear objeto ExerciseJSON, copiando exerciseCode, wording y shortName de resourceType
				//Añadir objeto ExerciseJSON creado a la lista exerciseJSONList
			
				List<Solution> solutionList=//Consultar la lista de soluciones con exerciseCode=exerciseCode y login=login
			
				SolutionJSON sJSON;
				//Si la lista es de 1 elemento (sólo puede haber un registro así en la tabla)
					//Crear objeto sJSON, con la ubicación de la resolución y su calificación
				//Si no
					//Crear objeto sJSON, sin URL y calificación "NONE"
				//Añadir objeto sJSON creado a la lista solutionJSONList

			}
			//Meter la lista exerciseJSONList en el objeto exercisesJSON
			//Ajustar el atributo total de exercisesJSON según el tamaño de la lista exerciseJSONList

			//Meter la lista solutionJSONList en el objeto solutionsJSON
			//Ajustar el atributo total de solutionsJSON según el tamaño de la lista solutionJSONList
		
			//Meter el objeto exercisesJSON en el objeto initialDataJSON
			//Meter el objeto solutionsJSON en el objeto initialDataJSON
		}

		return initialDataJSON;
	}	
*/
/*
	@SuppressWarnings("unchecked")
	//Anotación de método para REST
	//Anotación del tipo de datos consumido	
	//Anotación del tipo de datos producido	
	@Path("/addStudent")	
	public Response addStudent(StudentJSON studentJSON) {
		System.out.println("addStudent: "+hsr.getRemoteAddr());
		Response response;
		
		List<Student> rolledStudentList=//Consultar la lista de estudiantes con dni=dni de studentJSON
		if(rolledStudentList.size()==0) {
			
			String loginPrefix=studentJSON.getName().substring(0,1).toLowerCase()+studentJSON.getSurname().substring(0,1).toLowerCase();
			
			List<Student> students=//Consultar la lista de estudiantes cuyo login comience por loginPrefix
			
			Student student=new Student();
			student.setDni(studentJSON.getDni());
			student.setLogin(loginPrefix+students.size());
			student.setName(studentJSON.getName());
			student.setSurname(studentJSON.getSurname());
			
			//Persistir objeto student en el Contexto de Persistencia
			
			response=Response.status(200).entity(/*nuevo login).build();
		}
		else
			response=Response.status(200).entity(/*login del usuario).build();
		
		return response;
	}*/
}
