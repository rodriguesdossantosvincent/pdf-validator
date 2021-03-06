iAutomation testing
==================

Framework
---------

Validation Service Automation tests are using Rest-assured library. REST
Assured is a Java DSL(Domain-specific language) for simplifying testing
of REST based Services built on top of HTTP Builder. It supports `POST`,
`GET`, `PUT`, `DELETE`, `OPTIONS`, `PATCH` and `HEAD` requests and can be used to
validate and verify the response of these requests.

Software requirements
---------------------

- In order to run the tests there is needed an integrated development
  environment i.e. **IntelliJ IDEA** or **Eclipse**.
- **Apache Maven** latest version
- Using **Java 8**

Starting and configuring tests
------------------------------

### Import Project

Before You can start You have to import the test project into the IDEA.

1.  Open IntelliJ
2.  File --&gt; Import Project
3.  Chooce pdfValitator --&gt; pdf-validator-test --&gt; pom.xml
4.  Next --&gt; Next --&gt; Next
5.  Select project SDK 1.8
6.  JDK home path = \${JAVA\_HOME}
7.  Click the buttons "Next", "Finish" and "This Window".

### Configuration

You have to configure The URL to Your web Service endpoint in order to
run the tests. The configuration file file is located
at `pdf-validator-test/src/test/main/config/tests.properties`

**test.properties**

```
Service\_url=http://localhost:8080/pdf-validator-webapp/wService/validationService
```

### Executing

#### Run tests in the IntelliJ IDEA:

- All test cases are located in java classes in
  the `pdf-validator-test/src/test/java/ee.sk.pdfvalidatortest/` package.
- In order to execute all tests, you can right click the imported Java
  module (for example, `pdf-validator-test`) and select **Run 'All Tests'**.
- In order to execute all tests in a Java file, you can right click that
  file, for example `BaselineProfileTests.java` and select **Run
  'BaselineProfileTest'**

In order to execute a single test, you can right click
the `@Test` annotation in the java code editor and select **Run
'testName'**

#### Run tests from the command line:

1.  Open command line tools (i.e cmd, iTerm)
2.  Navigate to `PROJECT_ROOT/pdf-validator-parent/pdf-valdidator-test/`

To run all tests

```bash
mvn test
```

To run single class:

```bash
mvn -Dtest=\[class\] test
```

To run single method:

```bash
mvn -Dtest=\[class\]\#\[method\] test
```

### Checking logs for validation result

Very little information is given out when validating signatures but short summery is given.

Below is lines that will be outputted when validation worked as expected and all signatures are valid.

```bash
11:54:50.628 [http-bio-8080-exec-1] INFO  ValidationServiceImpl.java:75 - WsValidateDocument: begin
11:54:50.636 [http-bio-8080-exec-1] INFO  PdfObjFactory.java:53 - Fallback to 'eu.europa.esig.dss.pdf.pdfbox.PdfBoxObjectFactory' as the PDF Object Factory Implementation
11:54:50.929 [http-bio-8080-exec-1] INFO  SignedDocumentValidator.java:390 - Document validation...
11:54:51.617 [http-bio-8080-exec-1] INFO  ValidationServiceImpl.java:110 - Validation completed. Total signature count: 1 and valid signature count: 1
11:54:51.617 [http-bio-8080-exec-1] INFO  ValidationServiceImpl.java:116 - WsValidateDocument: end
```

When file is not validated as PDF then `DSSException` is thrown like shown below.

```bash
11:59:53.727 [http-bio-8080-exec-3] INFO  ValidationServiceImpl.java:75 - WsValidateDocument: begin
11:59:53.729 [http-bio-8080-exec-3] ERROR ValidationServiceImpl.java:120 - Document format not recognized/handled
eu.europa.esig.dss.DSSException: Document format not recognized/handled
        at eu.europa.esig.dss.validation.SignedDocumentValidator.fromDocument(SignedDocumentValidator.java:236) ~[dss-document-4.5.0.jar:na]
        at eu.europa.esig.dss.ws.impl.ValidationServiceImpl.validateDocument(ValidationServiceImpl.java:82) ~[dss-webservices-4.5.0.jar:na]
...        
```

Not valid signature when You look at the log line `ValidationServiceImpl.java:110 - Validation completed.`

```bash
12:03:19.528 [http-bio-8080-exec-10] INFO  ValidationServiceImpl.java:75 - WsValidateDocument: begin
12:03:19.677 [http-bio-8080-exec-10] INFO  SignedDocumentValidator.java:390 - Document validation...
12:03:19.689 [http-bio-8080-exec-10] INFO  CRLCertificateVerifier.java:67 - No CRL found for: 64f7eb544f1f9504d378e58d60441d37b40654a882922ce33adbc75ddb81ca98
12:03:19.726 [http-bio-8080-exec-10] INFO  ValidationServiceImpl.java:110 - Validation completed. Total signature count: 1 and valid signature count: 0
12:03:19.726 [http-bio-8080-exec-10] INFO  ValidationServiceImpl.java:116 - WsValidateDocument: end
```

