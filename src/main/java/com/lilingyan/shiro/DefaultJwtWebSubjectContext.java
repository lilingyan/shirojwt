package com.lilingyan.shiro;

import com.lilingyan.common.Constants;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.subject.WebSubjectContext;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;

/**
 * @Author: lilingyan
 * @Date 2018/12/16 14:41
 */
public class DefaultJwtWebSubjectContext extends DefaultWebSubjectContext {

    public DefaultJwtWebSubjectContext() {
        super();
    }

    public DefaultJwtWebSubjectContext(WebSubjectContext context) {
        super(context);
    }

    public String getJwtToken() {
        ShiroHttpServletRequest request = (ShiroHttpServletRequest) this.getServletRequest();
        Object authorization = request.getHeader(Constants.AUTHORIZATION);
        if(authorization != null && authorization instanceof String){
            return String.valueOf(authorization);
        }
        return null;
    }

    public void setJwtToken(String jwtToken) {
        ResponseFacade response = (ResponseFacade) this.resolveServletResponse();
        response.setHeader(Constants.AUTHORIZATION,jwtToken);
    }

}