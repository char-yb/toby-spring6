package tobyspring.hellospring;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = "tobyspring.hellospring")
public class DataConfig {

    // data source
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    // // entity manager factory
    // @Bean
    // public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    //     LocalContainerEntityManagerFactoryBean emf = new
    // LocalContainerEntityManagerFactoryBean();
    //     emf.setDataSource(dataSource());
    //     emf.setPackagesToScan("tobyspring.hellospring");
    //     emf.setJpaVendorAdapter(
    //             new HibernateJpaVendorAdapter() {
    //                 {
    //                     setDatabase(Database.H2);
    //                     setGenerateDdl(true);
    //                     setShowSql(true);
    //                 }
    //             });
    //
    //     return emf;
    // }

    // @Bean
    // public BeanPostProcessor persistenceAnnotationBeanPostProcessor() {
    //     return new PersistenceAnnotationBeanPostProcessor();
    // }

}
