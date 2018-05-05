package net.zetlan.orderserve.api;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.zetlan.orderserve.dao.InvoiceDAO;
import net.zetlan.orderserve.model.Invoice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Api("invoice")
public class InvoiceAPI {
    private InvoiceDAO invoiceDAO;

    @Inject
    public InvoiceAPI(InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
    }

    @GET
    @Timed
    @ApiOperation(
            value = "Lists all invoices",
            response = Invoice.class,
            responseContainer = "List"
    )
    @UnitOfWork
    public List<Invoice> getInvoices(
            @ApiParam("Invoice or PO Number") @QueryParam("key") String key,
            @ApiParam("Max rows to return - default unlimited") @QueryParam("limit") Integer limit,
            @ApiParam("Number of rows to skip before starting results - default 0") @QueryParam("offset") Integer offset
    ) {
        return invoiceDAO.findInvoices(key, limit, offset);
    }

    @POST
    @ApiOperation(
            value = "Creates an invoice",
            response = Invoice.class
    )
    @UnitOfWork
    public Invoice createInvoice(@ApiParam("New invoice") Invoice invoice) {
        // Both of these values are set at creation time in the DB/JPA layer, so ignore the ones we're given:
        invoice.setId(0);
        invoice.setCreatedAt(null);
        return invoiceDAO.saveInvoice(invoice);
    }
}
