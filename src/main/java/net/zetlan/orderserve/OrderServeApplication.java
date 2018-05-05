package net.zetlan.orderserve;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import net.zetlan.orderserve.api.InvoiceAPI;

public class OrderServeApplication extends Application<OrderServeConfig> {


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
                return config.swaggerBundleConfiguration;
            }
        });
        super.initialize(bootstrap);
    }

    @Override
    public void run(OrderServeConfig config, Environment environment) throws Exception {
        final InvoiceAPI invoiceAPI = new InvoiceAPI();
        environment.jersey().register(invoiceAPI);

    }
}
