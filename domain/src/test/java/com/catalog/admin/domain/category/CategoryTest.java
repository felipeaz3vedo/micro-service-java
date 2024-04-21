package com.catalog.admin.domain.category;

import com.catalog.admin.domain.exceptions.DomainException;
import com.catalog.admin.domain.validation.handler.ThrowsValidationHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiatesCategory() {
        final var expectedName = "Category name";
        final var expectedDescription = "Category description";
        final var expectedIsActive = true;

        final var category = Category.create(
                expectedName,
                expectedDescription,
                expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.getIsActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldThrowAException() {
        final var expectedExceptionMessage = "'name' should not be null";
        final var expectedExceptionsCount = 1;

        final var category = Category.create(
                null,
                "Category description",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldThrowAException() {
        final var invalidName = " ";
        final var expectedExceptionMessage = "'name' should not be empty";
        final var expectedExceptionsCount = 1;

        final var category = Category.create(
                invalidName,
                "Category description",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAnInvalidNameWithLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldThrowAException() {
        final var invalidName = "Fe ";
        final var expectedExceptionMessage = "'name' must be between 3 and 255 characters";
        final var expectedExceptionsCount = 1;

        final var category = Category.create(
                invalidName,
                "Category description",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAnInvalidNameWithLengthGreaterThan255_whenCallNewCategoryAndValidate_thenShouldThrowAException() {
        final var invalidName = """
                In the heart of the city, where the skyscrapers touch the sky,
                a small café nestled between the bustling streets offers a
                haven of tranquility. Here, the aroma of freshly brewed coffee
                fills the air, and the soft hum of conversations blends with the
                gentle clinking of cups. Customers, drawn by the promise of a
                moment's peace, gather around tables adorned with vintage
                magazines and postcards from distant lands. The barista, with a
                smile as warm as the coffee, serves each customer with a
                personal touch, creating a sense of community among the patrons.
                This café, a beacon of serenity in the midst of chaos, stands
                as a testament to the simple pleasures of life.
                """;
        final var expectedExceptionMessage = "'name' must be between 3 and 255 characters";
        final var expectedExceptionsCount = 1;

        final var category = Category.create(
                invalidName,
                "Category description",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> category.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategory_thenInstantiatesCategory() {
        final var expectedName = "Category name";
        final var validEmptyDescription = "";
        final var expectedIsActive = true;

        final var category = Category.create(
                expectedName,
                validEmptyDescription,
                expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(validEmptyDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.getIsActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategory_thenInstantiatesCategory() {
        final var expectedName = "Category name";
        final var expectedDescription = "Category description";
        final var expectedIsActive = false;

        final var category = Category.create(
                expectedName,
                expectedDescription,
                expectedIsActive);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(expectedIsActive, category.getIsActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNotNull(category.getDeletedAt());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnAnInactiveCategory() {
        final var expectedName = "Category name";
        final var expectedDescription = "Category description";
        final var expectedIsActive = false;

        final var category = Category.create(
                expectedName,
                expectedDescription,
                true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertTrue(category.getIsActive());
        Assertions.assertNull(category.getDeletedAt());

        final var createAtBeforeDeactivate = category.getCreatedAt();
        final var updatedAtBeforeDeactivate = category.getUpdatedAt();

        category.deactivate();

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedIsActive, category.getIsActive());
        Assertions.assertNotNull(category.getDeletedAt());
        Assertions.assertTrue(
                category.getUpdatedAt().isAfter(updatedAtBeforeDeactivate)
        );
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(createAtBeforeDeactivate, category.getCreatedAt());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallDeactivate_thenReturnAActivatedCategory() {
        final var expectedName = "Category name";
        final var expectedDescription = "Category description";
        final var expectedIsActive = true;

        final var category = Category.create(
                expectedName,
                expectedDescription,
                false);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertFalse(category.getIsActive());
        Assertions.assertNotNull(category.getDeletedAt());

        final var createAtBeforeActivate = category.getCreatedAt();
        final var updatedAtBeforeActivate = category.getUpdatedAt();

        category.activate();

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedIsActive, category.getIsActive());
        Assertions.assertNull(category.getDeletedAt());
        Assertions.assertTrue(
                category.getUpdatedAt().isAfter(updatedAtBeforeActivate)
        );
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedDescription, category.getDescription());
        Assertions.assertEquals(createAtBeforeActivate, category.getCreatedAt());
    }

    @Test
    public void givenValidCategory_whenCallUpdateProvidingValidParameters_thenReturnUpdatedCategory() {
        final var expectedName = "Category name after update";
        final var expectedDescription = "Category description after update";
        final var expectedIsActive = true;

        final var category = Category.create(
                "Category name before update",
                "Category description before update",
                true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var createAtBeforeUpdate = category.getCreatedAt();
        final var updatedAtBeforeUpdate = category.getUpdatedAt();

        final var updatedCategory = category.update(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertDoesNotThrow(() -> updatedCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(updatedCategory.getId());
        Assertions.assertEquals(expectedName, updatedCategory.getName());
        Assertions.assertEquals(expectedDescription, updatedCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, updatedCategory.getIsActive());
        Assertions.assertEquals(createAtBeforeUpdate, updatedCategory.getCreatedAt());
        Assertions.assertNull(updatedCategory.getDeletedAt());
        Assertions.assertTrue(
                updatedCategory.getUpdatedAt().isAfter(updatedAtBeforeUpdate)
        );
    }

    @Test
    public void givenAValidActivatedCategory_whenCallUpdateProvidingFalseToIsActive_thenReturnDeactivatedUpdatedCategory() {
        final var expectedName = "Category name after update";
        final var expectedDescription = "Category description after update";
        final var expectedIsActive = false;

        final var category = Category.create(
                "Category name before update",
                "Category description before update",
                true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertTrue(category.getIsActive());
        Assertions.assertNull(category.getDeletedAt());

        final var createAtBeforeUpdate = category.getCreatedAt();
        final var updatedAtBeforeUpdate = category.getUpdatedAt();

        final var updatedCategory = category.update(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertDoesNotThrow(() -> updatedCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(updatedCategory.getId());
        Assertions.assertEquals(expectedName, updatedCategory.getName());
        Assertions.assertEquals(expectedDescription, updatedCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, updatedCategory.getIsActive());
        Assertions.assertFalse(updatedCategory.getIsActive());
        Assertions.assertEquals(createAtBeforeUpdate, updatedCategory.getCreatedAt());
        Assertions.assertNotNull(updatedCategory.getDeletedAt());
        Assertions.assertTrue(
                updatedCategory.getUpdatedAt().isAfter(updatedAtBeforeUpdate)
        );
    }

    @Test
    public void givenAValidCategory_whenCallUpdateProvidingANullName_thenthenThrowAException() {
        final var expectedExceptionsCount = 1;
        final var expectedExceptionMessage = "'name' should not be null";

        final var category = Category.create(
                "Category name before update",
                "Category description before update",
                true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var updatedCategory = category.update(
                null,
                "Category description after update",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> updatedCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAValidCategory_whenCallUpdateProvidingAnEmptyName_thenthenThrowAException() {
        final var expectedExceptionsCount = 1;
        final var expectedExceptionMessage = "'name' should not be empty";

        final var category = Category.create(
                "Category name before update",
                "Category description before update",
                true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var updatedCategory = category.update(
                " ",
                "Category description after update",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> updatedCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAValidCategory_whenCallUpdateProvidingAnNameWithLessThan3Length_thenThrowAException() {
        final var expectedExceptionsCount = 1;
        final var expectedExceptionMessage = "'name' must be between 3 and 255 characters";

        final var category = Category.create(
                "Category name before update",
                "Category description before update",
                true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var updatedCategory = category.update(
                "Fe ",
                "Category description after update",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> updatedCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAValidCategory_whenCallUpdateProvidingAnNameWithGreaterThan255Length_thenThrowAException() {
        final var invalidName = """
                In the heart of the city, where the skyscrapers touch the sky,
                a small café nestled between the bustling streets offers a
                haven of tranquility. Here, the aroma of freshly brewed coffee
                fills the air, and the soft hum of conversations blends with the
                gentle clinking of cups. Customers, drawn by the promise of a
                moment's peace, gather around tables adorned with vintage
                magazines and postcards from distant lands. The barista, with a
                smile as warm as the coffee, serves each customer with a
                personal touch, creating a sense of community among the patrons.
                This café, a beacon of serenity in the midst of chaos, stands
                as a testament to the simple pleasures of life.
                """;
        final var expectedExceptionsCount = 1;
        final var expectedExceptionMessage = "'name' must be between 3 and 255 characters";

        final var category = Category.create(
                "Category name before update",
                "Category description before update",
                true);

        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));

        final var updatedCategory = category.update(
                invalidName,
                "Category description after update",
                true
        );

        final var exception = Assertions.assertThrows(
                DomainException.class,
                () -> updatedCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
                expectedExceptionsCount,
                exception.getErrors().size()
        );

        Assertions.assertEquals(
                expectedExceptionMessage,
                exception.getErrors().get(0).message()
        );
    }
}