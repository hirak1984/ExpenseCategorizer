package pt.hrk.main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import pt.hrk.api.Input;
import pt.hrk.api.MetadataInputCsvFormat;
import pt.hrk.api.Output;
import pt.hrk.model.Metadata;
import pt.hrk.model.Statement;

public class MyMain {

	private static final String directoryPath = "C:\\Users\\hchatterjee\\Downloads\\creditcard\\";
	private static final File directory = new File(directoryPath);

	private static final File[] statementFiles = { new File(directoryPath, "March2020_1.csv"),
			new File(directoryPath, "March2020_1.CSV"), new File(directoryPath, "March2020_2.csv") };

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		// step 1
		Metadata metadata = MetadataInputCsvFormat.parseMetadata(MyMain.class.getResourceAsStream("/metadata.csv"));
		// step 2
		List<Statement> statements = Input.parseFrom(metadata, statementFiles);
		// step 3
		Output.processResults(directory, metadata, statements);

	}

}
