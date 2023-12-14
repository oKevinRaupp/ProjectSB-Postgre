package com.kevinraupp.studyws.services.exceptions;

public class ResourceWithContentFoundException extends RuntimeException{
    public ResourceWithContentFoundException(Object id){
        super("Resource with content, can´t delete. Id: " + id);
    }
}
