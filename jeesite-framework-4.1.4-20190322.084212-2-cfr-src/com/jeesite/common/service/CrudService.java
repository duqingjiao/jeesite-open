/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.service;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.dao.CrudDao;	
import com.jeesite.common.dao.QueryDao;	
import com.jeesite.common.entity.BaseEntity;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.entity.Extend;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.e.j;	
import com.jeesite.common.service.QueryService;	
import com.jeesite.common.service.api.CrudServiceApi;	
import java.lang.annotation.Annotation;	
import org.springframework.transaction.annotation.Transactional;	
	
@Transactional(readOnly=true)	
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<?>>	
extends QueryService<D, T>	
implements CrudServiceApi<T> {	
    private static long num = 0L;	
	
    protected void preSave(T entity) {	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void delete(T entity) {	
        if (StringUtils.isBlank(((BaseEntity)entity).getId())) {	
            return;	
        }	
        Global.assertDemoMode();	
        ((CrudDao)this.dao).delete(entity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void updateStatus(T entity) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(((BaseEntity)entity).getId())) {	
            return;	
        }	
        ((CrudDao)this.dao).updateStatus(entity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void insert(T entity) {	
        Table a;	
        Global.assertDemoMode();	
        if (("0".equals(j.ALLATORIxDEMO().get("type")) || "9".equals(j.ALLATORIxDEMO().get("type"))) && (a = entity.getClass().getAnnotation(Table.class)) != null && StringUtils.endsWithIgnoreCase(a.name(), "sys_user")) {	
            if (num == 0L) {	
                CrudService crudService = this;	
                num = crudService.findCount(crudService.newEntity());	
            }	
            if (++num > 100L) {	
                return;	
            }	
        }	
        ((CrudDao)this.dao).insert(entity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void update(T entity) {	
        Global.assertDemoMode();	
        if (StringUtils.isBlank(((BaseEntity)entity).getId())) {	
            return;	
        }	
        ((CrudDao)this.dao).update(entity);	
    }	
	
    @Transactional(readOnly=false)	
    @Override	
    public void save(T entity) {	
        T t = entity;	
        this.preSave(t);	
        if (((BaseEntity)t).getIsNewRecord()) {	
            this.insert(entity);	
            return;	
        }	
        this.update(entity);	
    }	
}	
	
