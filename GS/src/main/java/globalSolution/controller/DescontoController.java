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




}
