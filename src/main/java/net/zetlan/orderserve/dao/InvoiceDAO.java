package net.zetlan.orderserve.dao;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import net.zetlan.orderserve.model.Invoice;
import org.hibernate.SessionFactory;

import java.util.List;

public class InvoiceDAO extends AbstractDAO<Invoice> {

    @Inject
    public InvoiceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

//    public List<Invoice> findInvoices() {
//
//    }
}
