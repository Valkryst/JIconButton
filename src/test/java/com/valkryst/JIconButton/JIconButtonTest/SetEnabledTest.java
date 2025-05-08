package com.valkryst.JIconButton.JIconButtonTest;

import com.valkryst.JIconButton.JIconButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;
import org.mockito.Mockito;

public class SetEnabledTest {
    @Test
    public void ensureSetEnabledIsCalledOnParent() {
        final JIconButton button = new JIconButton(FontIcon.of(MaterialDesignP.PLUS));
        Assertions.assertTrue(button.isEnabled());

        button.setEnabled(false);
        Assertions.assertFalse(button.isEnabled());
    }

    @Test
    public void ensureComponentResizedCalled() {
        final JIconButton buttonSpy = Mockito.spy(new JIconButton(FontIcon.of(MaterialDesignP.PLUS)));
        Mockito.clearInvocations(buttonSpy);

        buttonSpy.setEnabled(false);
        Mockito.verify(buttonSpy, Mockito.times(1)).componentResized(Mockito.isNull());
    }
}
