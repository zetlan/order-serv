package net.zetlan.orderserve;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import net.zetlan.orderserve.api.InvoiceAPI;
import net.zetlan.orderserve.model.Invoice;

public class OrderServeApplication extends Application<OrderServeConfig> {

    private final Injector injector;
    private final HibernateBundle<OrderServeConfig> hibernate = new HibernateBundle<OrderServeConfig>(Invoice.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(OrderServeConfig configuration) {
            return configuration.getDatabase();
        }
    };

    public OrderServeApplication() {
        injector = Guice.createInjector(new OrderServeModule(hibernate));
    }

    public static void main(String[] args) throws Exception {
        new OrderServeApplication().run(args);
    }

    @Override
    public String getName() {
        return "order-serve";
    }

    @Override
    public void initialize(Bootstrap<OrderServeConfig> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<OrderServeConfig>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(OrderServeConfig config) {
                return config.getSwaggerBundleConfiguration();
            }
        });
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(OrderServeConfig config, Environment environment) throws Exception {
        environment.jersey().register(injector.getInstance(InvoiceAPI.class));
    }
}
