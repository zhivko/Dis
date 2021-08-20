package si.telekom.dis.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSEnvelopedDataParser;
import org.bouncycastle.cms.CMSEnvelopedDataStreamGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipientInformation;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OutputEncryptor;

public class EncryptAndDecrypt {

	private static final String WORK_DIR = "/tmp/decryptTar-8013044885650982387";

	private static final File SOURCE_PDF = new File(WORK_DIR, "source.pdf");
	private static final File DESTINATION_FILE = new File(WORK_DIR, "lxVidIdCcu1-1629309251.18309.wav");
	private static final File DECRYPTED_FILE = new File(WORK_DIR, "lxVidIdCcu2t-1619094708.1651.wav.decrypted.pdf");

	public static void main(final String[] args) throws Exception {
		if (!new File(WORK_DIR).exists()) {
			throw new RuntimeException("Update WORK_DIR to point to the directory the project is cloned into.");
		}
		// Files.deleteIfExists(DESTINATION_FILE.toPath());
		// Files.deleteIfExists(DECRYPTED_FILE.toPath());

		Security.addProvider(new BouncyCastleProvider());

		X509Certificate certificate = getX509Certificate(new File(WORK_DIR, "cert.pem"));
		PrivateKey privateKey = getPrivateKey(new File(WORK_DIR, "keystore.pfx"), "valuid");

		// encrypt(certificate, SOURCE_PDF, DESTINATION_FILE);
		decrypt(privateKey, DESTINATION_FILE, DECRYPTED_FILE);
	}

	private static void decrypt(PrivateKey privateKey, File encrypted, File decryptedDestination) throws Exception {
		byte[] encryptedData = Files.readAllBytes(encrypted.toPath());

		// ByteArrayInputStream inStream = new ByteArrayInputStream(encryptedData);
		// ASN1InputStream asnInputStream = new ASN1InputStream(inStream);
		//
		// BcDigestCalculatorProvider calc = new BcDigestCalculatorProvider();
		//
		// CMSSignedDataParser parser = new CMSSignedDataParser(calc,encryptedData);
		// parser.getSignedContent().drain();
		//
		// Store certStore = parser.getCertificates();
		// SignerInformationStore signers = parser.getSignerInfos();
		//
		// Collection c = signers.getSigners();
		// Iterator it = c.iterator();
		//
		// while (it.hasNext())
		// {
		// SignerInformation signer = (SignerInformation)it.next();
		// Collection certCollection = certStore.getMatches(signer.getSID());
		//
		// Iterator certIt = certCollection.iterator();
		// X509CertificateHolder cert = (X509CertificateHolder)certIt.next();
		//
		// System.out.println("verify returns: " + signer.verify(new
		// JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert)));
		// }
		//
		//
		// CMSEnvelopedData data = new CMSEnvelopedData(encryptedData);
		// Collection<RecipientInformation> recipCertificatesIt =
		// data.getRecipientInfos().getRecipients();
		// for (RecipientInformation recipientInformation : recipCertificatesIt) {
		// System.out.println(String.format("Recipient id: '%s'",
		// recipientInformation.getRID()));
		// }

		CMSEnvelopedData envelopedData = new CMSEnvelopedData(encryptedData);

		Collection<RecipientInformation> recipients = envelopedData.getRecipientInfos().getRecipients();
		KeyTransRecipientInformation recipientInfo = (KeyTransRecipientInformation) recipients.iterator().next();
		JceKeyTransRecipient recipient = new JceKeyTransEnvelopedRecipient(privateKey);

		byte[] result = recipientInfo.getContent(recipient);

		// RecipientInformation recInfo = getSingleRecipient(parser);
		// Recipient recipient = new JceKeyTransEnvelopedRecipient(privateKey);
		//
		// try (InputStream decryptedStream =
		// recInfo.getContentStream(recipient).getContentStream()) {
		// Files.copy(decryptedStream, decryptedDestination.toPath());
		// }

		System.out.println(String.format("Decrypted '%s' to '%s'", encrypted.getAbsolutePath(), decryptedDestination.getAbsolutePath()));
	}

	private static void encrypt(X509Certificate cert, File source, File destination) throws CertificateEncodingException, CMSException, IOException {
		CMSEnvelopedDataStreamGenerator gen = new CMSEnvelopedDataStreamGenerator();
		gen.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(cert));
		OutputEncryptor encryptor = new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES256_CBC).setProvider(BouncyCastleProvider.PROVIDER_NAME).build();

		try (FileOutputStream fileStream = new FileOutputStream(destination); OutputStream encryptingStream = gen.open(fileStream, encryptor)) {

			byte[] unencryptedContent = Files.readAllBytes(source.toPath());
			encryptingStream.write(unencryptedContent);
		}

		System.out.println(String.format("Encrypted '%s' to '%s'", source.getAbsolutePath(), destination.getAbsolutePath()));
	}

	private static X509Certificate getX509Certificate(File certificate) throws IOException, CertificateException {
		try (InputStream inStream = new FileInputStream(certificate)) {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			return (X509Certificate) cf.generateCertificate(inStream);
		}
	}

	private static PrivateKey getPrivateKey(File file, String password) throws Exception {
		KeyStore ks = KeyStore.getInstance("PKCS12");
		try (FileInputStream fis = new FileInputStream(file)) {
			ks.load(fis, password.toCharArray());
		}

		Enumeration<String> aliases = ks.aliases();
		String alias = aliases.nextElement();
		return (PrivateKey) ks.getKey(alias, password.toCharArray());
	}

	private static RecipientInformation getSingleRecipient(CMSEnvelopedDataParser parser) {
		Collection recInfos = parser.getRecipientInfos().getRecipients();
		Iterator recipientIterator = recInfos.iterator();
		if (!recipientIterator.hasNext()) {
			throw new RuntimeException("Could not find recipient");
		}
		return (RecipientInformation) recipientIterator.next();
	}
}
