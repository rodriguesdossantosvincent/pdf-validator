Appendix 5 - Sample Validation Policy Configuration
===================================================

### The Validation Policy Configuration is stored in constraint.xml:

```xml
<ConstraintsParameters Name="QES AdESQC TL based" xmlns="http://dss.esig.europa.eu/validation/diagnostic">
	<Description>
	    Validate electronic signatures and indicates whether they are Advanced electronic
	    Signatures (AdES), AdES supported by a
		Qualified Certificate (AdES/QC) or a Qualified electronic
		Signature (QES). All certificates and their related chains supporting the signatures are validated against the EU Member State
		Trusted Lists (this includes signer's certificate and certificates used
		to validate certificate validity status Services - CRLs, OCSP, and time-stamps).
	</Description>
	<MainSignature>
		<AcceptablePolicies Level="FAIL">
			<Id>ANY_POLICY</Id>
			<Id>NO_POLICY</Id>
		</AcceptablePolicies>
		<!--
		<AcceptableSignatureFormats Level="FAIL">
			<Id>PAdES_BASELINE_LT</Id>
			<Id>PAdES_BASELINE_LTA</Id>
		</AcceptableSignatureFormats>
		-->
		<OcspDelayToBestSignatureTime Unit="MINUTES">
			<Warn>
				<MinimalDelay>15</MinimalDelay>
			</Warn>
			<Fail>
				<MinimalDelay>1440</MinimalDelay>
			</Fail>
		</OcspDelayToBestSignatureTime>
		<ReferenceDataExistence Level="FAIL">true</ReferenceDataExistence>
		<ReferenceDataIntact Level="FAIL">true</ReferenceDataIntact>
		<SignatureIntact Level="FAIL">true</SignatureIntact>
		<SigningCertificate>
			<Recognition Level="FAIL"/>
			<AttributePresent Level="FAIL"/>
			<DigestValuePresent Level="FAIL"/>
			<DigestValueMatch Level="FAIL">true</DigestValueMatch>
			<IssuerSerialMatch Level="WARN">true</IssuerSerialMatch>
			<Signed Level="FAIL" />
			<Signature Level="FAIL"/>
			<Expiration Level="IGNORE"/>
			<RevocationDataAvailable Level="FAIL"/>
			<RevocationDataIsTrusted Level="FAIL"/>
			<RevocationDataFreshness Level="WARN"/>
			<ProspectiveCertificateChain Level="FAIL"/>
			<KeyUsage Level="FAIL">
				<Identifier>nonRepudiation</Identifier>
			</KeyUsage>
			<Revoked Level="FAIL"/>
			<OnHold Level="FAIL"/>
			<TSLValidity Level="WARN"/>
			<TSLStatus Level="WARN"/>
			<TSLStatusAndValidity Level="FAIL"/>
			<Qualification Level="WARN"/>
			<SupportedBySSCD Level="WARN"/>
			<IssuedToLegalPerson Level="INFORM"/>
			<Cryptographic Level="FAIL">
				<AcceptableEncryptionAlgo>
					<Algo>RSA</Algo>
					<Algo>ECDSA</Algo>
				</AcceptableEncryptionAlgo>
				<MiniPublicKeySize>
					<Algo Size="1024">RSA</Algo>
					<Algo Size="224">ECDSA</Algo>
				</MiniPublicKeySize>
				<AcceptableDigestAlgo>
					<Algo>SHA1</Algo>
					<Algo>SHA224</Algo>
					<Algo>SHA256</Algo>
					<Algo>SHA384</Algo>
					<Algo>SHA512</Algo>
				</AcceptableDigestAlgo>
			</Cryptographic>
		</SigningCertificate>
		<CACertificate>
			<Signature Level="FAIL"/>
			<Expiration Level="FAIL"/>
			<RevocationDataAvailable Level="FAIL"/>
			<RevocationDataIsTrusted Level="FAIL"/>
			<RevocationDataFreshness Level="WARN"/>
			<Revoked Level="FAIL"/>
			<Cryptographic Level="FAIL">
				<AcceptableEncryptionAlgo>
					<Algo>RSA</Algo>
					<Algo>ECDSA</Algo>
				</AcceptableEncryptionAlgo>
				<MiniPublicKeySize>
					<Algo Size="1024">RSA</Algo>
					<Algo Size="224">ECDSA</Algo>
				</MiniPublicKeySize>
				<AcceptableDigestAlgo>
					<Algo>SHA1</Algo>
					<Algo>SHA224</Algo>
					<Algo>SHA256</Algo>
					<Algo>SHA384</Algo>
					<Algo>SHA512</Algo>
				</AcceptableDigestAlgo>
			</Cryptographic>
		</CACertificate>
		<Cryptographic Level="FAIL">
			<AcceptableEncryptionAlgo>
				<Algo>RSA</Algo>
				<Algo>ECDSA</Algo>
			</AcceptableEncryptionAlgo>
			<MiniPublicKeySize>
				<Algo Size="1024">RSA</Algo>
				<Algo Size="224">ECDSA</Algo>
			</MiniPublicKeySize>
			<AcceptableDigestAlgo>
				<Algo>SHA1</Algo>
				<Algo>SHA224</Algo>
				<Algo>SHA256</Algo>
				<Algo>SHA384</Algo>
				<Algo>SHA512</Algo>
			</AcceptableDigestAlgo>
		</Cryptographic>
		<MandatedSignedQProperties>
			<SigningTime Level="FAIL"/>
			<ContentTimeStamp>
				<MessageImprintDataFound Level="FAIL" />
				<MessageImprintDataIntact Level="FAIL" />
			</ContentTimeStamp>
			<!--<ContentType Level="FAIL">1.2.840.113549.1.7.1</ContentType>-->
		</MandatedSignedQProperties>
		<MandatedUnsignedQProperties>
			<CounterSignature>
				<ReferenceDataExistence Level="FAIL" />
				<ReferenceDataIntact Level="FAIL" />
				<SignatureIntact Level="FAIL" />
			</CounterSignature>
		</MandatedUnsignedQProperties>
	</MainSignature>
	<Timestamp>
		<TimestampDelay Unit="DAYS">0</TimestampDelay>
		<MessageImprintDataFound Level="FAIL"/>
		<MessageImprintDataIntact Level="FAIL"/>
		<RevocationTimeAgainstBestSignatureTime Level="FAIL"/>
		<BestSignatureTimeBeforeIssuanceDateOfSigningCertificate Level="FAIL"/>
		<SigningCertificateValidityAtBestSignatureTime Level="FAIL"/>
		<AlgorithmReliableAtBestSignatureTime Level="FAIL"/>
		<Coherence Level="WARN"/>
		<SigningCertificate>
			<Recognition Level="FAIL"/>
			<Signature Level="FAIL"/>
			<Expiration Level="FAIL"/>
			<RevocationDataAvailable Level="FAIL"/>
			<RevocationDataIsTrusted Level="FAIL"/>
			<RevocationDataFreshness Level="WARN"/>
			<ProspectiveCertificateChain Level="FAIL"/>
			<Revoked Level="FAIL"/>
			<OnHold Level="FAIL"/>
			<TSLStatus Level="FAIL"/>
			<Cryptographic Level="FAIL">
				<AcceptableEncryptionAlgo>
					<Algo>RSA</Algo>
					<Algo>ECDSA</Algo>
				</AcceptableEncryptionAlgo>
				<MiniPublicKeySize>
					<Algo Size="1024">RSA</Algo>
					<Algo Size="224">ECDSA</Algo>
				</MiniPublicKeySize>
				<AcceptableDigestAlgo>
					<Algo>SHA1</Algo>
					<Algo>SHA224</Algo>
					<Algo>SHA256</Algo>
					<Algo>SHA384</Algo>
					<Algo>SHA512</Algo>
				</AcceptableDigestAlgo>
			</Cryptographic>
		</SigningCertificate>
		<CACertificate>
			<Signature Level="FAIL"/>
			<Expiration Level="FAIL"/>
			<RevocationDataAvailable Level="FAIL"/>
			<RevocationDataIsTrusted Level="FAIL"/>
			<RevocationDataFreshness Level="WARN"/>
			<Revoked Level="FAIL"/>
			<Cryptographic Level="FAIL">
				<AcceptableEncryptionAlgo>
					<Algo>RSA</Algo>
					<Algo>ECDSA</Algo>
				</AcceptableEncryptionAlgo>
				<MiniPublicKeySize>
					<Algo Size="1024">RSA</Algo>
					<Algo Size="224">ECDSA</Algo>
				</MiniPublicKeySize>
				<AcceptableDigestAlgo>
					<Algo>SHA1</Algo>
					<Algo>SHA224</Algo>
					<Algo>SHA256</Algo>
					<Algo>SHA384</Algo>
					<Algo>SHA512</Algo>
				</AcceptableDigestAlgo>
			</Cryptographic>
		</CACertificate>
	</Timestamp>
	<Revocation>
		<RevocationFreshness Unit="DAYS">0</RevocationFreshness>
		<SigningCertificate>
			<Signature Level="FAIL"/>
			<Expiration Level="FAIL"/>
			<RevocationDataAvailable Level="FAIL"/>
			<RevocationDataIsTrusted Level="FAIL"/>
			<RevocationDataFreshness Level="WARN"/>
			<Revoked Level="FAIL"/>
			<Cryptographic Level="WARN">
				<AcceptableEncryptionAlgo>
					<Algo>RSA</Algo>
					<Algo>ECDSA</Algo>
				</AcceptableEncryptionAlgo>
				<MiniPublicKeySize>
					<Algo Size="1024">RSA</Algo>
					<Algo Size="224">ECDSA</Algo>
				</MiniPublicKeySize>
				<AcceptableDigestAlgo>
					<Algo>SHA1</Algo>
					<Algo>SHA224</Algo>
					<Algo>SHA256</Algo>
					<Algo>SHA384</Algo>
					<Algo>SHA512</Algo>
				</AcceptableDigestAlgo>
			</Cryptographic>
		</SigningCertificate>
		<CACertificate>
			<Signature Level="FAIL"/>
			<Expiration Level="FAIL"/>
			<RevocationDataAvailable Level="FAIL"/>
			<RevocationDataIsTrusted Level="FAIL"/>
			<RevocationDataFreshness Level="WARN"/>
			<Revoked Level="FAIL"/>
			<Cryptographic Level="FAIL">
				<AcceptableEncryptionAlgo>
					<Algo>RSA</Algo>
					<Algo>ECDSA</Algo>
				</AcceptableEncryptionAlgo>
				<MiniPublicKeySize>
					<Algo Size="1024">RSA</Algo>
					<Algo Size="224">ECDSA</Algo>
				</MiniPublicKeySize>
				<AcceptableDigestAlgo>
					<Algo>SHA1</Algo>
					<Algo>SHA224</Algo>
					<Algo>SHA256</Algo>
					<Algo>SHA384</Algo>
					<Algo>SHA512</Algo>
				</AcceptableDigestAlgo>
			</Cryptographic>
		</CACertificate>
	</Revocation>
	<Cryptographic>
		<AlgoExpirationDate Format="yyyy-MM-dd">
			<!-- Algorithms under 112 bit strength: -->
			<Algo Date="2017-02-24">SHA1</Algo>
			<Algo Date="2017-02-24">RSA1024</Algo>
			<Algo Date="2017-02-24">RSA1536</Algo>

			<!-- Algorithms with ~ 112 bit strength: -->
			<Algo Date="2025-02-24">SHA224</Algo>
			<Algo Date="2025-02-24">RSA2048</Algo>
			<Algo Date="2025-02-24">ECDSA224</Algo>

			<!-- Algorithms with ~ 128 bit strength: -->
			<Algo Date="2030-02-24">SHA256</Algo>
			<Algo Date="2030-02-24">RSA3072</Algo>
			<Algo Date="2030-02-24">RSA4096</Algo>
			<Algo Date="2030-02-24">ECDSA256</Algo>

			<!-- Algorithms with ~ 192 bit strength: -->
			<Algo Date="2035-02-24">SHA384</Algo>

			<!-- Algorithms with ~ 256 bit strength: -->
			<Algo Date="2035-02-24">SHA512</Algo>
		</AlgoExpirationDate>
	</Cryptographic>
</ConstraintsParameters>

```