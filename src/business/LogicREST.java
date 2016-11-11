package business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.annotation.PostConstruct;
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


@Singleton//Anotación de EJB compatible con Web Service
@Path("/Bilbapp")
public class LogicREST {
    @Context
    private javax.servlet.http.HttpServletRequest hsr;
    
    @PersistenceContext
    EntityManager em;
	
	public LogicREST() {
	}
	
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
			OpcionJSON lJSON=new OpcionJSON(l.getOpcion());//Crear objeto LessonJSON, copiando lessonCode y title
			opcionJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		
		System.out.println(opcionJSONList.size());
		
		opcionesJSON.setOpciones(opcionJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON
		
		return opcionesJSON;
		
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/requestSitios")	
	public SitiosJSON SitioJSON() {
		System.out.println("requestSitios: "+hsr.getRemoteAddr());
		
		System.out.println("DENTRO");
		
		List<Sitio> sitiosList=(List<Sitio>)em.createNamedQuery("Sitio.findAll").getResultList();//Consultar la lista de todas las lecciones
		
		System.out.println("SALIDA");
		
		SitiosJSON sitiosJSON=new SitiosJSON();
		List<SitioJSON> sitioJSONList=new ArrayList<SitioJSON>();
		
		
		System.out.println(sitiosList.size());
		
		
		for(int i=0;i<sitiosList.size();i++){//Para cada lección de la lista
			Sitio l=sitiosList.get(i);
			SitioJSON lJSON=new SitioJSON(l.getSitio(),l.getDireccion(),l.getPuntuacion());//Crear objeto LessonJSON, copiando lessonCode y title
			sitioJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
		}
		
		System.out.println(sitioJSONList.size());
		
		sitiosJSON.setSitios(sitioJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON
		
		return sitiosJSON;
		
	}
	
	
	
	
	
	///////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/requestCriticas")	
	public CriticasJSON CriticaJSON() {
	System.out.println("requestCriticas: "+hsr.getRemoteAddr());
	
	System.out.println("DENTRO");
	
	List<Critica> criticasList=(List<Critica>)em.createNamedQuery("Critica.findAll").getResultList();//Consultar la lista de todas las lecciones
	
	System.out.println("SALIDA");
	
	CriticasJSON criticasJSON=new CriticasJSON();
	List<CriticaJSON> criticaJSONList=new ArrayList<CriticaJSON>();
	
	
	System.out.println(criticasList.size());
	
	
	for(int i=0;i<criticasList.size();i++){//Para cada lección de la lista
	Critica l=criticasList.get(i);
	CriticaJSON lJSON=new CriticaJSON(l.getIdCritica(),l.getCritica(),l.getFecha(),l.getUsuario());//Crear objeto LessonJSON, copiando lessonCode y title
	criticaJSONList.add(lJSON);//Añadir objeto LessonJSON creado a la lista lessonJSONList
	}
	
	System.out.println(criticaJSONList.size());
	
	criticasJSON.setCriticas(criticaJSONList);//Meter la lista lessonJSONList en el objeto lessonsJSON
	
	return criticasJSON;
	
	}


}
