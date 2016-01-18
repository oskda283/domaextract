package se.daniels.maps;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.lang.Rational;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MapLocationExtractor {
    public static GPSLocation extractFromJpgUrl(String mapUrl)  {
        File imageFile = new File("mapFile.jpg");
        try {
            FileUtils.copyURLToFile(new URL(mapUrl), imageFile);
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            GpsDirectory directory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if(directory == null){
                return null;
            }
            GeoLocation location = directory.getGeoLocation();
            imageFile.delete();
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
