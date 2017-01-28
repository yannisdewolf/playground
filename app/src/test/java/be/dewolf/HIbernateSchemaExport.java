package be.dewolf;

import be.dewolf.model.Person;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.boot.spi.MetadataBuilderImplementor;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by yannis on 21/01/17.
 */
public class HIbernateSchemaExport {

    public static void main(String[] args) {
        MetadataSources metadataSources = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting(Environment.DIALECT, "org.hibernate.dialect.H2Dialect")
                        .build()
                )
                .addAnnotatedClass(Person.class);


        ClassPathScanningCandidateComponentProvider p = new ClassPathScanningCandidateComponentProvider(false);
        p.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        Set<BeanDefinition> candidateComponents = p.findCandidateComponents("be.dewolf");
        candidateComponents.forEach(bd -> metadataSources.addAnnotatedClassName(bd.getBeanClassName()));


        Metadata metadata = metadataSources.getMetadataBuilder().build();

        SchemaExport schemaExport = new SchemaExport((MetadataImplementor) metadata)
                .setOutputFile("my-statements.ddl");

        schemaExport.setDelimiter(";");
        schemaExport.setFormat(true);
        schemaExport.execute(false, false, false, true);


    }

}
