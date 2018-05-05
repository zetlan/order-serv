package net.zetlan.orderserve.dao;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import net.zetlan.orderserve.model.Invoice;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class InvoiceDAO extends AbstractDAO<Invoice> {

    @Inject
    public InvoiceDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Invoice> findInvoices(String key, Integer limit, Integer offset) {
        Query query;

        if (StringUtils.isBlank(key)) {
            query = namedQuery("net.zetlan.orderserve.model.Invoice.findAll");
        } else {
            query = namedQuery("net.zetlan.orderserve.model.Invoice.findInvoicesByKey")
                    .setParameter("key", "%" + key + "%");
        }

        if (limit != null && limit > 0) {
            query.setMaxResults(limit);
        }
        if (offset != null && offset > 0) {
            query.setFirstResult(offset);
        }

        return query.getResultList();
    }

    public Invoice saveInvoice(Invoice invoice) {
        return persist(invoice);
    }
}
