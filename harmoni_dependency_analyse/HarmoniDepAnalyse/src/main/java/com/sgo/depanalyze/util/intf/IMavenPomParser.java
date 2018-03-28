package com.sgo.depanalyze.util.intf;

import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.sgo.depanalyze.util.MavenPomProperties;

/**
 * The Interface IHbmMappingParser.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Dec 4, 2013 4:12:48 PM
 */
public interface IMavenPomParser {
 
    
    MavenPomProperties parsePomFile(JarFile jarFile, ZipEntry zipEntry);
            
}
