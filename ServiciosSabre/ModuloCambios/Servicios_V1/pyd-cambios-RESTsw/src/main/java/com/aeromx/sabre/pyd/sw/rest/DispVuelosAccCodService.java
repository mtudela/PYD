package com.aeromx.sabre.pyd.sw.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aeromx.sabre.pyd.SSSAdvShopPortTypeClient;
import com.aeromx.sabre.pyd.dao.DisponibilidadVuelosDAO;
import com.aeromx.sabre.pyd.pojo.PricedItinerary;
import com.aeromx.sabre.pyd.pojo.Segmento;
import com.aeromx.sabre.pyd.pojo.TipoPasajeroDisp;
import com.aeromx.sabre.pyd.util.Util;


@Path("/dispVuelosAccCodService")
public class DispVuelosAccCodService {
	
		/*
	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response getDispVuelos(@PathParam("f") String pOrigen) throws JSONException {

		JSONObject jsonObject = new JSONObject();
		//float celsius;
		//celsius =  (f - 32)*5/9; 
		jsonObject.put("Origen", pOrigen); 
		//jsonObject.put("C Value", celsius);

		SSSAdvShopPortTypeClient client = new SSSAdvShopPortTypeClient();
		
		String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }  
	  DisponibilidadVuelosDAO pInputAvailability
	  */
	  
	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response getDispVuelos(@PathParam("f") String pToken) throws JSONException {

		  SSSAdvShopPortTypeClient client = new SSSAdvShopPortTypeClient();  
		
		  /*
		   * Datos de prueba
		   * */
		  String tokenSec    = pToken ; 
	      int    maxConns    = 999; //Maximo de conexiones
	      String versionHdr  = "1.9.7";
	      String partyIdFrom = "AeromexicoPyD";
	      String partyIdTo   = "Sabre";
	      String orgCode     = "AM";
	      String convId      = "123456789";
	      String service     = "SSSAdvShopService";
	      String action      = "SSSAdvShopRQ";
	      String pCC         = "AZG";
	      String tipoReq     = "0.AAA.X";
	      String idReq       = "STRING";
	      String compCode    = "SSW";
	      String compContx   = "Context";
	      String accountCode = "PASNS";
	      String tpoTrans    = "ALTDATES";  //"ALTDATES"  "BRDFARES" 
	                                        //, depende cual se mande devuelve 
	                                        //los tipos en un tipo 
	                                        //difernete de datos
	      String versionService = "1.9.7";
	      

		  String urlWsdl = Util.getEnvironmentVariable("WSDL_SSSADVSHOP");
		  System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno desde Sw: "+urlWsdl);
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL Sw: "+this.getEnvironmentVariable("WSDL_SSSADVSHOP"));
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL - LOCAL Sw: "+System.getenv("WSDL_SSSADVSHOP"));
		
		
	      
	      tokenSec = "Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTD!ICESMSLB\\/CRT.LB!-3243144158630237306!822835!0";
	      
	      List<TipoPasajeroDisp> passQtyTypeLst = new ArrayList<TipoPasajeroDisp>();
	      TipoPasajeroDisp tpoPass = new TipoPasajeroDisp();
	      tpoPass.setCantidad(2);
	      tpoPass.setCodigo("STA");
	      
	      passQtyTypeLst.add(tpoPass);
	      
	      List<Segmento> origenDestLst = new ArrayList<Segmento>();
	      
	      Segmento segmentoIda = new Segmento();
	      segmentoIda.setFechaVuelo("2017-09-15T00:00:00");
	      segmentoIda.setHrVuelo("00002359");
	      segmentoIda.setIataOrigen("MTY");                                   
	      segmentoIda.setIataDestino("GRU");
	      segmentoIda.setRph("1");
	      
	                           
	      Segmento segmentoRegreso = new Segmento();
	      segmentoRegreso.setFechaVuelo("2017-09-26T00:00:00");
	      segmentoRegreso.setHrVuelo("00002359");
	      segmentoRegreso.setIataOrigen("GRU");                                   
	      segmentoRegreso.setIataDestino("MTY");
	      segmentoRegreso.setRph("2");     
	      
	      /*Segmento segmentoIda = new Segmento();
	      segmentoIda.setFechaVuelo("2017-09-11T00:00:00");
	      segmentoIda.setHrVuelo("00002359");
	      segmentoIda.setIataOrigen("MTY");                                   
	      segmentoIda.setIataDestino("GRU");
	      segmentoIda.setRph("1");*/
	      
	    /*  Segmento segmentoRegreso = new Segmento();
	      segmentoRegreso.setFechaVuelo("2017-09-25T00:00:00");
	      segmentoRegreso.setHrVuelo("00002359");
	      segmentoRegreso.setIataOrigen("GRU");                                   
	      segmentoRegreso.setIataDestino("MTY");
	      segmentoRegreso.setRph("2");*/
	      
	      origenDestLst.add(segmentoIda);
	      origenDestLst.add(segmentoRegreso);

	       List<PricedItinerary> disponibilidadLst = client.consultaItinerario(tokenSec
	                       , versionHdr, partyIdFrom
	                       , partyIdTo, orgCode
	                       , convId,  service
	                       , action, pCC
	                       , tipoReq, idReq  
	                       , compCode, compContx  
	                       , passQtyTypeLst
	                       , accountCode, tpoTrans
	                       , versionService  
	                       , origenDestLst  
	                       , maxConns);
	      //System.out.println("Tama�o final: "+priceItineraryOutputLst.size());
	       client.imprimeResultadoDisponibilidad(disponibilidadLst);
	      
	      //Imprimiendo Salida
		  
	      JSONObject jsonObject = new JSONObject();
		  //float celsius;
		  //celsius =  (f - 32)*5/9; 
		  jsonObject.put("Token", pToken); 
		  jsonObject.put("TokenLocal", tokenSec); 
		  jsonObject.put("FlightsAvailable", disponibilidadLst);
		  if(disponibilidadLst !=null) {
			  jsonObject.put("Result", disponibilidadLst.size());
		  }

		String result = "@Produces(\"application/json\") Output: \n\nResultado Output: \n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	  }
	  
	  /*public String getEnvironmentVariable(String variable){
	        String valueVariable = null;
	        try{
	            valueVariable = System.getenv(variable);
	            System.err.println("Valor de variable: "+valueVariable);
	        }catch(Exception e){
	            valueVariable = null;
	        }
	        return valueVariable;
	 } */
	
	  /*@GET
	  @Produces("application/json")
	  @Consumes("application/json")
	  public JSONObject getDispVuelos(JSONObject inputJsonObj) throws JSONException {

		  SSSAdvShopPortTypeClient client = new SSSAdvShopPortTypeClient();  
		
		  DisponibilidadVuelosDAO inputDAO = (DisponibilidadVuelosDAO) inputJsonObj.get("disponibilidadVlos");
		  
		  /*
		   * Datos de prueba
		   * */
		 /* String tokenSec    = inputDAO.getTokenSec() ; 
	      int    maxConns    = inputDAO.getMaxConns(); //Maximo de conexiones
	      String versionHdr  = inputDAO.getVersionHdr();
	      String partyIdFrom = inputDAO.getPartyIdFrom();
	      String partyIdTo   = inputDAO.getPartyIdTo();
	      String orgCode     = inputDAO.getOrgCode();
	      String convId      = inputDAO.getConvId();
	      String service     = inputDAO.getService();
	      String action      = inputDAO.getAction();
	      String pCC         = inputDAO.getpCC();
	      String tipoReq     = inputDAO.getTipoReq();
	      String idReq       = inputDAO.getIdReq();
	      String compCode    = inputDAO.getCompCode();
	      String compContx   = inputDAO.getCompContx();
	      String accountCode = inputDAO.getAccountCode();
	      String tpoTrans    = inputDAO.getTpoTrans();  //"ALTDATES"  "BRDFARES" 
	                                        //, depende cual se mande devuelve 
	                                        //los tipos en un tipo 
	                                        //difernete de datos
	      String versionService = inputDAO.getVersionService();
	      

		  String urlWsdl = Util.getEnvironmentVariable("WSDL_SSSADVSHOP");
		  System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno desde Sw: "+urlWsdl);
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL Sw: "+this.getEnvironmentVariable("WSDL_SSSADVSHOP"));
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL - LOCAL Sw: "+System.getenv("WSDL_SSSADVSHOP"));
		
		
	      
	      //tokenSec = "Shared/IDL:IceSess\\/SessMgr:1\\.0.IDL/Common/!ICESMS\\/ACPCRTD!ICESMSLB\\/CRT.LB!-3243144158630237306!822835!0";
	      
	      List<TipoPasajeroDisp> passQtyTypeLst = inputDAO.getPassQtyTypeLst();
	      
	      List<Segmento> origenDestLst = inputDAO.getOrigenDestLst();

	      List<PricedItinerary> disponibilidadLst = client.consultaItinerario(tokenSec
	                       , versionHdr, partyIdFrom
	                       , partyIdTo, orgCode
	                       , convId,  service
	                       , action, pCC
	                       , tipoReq, idReq  
	                       , compCode, compContx  
	                       , passQtyTypeLst
	                       , accountCode, tpoTrans
	                       , versionService  
	                       , origenDestLst  
	                       , maxConns);
	      //System.out.println("Tama�o final: "+priceItineraryOutputLst.size());
	       client.imprimeResultadoDisponibilidad(disponibilidadLst);
	      
	      //Imprimiendo Salida
		  
	      JSONObject jsonObject = new JSONObject();
		  //float celsius;
		  //celsius =  (f - 32)*5/9; 
		  //jsonObject.put("Token", pToken); 
		  jsonObject.put("TokenLocal", tokenSec); 
		  jsonObject.put("FlightsAvailable", disponibilidadLst);
		  if(disponibilidadLst !=null) {
			  jsonObject.put("Result", disponibilidadLst.size());
		  }

		String result = "@Produces(\"application/json\") Output: \n\nResultado Output: \n\n" + jsonObject;
		System.out.println("******************************RESULTADO FINAL *****************************");
		System.out.println(result);
		return jsonObject; 
	  }*/

	  //DisponibilidadVuelosDAO
	  /*@POST
	  /*@Produces("application/json")
	  @Consumes("application/json")*/
	 /* @Consumes({MediaType.APPLICATION_JSON})
	  @Produces({MediaType.APPLICATION_JSON})
	  @Path("/post")
	  public JSONObject postDispVuelos(JSONObject inputJsonObj) throws JSONException {

		  System.out.println("Llego al servicio - OK !!!!");
		  SSSAdvShopPortTypeClient client = new SSSAdvShopPortTypeClient();  
		
		  String tokenSec = (String)inputJsonObj.get("tokenSec");
		  int    maxConns    = (Integer)inputJsonObj.get("maxConns");
		  String versionHdr  = (String)inputJsonObj.get("versionHdr");
	      String partyIdFrom = (String)inputJsonObj.get("partyIdFrom");
	      String partyIdTo   = (String)inputJsonObj.get("partyIdTo");
	      String orgCode     = (String)inputJsonObj.get("orgCode");
	      String convId      = (String)inputJsonObj.get("convId");
	      String service     = (String)inputJsonObj.get("service");
	      String action      = (String)inputJsonObj.get("action");
	      String pCC         = (String)inputJsonObj.get("pCC");
	      String tipoReq     = (String)inputJsonObj.get("tipoReq");
	      String idReq       = (String)inputJsonObj.get("idReq");
	      String compCode    = (String)inputJsonObj.get("compCode");
	      String compContx   = (String)inputJsonObj.get("compContx");
	      String accountCode = (String)inputJsonObj.get("accountCode");
	      String tpoTrans    = (String)inputJsonObj.get("tpoTrans");
	      String versionService = (String)inputJsonObj.get("versionService");
		  
	      List<TipoPasajeroDisp> passQtyTypeLst = (List<TipoPasajeroDisp>)inputJsonObj.get("passQtyTypeLst");
	      
	      List<Segmento> origenDestLst = (List<Segmento>)inputJsonObj.get("origenDestLst");	

		  /*
		   * Datos de prueba
		   * */
		  /*String tokenSec    = token; //inputDAO.getTokenSec() ; 
	      int    maxConns    = inputDAO.getMaxConns(); //Maximo de conexiones
	      String versionHdr  = inputDAO.getVersionHdr();
	      String partyIdFrom = inputDAO.getPartyIdFrom();
	      String partyIdTo   = inputDAO.getPartyIdTo();
	      String orgCode     = inputDAO.getOrgCode();
	      String convId      = inputDAO.getConvId();
	      String service     = inputDAO.getService();
	      String action      = inputDAO.getAction();
	      String pCC         = inputDAO.getpCC();
	      String tipoReq     = inputDAO.getTipoReq();
	      String idReq       = inputDAO.getIdReq();
	      String compCode    = inputDAO.getCompCode();
	      String compContx   = inputDAO.getCompContx();
	      String accountCode = inputDAO.getAccountCode();
	      String tpoTrans    = inputDAO.getTpoTrans();  //"ALTDATES"  "BRDFARES" 
	                                        //, depende cual se mande devuelve 
	                                        //los tipos en un tipo 
	                                        //difernete de datos
	      String versionService = inputDAO.getVersionService();
	      */

	/*	  String urlWsdl = Util.getEnvironmentVariable("WSDL_SSSADVSHOP");
		  System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno desde Sw: "+urlWsdl);
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL Sw: "+this.getEnvironmentVariable("WSDL_SSSADVSHOP"));
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL - LOCAL Sw: "+System.getenv("WSDL_SSSADVSHOP"));
		
	      
	     /* List<TipoPasajeroDisp> passQtyTypeLst = inputDAO.getPassQtyTypeLst();
	      
	      List<Segmento> origenDestLst = inputDAO.getOrigenDestLst();*/
/*
	      List<PricedItinerary> disponibilidadLst = client.consultaItinerario(tokenSec
	                       , versionHdr, partyIdFrom
	                       , partyIdTo, orgCode
	                       , convId,  service
	                       , action, pCC
	                       , tipoReq, idReq  
	                       , compCode, compContx  
	                       , passQtyTypeLst
	                       , accountCode, tpoTrans
	                       , versionService  
	                       , origenDestLst  
	                       , maxConns);
	      //System.out.println("Tama�o final: "+priceItineraryOutputLst.size());
	       client.imprimeResultadoDisponibilidad(disponibilidadLst);
	      
	      //Imprimiendo Salida
		  
	      JSONObject jsonObject = new JSONObject();
		  //float celsius;
		  //celsius =  (f - 32)*5/9; 
		  //jsonObject.put("Token", pToken); 
		  jsonObject.put("TokenLocal", tokenSec); 
		  //jsonObject.put("FlightsAvailable", disponibilidadLst);
		  //if(disponibilidadLst !=null) {
		//	  jsonObject.put("Result", disponibilidadLst.size());
		 // }

		String result = "@Produces(\"application/json\") Output: \n\nResultado Output: \n\n" + jsonObject;
		System.out.println("******************************RESULTADO FINAL *****************************");
		System.out.println(result);
		return jsonObject; 
	  }*/
	  
	  public String getEnvironmentVariable(String variable){
	        String valueVariable = null;
	        try{
	            valueVariable = System.getenv(variable);
	            System.err.println("Valor de variable: "+valueVariable);
	        }catch(Exception e){
	            valueVariable = null;
	        }
	        return valueVariable;
	 } 
	  
	  @POST
	  @Consumes({MediaType.APPLICATION_JSON})
	  @Produces({MediaType.APPLICATION_JSON})
	  @Path("/postDispVuelos")
	  public Response postDispVuelos(InputStream incomingData) throws JSONException {
	  //public JSONObject postDispVuelos(DisponibilidadVuelosDAO inputDAO) throws JSONException {

		  System.out.println("**************************************************");
	      System.out.println("**************************************************");
	      System.out.println("  Iniciando peticiones de servicio: postDispVuelos");
	      System.out.println("**************************************************");
	      System.out.println("**************************************************");

		  
		  String tokenSec = "";
		  int    maxConns    = 0;
		  String versionHdr  = "";
	      String partyIdFrom = "";
	      String partyIdTo   = "";
	      String orgCode     = "";
	      String convId      = "";
	      String service     = "";
	      String action      = "";
	      String pCC         = "";
	      String tipoReq     = "";
	      String idReq       = "";
	      String compCode    = "";
	      String compContx   = "";
	      String accountCode = "";
	      String tpoTrans    = "";
	      String versionService = "";
	      List<TipoPasajeroDisp> passQtyTypeLst = new ArrayList<TipoPasajeroDisp>();
	      List<Segmento> origenDestLst = new ArrayList<Segmento>();
	      List<PricedItinerary> disponibilidadLst = new ArrayList<PricedItinerary>();
	      
	      String urlWsdl = Util.getEnvironmentVariable("WSDL_SSSADVSHOP");
		  System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno desde Sw: "+urlWsdl);
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL Sw: "+this.getEnvironmentVariable("WSDL_SSSADVSHOP"));
	      System.out.println("******************************************");
	      System.out.println("******************************************");
	      System.out.println("Valor de variable de entorno LOCAL - LOCAL Sw: "+System.getenv("WSDL_SSSADVSHOP"));
		
		  
		  StringBuilder crunchifyBuilder = new StringBuilder();
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
				String line = null;
				while ((line = in.readLine()) != null) {
					crunchifyBuilder.append(line);
				}
			} catch (Exception e) {
				System.out.println("Error Parsing: - ");
			}
			System.out.println("Data Received: " + crunchifyBuilder.toString());
			JSONObject requestJson = new JSONObject(crunchifyBuilder.toString());
			
			System.out.println("*********************************************");
			System.out.println("Imprimiendo JSON: "+requestJson.toString());
			System.out.println("*********************************************");
			
			if(requestJson !=null) {
				  SSSAdvShopPortTypeClient client = new SSSAdvShopPortTypeClient();
				  System.out.println("Valor no nulo......");
				  System.out.println("Num Max: "+requestJson.getString("tokenSec"));
				  tokenSec = (String)requestJson.get("tokenSec");
				  maxConns    = (Integer)requestJson.get("maxConns");
				  versionHdr  = (String)requestJson.get("versionHdr");
			      partyIdFrom = (String)requestJson.get("partyIdFrom");
			      partyIdTo   = (String)requestJson.get("partyIdTo");
			      orgCode     = (String)requestJson.get("orgCode");
			      convId      = (String)requestJson.get("convId");
			      service     = (String)requestJson.get("service");
			      action      = (String)requestJson.get("action");
			      pCC         = (String)requestJson.get("pCC");
			      tipoReq     = (String)requestJson.get("tipoReq");
			      idReq       = (String)requestJson.get("idReq");
			      compCode    = (String)requestJson.get("compCode");
			      compContx   = (String)requestJson.get("compContx");
			      accountCode = (String)requestJson.get("accountCode");
			      tpoTrans    = (String)requestJson.get("tpoTrans");
			      versionService = (String)requestJson.get("versionService");
			      //passQtyTypeLst = (ArrayList<TipoPasajeroDisp>)requestJson.get("passQtyTypeLst");
			      //origenDestLst = (ArrayList<Segmento>)requestJson.get("origenDestLst");
			      JSONArray passQtyArr = requestJson.getJSONArray("passQtyTypeLst");
			      JSONArray origenDestinoArr = requestJson.getJSONArray("origenDestLst");
			      
			      int i = passQtyArr.length();
			      for(int j=0; j<i; j++) {
			    	  JSONObject persJson = passQtyArr.getJSONObject(j);
			    	  TipoPasajeroDisp pass = new TipoPasajeroDisp();
			    	  pass.setCantidad(persJson.getInt("cantidad"));
			    	  pass.setCodigo(persJson.getString("codigo"));
			    	  passQtyTypeLst.add(pass);
			      }
			      
			      i = origenDestinoArr.length();
			      for(int j=0; j<i; j++) {
			    	  JSONObject origDstJson = origenDestinoArr.getJSONObject(j);
			    	  String names[] = origDstJson.getNames(origDstJson);
			    	  for(String n: names) {
			    		  System.out.println(n);
			    	  }
			    	  System.out.println(origDstJson.toString());
			    	  Segmento seg = new Segmento();
			    	  seg.setFechaVuelo(origDstJson.getString("fechaVuelo"));
			    	  seg.setHrVuelo(origDstJson.getString("hrVuelo"));
			    	  seg.setIataDestino(origDstJson.getString("iataDestino"));
			    	  seg.setIataOrigen(origDstJson.getString("iataOrigen"));
			    	  seg.setRph(origDstJson.getString("rph"));
			    	  origenDestLst.add(seg);
			      }
			      
				  System.out.println("TOKEN: "+requestJson.getString("tokenSec"));
				  System.out.println("LISTA 1 - Array: "+passQtyArr.length());
				  System.out.println("LISTA 1 - Lista: "+passQtyTypeLst.size());
				  System.out.println("LISTA 2 - Array: "+origenDestinoArr.length());
				  System.out.println("LISTA 2 - ListaS: "+origenDestLst.size());
				  System.out.println("***********************************");
				  disponibilidadLst = client.consultaItinerario(tokenSec
	                       , versionHdr, partyIdFrom
	                       , partyIdTo, orgCode
	                       , convId,  service
	                       , action, pCC
	                       , tipoReq, idReq  
	                       , compCode, compContx  
	                       , passQtyTypeLst
	                       , accountCode, tpoTrans
	                       , versionService  
	                       , origenDestLst  
	                       , maxConns);
				  System.out.println("Tamano final: "+disponibilidadLst.size());
				  client.imprimeResultadoDisponibilidad(disponibilidadLst);
			}
			//Se genera Json de Salida
			
			// return HTTP response 200 in case of success
			//return Response.status(200).entity(crunchifyBuilder.toString()).build();
			JSONObject jsonOutputObject = new JSONObject();
			jsonOutputObject.put("Response", "OK"); 
			if(disponibilidadLst !=null && (disponibilidadLst.size()>0)) {
				jsonOutputObject.put("FlightsAvailable", disponibilidadLst);
			}

			String result = jsonOutputObject.toString();
			System.out.println("******************************************************");
			System.out.println("Resultado antes de enviar respuesta correcta: "+result);
			System.out.println("******************************************************");
			return Response.status(200).entity(result).build();
	  }
	  
	  @POST
	  /*@Produces("application/json")
	  @Consumes("application/json")*/
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("/postDispVuelosRest")
	  public Response postDispVuelos(JSONObject inputJSONoBJ) throws JSONException {
	  //public JSONObject postDispVuelos(DisponibilidadVuelosDAO inputDAO) throws JSONException {

		  System.out.println("Llego al servicio - OK !!!!");
		  
		  if(inputJSONoBJ !=null) {
			  System.out.println("Valor no nulo......");
			  System.out.println("Num Max: "+inputJSONoBJ.getString("tokenSec"));
			  System.out.println("***********************************");
		  }
			
		  //System.out.println(test.toString());
		  JSONObject jsonObject = new JSONObject();
		
		  jsonObject.put("Resultado", "Exitoso!!!"); 

		  //SSSAdvShopPortTypeClient client = new SSSAdvShopPortTypeClient();
			
		  String result = "@Produces(\"application/json\") Output: \n\nTester Output: \n\n" + jsonObject;
		  return Response.status(200).entity(result).build();

	  }
	  
}
