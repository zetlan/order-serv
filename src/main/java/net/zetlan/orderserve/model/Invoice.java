package net.zetlan.orderserve.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(
        name = "invoices",
        indexes = {
                @Index(columnList = "invoice_number", name = "idx_invoice_number"),
                @Index(columnList = "po_number", name = "idx_po_number")
        }
)
@JsonSnakeCase
@NamedQueries({

        // To be used when searching by a key (invoice number or PO number):
        @NamedQuery(
                name = "net.zetlan.orderserve.model.Invoice.findInvoicesByKey",
                query = "select i from Invoice i "
                    + "where lower(i.invoiceNumber) like lower(:key) or lower(i.poNumber) like lower(:key) "
                    + "order by i.createdAt asc"
                ),

        // To be used if you just want to get all invoices:
        @NamedQuery(
                name = "net.zetlan.orderserve.model.Invoice.findAll",
                query = "select i from Invoice i order by i.createdAt asc"
        )
        }
)
public class Invoice implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @Column(name = "invoice_number", length = 64)
    @NotNull
    private String invoiceNumber;

    @Column(name = "po_number", length = 64)
    @NotNull
    private String poNumber;

    @Column(name = "due_date")
    @NotNull
    private Date dueDate;

    @Column(name = "amount_cents")
    @NotNull
    private long amountCents;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;


    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @JsonProperty
    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty
    public long getAmountCents() {
        return amountCents;
    }

    public void setAmountCents(long amountCents) {
        this.amountCents = amountCents;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'hh:mm:ssZ")
    @JsonProperty
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
