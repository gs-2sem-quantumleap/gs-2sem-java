package globalSolution.controller;


import globalSolution.dominio.Desconto;
import globalSolution.dominio.Morador;
import globalSolution.infra.dao.DescontoDAO;
import globalSolution.service.DescontoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("descontos")
public class DescontoController {
    private DescontoService descontoService;
    private DescontoDAO descontoDAO;

    public DescontoController(){
        descontoDAO = new DescontoDAO();
        descontoService = new DescontoService(descontoDAO);
    }

    @POST
    public Response salvarDesconto(Desconto desconto) {
        try {
            descontoService.adicionar(desconto);
            return Response.status(Response.Status.CREATED).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMoradores() {
        try {
            ArrayList<Desconto> descontos = descontoService.listaDesconto();
            return Response.status(Response.Status.OK).entity(descontos).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarDesconto(@PathParam("id") long id, Desconto desconto) {
        try{
            descontoService.atualizarDesconto(id, desconto);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarDesconto(@PathParam("id") long id) {
        try {
            descontoService.removerDesconto(id);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
