package com.development.productService.utility;

import com.development.productService.exception.InValidPatternException;

public class ValidateUuid {
    public static void isValidUUID(String id) throws InValidPatternException {
        boolean match = PatternMatcher.isValidUUID(id);
        if (!match)
            throw new InValidPatternException("ID :"+id+" is not valid");
    }
}
