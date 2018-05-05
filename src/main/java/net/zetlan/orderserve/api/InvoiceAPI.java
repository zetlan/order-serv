package net.zetlan.orderserve.api;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.zetlan.orderserve.model.Invoice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Api("invoice")
public class InvoiceAPI {

    @GET
    @Timed
    @ApiOperation(
            value = "Lists all invoices",
            response = Invoice.class,
            responseContainer = "List"
    )
    public List<Invoice> getInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice = new Invoice();

        invoice.setId(1);
        invoice.setAmountCents(100);
        invoice.setInvoiceNumber("invoice1");
        invoice.setPoNumber("ponumber1");
        invoice.setDueDate(new Date());
        invoice.setCreatedAt(new Date());
        invoices.add(invoice);
        return invoices;
    }

    @POST
    @ApiOperation(
            value = "Creates an invoice",
            response = Invoice.class
    )
    public Invoice getInvoice(@ApiParam("New invoice") Invoice invoice) {
        throw new WebApplicationException("Not yet implemented", Response.Status.BAD_REQUEST);
    }
}
