package filetestharness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;





public class FileProcessing implements Callable{
	private final String PATHSEPERATOR="/";

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		String resultFileNames=eventContext.getMessage().getInvocationProperty("FileNames");
		String sourcePath=eventContext.getMessage().getInvocationProperty("FileSourcePath");
		String destination=eventContext.getMessage().getInvocationProperty("FileTargetPath");
		if(resultFileNames.contains(",")){
			String[] resultFileName=resultFileNames.split(",");
			
			for (String string : resultFileName) {
				copyFile(new File(sourcePath+PATHSEPERATOR+string), new File(destination+PATHSEPERATOR+string));
			}
			
			
		}
		else{
			copyFile(new File(sourcePath+PATHSEPERATOR+resultFileNames), new File(destination+PATHSEPERATOR+resultFileNames));
		}
		
		return eventContext.getMessage().getPayload();
		
		
	}
	
	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            long count = 0;
            long size = source.size();              
            while((count += destination.transferFrom(source, count, size-count))<size);
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }


}
