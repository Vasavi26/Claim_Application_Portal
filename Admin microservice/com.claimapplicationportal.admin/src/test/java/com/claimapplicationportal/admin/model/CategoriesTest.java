package com.claimapplicationportal.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CategoriesTest {

    @Test
    void testConstructor() {
        Categories actualCategories = new Categories();
        actualCategories.setCategoryId(1);
        actualCategories.setCategoryName("Category Name");
        actualCategories.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        actualCategories.setCreatedOn("Jan 1, 2020 8:00am GMT+0100");
        actualCategories.setUpdatedBy("2020-03-01");
        actualCategories.setUpdatedOn("2020-03-01");
//        String actualToStringResult = actualCategories.toString();
        assertEquals(1, actualCategories.getCategoryId());
        assertEquals("Category Name", actualCategories.getCategoryName());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCategories.getCreatedBy());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCategories.getCreatedOn());
        assertEquals("2020-03-01", actualCategories.getUpdatedBy());
        assertEquals("2020-03-01", actualCategories.getUpdatedOn());

    }


}