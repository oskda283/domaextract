package se.daniels.domaextract.application.gpslocation;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import org.apache.commons.io.FileUtils;
import se.daniels.domaextract.domain.gpslocation.GPSLocation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;

public class GPSLocationExtractor {
    public static GPSLocation extractFromJpgUrl(String mapUrl)  {
        try {
            InputStream inputStream = new URL(mapUrl).openStream();
            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
            GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if(directory == null){
                return null;
            }
            GeoLocation location = directory.getGeoLocation();
            if(location == null){
                return null;
            }
            return new GPSLocation(location.getLatitude(),location.getLongitude());
        } catch (ImageProcessingException e) {
            return null;
            //TODO: Add log
        } catch (IOException e) {
            return null;
            //TODO: Add log
        }
    }
}
