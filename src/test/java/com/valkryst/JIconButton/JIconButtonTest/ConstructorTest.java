package com.valkryst.JIconButton.JIconButtonTest;

import com.valkryst.JIconButton.JIconButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.image.BufferedImage;

public class ConstructorTest {
    @Test
    public void canConstructWithFontIcon() {
        new JIconButton(FontIcon.of(MaterialDesignP.PLUS));
    }

    @Test
    public void canConstructWithImageIcon() {
        final BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        new JIconButton(new ImageIcon(image));
    }

    @Test
    public void cannotConstructWithUnsupportedIcon() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            new JIconButton(new MetalIconFactory.FileIcon16());
        });
    }

    @Test
    public void cannotConstructWithNullIcon() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new JIconButton(null);
        });
    }
}
