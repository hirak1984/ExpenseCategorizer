package pvt.hrk.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import pvt.hrk.model.Metadata;
import pvt.hrk.model.SubCategory;

public class MetadataInputCsvFormat {

	/**
	 * Format - Category,SubCategory,keywordContainsIgnoreCase...
	 * @param metadataCSV
	 * @return
	 * @throws IOException 
	 */
	public static Metadata parseMetadata(InputStream metadataCSV) throws IOException {
		Reader in = new InputStreamReader(metadataCSV);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		Metadata metadata = new Metadata();
		
		for (CSVRecord record : records) {
			int columns = record.size();
			String categoryName = record.get(0);
			String subCategoryName = record.get(1);
			List<String>  keywordContainsIgnoreCase = new LinkedList<String>();
				for (int i=2;i<columns;i++) {
					keywordContainsIgnoreCase.add(record.get(i));
				}
			SubCategory sc = new SubCategory();
			sc.name=subCategoryName;
			sc.keywordContainsIgnoreCase = keywordContainsIgnoreCase;
			metadata.addSubCategoryToCategory(categoryName, sc);
		
		}
		return metadata;
	}
	
}
