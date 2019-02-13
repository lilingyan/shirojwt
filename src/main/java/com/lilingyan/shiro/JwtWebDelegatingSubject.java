package com.lilingyan.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: lilingyan
 * @Date 2018/12/16 15:14
 */
public class JwtWebDelegatingSubject extends WebDelegatingSubject {

    //since 1.2
    public JwtWebDelegatingSubject(PrincipalCollection principals, boolean authenticated,
                                   String host, Session session, boolean sessionEnabled,
                                   ServletRequest request, ServletResponse response,
                                   SecurityManager securityManager) {
        super(principals, authenticated, host, session, sessionEnabled, request , response ,securityManager);
    }

}