### Test structure

All testcases are in the
`pdf-validator-test/src/test/java/ee.sk.pdfvalidatortest/ package.`

There are two helper classes `XmlUtil.java` and
`PdfValidatorSoapTests.java`  that are used in various test cases.

- `XmlUtil` class is for xpath rules and xml validation. 
- `PdfValidatorSoapTests` is for reading and parsing file. 

At the time of this writing, the tests are categorized into the
following five groups (five java classes):

- **BaselineProfileTests** - contains tests with different baseline
  profiles i.e `B`, `T`, `LT`, `LTA`
- **DocumentFormatTests** - contains tests with files in different
  formats, i.e `asice`, `ddoc`, `zip`
- **SignatureCryptographicAlgorithmTests** - contains tests with
  different cryptographic algorithms, i.e `SHA1`, `SHA256`, `RSA1024` etc.
- **SignatureRevocationValueTests** - contains tests with different
  OCSP response edge cases
- **SigningCertificateTests** - contains tests with different signing
  certificate manipulations

`PdfValidatorSoapTests` class reads test files
form `pdf-validator-test/src/test/java/resources/`. If You want to add
new tests files, You can do it by just drag and drop through IntelliJ
and creating a new testcase for that file.

 **Example**

```java
@Test
public void validSignaturesRemainValidAfterSigningCertificateExpires() {
	String httpBody = post(validationRequestFor(readFile("hellopades-lt-sha256-rsa1024-not-expired.pdf")))
		.andReturn().body().asString();

	assertEquals(1, validSignatures(simpleReport(httpBody)));
}
```

The above test is verifying the result of validating a pdf file which
has been signed with a certificate that has expired after signing.

Description of Test Cases and Test Data
---------------------------------------


1. The PDF-file is signed with Certificate that does does not have the
   "nonRepudation" bit set (PAdES Baseline LT)
	- **NFR-ID:**
		- DSS-CRT-NONREPU
	- **FILES:**
		- hellopades-pades-lt-sha256-auth.pdf
	- **EXPECTE RESULT:**
		- Signing Certificate without NonRepudiation key usage attribute should **fail**
1. The PDF-file has got signatures of two different profiles - PAdES Baseline-B and PAdES-LTA
	- ""FILE:""
		- hellopades-sigb-signed.pdf
	- **EXPECTE RESULT:**
		- Signature profile Baseline - LTA should pass and signature baseline-b should **fail**
1. The PDF has PAdES-LT profile signature, but no OCSP confirmation
	- **NFR-ID:**
		- DSS-SIG-NO-OCSP
	- **FILES:**
		- hellopades-lta-no-ocsp.pdf
	- **EXPECTE RESULT:**
		- Document with no ocsp or crl in signature should **fail**
1. The PDF-file has PAdES-LT profile signature and an OCSP confirmation that is more than 15 minutes later than the signature’s Time Stamp.
	- **NFR-ID:**
		- DSS-SIG-OCSP-TS-WARN
	- **FILES:**
		- hellopades-lt-sha256-ocsp-15min.pdf
	- **EXPECTE RESULT:**
		- Document with ocsp over 15 min delay should **pass** but **warn**
1. The PDF-file has PAdES-LT profile signature and an OCSP confirmation more than 24 hours later than the signature’s Time Stamp.
	- **NFR-ID:**
		- DSS-SIG-OCSP-TS-ERROR
	- **FILES:**
		- hellopades-lt-sha256-ocsp-25h.pdf
	- **EXPECTE RESULT:**
		- Document with over 24h delay should **fail**
1. The PDF-file has been signed with PAdES Baseline LT profile signature, signer’s certificate has been revoked.
	- **FILES:**
		- hellopades-lt-sha256-revoked.pdf
	- **EXPECTE RESULT:**
		- Document signed with revoked certificate should **fail**
1. The PDF-file has been signed with PAdES Baseline LTA profile signature, the signature contains CRL.
	- **FILES:**
		- hellopades-lta-no-ocsp.pdf
	- **EXPECTE RESULT:**
		- Document with no ocsp or crl in signature should **fail**
1. The PDF-file has been signed with PadES-LT profile signature, file has corrupt Time Stamp.
	- **FILES:**
		- hellopades-lt-sha256-rsa1024-invalid-ts.pdf - (test)
	- **EXPECTE RESULT:**
		- Document with invalid timestamp should **fail**.
