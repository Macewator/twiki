package com.twiki.util;

import com.twiki.entry.Entry;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserEntry {

    private Entry entry;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Entry prepareEntry(){
        entry = new Entry();
        return entry;
    }

    public void clearEntry(){
        entry.setContent("");
        entry.setEntryCreateDate(null);
        entry.setEntryStatus(null);
        entry.setEntryType(null);
    }
}
