package globalSolution.controller;


import globalSolution.dominio.Apartamento;
import globalSolution.dominio.Condominio;
import globalSolution.dominio.Morador;
import globalSolution.infra.dao.CondominioDAO;
import globalSolution.service.CondominioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("contominios")
public class CondominioController {
    private CondominioDAO condominioDAO;
    private CondominioService condominioService;

    public CondominioController() {
        condominioDAO = new CondominioDAO();
        condominioService = new CondominioService(condominioDAO);
    }
    @POST
    public Response salvarCondominio(Condominio condominio) {
        try {
            condominioService.adicionarCondominio(condominio);
            return Response.status(Response.Status.CREATED).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("apartamentoPorCondominio/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosApartamentosPorCondominio(@PathParam("id") int id) {
        try {
            List<Apartamento> apartamentos = condominioService.listarTodosApartamentosPorCondominio(id);
            return Response.status(Response.Status.OK).entity(apartamentos).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCondominios() {
        try {
            List<Condominio> condominios = condominioService.listarTodosCondominios();
            return Response.status(Response.Status.OK).entity(condominios).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response condominioPorID(@PathParam("id") int id) {
        try {
            Condominio condominio = condominioService.retornarCondominioPorId(id);
            return Response.status(Response.Status.OK).entity(condominio).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCondominio(@PathParam("id") long id, Condominio condominio) {
        try{
            condominioService.atualizarCondominio(id, condominio);
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
    public Response deletarCondomino(@PathParam("id") long id) {
        try {
            condominioService.excluirCondominio(id);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }




}
