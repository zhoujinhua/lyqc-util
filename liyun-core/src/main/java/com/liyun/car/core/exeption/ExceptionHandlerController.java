package com.liyun.car.core.exeption;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
 @Controller  
public class ExceptionHandlerController {  
      
    @ExceptionHandler(value={IOException.class,SQLException.class})  
    public String exp(Exception ex,HttpServletRequest request) {  
        request.setAttribute("ex", ex);  
        return "exception";  
    }  
  
} 