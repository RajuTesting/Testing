/**
 *
 */
package com.borngroup.ssl.core.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;


/**
 * @author t.balagopalan
 *
 */
public final class XmlFontProvider extends FontFactoryImp
{
    private final String fontName;
    private final float  fontSize;

    public XmlFontProvider(final String fontName, final float fontSize)
    {
        this.fontName = fontName;
        this.fontSize = fontSize;
    }

    @Override
    public Font getFont(String fontName, final String encoding, final boolean embedded, float size, final int style,
            final BaseColor color, final boolean cached)
    {
        if (fontName == null || size == 0)
        {
            fontName = this.fontName;
            size = this.fontSize;
        }

        return super.getFont(fontName, "cp1250", embedded, size, style, color, cached);
    }
}
