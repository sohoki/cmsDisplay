package egovframework.let.utl.fcc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IRational;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.IVideoResampler;
import com.xuggle.xuggler.Utils;



@Controller
public class VideoStroyboard {

	    private static final Logger LOGGER = LoggerFactory.getLogger(VideoStroyboard.class);
	     
        //동영상 thumnail 	   
	    @SuppressWarnings("deprecation")
	    public static String  thumnail(String _fileNm, String _filePath) throws NumberFormatException,IOException {
	    	
	    	
	    	
	    	   
	    	
	    	    Calendar calendar = Calendar.getInstance();
	            java.util.Date date = calendar.getTime();
	            String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
	    	   
	    	    IStreamCoder videoCoder = null;
	    	    IContainer container = IContainer.make();
	    	    
	    	    String thumnailFull = null;
                String thumnail = null;
                String duration = "";
	    	    
	            try{
	            	
		            	
			       	    
			            if (!IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION)) 
			            {
			            	LOGGER.debug("license 문제");
			            	System.out.println("you must install the GPL version of Xuggler (with IVideoResampler support) for this demo to work");
			            	throw new Exception();
			            	
			            }
			            
			             
		
			            if (container.open(_fileNm, IContainer.Type.READ, null) < 0) {
			            	LOGGER.debug("파일이 열리지 않을때");
			            	System.out.println("could not open file: " + _fileNm);
			            	throw new Exception();
			            }
			                    
			            String seconds=container.getDuration()/(10000000*3)+""; // 2초 후 time of thumbnail 
			            
			            LOGGER.debug("seconds:"+seconds);
			            LOGGER.debug("getDuration:"+container.getDuration());
			            			            
			            duration = ""+ container.getDuration() ;
			            System.out.println("duration:"+duration);
			            int numStreams = container.getNumStreams(); 		
			            // and iterate through the streams to find the first video stream 
			            int videoStreamId = -1; 
			             
			            for (int i = 0; i < numStreams; i++) { 
			                    // find the stream object 
			                    IStream stream = container.getStream(i); 
			                    // get the pre-configured decoder that can decode this stream; 
			                    IStreamCoder coder = stream.getStreamCoder(); 
		
			                    if (coder.getCodecType() == com.xuggle.xuggler.ICodec.Type.CODEC_TYPE_VIDEO) { 
			                            videoStreamId = i; 
			                            videoCoder = coder; 
			                            break; 
			                    } 
			            } 

	            if (videoStreamId == -1) {
	            	System.out.println("could not find video stream in container: " + _fileNm);
	            	throw new Exception();
	            }
	                    


	            if (videoCoder.open() < 0){
	            	System.out.println("could not open video decoder for container: " + _fileNm);
	            	throw new Exception();
	            }
	                    
	            IVideoResampler resampler = null; 
	            if (videoCoder.getPixelType() != IPixelFormat.Type.BGR24) { 


	                    resampler = IVideoResampler.make(videoCoder.getWidth(), videoCoder 
	                                    .getHeight(), IPixelFormat.Type.BGR24, videoCoder 
	                                    .getWidth(), videoCoder.getHeight(), videoCoder 
	                                    .getPixelType()); 
	                    if (resampler == null) {
	                    	System.out.println("RuntimeException could not create color space resampler for: " + _fileNm);
	    	            	throw new Exception();
	                    }        
	            } 


	            IPacket packet = IPacket.make();	            
	            IRational timeBase = container.getStream(videoStreamId).getTimeBase();
	            System.out.println("Timebase " + timeBase.toString()); 

	            
	            long timeStampOffset = (timeBase.getDenominator() / timeBase.getNumerator())* Integer.parseInt(seconds);
	            
	            long target = container.getStartTime() + timeStampOffset;
	            System.out.println("target " + target); 
	            container.seekKeyFrame(videoStreamId, target, 0); 

	            boolean isFinished = false; 
	            
	            thumnailFull = _filePath+ "\\"+ today+"_thumnail.png";
	            
	            
	            System.out.println("thumnailFull:" + thumnailFull);
	    	    thumnail = today+"_thumnail.png";	
	    	    if (container.readNextPacket(packet)  >= 0){
	    	    	System.out.println("packet: +" );
	    	    }else {
	    	    	System.out.println("packet: -" );
	    	    }
	    	    
	    	    
	            while(container.readNextPacket(packet) >= 0 && !isFinished ) {
	                    if (packet.getStreamIndex() == videoStreamId) { 
	                    	
	                            IVideoPicture picture = IVideoPicture.make(videoCoder  .getPixelType(), videoCoder.getWidth(), videoCoder .getHeight()); 

	                            int offset = 0; 	                            
	                            while (offset < packet.getSize()) { 
	                            	
	                            	    System.out.println("offset: ");

	                                    int bytesDecoded = videoCoder.decodeVideo(picture, packet, 
	                                                    offset); 
	                                    if (bytesDecoded < 0) { 
	                                            System.err.println("WARNING!!! got no data decoding " +"video in one packet"); 
	                                    } 
	                                    offset += bytesDecoded; 
	                                    
	                                    if (picture.isComplete()) { 

	                                            IVideoPicture newPic = picture; 

	                                            if (resampler != null) { 

	                                                    newPic = IVideoPicture.make(resampler 
	                                                                    .getOutputPixelFormat(), picture.getWidth(), 
	                                                                    picture.getHeight()); 
	                                                    if (resampler.resample(newPic, picture) < 0) {
	                                                    	System.out.println("RuntimeException could not resample video from: " + _fileNm);
	                            	    	            	throw new Exception();
	                                                    }
	                                                             
	                                            } 

	                                            if (newPic.getPixelType() != IPixelFormat.Type.BGR24){
	                                            	System.out.println("could not decode video as BGR 24 bit data in:" + _fileNm);
                        	    	            	throw new Exception();
	                                            }	                                                    
	                                            BufferedImage javaImage = Utils.videoPictureToImage(newPic);
	                                            
	                                            //수정 파일명 	                                            
	                                            File file=new File(thumnailFull);//name of pic
	                    	                    ImageIO.write(javaImage, "png", file);
	                    	                    System.out.println("isFinished :" + file.toString());
	                                            isFinished = true; 	                                            
	                                    }
	                            } 	                            
	                    } 
	                    
	               } 
	               if (isFinished == true){
	            	   return thumnail +":"+duration ;
	               }else {
	            	   return "Fail";
	               }
	            }catch (Exception e1){
	            	System.out.println("isFinished :" + e1.toString());
	            	return "Fail:";
	            }
	            finally {
	            	if (videoCoder != null) { 
		                    videoCoder.close(); 
		                    videoCoder = null; 
		            } 
		            if (container != null) { 
		                    container.close(); 
		                    container = null; 
		            } 
	            }
 

	          
	    }
	
}
