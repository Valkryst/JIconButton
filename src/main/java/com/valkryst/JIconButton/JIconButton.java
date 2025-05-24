package com.valkryst.JIconButton;

import org.imgscalr.Scalr;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
     * <p>The original {@link Icon} provided to the button during construction or VIA {@link #setIcon(Icon)}.</p>
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
    public JIconButton(final Icon icon) {
        this.addComponentListener(this);
        this.setIcon(icon);
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

        super.setIcon(
            new ImageIcon(
                Scalr.resize(
                    (BufferedImage) ((ImageIcon) originalIcon).getImage(),
                    (int) (Math.min(getWidth(), getHeight()) * SCALE),
                    (int) (Math.min(getWidth(), getHeight()) * SCALE)
                )
            )
        );
    }

    @Override
    public void componentMoved(final ComponentEvent e) {}

    @Override
    public void componentShown(final ComponentEvent e) {}

    @Override
    public void componentHidden(final ComponentEvent e) {}

    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.componentResized(null);
    }

    @Override
    public void setIcon(final Icon icon) {
        Objects.requireNonNull(icon);

        if (!(icon instanceof FontIcon || icon instanceof ImageIcon)) {
            throw new UnsupportedOperationException("Encountered an unsupported icon type: " + icon.getClass().getName());
        }

        originalIcon = icon;
        super.setIcon(icon);
        this.componentResized(null);
    }
}
