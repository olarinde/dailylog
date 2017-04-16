package org.koweg.demo.resources.impl;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.koweg.demo.dailylog.api.ApplicationInfo;
import org.koweg.demo.dailylog.api.ApplicationInfo.Builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/dailylogs")
public class DailyLogsResourceImpl {

    private static final ObjectMapper mapper = new ObjectMapper();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String dailyLogs() {
        ApplicationInfo applicationInfo = new ApplicationInfo("Daily Logs", "1.0.0", "ACTIVE");
        String result = null;
        try {
            result = mapper.writeValueAsString(applicationInfo).toString();
        } catch (JsonProcessingException e) {
        }
        return result;
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
