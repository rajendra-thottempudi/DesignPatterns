package Examples;

public class StringToInteger {
    public static void main(String[] args) {
        // Create an instance of the Solution class
        Solution solution = new Solution();

        // Test strings
        String[] inputs = {
                "42",
                "   -42",
                "4193 with words",
                "words and 987",
                "-91283472332",
                "",
                "   ",
                null
        };

        // Process each input string
        for (String input : inputs) {
            try {
                int result = solution.myAtoi(input);
                System.out.println("Input: \"" + input + "\", Output: " + result);
            } catch (Exception e) {
                System.out.println("Input: \"" + input + "\", Exception: " + e.getMessage());
            }
        }
    }
}


class Solution {
    public int myAtoi(String input) throws Exception {
        if (input == null || input.isEmpty()) {
            throw new Exception("Input string is null or empty.");
        }

        int sign = 1;
        int result = 0;
        int index = 0;
        int n = input.length();

        // Discard all spaces from the beginning of the input string.
        while (index < n && input.charAt(index) == ' ') {
            index++;
        }

        if (index == n) {
            throw new Exception("Input string contains only whitespace.");
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && input.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        if (index == n || !Character.isDigit(input.charAt(index))) {
            throw new Exception("No digits found after optional sign.");
        }

        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(input.charAt(index))) {
            int digit = input.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if (
                    (result > Integer.MAX_VALUE / 10) ||
                            (result == Integer.MAX_VALUE / 10 &&
                                    digit > Integer.MAX_VALUE % 10)
            ) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return sign * result;
    }
}