package si.telekom.dis.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.smime.SMIMECapabilitiesAttribute;
import org.bouncycastle.asn1.smime.SMIMECapability;
import org.bouncycastle.asn1.smime.SMIMECapabilityVector;
import org.bouncycastle.asn1.smime.SMIMEEncryptionKeyPreferenceAttribute;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnvelopedGenerator;
import org.bouncycastle.mail.smime.SMIMEException;
import org.bouncycastle.mail.smime.SMIMESignedGenerator;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.Strings;

/**
 * Example that sends a signed and encrypted mail message.
 */
public class SendSignedAndEncryptedMail {

	static String alias = "valu_videoid_storage’s telekom slovenije d.d. id #2";
	static File keystore = new File("/home/klemen/temp/VALU_VideoID_Storage_test.pfx");
	static String password = "valuid";
	static String smtpServer = "smtp.gmail.com";
	static String emailAddress = "klemen.zivkovic@gmail.com";

	final static String[] args1 = { keystore.getAbsolutePath(), password, alias, smtpServer, emailAddress };

	public static void main(String args[]) {
		args = args1;
		if (args.length != 5) {
			System.err.println("usage: SendSignedAndEncryptedMail <pkcs12Keystore> <password> <keyalias> <smtp server> <email address>");
			System.exit(0);
		}

		try {
			MailcapCommandMap mailcap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();

			mailcap.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
			mailcap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
			mailcap.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
			mailcap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
			mailcap.addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");

			CommandMap.setDefaultCommandMap(mailcap);

			/* Add BC */
			Security.addProvider(new BouncyCastleProvider());

			/* Open the keystore */
			KeyStore keystore = KeyStore.getInstance("PKCS12", "BC");
			keystore.load(new FileInputStream(args[0]), args[1].toCharArray());
			Certificate[] chain = keystore.getCertificateChain(args[2]);

			/* Get the private key to sign the message with */
			PrivateKey privateKey = (PrivateKey) keystore.getKey(args[2], args[1].toCharArray());
			if (privateKey == null) {
				throw new Exception("cannot find private key for alias: " + args[2]);
			}

			/* Create the message to sign and encrypt */
			Properties props = System.getProperties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.timeout", "10000");
      props.put("mail.smtp.connectiontimeout", "1000"); 
			
			
			String userName = "klemen.zivkovic@gmail.com";
			String password = "iwothu^$BP5kw7W7";

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(userName, password);
				}
			});

			MimeMessage message = new MimeMessage(session);

			MimeMultipart multipart = new MimeMultipart(); // creating a multipart is OK

			// Creating the first body part of the multipart, it's OK
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			// ... bla bla
			// ok, so this body part is the "attachment file"
			FileDataSource fds = new FileDataSource(new File("/home/klemen/temp/report.xml"));
			messageBodyPart.setDataHandler(new DataHandler(fds));
			// ... bla bla
			multipart.addBodyPart(messageBodyPart); // at this point, the multipart
																							// contains your file attachment,
																							// but only that!

			// at this point, you set your mail's body to be the HTML message
			message.setContent("<b>strong msg</b>", "text/html; charset=utf-8");
			// and then right after that, you **reset** your mail's content to be your
			// multipart, which does not contain the HTML
			message.setContent(multipart);

			MimeBodyPart body = new MimeBodyPart();
			body.setContent("example encrypted message", "text/plain");

			message.setFrom(new InternetAddress(emailAddress));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
			message.setSubject("example encrypted message");

			message.saveChanges();

			/* Create the SMIMESignedGenerator */
			SMIMECapabilityVector capabilities = new SMIMECapabilityVector();
			capabilities.addCapability(SMIMECapability.dES_EDE3_CBC);
			capabilities.addCapability(SMIMECapability.rC2_CBC, 128);
			capabilities.addCapability(SMIMECapability.dES_CBC);

			ASN1EncodableVector attributes = new ASN1EncodableVector();
			attributes
					.add(new SMIMEEncryptionKeyPreferenceAttribute(new IssuerAndSerialNumber(new X500Name(((X509Certificate) chain[0]).getIssuerDN().getName()),
							((X509Certificate) chain[0]).getSerialNumber())));
			attributes.add(new SMIMECapabilitiesAttribute(capabilities));

			SMIMESignedGenerator signer = new SMIMESignedGenerator();
			signer.addSignerInfoGenerator(
					new JcaSimpleSignerInfoGeneratorBuilder().setProvider("BC").setSignedAttributeGenerator(new AttributeTable(attributes))
							.build("DSA".equals(privateKey.getAlgorithm()) ? "SHA1withDSA" : "MD5withRSA", privateKey, (X509Certificate) chain[0]));

			/* Add the list of certs to the generator */
			List certList = new ArrayList();
			certList.add(chain[0]);
			Store certs = new JcaCertStore(certList);
			signer.addCertificates(certs);

			/* Sign the message */
			MimeMultipart mm = signer.generate(body);
	
			/* Set all original MIME headers in the signed message */
			Enumeration headers = body.getAllHeaderLines();
			while (headers.hasMoreElements()) {
				message.addHeaderLine((String) headers.nextElement());
			}

			/* Set the content of the signed message */
			message.setContent(mm);
			message.saveChanges();

			/* Create the encrypter */
			SMIMEEnvelopedGenerator encrypter = new SMIMEEnvelopedGenerator();
			encrypter.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator((X509Certificate) chain[0]).setProvider("BC"));

			/* Encrypt the message */
			MimeBodyPart encryptedPart = encrypter.generate(message,
					new JceCMSContentEncryptorBuilder(CMSAlgorithm.RC2_CBC).setProvider("BC").build());

			/*
			 * Create a new MimeMessage that contains the encrypted and signed content
			 */
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			encryptedPart.writeTo(out);

			// MimeMessage encryptedMessage = new MimeMessage(session, new
			// ByteArrayInputStream(out.toByteArray()));
			//
			/* Set all original MIME headers in the encrypted message */
			headers = body.getAllHeaderLines();
			while (headers.hasMoreElements()) {
				String headerLine = (String) headers.nextElement();
				/*
				 * Make sure not to override any content-* headers from the original
				 * message
				 */
				if (!Strings.toLowerCase(headerLine).startsWith("content-")) {
					message.addHeaderLine(headerLine);
				}
			}

			Transport.send(message);
		} catch (SMIMEException ex) {
			ex.getUnderlyingException().printStackTrace(System.err);
			ex.printStackTrace(System.err);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}
}
