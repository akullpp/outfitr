package de.akull.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Message retrieval from property files ready for i18n.
 */
@Component
public class Messages {

    private MessageSourceAccessor accessor;

    @Autowired
    public Messages(MessageSource source) {
        accessor = new MessageSourceAccessor(source, Locale.getDefault());
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }
}
