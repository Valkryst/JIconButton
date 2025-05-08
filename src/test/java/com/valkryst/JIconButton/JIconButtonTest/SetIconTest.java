package com.valkryst.JIconButton.JIconButtonTest;

import com.valkryst.JIconButton.JIconButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;
import org.mockito.Mockito;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.image.BufferedImage;

public class SetIconTest {
    @Test
    public void canSetIconWithFontIcon() {
        final FontIcon icon = FontIcon.of(MaterialDesignP.PLUS);
        final JIconButton button = new JIconButton(icon);
        button.setIcon(icon);

        Assertions.assertEquals(icon, button.getIcon());
    }

    @Test
    public void canSetIconWithImageIcon() {
        final BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        final JIconButton button = new JIconButton(new ImageIcon(image));
        final ImageIcon icon = new ImageIcon(image);
        button.setIcon(icon);

        Assertions.assertEquals(icon, button.getIcon());
    }

    @Test
    public void cannotSetIconWithUnsupportedIcon() {
        final JIconButton button = new JIconButton(FontIcon.of(MaterialDesignP.PLUS));

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            button.setIcon(new MetalIconFactory.FileIcon16());
        });
    }

    @Test
    public void cannotSetIconWithNullIcon() {
        final JIconButton button = new JIconButton(FontIcon.of(MaterialDesignP.PLUS));

        Assertions.assertThrows(NullPointerException.class, () -> {
            button.setIcon(null);
        });
    }

    @Test
    public void ensureSetIconIsCalledOnParent() {
        final FontIcon iconA = FontIcon.of(MaterialDesignP.PLUS);
        final FontIcon iconB = FontIcon.of(MaterialDesignP.PAIL_MINUS);

        final JIconButton button = new JIconButton(iconA);
        button.setIcon(iconB);
        Assertions.assertNotEquals(iconA, button.getIcon());
    }

    @Test
    public void ensureComponentResizedCalled() {
        final JIconButton buttonSpy = Mockito.spy(new JIconButton(FontIcon.of(MaterialDesignP.PLUS)));
        Mockito.clearInvocations(buttonSpy);

        buttonSpy.setIcon(FontIcon.of(MaterialDesignP.PAIL_MINUS));
        Mockito.verify(buttonSpy, Mockito.times(1)).componentResized(Mockito.isNull());
    }
}
