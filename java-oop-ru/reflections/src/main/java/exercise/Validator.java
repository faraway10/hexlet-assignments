package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    static List<String> validate(Object obj) {
        List<String> result = new ArrayList<String>();

        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                NotNull notNull = field.getAnnotation(NotNull.class);
                field.setAccessible(true);
                if (notNull != null && field.get(obj) == null) {
                    result.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> result = new HashMap<String, List<String>>();

        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                NotNull notNull = field.getAnnotation(NotNull.class);
                MinLength minLength = field.getAnnotation(MinLength.class);
                List<String> errorList = new ArrayList<String>();

                field.setAccessible(true);
                Object value = field.get(obj);

                if (notNull != null && value == null) {
                    errorList.add("can not be null");
                }

                if (minLength != null && value != null && minLength.minLength() > ((String) value).length()) {
                    errorList.add("length less than " + minLength.minLength());
                }

                if (!errorList.isEmpty()) {
                    result.put(field.getName(), errorList);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }
}
// END
