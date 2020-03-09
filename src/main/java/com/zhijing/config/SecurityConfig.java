package com.zhijing.config;

import com.zhijing.pojo.User;
import com.zhijing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;
    //认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("manager");
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");
        http.rememberMe();
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/");
    }

    //授权
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User user = userService.selectUserById(new Long(1));
        String username = user.getUsername();
        String password = user.getPassword();
        String manager = user.getManager();
        System.out.println(user);
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .roles(manager);
    }
}
