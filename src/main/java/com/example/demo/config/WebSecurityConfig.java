package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Webセキュリティコンフィグ。
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(WebSecurity web) {
        // 認証状態によらず許可するパス
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/bootstrap/css/**", "/bootstrap/js/**", "/jquery/**", "/images/**", "/fonts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // SessionFixation対策
        http.sessionManagement().sessionFixation().newSession();

        http.authorizeRequests()
                // 認証状態によらず許可するURL
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/account/register/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login") // ログインページのパス
                .loginProcessingUrl("/login") // 認証処理を起動させるパス
                .failureUrl("/login/?error") // ログイン処理失敗時の遷移先
                .successForwardUrl("/top/loginSuccess") // ログイン成功時の繊維先
                .usernameParameter("login_id")// ユーザid
                .passwordParameter("login_password").permitAll(); // パスワード

        http.logout()
                .logoutUrl("/logout") // ログアウト処理を起動させるパス
                .logoutSuccessUrl("/login") // ログアウト完了時のパス
                .deleteCookies("JSESSIONID", "SESSION")
                .invalidateHttpSession(true).permitAll();

    }
}
