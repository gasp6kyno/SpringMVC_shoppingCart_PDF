package org.o7planning.springmvcshoppingcart.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.CartLineInfo;
import org.o7planning.springmvcshoppingcart.model.CustomerInfo;
import org.o7planning.springmvcshoppingcart.util.Utils;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;

public class LowagiePdfView extends AbstractPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
//        List<CartLineInfo> cartinfo = (List<CartLineInfo>) model.get("cartinfo");

//        PdfFormField form = new PdfFormField(writer, llx, lly, urx, ury, action);
        CartInfo cartInfo = Utils.getCartInSession(request); 
        
        PdfPTable table = new PdfPTable(4);

        table.addCell("Code");
        table.addCell("Name");
        table.addCell("Units");
        table.addCell("Price");

        for (CartLineInfo cartlines : cartInfo.getCartLines()){
        	table.addCell(String.valueOf(cartlines.getProductInfo().getCode()));
        	table.addCell(cartlines.getProductInfo().getName());
//        	table.addCell(cartlines.getQuantity());
//            table.addCell(cartlines.get);
        }

        document.add(table);
    }
}