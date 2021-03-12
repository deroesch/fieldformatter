package org.deroesch.fieldformatter;

import java.util.Set;
import java.util.TreeSet;

import lombok.NonNull;

/**
 * Simple string field formatter for date, phone, social security number
 *
 */
public class FieldFormatter {

    /**
     * Formats a candidate string of digits as a date
     *
     * @param candidate The candidate string
     * @return The formatted date
     */
    public String asDate(@NonNull final String candidate) {
        final String digits = filterDigits(candidate);
        return format(digits, dateLimit, dateChars, dateDelim);
    }

    /**
     * Formats a candidate string of digits as a social security number
     *
     * @param candidate The candidate string
     * @return The formatted social security number
     */
    public String asSSN(@NonNull final String candidate) {
        final String digits = filterDigits(candidate);
        return format(digits, ssnLimit, ssnChars, ssnDelim);
    }

    /**
     * Formats a candidate string of digits as a US phone number
     *
     * @param candidate The candidate string
     * @return The formatted US phone number
     */
    public String asPhone(@NonNull final String candidate) {
        final String digits = filterDigits(candidate);
        return format(digits, phoneLimit, phoneChars, phoneDelim);
    }

    /**
     * Strips non-digits out of candidate string
     *
     * @param candidate The string to filter
     * @return The filtered string
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
     * Formats input string by stripping out non-digits, then inserting delimiter
     * chars at locations.
     *
     * @param candidate      The unformatted string
     * @param requiredLength Mandatory length of candidate string
     * @param delimLocations Set of integers where delimiters should appear in the
     *                       output
     * @param delimChar      Delimiter to insert at locations
     * @return The formatted string
     */
    @NonNull
    String format(@NonNull final String candidate, final int requiredLength, @NonNull final Set<Integer> delimLocations,
            @NonNull final String delimChar) {

        // Exit immediately if length is wrong.
        if (requiredLength < 0 || candidate.length() != requiredLength)
            return "";

        // Rewrite string, adding delimiters
        final StringBuffer formattingBuffer = new StringBuffer();
        for (int i = 0; i < requiredLength; i++) {
            formattingBuffer.append(candidate.charAt(i));
            if (delimLocations.contains(i))
                formattingBuffer.append(delimChar);
        }

        return formattingBuffer.toString();
    }

    // For dates of the form XX/XX/XXXX
    private final int dateLimit = 8;
    private final String dateDelim = "/";
    private final Set<Integer> dateChars = new TreeSet<>();
    {
        dateChars.add(1);
        dateChars.add(3);
    }

    // For social security numbers of the form XXX-XX-XXXX
    private final int ssnLimit = 9;
    private final String ssnDelim = "-";
    private final Set<Integer> ssnChars = new TreeSet<>();
    {
        ssnChars.add(2);
        ssnChars.add(4);
    }

    // For US phone numbers of the form XXX-XXX-XXXX
    private final int phoneLimit = 10;
    private final String phoneDelim = "-";
    private final Set<Integer> phoneChars = new TreeSet<>();
    {
        phoneChars.add(2);
        phoneChars.add(5);
    }
}
