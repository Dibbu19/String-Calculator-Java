package com.stringcalculator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }

        try {
            String delimiter = ",|\n";
            String val = numbers;
            
       
            if (numbers.startsWith("//")) {
                Matcher m = Pattern.compile("//(.*?)\n(.*)").matcher(numbers);
                if (m.matches()) {
                    delimiter = Pattern.quote(m.group(1));
                    val = m.group(2);
                }
            }

            return calculateSum(val, delimiter);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static String calculateSum(String numbers, String delimiter) throws Exception {
        String[] tokens = numbers.split(delimiter);
        BigDecimal sum = BigDecimal.ZERO;
        List<String> errors = new ArrayList<>();
        List<BigDecimal> negatives = new ArrayList<>();
        int position = 0;

        for (String token : tokens) {
            if (token.isEmpty()) {
                errors.add("Number expected but '" + delimiter + "' found at position " + position);
                continue;
            }

            try {
                BigDecimal num = new BigDecimal(token);
                if (num.compareTo(BigDecimal.ZERO) < 0) {
                    negatives.add(num);
                }
                sum = sum.add(num);
            } catch (NumberFormatException e) {
                errors.add("Number expected but '" + token + "' found at position " + position);
            }

            position += token.length() + 1;
        }

        if (numbers.endsWith(delimiter)) {
            errors.add("Number expected but EOF found.");
        }

        if (!negatives.isEmpty()) {
            StringBuilder negativeMessage = new StringBuilder("Negative not allowed : ");
            for (int i = 0; i < negatives.size(); i++) {
                negativeMessage.append(negatives.get(i).toPlainString());
                if (i < negatives.size() - 1) {
                    negativeMessage.append(", ");
                }
            }
            errors.add(negativeMessage.toString());
        }

        if (!errors.isEmpty()) {
            throw new Exception(String.join("\n", errors));
        }

        return sum.stripTrailingZeros().toPlainString();
    }
}
