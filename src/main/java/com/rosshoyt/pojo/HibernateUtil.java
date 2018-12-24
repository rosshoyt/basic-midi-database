package com.rosshoyt.pojo;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.rosshoyt.pojo.PNote;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Properties;
import org.hibernate.HibernateException;


import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
   public HibernateUtil(){

   }

   public SessionFactory createSessionFactory(Properties properties) {
      Configuration configuration = new Configuration();
      configuration.setProperties(properties);
      configuration.addAnnotatedClass(PNote.class);

      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
      return configuration.buildSessionFactory(builder.build());
   }
}