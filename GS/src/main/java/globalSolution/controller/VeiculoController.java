package globalSolution.controller;


import globalSolution.dominio.Morador;
import globalSolution.dominio.Veiculo;
import globalSolution.infra.dao.VeiculoDAO;
import globalSolution.service.VeiculoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("veiculos")
public class VeiculoController {

    private VeiculoDAO veiculoDAO;
    private VeiculoService veiculoService;

    public VeiculoController(){
        veiculoDAO = new VeiculoDAO();
        veiculoService = new VeiculoService(veiculoDAO);
    }

    @POST
    public Response salvarVeiculo(Veiculo veiculo){
        try {
            veiculoService.adicionar(veiculo);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos(){
        try{
            ArrayList<Veiculo> veiculos = veiculoService.listarVeiculos();
            return Response.status(Response.Status.OK).entity(veiculos).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornaPorID(@PathParam("id") int id){
        try {
            Veiculo veiculo = veiculoService.buscarMoradorPorID(id);
            return Response.status(Response.Status.OK).entity(veiculo).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(@PathParam("id") long id, Veiculo veiculo){
        try{
            veiculoService.atualizar(id, veiculo);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarVeiculo(@PathParam("id") long id) {
        try {
            veiculoService.remover(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }




}
