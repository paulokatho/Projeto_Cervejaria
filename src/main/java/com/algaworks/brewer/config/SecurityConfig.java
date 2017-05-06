package com.algaworks.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("CADASTRO_CLIENTE");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")//tudo o que estiver depois de layout pode liberar(ex: css, js), pois se não na tela não permite exibir esses estilos	
			.antMatchers("/images/**");
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//*** A ORDEM EM QUE OS ITENS SÃO IMPLEMENTADOS FAZ TODA A DIFERENÇA
		http
			.authorizeRequests()//autoriza as requisições
				.anyRequest().authenticated()//tudo tem que estar autenticado, daqui para baixo não é nada liberado e tudo que não for autenticado retorna para a tela de login
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
