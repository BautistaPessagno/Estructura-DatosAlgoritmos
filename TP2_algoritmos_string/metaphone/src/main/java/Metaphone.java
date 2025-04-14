// DBPP
public class Metaphone{
    public String metaphone(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        input = input.toUpperCase();

        StringBuilder result = new StringBuilder();
        int length = input.length();
        int index = 0;

        while (index < length) {
            char currentChar = input.charAt(index);

            if (index > 0 && input.charAt(index - 1) == currentChar) {
                index++;
                continue;
            }

            switch (currentChar) {
                case 'A': case 'E': case 'I': case 'O': case 'U':
                    if (index == 0) {
                        result.append(currentChar);
                    }
                    break;
                case 'B':
                    result.append('B');
                    break;
                case 'C':
                    if (index + 1 < length && input.charAt(index + 1) == 'H') {
                        result.append('X');
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'I' && (index + 2 < length && (input.charAt(index + 2) == 'A' || input.charAt(index + 2) == 'O'))) {
                        result.append('X');
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'I' || input.charAt(index + 1) == 'E' || input.charAt(index + 1) == 'Y') {
                        result.append('S');
                        index++;
                    } else {
                        result.append('K');
                    }
                    break;
                case 'D':
                    if (index + 1 < length && input.charAt(index + 1) == 'G' && (index + 2 < length && (input.charAt(index + 2) == 'E' || input.charAt(index + 2) == 'I' || input.charAt(index + 2) == 'Y'))) {
                        result.append('J');
                        index += 2;
                    } else {
                        result.append('T');
                    }
                    break;
                case 'F':
                    result.append('F');
                    break;
                case 'G':
                    if (index + 1 < length && input.charAt(index + 1) == 'H' && (index + 2 < length && !isVowel(input.charAt(index + 2)))) {
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'N' && (index + 2 == length || (index + 2 < length && !isVowel(input.charAt(index + 2))))) {
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'N' && (index + 2 < length && isVowel(input.charAt(index + 2)))) {
                        result.append('K');
                    } else if (index + 1 < length && input.charAt(index + 1) == 'I' && (index + 2 < length && (input.charAt(index + 2) == 'A' || input.charAt(index + 2) == 'O'))) {
                        result.append('J');
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'E' || input.charAt(index + 1) == 'I' || input.charAt(index + 1) == 'Y') {
                        result.append('J');
                        index++;
                    } else {
                        result.append('K');
                    }
                    break;
                case 'H':
                    if (index == 0 || (index > 0 && isVowel(input.charAt(index - 1)))) {
                        result.append('H');
                    }
                    break;
                case 'J':
                    result.append('J');
                    break;
                case 'K':
                    if (index == 0 || (index > 0 && input.charAt(index - 1) != 'C')) {
                        result.append('K');
                    }
                    break;
                case 'L':
                    result.append('L');
                    break;
                case 'M':
                    result.append('M');
                    break;
                case 'N':
                    result.append('N');
                    break;
                case 'P':
                    if (index + 1 < length && input.charAt(index + 1) == 'H') {
                        result.append('F');
                        index++;
                    } else {
                        result.append('P');
                    }
                    break;
                case 'Q':
                    result.append('K');
                    break;
                case 'R':
                    result.append('R');
                    break;
                case 'S':
                    if (index + 1 < length && input.charAt(index + 1) == 'H') {
                        result.append('X');
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'I' && (index + 2 < length && (input.charAt(index + 2) == 'A' || input.charAt(index + 2) == 'O'))) {
                        result.append('X');
                        index++;
                    } else {
                        result.append('S');
                    }
                    break;
                case 'T':
                    if (index + 1 < length && input.charAt(index + 1) == 'I' && (index + 2 < length && (input.charAt(index + 2) == 'A' || input.charAt(index + 2) == 'O'))) {
                        result.append('X');
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'H') {
                        result.append('0');
                        index++;
                    } else if (index + 1 < length && input.charAt(index + 1) == 'C' && (index + 2 < length && input.charAt(index + 2) == 'H')) {
                        result.append('X');
                        index += 2;
                    } else {
                        result.append('T');
                    }
                    break;
                case 'V':
                    result.append('F');
                    break;
                case 'W': case 'Y':
                    if (index + 1 < length && isVowel(input.charAt(index + 1))) {
                        result.append(currentChar);
                    }
                    break;
                case 'X':
                    result.append('KS');
                    break;
                case 'Z':
                    result.append('S');
                    break;
            }
            index++;
        }

        return result.toString();
    }

    private boolean isVowel(char c) {
        return "AEIOU".indexOf(c) != -1;
    }
}
