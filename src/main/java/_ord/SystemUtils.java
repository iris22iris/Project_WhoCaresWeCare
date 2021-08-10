package _ord;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

public class SystemUtils {

	public static Blob fileToBlob(String path) {
		Blob blob = null;
		try(
			FileInputStream fis = new FileInputStream(path);
		) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b = new byte[81920];
			while ((len = fis.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			blob = new SerialBlob(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return blob;
	}

}