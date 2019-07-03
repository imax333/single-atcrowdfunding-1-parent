package com.atguigu.crowd.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.constant.AttrNameConstant;
import com.atguigu.crowd.entity.ResultEntity;
import com.google.gson.Gson;

//基于注解的     异常映射
@ControllerAdvice
public class CrowdExceptionResolverHandler {
	
	@ExceptionHandler(value=Exception.class)
	public ModelAndView loginException(Exception exception ,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		if(request.getHeader("accept").contains("application/json") || 
				(request.getHeader("X-Requested-With") != null && 
				request.getHeader("X-Requested-With").contains("XMLHttpRequest"))){

			ResultEntity<String> resultEntity = new ResultEntity<String>();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(exception.getMessage());
			resultEntity.setData(ResultEntity.NO_DATA);
			Gson gson = new Gson();
			String json = gson.toJson(resultEntity);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(json);
			return null;
			
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(AttrNameConstant.EXCEPTION, exception);
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
