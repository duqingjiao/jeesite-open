/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.atomikos.icatch.jta.UserTransactionImp	
 *  com.atomikos.icatch.jta.UserTransactionManager	
 *  javax.transaction.TransactionManager	
 *  javax.transaction.UserTransaction	
 *  org.springframework.boot.autoconfigure.AutoConfigureAfter	
 *  org.springframework.boot.autoconfigure.AutoConfigureBefore	
 *  org.springframework.boot.autoconfigure.condition.ConditionalOnProperty	
 *  org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration	
 *  org.springframework.context.annotation.Bean	
 *  org.springframework.context.annotation.Configuration	
 *  org.springframework.context.annotation.DependsOn	
 *  org.springframework.jdbc.datasource.DataSourceTransactionManager	
 *  org.springframework.transaction.PlatformTransactionManager	
 *  org.springframework.transaction.annotation.EnableTransactionManagement	
 *  org.springframework.transaction.jta.JtaTransactionManager	
 */	
package com.jeesite.autoconfigure.core;	
	
import com.atomikos.icatch.jta.UserTransactionImp;	
import com.atomikos.icatch.jta.UserTransactionManager;	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.config.Global;	
import javax.sql.DataSource;	
import javax.transaction.TransactionManager;	
import javax.transaction.UserTransaction;	
import org.hyperic.sigar.Mem;	
import org.springframework.boot.autoconfigure.AutoConfigureAfter;	
import org.springframework.boot.autoconfigure.AutoConfigureBefore;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.jdbc.datasource.DataSourceTransactionManager;	
import org.springframework.transaction.PlatformTransactionManager;	
import org.springframework.transaction.annotation.EnableTransactionManagement;	
import org.springframework.transaction.jta.JtaTransactionManager;	
	
@Configuration	
@AutoConfigureAfter(value={DataSourceAutoConfiguration.class})	
@AutoConfigureBefore(value={DataSourceTransactionManagerAutoConfiguration.class})	
@EnableTransactionManagement	
public class TransactionAutoConfiguration {	
    @Bean(value={"transactionManager"})	
    @ConditionalOnProperty(name={"jdbc.jta.enabled"}, havingValue="false", matchIfMissing=true)	
    public PlatformTransactionManager transactionManager(DataSource dataSource) {	
        return new DataSourceTransactionManager(dataSource);	
    }	
	
    @Bean(initMethod="init", destroyMethod="close")	
    @ConditionalOnProperty(name={"jdbc.jta.enabled"}, havingValue="true", matchIfMissing=false)	
    public TransactionManager atomikosTransactionManager() throws Throwable {	
        UserTransactionManager userTransactionManager = new UserTransactionManager();	
        userTransactionManager.setForceShutdown(false);	
        return userTransactionManager;	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = (2 ^ 5) << 4 ^ (3 << 2 ^ 1);	
        int n4 = n2;	
        4 << 4 ^ (2 ^ 5) << 1;	
        int n5 = (2 ^ 5) << 4 ^ (3 ^ 5) << 1;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    @Bean(value={"transactionManager"})	
    @DependsOn(value={"userTransaction", "atomikosTransactionManager"})	
    @ConditionalOnProperty(name={"jdbc.jta.enabled"}, havingValue="true", matchIfMissing=false)	
    public PlatformTransactionManager jtaTransactionManager(UserTransaction userTransaction, TransactionManager atomikosTransactionManager) throws Throwable {	
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);	
    }	
	
    @Bean	
    @ConditionalOnProperty(name={"jdbc.jta.enabled"}, havingValue="true", matchIfMissing=false)	
    public UserTransaction userTransaction() throws Throwable {	
        UserTransactionImp userTransactionImp = new UserTransactionImp();	
        userTransactionImp.setTransactionTimeout(Global.getPropertyToInteger(" dbc.jta.transactionTimeout", String.valueOf(180)).intValue());	
        return userTransactionImp;	
    }	
}	
	
