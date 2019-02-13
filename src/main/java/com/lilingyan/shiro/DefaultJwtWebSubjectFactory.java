package com.lilingyan.shiro;

import com.lilingyan.util.JWTUtil;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.subject.WebSubjectContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: lilingyan
 * @Date 2018/12/16 15:11
 */
public class DefaultJwtWebSubjectFactory extends DefaultWebSubjectFactory {

    public DefaultJwtWebSubjectFactory() {
        super();
    }

    public Subject createSubject(SubjectContext context) {
        if (!(context instanceof WebSubjectContext)) {
            return super.createSubject(context);
        }
        DefaultJwtWebSubjectContext wsc = (DefaultJwtWebSubjectContext) context;
        SecurityManager securityManager = wsc.resolveSecurityManager();
        Session session = wsc.resolveSession();
        boolean sessionEnabled = wsc.isSessionCreationEnabled();
        PrincipalCollection principals = wsc.resolvePrincipals();
        boolean authenticated = wsc.resolveAuthenticated();
        if(authenticated){
            createJwtTokenToResponse(wsc);
        }else{
            authenticated = checkJwtToken(wsc);
        }
        String host = wsc.resolveHost();
        ServletRequest request = wsc.resolveServletRequest();
        ServletResponse response = wsc.resolveServletResponse();

        return new JwtWebDelegatingSubject(principals, authenticated, host, session, sessionEnabled,
                request, response, securityManager);
    }

    protected boolean checkJwtToken(DefaultJwtWebSubjectContext context){
        String jwtToken = context.getJwtToken();
        if(jwtToken != null){
            boolean authc = JWTUtil.verify(jwtToken,JWTUtil.secret);
            if(authc){
                touchJwtToken(context);
            }
            return authc;
        }
        return false;
    }

    protected void createJwtTokenToResponse(DefaultJwtWebSubjectContext context){
        PrincipalCollection principals = context.resolvePrincipals();
        String jwtToken = JWTUtil.sign(String.valueOf(principals.getPrimaryPrincipal()), JWTUtil.secret);
        context.setJwtToken(jwtToken);
    }

    protected void touchJwtToken(DefaultJwtWebSubjectContext context){
        String jwtToken = context.getJwtToken();
        jwtToken = JWTUtil.sign(JWTUtil.getUsername(jwtToken), JWTUtil.secret);
        context.setJwtToken(jwtToken);
    }

}
