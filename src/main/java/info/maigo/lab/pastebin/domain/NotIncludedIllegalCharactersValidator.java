package info.maigo.lab.pastebin.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotIncludedIllegalCharactersValidator implements ConstraintValidator<NotIncludedIllegalCharacters, String> {

    public void initialize(NotIncludedIllegalCharacters constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !hasIllegalCharacters(value);
    }

    private static boolean hasIllegalCharacters(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // No good: Control Characters
            // No Good: 0x7F: DEL (Delete character)
            // Good: 0x09: HT (Horizontal tab)
            // Good: 0x0A: LF (Line feed)
            // Good: 0x0D: CR (Carriage return)
            if ((ch < 0x20 && ch != 0x09 && ch != 0x0A && ch != 0x0D) || ch == 0x7f) {
                return true;
            }
        }
        return false;
    }
}
