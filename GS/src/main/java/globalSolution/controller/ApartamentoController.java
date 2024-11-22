package globalSolution.controller;


import globalSolution.dominio.Apartamento;
import globalSolution.dominio.ConsumoMorador;
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

    @GET
    @Path("/menoresGastos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTop3() {
        try {
            List<ConsumoMorador> apartamento = apartamentoService.buscarTop3MenoresConsumos();
            return Response.status(Response.Status.OK).entity(apartamento).build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
