package com.auguigu.crowd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration // 配置类
@EnableWebSecurity 
@EnableGlobalMethodSecurity(prePostEnabled=true)// @EnableGlobalMethodSecurity(prePostEnabled=true)注解表示启用全局方法权限管理功能。
public class CrowdingFundingSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService  userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//		builder.inMemoryAuthentication()
//		.withUser("cc")
//		.password("123")
//		.roles("ADMIN");
		BCryptPasswordEncoder passwordEncoder = getPasswordEncoder();
		
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
	}
	
	

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security
		.authorizeRequests()
		.antMatchers("/index.html")
		.permitAll()
		.antMatchers("/bootstrap/**")
		.permitAll()
		.antMatchers("/css/**")
		.permitAll()
		.antMatchers("/fonts/**")
		.permitAll()
		.antMatchers("/img/**")
		.permitAll()
		.antMatchers("/jquery/**")
		.permitAll()
		.antMatchers("/layer/**")
		.permitAll()
		.antMatchers("/script/**")
		.permitAll()
		.antMatchers("/ztree/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/admin/to/login/page.html")
		.permitAll()
		.loginProcessingUrl("/admin/security/login.html")
		.permitAll()
		.usernameParameter("loginacct")
		.passwordParameter("userpswd")
		.defaultSuccessUrl("/admin/to/main/page.html")
		.and()
		.logout()
		.logoutUrl("/admin/security/logout.html")
		.logoutSuccessUrl("/index.html")
		.and()
		.csrf()
		.disable()
		;
		
	}
}
