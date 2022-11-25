package com.example.signature.filter;

import com.example.signature.domain.FilterError;
import com.example.signature.service.SignatureService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class FilterSignature extends GenericFilterBean {
    @Autowired
    private SignatureService signatureService;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        var request = (HttpServletRequest) servletRequest;
        var response= (HttpServletResponse) servletResponse;

        String signature = request.getHeader("X-HEADER");
        String message = request.getHeader("X-MESSAGE");
        try {
            if (signature==null){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);//when exception appear in filter, construct error message
            }else{
                if(!signatureService.isValid(message,signature)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }else{
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }catch (ResponseStatusException exception){
            setErrorResponse(exception.getStatus(), response, exception.getMessage());
        }
    }

    public void setErrorResponse(HttpStatus httpStatus, HttpServletResponse response, String message) throws IOException {
        FilterError filterError = new FilterError();
        filterError.setErrorDate(new Date());
        filterError.setMessage(message);
        filterError.setStatusCode(String.valueOf(httpStatus.value()));

        String json = filterError.convertToJson();
        response.getWriter().write(json);
    }
}
