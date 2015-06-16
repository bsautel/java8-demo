package cities.parser;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class CoordinateParser extends CellProcessorAdaptor {
    @Override
    public Object execute(Object value, CsvContext csvContext) {
        validateInputNotNull(value, csvContext);
        if (value instanceof String) {
            String stringValue = (String) value;
            stringValue = stringValue.replace(",", ".");
            if (stringValue.equals("-")) {
                return null;
            }
            return Double.parseDouble(stringValue);
        }
        throw new SuperCsvCellProcessorException("Input value must be string", csvContext, this);
    }
}
