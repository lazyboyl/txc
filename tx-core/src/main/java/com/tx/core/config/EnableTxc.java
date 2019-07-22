package com.tx.core.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({TxcScannerRegister.class})
public @interface EnableTxc {

    String [] basePackages() default {"com.tx.core"};

}
