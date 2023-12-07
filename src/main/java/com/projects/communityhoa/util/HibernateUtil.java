package com.projects.communityhoa.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;

import com.projects.communityhoa.model.*;

public class HibernateUtil {
	
    public static SessionFactory buildSessionFactory(){
        Map<String, Object> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/community_hoa");
        settings.put("hibernate.connection.username", "csye_user");
        settings.put("hibernate.connection.password","csye6220");

        // settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        settings.put("hibernate.dialect.storage_engine", "innodb");
        settings.put("hibernate.show-sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addPackage("com.projects.communityhoa.model");
        metadataSources.addAnnotatedClass(Member.class);
        metadataSources.addAnnotatedClass(Invoice.class);
        metadataSources.addAnnotatedClass(SubscriptionPlan.class);

        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }

}





