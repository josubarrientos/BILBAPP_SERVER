/*
 * 
 * Copyright (C) 2016 Josu Barrientos Bahamonde
 * 
 * 
 * BILBAPP_SERVER is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * BILBAPP is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details
 * <http://www.gnu.org/licenses/>.
 */

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
	public Response addCritica(CriticaJSON criticaJSON) {
		
	
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
	
//////////////////////////////////
///////////////////AddCalificaicon
//////////////////////////////////
	
	@SuppressWarnings("unchecked")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("/addPuntuacion")	
	public Response addPuntuacion(CalificacionJSON calificacionJSON) {
		
	
		System.out.println("Solicitud de añadir calificacion de: "+hsr.getRemoteAddr());
		
		Response response;
		
		List<Sitio> sitiosList=(List<Sitio>)em.createNamedQuery("Sitio.findAllBySitio").setParameter("sitio", calificacionJSON.getSitio()).getResultList();
		
		
		System.out.println("Añadiendo nueva puntuacion de usuario en la DDBB");
		System.out.println(calificacionJSON.getCalificacion());
		System.out.println(calificacionJSON.getSitio());
		System.out.println();
		
		int contador_base=sitiosList.get(0).getContador();
		float puntuacion_base=sitiosList.get(0).getPuntuacion();
		
		float puntos_totales=puntuacion_base*contador_base+calificacionJSON.getCalificacion();
		int contador_actualizado=contador_base+1;
		
		float nueva_puntuacion=puntos_totales/contador_actualizado;
		
		sitiosList.get(0).setContador(contador_actualizado);
		sitiosList.get(0).setPuntuacion(nueva_puntuacion);
		
		em.persist(sitiosList.get(0));
		
		response=Response.status(200).entity("ok").build();
		return response;	
		
	}
	
}
