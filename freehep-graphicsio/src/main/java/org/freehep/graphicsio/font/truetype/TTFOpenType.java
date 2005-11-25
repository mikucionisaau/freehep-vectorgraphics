// Copyright 2001, FreeHEP.
package org.freehep.graphicsio.font.truetype;

import java.awt.font.OpenType;
import java.io.IOException;

/**
 * Conrete implementation of a TrueType font, attached to a JavaFont which implements
 * the OpenType interface.
 *
 *  FIXME: Test as soon as some Java Fonts implements OpenType.
 *  Probably TTFMemoryInput won't work. Tag names may be different for OpenType and TrueType.
 *
 *  @author Simon Fischer
 *  @version $Id: freehep-graphicsio/src/main/java/org/freehep/graphicsio/font/truetype/TTFOpenType.java 399e20fc1ed9 2005/11/25 23:40:46 duns $
 */
public class TTFOpenType extends TTFFont {

    private OpenType openType;

    public TTFOpenType(OpenType openType) throws IOException {
	this.openType = openType;

	for (int i = 0; i < TTFTable.TT_TAGS.length; i++) {
	    byte[] data = openType.getFontTable(TTFTable.TT_TAGS[i]);
	    if (data != null) {
		newTable(TTFTable.TT_TAGS[i], new TTFMemoryInput(data));
	    } else {
		System.err.println("No table found for '"+TTFTable.TT_TAGS[i]+"'.");
	    }
	}
	
    }

    public int getFontVersion() {
	return openType.getVersion();
    }
}
