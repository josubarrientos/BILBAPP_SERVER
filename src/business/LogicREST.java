package business;

import java.util.ArrayList;
import java.util.List;
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


@Singleton//Anotación de EJB compatible con Web Service
@Path("/Bilbapp")
public class LogicREST {
    @Context
    private javax.servlet.http.HttpServletRequest hsr;
    
    @PersistenceContext
    EntityManager em;
	
	public LogicREST() {
	}
	
//////////////////////////////////	
///////////////////RequestOpciones
//////////////////////////////////	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/requestOpciones")	
	public OpcionesJSON OpcionJSON() {
		
		System.out.println("Solicitud de opciones de: "+hsr.getRemoteAddr());

		List<Opcion> opcionesList=(List<Opcion>)em.createNamedQuery("Opcion.findAll").getResultList();
		
		OpcionesJSON opcionesJSON=new OpcionesJSON();
		List<OpcionJSON> opcionJSONList=new ArrayList<OpcionJSON>();
		
		for(int i=0;i<opcionesList.size();i++){
			Opcion l=opcionesList.get(i);
			OpcionJSON lJSON=new OpcionJSON(l.getOpcion());
			opcionJSONList.add(lJSON);
		}
		
		opcionesJSON.setOpciones(opcionJSONList);
		
		return opcionesJSON;
		
	}
	
//////////////////////////////////
///////////////////RequestSitios
//////////////////////////////////
	
	//http://localhost:8080/BILBAPP_SERVER/rest/Bilbapp/requestSitios?opcionName=Fiesta
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/requestSitios")	
	public SitiosJSON SitioJSON(@QueryParam("opcionName") String opcionName) {
		
		System.out.println("Solicitud de sitios de: "+hsr.getRemoteAddr());

		List<Sitio> sitiosList=(List<Sitio>)em.createNamedQuery("Sitio.findAllByOpcion").setParameter("opcion", opcionName).getResultList();
		
		SitiosJSON sitiosJSON=new SitiosJSON();
		List<SitioJSON> sitioJSONList=new ArrayList<SitioJSON>();		
		
		for(int i=0;i<sitiosList.size();i++){
			Sitio l=sitiosList.get(i);
			SitioJSON lJSON=new SitioJSON(l.getSitio(),l.getDireccion(),l.getPuntuacion());
			sitioJSONList.add(lJSON);
		}
		
		sitiosJSON.setSitios(sitioJSONList);
		
		return sitiosJSON;
		
	}		
	
//////////////////////////////////
///////////////////RequestCriticas
//////////////////////////////////
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/requestCriticas")	
	public CriticasJSON CriticaJSON(@QueryParam("sitioName") String sitioName) {
	
		System.out.println("Solicitud de criticas de: "+hsr.getRemoteAddr());

	
		List<Critica> criticasList=(List<Critica>)em.createNamedQuery("Critica.findAllBySitio").setParameter("sitio", sitioName).getResultList();
	
		CriticasJSON criticasJSON=new CriticasJSON();
		List<CriticaJSON> criticaJSONList=new ArrayList<CriticaJSON>();
	
		for(int i=0;i<criticasList.size();i++){
			Critica l=criticasList.get(i);
			CriticaJSON lJSON=new CriticaJSON(l.getCritica(),l.getFecha(),l.getUsuario(),null);
			criticaJSONList.add(lJSON);
		}
	
		criticasJSON.setCriticas(criticaJSONList);
	
		return criticasJSON;
	
	}
	
//////////////////////////////
///////////////////AddCriticas
//////////////////////////////
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/addCriticas")	
	public Response addUsuario(CriticaJSON criticaJSON) {
		
	
		System.out.println("Solicitud de añadir critica de: "+hsr.getRemoteAddr());
		
		Response response;
		
		List<Sitio> sitiosList=(List<Sitio>)em.createNamedQuery("Sitio.findAllBySitio").setParameter("sitio", criticaJSON.getSitio()).getResultList();
		
		
		System.out.println("Añadiendo nueva critica de usuario en la DDBB");
		System.out.println(criticaJSON.getUsuario());
		System.out.println(criticaJSON.getCritica());
		System.out.println(criticaJSON.getFecha());
		System.out.println(criticaJSON.getSitio());
		System.out.println();
		
		Critica critic=new Critica();
		critic.setCritica(criticaJSON.getCritica());
		critic.setFecha(criticaJSON.getFecha());
		critic.setUsuario(criticaJSON.getUsuario());
		critic.setSitio(sitiosList.get(0));
		//Persistir objeto student en el Contexto de Persistencia
		em.persist(critic);
		
		response=Response.status(200).entity("ok").build();
		
		return response;
		
		
	}
	
	/*
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
		
	}*/
	
	/*@SuppressWarnings("unchecked")
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
	
	}*/
	
	
}
