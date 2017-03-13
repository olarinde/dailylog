package org.koweg.demo.dailylog.api.exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.ParamException;
import org.koweg.demo.dailylog.api.model.ErrorMessage;

public class ParameterExceptionMapper implements ExceptionMapper<ParamException> {

    @Override
    public Response toResponse(ParamException exception) {
        return Response.status(Status.BAD_REQUEST)
                .entity(new ErrorMessage()
                        .withErrorCode(Status.BAD_REQUEST.name())
                        .withErrorMessage("Invalid parameter"))
                .build();
    }

}
