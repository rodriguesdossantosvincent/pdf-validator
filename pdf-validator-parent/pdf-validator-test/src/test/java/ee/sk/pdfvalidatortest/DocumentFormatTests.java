package ee.sk.pdfvalidatortest;

import ee.sk.pdfvalidatortest.configuration.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.w3c.dom.Document;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
public class DocumentFormatTests extends PdfValidatorSoapTests {
	
	@Test
    public void PAdESDocumentShouldPass() {
        String request = validationRequestFor(readFile("hellopades-pades-lt-sha256-sign.pdf"));
        String httpBody = post(request).
                andReturn().body().asString();
        //System.out.println(httpBody.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&#xD;", "").replaceAll("&quot;", "\""));
        
        assertEquals("PAdES_BASELINE_LT", signatureFormat(simpleReport(httpBody)));
        assertEquals(1, validSignatures(simpleReport(httpBody)));
    }

	@Test
    public void ASiCDocumentShouldFail() {
        String httpBody = post(validationRequestFor(readFile("TS-11_23634_TS_2_timestamps.asice"))).
                andReturn().body().asString();

        assertEquals("Document format not recognized/handled", soapFaultstring(httpBody));
    }

	@Test
    public void XAdESDocumentShouldFail() {
        String httpBody = post(validationRequestFor(readFile("ALLK_KIIP_02_DIGIDOC-XML_1.3.ddoc"))).
                andReturn().body().asString();

        assertEquals("Document format not recognized/handled", soapFaultstring(httpBody));
    }

	@Test
    public void CAdESDocumentShouldFail() {
        String httpBody = post(validationRequestFor(readFile("hellocades.p7s"))).
                andReturn().body().asString();

        assertEquals("Document format not recognized/handled", soapFaultstring(httpBody));
    }
    @Test
    public void ZipDocumentShouldFail() {
        String httpBody = post(validationRequestFor(readFile("42.zip"))).
                andReturn().body().asString();

        assertEquals("Document format not recognized/handled", soapFaultstring(httpBody));
    }

    @Test
    public void DocDocumentShouldFail() {
        String httpBody = post(validationRequestFor(readFile("hellopades-pades-lt-sha256-sign.doc"))).
                andReturn().body().asString();
        assertEquals("Document format not recognized/handled", soapFaultstring(httpBody));
    }


	protected byte[] readFile(String fileName) {
    	return readFileFromTestResources("document_format_test_files/", fileName);
    }

	private String signatureFormat(Document simpleReport) {
        String stringResult = XmlUtil.findElementByXPath(
                simpleReport,
                "//d:SimpleReport/d:Signature",
                Collections.singletonMap("d", "http://dss.esig.europa.eu/validation/diagnostic")).getAttribute("SignatureFormat");

        return stringResult;
    }

	private String soapFaultstring(String httpBody) {
		Document document = XmlUtil.parseXml(httpBody);
        String stringResult = XmlUtil.findElementByXPath(
                document,
                "//soap:Fault/faultstring",
                Collections.singletonMap("soap", "http://schemas.xmlsoap.org/soap/envelope/")).getTextContent();

        return stringResult;
    }

}