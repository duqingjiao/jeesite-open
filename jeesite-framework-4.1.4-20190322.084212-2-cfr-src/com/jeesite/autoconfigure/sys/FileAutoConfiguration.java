/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.autoconfigure.sys;	
	
import com.jeesite.modules.file.service.FileEntityService;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import com.jeesite.modules.file.service.support.FileEntityServiceSupport;	
import com.jeesite.modules.file.service.support.FileUploadServiceExtendSupport;	
import com.jeesite.modules.file.service.support.FileUploadServiceSupport;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
@ConditionalOnProperty(name={"file.enabled"}, havingValue="true", matchIfMissing=true)	
public class FileAutoConfiguration {	
    @Bean	
    @ConditionalOnMissingBean	
    public FileUploadService fileUploadService() {	
        return new FileUploadServiceSupport();	
    }	
	
    @Bean	
    @ConditionalOnMissingBean	
    public FileUploadServiceExtend fileUploadServiceExtend() {	
        return new FileUploadServiceExtendSupport();	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 1 << 3;	
        (3 ^ 5) << 4 ^ (2 ^ 5) << 1;	
        int n4 = n2;	
        int n5 = 1 << 3 ^ 2;	
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
	
    @Bean	
    @ConditionalOnMissingBean	
    public FileEntityService fileEntityService() {	
        return new FileEntityServiceSupport();	
    }	
}	
	
