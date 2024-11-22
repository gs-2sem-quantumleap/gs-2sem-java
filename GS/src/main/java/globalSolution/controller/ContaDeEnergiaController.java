package globalSolution.controller;

import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.Morador;
import globalSolution.infra.dao.ContaDeEnergiaDAO;
import globalSolution.infra.dao.VeiculoDAO;
import globalSolution.service.ContaDeEnergiaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("contas")
public class ContaDeEnergiaController {

    private ContaDeEnergiaDAO contaDeEnergiaDAO;
    private ContaDeEnergiaService contaDeEnergiaService;
    private VeiculoDAO veiculoDAO;

    public ContaDeEnergiaController() {
        contaDeEnergiaDAO = new ContaDeEnergiaDAO();
        veiculoDAO = new VeiculoDAO();
        contaDeEnergiaService = new ContaDeEnergiaService(contaDeEnergiaDAO, veiculoDAO);
    }

    @POST
    public Response salvarConta(ContaDeEnergia contaDeEnergia) {
        try {
            contaDeEnergiaService.adicionarConta(contaDeEnergia);
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
            ContaDeEnergia contaDeEnergia = contaDeEnergiaService.buscarConta(id);
            return Response.status(Response.Status.OK).entity(contaDeEnergia).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/buscaPorCPF/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaContasPorCPF(@PathParam("cpf") String cpf) {
        try {
            List<ContaDeEnergia> conta = contaDeEnergiaService.buscarContasPorCPF(cpf);
            return Response.status(Response.Status.OK).entity(conta).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarConta(@PathParam("id") long id, ContaDeEnergia contaDeEnergia) {
        try{
            contaDeEnergiaService.atualizarContas(id, contaDeEnergia);
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
    public Response deletarConta(@PathParam("id") long id) {
        try {
            contaDeEnergiaService.removerConta(id);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
