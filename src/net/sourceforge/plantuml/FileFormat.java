/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2017, Arnaud Roques
 *
 * Project Info:  http://plantuml.sourceforge.net
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * Original Author:  Arnaud Roques
 *
 * Revision $Revision: 9786 $
 *
 */
package net.sourceforge.plantuml;

import java.io.File;

/**
 * Format for output files generated by PlantUML.
 * 
 * @author Arnaud Roques
 * 
 */
public enum FileFormat {
	PNG, SVG, EPS, EPS_TEXT, ATXT, UTXT, XMI_STANDARD, XMI_STAR, XMI_ARGO, PDF, MJPEG, ANIMATED_GIF, HTML, HTML5, VDX, LATEX, BASE64;

	/**
	 * Returns the file format to be used for that format.
	 * 
	 * @return a string starting by a point.
	 */
	public String getFileSuffix() {
		if (name().startsWith("XMI")) {
			return ".xmi";
		}
		if (this == MJPEG) {
			return ".avi";
		}
		if (this == ANIMATED_GIF) {
			return ".gif";
		}
		if (this == EPS_TEXT) {
			return EPS.getFileSuffix();
		}
		return "." + StringUtils.goLowerCase(name());
	}

	/**
	 * Check if this file format is Encapsulated PostScript.
	 * 
	 * @return <code>true</code> for EPS.
	 */
	public boolean isEps() {
		if (this == EPS) {
			return true;
		}
		if (this == EPS_TEXT) {
			return true;
		}
		return false;
	}

	public String changeName(String fileName, int cpt) {
		if (cpt == 0) {
			return changeName(fileName, getFileSuffix());
		}
		return changeName(fileName, "_" + String.format("%03d", cpt) + getFileSuffix());
	}

	private String changeName(String fileName, String replacement) {
		String result = fileName.replaceAll("\\.\\w+$", replacement);
		if (result.equals(fileName)) {
			result = fileName + replacement;
		}
		return result;
	}

	public File computeFilename(File pngFile, int i) {
		if (i == 0) {
			return pngFile;
		}
		final File dir = pngFile.getParentFile();
		return new File(dir, computeFilename(pngFile.getName(), i));
		// String name = pngFile.getName();
		// name = name.replaceAll("\\" + getFileSuffix() + "$", "_" + String.format("%03d", i) + getFileSuffix());
		// return new File(dir, name);

	}

	public String computeFilename(String name, int i) {
		if (i == 0) {
			return name;
		}
		return name.replaceAll("\\" + getFileSuffix() + "$", "_" + String.format("%03d", i) + getFileSuffix());
	}

}
