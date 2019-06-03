package info.maigo.lab.pastebin.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotIncludedControlCharactersValidator implements ConstraintValidator<NotIncludedControlCharacters, String> {

    public void initialize(NotIncludedControlCharacters constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !hasControlCharacters(value);
    }

    private static boolean hasControlCharacters(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 0x09: HT (Horizontal tab)
            // 0x0A: LF (Line feed)
            // 0x0D: CR (Carriage return)
            // 0x7F: DEL (Delete character)
            if ((ch < 0x20 && ch != 0x09 && ch != 0x0A && ch != 0x0D) || ch == 0x7f) {
                return true;
            }
        }
        return false;
    }
}
