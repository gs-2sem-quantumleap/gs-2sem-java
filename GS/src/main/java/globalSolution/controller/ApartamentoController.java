package globalSolution.controller;


import globalSolution.dominio.Apartamento;
import globalSolution.infra.dao.ApartamentoDAO;
import globalSolution.service.ApartamentoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("apartamentos")
public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;
    private ApartamentoService apartamentoService;

    public ApartamentoController() {
        apartamentoDAO = new ApartamentoDAO();
        apartamentoService = new ApartamentoService(apartamentoDAO);
    }

    @POST
    public Response salvarApartamento(Apartamento apartamento) {
        try {
            apartamentoService.adicionarApartamento(apartamento);
            return Response.status(Response.Status.CREATED).build();
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
            Apartamento apartamento = apartamentoService.buscarApartamento(id);
            return Response.status(Response.Status.OK).entity(apartamento).build();
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
            List<Apartamento> apartamento = apartamentoService.buscarApartamentos();
            return Response.status(Response.Status.OK).entity(apartamento).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarApartamento(@PathParam("id") long id, Apartamento apartamento) {
        try{
            apartamentoService.alterarApartamento(id, apartamento);
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
    public Response deletarApartamento(@PathParam("id") long id) {
        try {
            apartamentoService.excluirApartamento(id);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }









}
