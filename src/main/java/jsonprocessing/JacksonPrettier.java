package jsonprocessing;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class JacksonPrettier extends DefaultPrettyPrinter {
    public JacksonPrettier() {
        this._objectFieldValueSeparatorWithSpaces = ": ";
        this.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE.withIndent("    "));  // 4 espaces
        this.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE.withIndent("    "));
    }
}