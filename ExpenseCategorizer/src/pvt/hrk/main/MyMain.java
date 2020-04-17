package pvt.hrk.main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import pvt.hrk.api.Input;
import pvt.hrk.api.MetadataInputCsvFormat;
import pvt.hrk.api.Output;
import pvt.hrk.model.Metadata;
import pvt.hrk.model.Statement;

public class MyMain {

	private static final String directoryPath = "C:\\Users\\hchatterjee\\Downloads\\april";
	private static final File directory = new File(directoryPath);

	private static final File[] statementFiles = { new File(directoryPath, "April2020_9145.csv"),
			new File(directoryPath, "April2020_3696.csv")};

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		// step 1
		Metadata metadata = MetadataInputCsvFormat.parseMetadata(MyMain.class.getResourceAsStream("/metadata.csv"));
		// step 2
		List<Statement> statements = Input.parseFrom(metadata, statementFiles);
		// step 3
		Output.processResults(directory, metadata, statements);

	}

}
