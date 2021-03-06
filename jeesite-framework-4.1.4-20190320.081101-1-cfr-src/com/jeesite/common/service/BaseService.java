/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.service.api.BaseServiceApi;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
	
public abstract class BaseService	
implements BaseServiceApi {	
    protected Logger logger;	
	
    public BaseService() {	
        BaseService baseService = this;	
        baseService.logger = LoggerFactory.getLogger(baseService.getClass());	
    }	
	
    @Override	
    public /* varargs */ String text(String code, String ... params) {	
        return Global.getText(code, params);	
    }	
}	
	
