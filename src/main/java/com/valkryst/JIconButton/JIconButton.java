package com.valkryst.JIconButton;

import lombok.NonNull;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

/**
 * <p>
 *     Represents a button which displays an {@link Icon} which automatically resizes itself to fit the button's
 *     bounds.
 * </p>
 */
public class JIconButton extends JButton implements ComponentListener {
    /** A percentage to scale the {@link #originalIcon} by when the button is resized.*/
    private static final float SCALE = 0.8f;

    /**
     * <p>The original {@link Icon} provided to the button during construction or via {@link #setIcon(Icon)}.</p>
     *
     * <p>
     *     All operations are performed on this icon to ensure that we do not lose any quality due to repeated
     *     alterations.
     * </p>
     */
    private Icon originalIcon;

    /**
     * Constructs a new {@link JIconButton}.
     *
     * @param icon {@link Icon} to display on the button.
     */
    public JIconButton(final @NonNull Icon icon) {
        super(icon);
        this.addComponentListener(this);
    }

    /**
     * Resizes the button's icon to fit its new bounds.
     *
     * @param e See {@link ComponentListener#componentResized(ComponentEvent)}.
     *
     * @throws UnsupportedOperationException If the icon is of an unsupported type.
     */
    @Override
    public void componentResized(final ComponentEvent e) {
        if (this.getWidth() + this.getHeight() == 0) {
            return;
        }

        if (originalIcon instanceof FontIcon) {
            final FontIcon fontIcon = (FontIcon) originalIcon;

            super.setIcon(FontIcon.of(
                fontIcon.getIkon(),
                (int) (Math.min(getWidth(), getHeight()) * SCALE),
                super.isEnabled() ? fontIcon.getIconColor() : fontIcon.getIconColor().darker()
            ));

            return;
        }

        if (originalIcon instanceof ImageIcon) {
            super.setIcon(
                new ImageIcon(
                    this.getScaledImage(((ImageIcon) originalIcon).getImage())
                )
            );
        }
    }

    @Override
    public void componentMoved(final ComponentEvent e) {}

    @Override
    public void componentShown(final ComponentEvent e) {}

    @Override
    public void componentHidden(final ComponentEvent e) {}

    /**
     * Resizes the provided {@link Image} to fit within the button's bounds.
     *
     * @param image {@link Image} to resize.
     * @return A new {@link BufferedImage}, which is a scaled version of the provided image.
     */
    private BufferedImage getScaledImage(final @NonNull Image image) {
        // Get original image dimensions
        int origWidth = image.getWidth(null);
        int origHeight = image.getHeight(null);

        if (origWidth <= 0 || origHeight <= 0) {
            return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
        }

        // Calculate maximum dimensions
        final int maxSize = (int) (Math.min(super.getWidth(), super.getHeight()) * SCALE);

        // Calculate scaled dimensions preserving aspect ratio
        int width, height;
        if (origWidth > origHeight) {
            width = maxSize;
            height = (int) (maxSize * ((double) origHeight / origWidth));
        } else {
            height = maxSize;
            width = (int) (maxSize * ((double) origWidth / origHeight));
        }

        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return bufferedImage;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.componentResized(null);
    }

    @Override
    public void setIcon(final @NonNull Icon icon) {
        if (!(icon instanceof FontIcon || icon instanceof ImageIcon)) {
            throw new UnsupportedOperationException("Encountered an unsupported icon type: " + icon.getClass().getName());
        }

        originalIcon = icon;
        super.setIcon(icon);
        this.componentResized(null);
    }
}