1. The PDF-file has been signed with certificate that will expire in 7 days after signing (PAdES Baseline LT)
	- **FILES:**
		- hellopades-lt-sha256-rsa2048-7d.pdf
		- hellopades-lt-sha256-rsa1024-no-expired.pdf
	- **EXPECTE RESULT:**
		- Document signed with certificate that expired after signing should **pass**.
1. SHA1, SHA256, RSA1024, RSA2048, ECDSA224, ECDSA 256 algorithms (PAdES Baseline LT)
	- **NFR-ID:**
		- DSS-CRY-ALGOEXP
	- **FILES:**
		- hellopades-lt-sha512.pdf
		- hellopades-lt-sha1.pdf
		- hellopades-lt-sha256-ec256.pdf -(test)
		- hellopades-lt-sha256-ec224.pdf - (test)
		- hellopades-lt-sha256-rsa1024.pdf - (test)
		- hellopades-lt-sha256-rsa2048.pdf
	- **EXPECTE RESULT:**
		- Document signed with SHA1, SHA256, RSA1024, RSA2048, ECDSA224, ECDSA 256 algorithm should **pass**
1. Encryption Algorithm RSA with 1023-bit keys (PAdES Baseline LT) 
	- **FILES:**
		- hellopades-lt-sha256-rsa1023.pdf
	- **EXPECTE RESULT:**
		- Document signed with RSA1023 algorithm should **pass**
1. Encryption Algorithm RSA with 2047-bit keys (PAdES Baseline LT)
	- **FILES:**
		- hellopades-lt-sha256-rsa2047.pdf
	- **EXPECTE RESULT:**
		- Document signed with RSA2047 algorithm should **pass**
1. hellopadess been signed with an expired certificate, where signing time is within the original validity period of the
   certificate, but OCSP confirmation and Time Stamp are current date (PAdES Baseline LT). 
	- **FILES:**
		- hellopades-lt-sha256-rsa1024-expired1.pdf
		- hellopades-lt-sha1-rsa1024-expired2.pdf
		- hellopades-lt-sha256-rsa1024-expired2.pdf
		- hellopades-lt-sha256-rsa2048-expired.pdf
	- **EXPECTE RESULT:**
		- Document signed with expired certificate should **fail**
1. PDF-file’s signature has OCSP confirmation before Time Stamp
	- **FILES:**
		- hellopades-lt-sha256-rsa2048-ocsp-before-ts.pdf
	- **EXPECTE RESULT:**
		- Documetn signed with ocsp time value before best signature time should **fail**
1. PDF file with a serial signature
	- **NFR-ID:**
		- DSS-SIG-MULTISIG
	- **FILES:**
		- hellopades-lt1-lt2-Serial.pdf
	- **EXPECTE RESULT:**
		- Document signed with multiple signers with serial signatures should **pass**
1. PDF file with a parallel signature
	- **NFR-ID:**
		- DSS-SIG-MULTISIG
	- **FILES:**
		- hellopades-lt1-lt2-parallel.pdf (WAITING)
	- **EXPECTE RESULT:**
		- Document signed with multiple signers with parallel signatures should **fail**
1. PDF file without a timestamp
	- **NFR-ID:**
		- DSS-SIG-TIMESTAMP
	- **FILES:**
		- hellopades-lt-sha256-rsa2048-no-ts.pdf
	- **EXPECTE RESULT:**
		- Document signed with no timestamp should **fail**
1. PDF file where signature timestamp is after certificate expiry date.
	- **NFR-ID:**
		- DSS-SIG-VALID_CERTS
	- **FILES:**
		- hellopades-lt-sha256-rsa2048-expired.pdf
	- **EXPECTE RESULT:**
		- Document signed with expired certificate should **fail**
1. PDF file signed with 223 bit ECDSA key (PAdES Baseline LT)
	- **NFR-ID:**
		- DSS-CRY-ECDSAKEY
	- **FILES:**
		- No test file.
	- **EXPECTE RESULT:**
		- Document signed with ECDSA 223 algorithm should **pass**.
1. Larger signed PDF files (PAdES Baseline LT).
	- **FILES:**
		- hellopades-lta-no-ocsp.pdf
		- scout_x4-manual-signed_lt_9mb.pdf
		- scout_x4-manual-signed_lta_9mb.pdf
		- digidocservice-signed_lt_1-2mb.pdf
		- digidocservice-signed_lta_1-2mb.pdf
		- egovernment-benchmark_lt_3-8mb.pdf
		- egovernment-benchmark_lta_3-8mb.pdf
	- **EXPECTE RESULT:**
		- Bigger documents with valid signature should **pass**
1. Message-digest attribute value does not match calculated value
	- **FILES:**
		- hellopades-lt1-lt2-parallel3.pdf
	- **EXPECTE RESULT:**
		- Document with no qualified and without SSCD should **fail**
