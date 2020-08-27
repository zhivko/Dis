package si.telekom.dis.server;

import java.io.File;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

// import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.OutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

public class Pdf2TiffConverter {

    private String tmpFilePath;

    public Pdf2TiffConverter(String fileName) {
    	this.tmpFilePath = fileName;
		}

   
    public ByteArrayOutputStream converter2tiff(float DPI) throws Exception {
        // return format
        ByteArrayOutputStream imageBaos = new ByteArrayOutputStream();
        ImageOutputStream output = ImageIO.createImageOutputStream(imageBaos);
        // Obtain a tiffer writer for continue writing tiff files into a multi-page file
        ImageWriter writer = ImageIO.getImageWritersByFormatName("TIFF").next();

        // Read pdf file from tmp file
        try (final PDDocument document = PDDocument.load(new File(this.tmpFilePath))) {

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            int pageCount = document.getNumberOfPages();

            BufferedImage[] images = new BufferedImage[pageCount];
            // ByteArrayOutputStream[] baosArray = new ByteArrayOutputStream[pageCount];

            writer.setOutput(output);

            ImageWriteParam params = writer.getDefaultWriteParam();

            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

            // Compression: None, PackBits, ZLib, Deflate, LZW, JPEG and CCITT
            // variants allowed
            // needs to decided which compression mode is the best
            // cuz the tiff is too large
            params.setCompressionType("Deflate");
            // params.setCompressionType("CCITT T.6");

            writer.prepareWriteSequence(null);

            for (int page = 0; page < pageCount; page++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, DPI, ImageType.RGB);
                images[page] = image;
                IIOMetadata metadata = writer.getDefaultImageMetadata(new ImageTypeSpecifier(image), params);
                writer.writeToSequence(new IIOImage(image, null, metadata), params);
                // ImageIO.write(image, "tiff", baosArray[page]);
            }

            System.out.println("imageBaos size: " + imageBaos.size());
            // Finished write to output

            writer.endWriteSequence();
            document.close();
            
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            // avoid memory leaks
            writer.dispose();
        }

        return imageBaos;
    }

}