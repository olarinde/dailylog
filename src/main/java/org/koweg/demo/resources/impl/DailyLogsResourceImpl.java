package org.koweg.demo.resources.impl;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/dailylogs")
public class DailyLogsResourceImpl {

    @GET
    @Produces("text/plain")
    public String dailyLogs() {
        return "Daily Logs. Work in progress.....";
    }

    @GET
    @Path("/download")
    @Produces("application/pdf")
    public Response getEventAsPdf() {
        System.out.println("######### LOADING PDF file ############");
       InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("events.json.pdf");
        ResponseBuilder response = Response.ok((Object) resourceAsStream);
        response.header("Content-Disposition", "attachment; filename=events.pdf");
        return response.build();
    }

}
