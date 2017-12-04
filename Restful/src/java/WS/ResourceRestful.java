/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Dao.DaoCliente;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.Cliente;

/**
 * REST Web Service
 *
 * @author jonat
 */
@Path("ADO2")
public class ResourceRestful {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public ResourceRestful() {
    }

    /**
     * Retrieves representation of an instance of WS.ResourceRestful
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        return "teste";
    }
    
    @GET
    @Produces("application/json")
    @Path("cliente/listarClientes")
    public String getClientes() throws Exception {
        Gson g = new Gson();
        DaoCliente dc = new DaoCliente();
        List<Cliente> clientes = dc.listar();
        
        return g.toJson(clientes);
    }

    /**
     * PUT method for updating or creating an instance of ResourceRestful
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
