package globalSolution.controller;


import globalSolution.dominio.Morador;
import globalSolution.infra.dao.MoradorDAO;
import globalSolution.service.MoradorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("moradores")
public class MoradorController {
    private MoradorDAO moradorDAO;
    private MoradorService moradorService;

    public MoradorController() {
        moradorDAO = new MoradorDAO();
        moradorService = new MoradorService(moradorDAO);
    }

    @POST
    public Response salvarMorador(Morador morador) {
        try {
            moradorService.adicionarMorador(morador);
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
            ArrayList<Morador> moradores = moradorService.listarMorador();
            return Response.status(Response.Status.OK).entity(moradores).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornaPorId(@PathParam("id") int id) {
        try{
            Morador morador = moradorService.buscarMoradorPorId(id);
            return Response.status(Response.Status.OK).entity(morador).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarMorador(@PathParam("id") long id, Morador morador) {
        try{
            moradorService.atualizarMorador(id, morador);
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
    public Response deletarMorador(@PathParam("id") long id) {
        try {
            moradorService.excluirMorador(id);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }









}
