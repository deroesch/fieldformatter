package org.deroesch.fieldformatter;

import java.util.Set;
import java.util.TreeSet;

import lombok.NonNull;

/**
 * Simple string validators for date, phone, soc sec number.
 *
 */
public class FieldFormatter {

    /**
     * 
     * @param candidate
     * @return
     */
    public String asDate(@NonNull final String candidate) {
        final String digits = filterDigits(candidate);
        return format(digits, dateLimit, dateChars, dateDelim);
    }

    /**
     * 
     * @param candidate
     * @return
     */
    public String asSSN(@NonNull final String candidate) {
        final String digits = filterDigits(candidate);
        return format(digits, ssnLimit, ssnChars, ssnDelim);
    }

    /**
     * 
     * @param candidate
     * @return
     */
    public String asPhone(@NonNull final String candidate) {
        final String digits = filterDigits(candidate);
        return format(digits, phoneLimit, phoneChars, phoneDelim);
    }

    /**
     * 
     * @param candidate
     * @return
     */
    @NonNull
    String filterDigits(@NonNull final String candidate) {
        // Collect digit characters only
        final StringBuffer formattingBuffer = new StringBuffer();
        for (int i = 0; i < candidate.length(); i++) {
            final Character c = candidate.charAt(i);
            if (Character.isDigit(c))
                formattingBuffer.append(candidate.charAt(i));
        }

        return formattingBuffer.toString();
    }

    /**
     * 
     * @param candidate
     * @param length
     * @param locations
     * @param delimChar
     * @return
     */
    @NonNull
    String format(@NonNull final String candidate, final int length, @NonNull final Set<Integer> locations,
            @NonNull final String delimChar) {

        // Exit immediately if length is wrong.
        if (length < 0 || candidate.length() != length)
            return "";

        // Rewrite string, adding delimiters
        final StringBuffer formattingBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            formattingBuffer.append(candidate.charAt(i));
            if (locations.contains(i))
                formattingBuffer.append(delimChar);
        }

        return formattingBuffer.toString();
    }

    // For dates
    private final int dateLimit = 8;
    private final String dateDelim = "/";
    private final Set<Integer> dateChars = new TreeSet<>();
    {
        dateChars.add(1);
        dateChars.add(3);
    }

    // For social security numbers
    private final int ssnLimit = 9;
    private final String ssnDelim = "-";
    private final Set<Integer> ssnChars = new TreeSet<>();
    {
        ssnChars.add(2);
        ssnChars.add(4);
    }

    // For phone numbers
    private final int phoneLimit = 10;
    private final String phoneDelim = "-";
    private final Set<Integer> phoneChars = new TreeSet<>();
    {
        phoneChars.add(2);
        phoneChars.add(5);
    }
}
