package com.itjenny.common.util;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class Mysql5BitBooleanDialect extends MySQL5InnoDBDialect {     
    public Mysql5BitBooleanDialect() {
        super();
        registerColumnType( java.sql.Types.BOOLEAN, "bit" );
    }       
}
