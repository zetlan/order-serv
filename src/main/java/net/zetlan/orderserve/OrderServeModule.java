package net.zetlan.orderserve;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

public class OrderServeModule extends AbstractModule {

    private HibernateBundle<OrderServeConfig> hibernate;

    public OrderServeModule(HibernateBundle<OrderServeConfig> hibernate) {
        this.hibernate = hibernate;
    }

    @Provides
    SessionFactory getSessionFactory() {
        return hibernate.getSessionFactory();
    }

}
