package com.atguigu.crowd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.constant.AttrNameConstant;
import com.atguigu.constant.MessageConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.ResultEntity;
import com.google.gson.Gson;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute(AttrNameConstant.LOGIN_ADMIN);
		if (admin == null) {
			if (request.getHeader("accept").contains("application/json")
					|| (request.getHeader("X-Requested-With") != null
							&& request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {

				ResultEntity<String> resultEntity = new ResultEntity<String>();
				resultEntity.setResult(ResultEntity.FAILED);
				resultEntity.setMessage(ResultEntity.NO_MSG);
				resultEntity.setData(ResultEntity.NO_DATA);
				Gson gson = new Gson();
				String json = gson.toJson(resultEntity);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(json);
				return false;
			}
			request.setAttribute(AttrNameConstant.MESSAGE, MessageConstant.ACCESS_FORBIDDEN_MESSAGE);
			request.getRequestDispatcher("/WEB-INF/admin_login.jsp").forward(request, response);
			return false;
		}
		return true;
	}
}
