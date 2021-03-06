package de.perdian.apps.fimasu.model.impl.parsers;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.perdian.apps.fimasu.model.Transaction;
import de.perdian.apps.fimasu.model.TransactionParser;
import de.perdian.apps.fimasu.model.support.LineProcessorList;

public abstract class AbstractPdfTransactionParser<T extends Transaction> implements TransactionParser {

    private static final Logger log = LoggerFactory.getLogger(AbstractPdfTransactionParser.class);

    @Override
    public boolean canHandleFile(File documentFile) {
        return documentFile.getName().toLowerCase().endsWith(".pdf");
    }

    @Override
    public List<Transaction> parseTransactionsFromFile(File pdfFile) {
        try {
            log.debug("Analyzing PDF file at: {}", pdfFile.getAbsolutePath());
            try (PDDocument pdfDocument = PDDocument.load(pdfFile)) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String pdfText = pdfStripper.getText(pdfDocument);
                T transaction = this.createTransaction(pdfText, pdfFile);
                this.createLineProcessorList(transaction, pdfFile).process(pdfText);
                return transaction == null ? Collections.emptyList() : List.of(transaction);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot analyze file: " + pdfFile.getAbsolutePath(), e);
        }
    }

    protected abstract LineProcessorList createLineProcessorList(T transaction, File pdfFile);
    protected abstract T createTransaction(String pdfText, File pdfFile) throws Exception;

}
