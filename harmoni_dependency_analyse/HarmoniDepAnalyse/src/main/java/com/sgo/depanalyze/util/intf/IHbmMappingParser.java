package com.sgo.depanalyze.util.intf;

import java.io.InputStream;

import com.sgo.depanalyze.util.HbmMappingProperties;

/**
 * The Interface IHbmMappingParser.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Dec 4, 2013 4:12:48 PM
 */
public interface IHbmMappingParser {
    /**
     * Parses the hbm.
     * 
     * @param input
     *            the input
     * @param entryName
     *            the entry name
     * @return the hbm mapping properties
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:12:48 PM
     */
    HbmMappingProperties parseHbm(InputStream input, String entryName);
}
