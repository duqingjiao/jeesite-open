/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.StringUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
import org.beetl.ext.fn.EmptyExpressionFunction;	
	
public class IsBlank	
implements Function {	
    EmptyExpressionFunction fn;	
	
    @Override	
    public Object call(Object[] paras, Context ctx) {	
        Object a = paras[0];	
        if (a == null) {	
            return true;	
        }	
        if (a instanceof String) {	
            return StringUtils.isBlank((String)a);	
        }	
        return this.fn.call(paras, ctx);	
    }	
	
    public IsBlank() {	
        IsBlank isBlank = this;	
        isBlank.fn = new EmptyExpressionFunction();	
    }	
}	
	
