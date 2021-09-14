package br.com.fairie.partypay.configuration.security

import br.com.fairie.partypay.configuration.security.filter.AuthenticationTokenFilter
import br.com.fairie.partypay.configuration.security.service.AuthenticationService
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
open class SecurityConfiguration(
    private val authenticationService: AuthenticationService
) : WebSecurityConfigurerAdapter() {

    final override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authenticationService).passwordEncoder(BCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {

        authenticationService.setAuthManager(authenticationManager())

        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/menu/**").permitAll()
            .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(AuthenticationTokenFilter(authenticationService), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**")
    }
}
