/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.annotation;	
	
import java.lang.annotation.Annotation;	
import java.lang.annotation.Documented;	
import java.lang.annotation.ElementType;	
import java.lang.annotation.Retention;	
import java.lang.annotation.RetentionPolicy;	
import java.lang.annotation.Target;	
import org.springframework.stereotype.Component;	
	
@Retention(value=RetentionPolicy.RUNTIME)	
@Target(value={ElementType.TYPE})	
@Documented	
@Component	
public @interface MyBatisDao {	
    public Class<?> entity() default Class.class;	
	
    public String dataSourceName() default "";	
	
    public String value() default "";	
}	
	
