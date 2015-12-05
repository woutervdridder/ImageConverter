package net.wvdr;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Utility class to convert images.
 */
public class ImageConverter {

    /**
     * Convert a bufferedImage to a Base64 binary string.
     * @param img The BufferedImage to convert.
     * @param formatName The format of the image.
     * @return The Base64 binary string.
     */
    public static String imgToBase64String(final RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, formatName, Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.ISO_8859_1.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * Convert a Base64 binary string to a BufferedImage.
     * @param base64String The base64 string to convert.
     * @return The BufferedImage.
     */
    public static BufferedImage base64StringToImg(final String base64String) {
        try {
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * Convert a bufferedImage to a hex string.
     * @param img The BufferedImage to convert.
     * @return Hexadecimal binary string.
     */
    public static String imgToHexString(final BufferedImage img, final String formatName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write( img, formatName, baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return DatatypeConverter.printHexBinary(imageInByte);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * Convert a hex binary string to a BufferedImage.
     * @param hexString Hexadecimal binary string.
     * @return BufferedImage.
     */
    public static BufferedImage hexStringToImg(final String hexString) {
        byte[] imageInByte= DatatypeConverter.parseHexBinary(hexString);

        InputStream in = new ByteArrayInputStream(imageInByte);
        try {
            return ImageIO.read(in);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
}
