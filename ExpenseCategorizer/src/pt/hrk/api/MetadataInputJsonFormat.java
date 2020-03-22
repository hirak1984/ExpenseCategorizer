package pt.hrk.api;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pt.hrk.model.Metadata;

/**
 * Initially thought JSON format will be better , but later found CSV is more modifiable in real life.
 * @author hchatterjee
 *
 */
@Deprecated
public class MetadataInputJsonFormat {
	private static Gson getGson() {
		return new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	}
	public static Metadata parseMetadata(InputStream metadataJson) {
		Metadata metadata = getGson().fromJson(new InputStreamReader(metadataJson), Metadata.class);
		return metadata;
	}
}
