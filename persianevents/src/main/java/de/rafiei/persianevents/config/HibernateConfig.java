package de.rafiei.persianevents.config;

import de.rafiei.persianevents.model.Events;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateConfig {

    private String poolName = "FBIM-DB-Pool";

    private String jdbcUrl = "jdbc:mysql://localhost:3306/persianevent?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String dbUserName = "eli";

    private String password = "eli";

    private Integer minIdleConnections = 5;

    private Integer maxPoolSize = 350;

    private Boolean cachePreparedStatements = true;

    private Integer preparedStatementsCacheSize = 50;

    private Integer preparedStatementsCacheSqlLimit = 1024;

    private Boolean useServerSidePreparedStatements = true;

    private String className = "com.mysql.jdbc.Driver";


    protected SessionFactory generateSessionFactory() {

        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.url", this.jdbcUrl);
        prop.setProperty("hibernate.connection.username", this.dbUserName);
        prop.setProperty("hibernate.connection.password", this.password);
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        prop.setProperty("hibernate.hbm2ddl.auto", "none");
        prop.setProperty("hibernate.show_sql", "true");
        prop.setProperty("hibernate.connection.pool_size", "10");


        SessionFactory sessionFactory = new Configuration()
                .addProperties(prop)
                .addPackage("de.rafiei.persianevent.model")
                .addAnnotatedClass(Events.class)
                .buildSessionFactory();


        return sessionFactory;
    }

}
