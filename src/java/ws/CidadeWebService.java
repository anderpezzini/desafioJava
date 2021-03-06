/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import model.Cidade;
import daocidade.DAOCidade;
import dao.DAOFactory;
import daocsv.DAOCSV;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ander
 */
@Path("cidade")
public class CidadeWebService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CidadeWebService
     */
    public CidadeWebService() {
    }
    
    /**
     * Retrieves representation of an instance of WS.CidadeWebService
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/capitais") 
    public String getCapitais() {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        Gson g = new Gson();
        try {
            return g.toJson(daoCidade.getCapitais());
        } catch (SQLException | ClassNotFoundException | IOException ex) {            
            return g.toJson(ex.getMessage());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/maior-menor-estados")
    public String getMaiorEMenorEstados() {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        Gson g = new Gson();
        try {
            return g.toJson(daoCidade.getMaiorEMenorEstados());
        } catch (SQLException | ClassNotFoundException | IOException ex) {            
            return g.toJson(ex.getMessage());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idibge}")
    public String getCidadesPorIBGE(@PathParam("idibge")int idIBGE) {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        Gson g = new Gson();
        try {
            return g.toJson(daoCidade.getCidade(idIBGE));
        } catch (SQLException | ClassNotFoundException | IOException ex) {            
            return g.toJson(ex.getMessage());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/nomes/{uf}")
    public String getNomes(@PathParam("uf")String uf) {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        Gson g = new Gson();
        try {
            return g.toJson(daoCidade.getNomes(uf));
        } catch (SQLException | ClassNotFoundException | IOException ex) {            
            return g.toJson(ex.getMessage());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/csv/{coluna}/{filtro}")
    public String getCidadesCSV(@PathParam("coluna")int coluna, @PathParam("filtro")String filtro) {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        Gson g = new Gson();
        DAOCSV daoCsv = new DAOCSV();
        try {
            return g.toJson(daoCsv.getRegistros(coluna, filtro));
        } catch (IOException ex) {            
            return g.toJson(ex.getMessage());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/quantidade")
    public String getQuantidadeRegistros(@QueryParam("coluna")String coluna, @QueryParam("uf")String uf) {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        Gson g = new Gson();
        try {
            if (!coluna.equals("")) {
                return g.toJson(daoCidade.getQuantidade(coluna));
            }
            if (!uf.equals("")) {
                return g.toJson(daoCidade.getQuantidadeUF(uf)); 
            }
            return g.toJson(daoCidade.getQuantidade());
        } catch (SQLException | ClassNotFoundException | IOException ex) {            
            return g.toJson(ex.getMessage());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cidades-mais-distantes")
    public String getCidadesMaisDistantes() {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        Gson g = new Gson();
        try {
            return g.toJson(daoCidade.getCidadesMaisDistantes());
        } catch (SQLException | ClassNotFoundException | IOException ex) {            
            return g.toJson(ex.getMessage());
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean inserir(String content) {
        Gson g = new Gson();
        Cidade cidade = g.fromJson(content, Cidade.class);
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        try {
            return daoCidade.inserir(cidade);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            return false;
        }
    }
    
    @DELETE
    @Path("/{idibge}")
    public boolean excluir(@PathParam("idibge")int idIBGE) {
        DAOCidade daoCidade = DAOFactory.getDAOFactory().getDAOCidade();
        try {
            return daoCidade.excluir(idIBGE);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            return false;
        }
    }
    
    /**
     * PUT method for updating or creating an instance of CidadeWebService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
