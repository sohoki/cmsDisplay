package emart_cms;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

public class testMP3 {

	public static void main(String args[]) throws UnsupportedAudioFileException, IOException {
		String timeck = "0250";
		System.out.println(timeck.substring(0,2) + timeck.substring(2,4) );
		
	}

	private static String getDurationWithMp3Spi(File file)
			throws UnsupportedAudioFileException, IOException {

		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		String mp3Time = "";
		if (fileFormat instanceof TAudioFileFormat) {
			Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
			String key = "duration";
			Long microseconds = (Long) properties.get(key);
			int mili = (int) (microseconds / 1000);
			int sec = (mili / 1000) ;
			int sec_m = (mili / 1000) % 60 ;
			int min = (mili / 1000) / 60;
			System.out.println("time:" + min + ":" + sec_m);
			System.out.println("time:" + sec );
			mp3Time = min+":"+sec;
		} else {
			throw new UnsupportedAudioFileException();			
		}		
		return mp3Time;

	}
}
