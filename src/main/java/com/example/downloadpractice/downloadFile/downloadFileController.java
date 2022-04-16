package com.example.downloadpractice.downloadFile;

import com.example.downloadpractice.common.utils.PdfUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/download")
public class downloadFileController {

    @GetMapping("/pdf")
    public void  downloadPdf (HttpServletResponse response) throws IOException, DocumentException {
        String filename = "测试pdf";
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".pdf");
        BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
        Document document = PdfUtil.createDocument();
        PdfWriter.getInstance(document, os);
        document.open();
        Font bigFont = PdfUtil.createFont(14, Font.NORMAL, BaseColor.BLACK);
        Paragraph title = PdfUtil.createParagraph("测试pdf", bigFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.close();
        os.close();
    }
}
