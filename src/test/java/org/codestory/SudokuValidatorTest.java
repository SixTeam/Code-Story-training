package org.codestory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

public class SudokuValidatorTest {

   @Test
   public void valid_a_right_1_sudoku() {
      assertThat(validateSudoku(new Integer[][]{{1}}), is(true));
   }

   @Test
   public void not_valid_a_wrong_1_sudoku() {
      assertThat(validateSudoku(new Integer[][]{{8}}), is(false));
   }

   @Test
   public void not_valid_a_empty_sudoku() {
      assertThat(validateSudoku(new Integer[][]{{}}), is(false));
   }

   @Test
   public void valid_a_right_1_dimension() {
      assertThat(validateDimension(new Integer[]{1}), is(true));

   }

   @Test
   public void valid_a_right_2_dimension() {
      assertThat(validateDimension(new Integer[]{1, 2}), is(true));
   }

   @Test
   public void valid_a_right_2_dimension_not_in_order() {
      assertThat(validateDimension(new Integer[]{2, 1}), is(true));
   }

   @Test
   public void valid_a_right_2_sudoku() {
      assertThat(validateSudoku(new Integer[][]{{3, 4, 1, 2}, {1, 2, 3, 4}, {2, 3, 4, 1}, {4, 1, 2, 3}}), is(true));
   }

   @Test
   public void valid_a_wrong_2_sudoku() {
      assertThat(validateSudoku(new Integer[][]{{4, 3, 1, 2}, {1, 2, 2, 4}, {2, 3, 4, 1}, {4, 1, 2, 3}}), is(false));
   }

   @Test
   public void valid_a_wrong_2_sudoku_because_of_column() {
      assertThat(validateSudoku(new Integer[][]{{4, 3, 1, 2}, {1, 3, 2, 4}, {1, 3, 2, 4}, {4, 1, 2, 3}}), is(false));
   }

   @Test
   public void valid_a_wrong_2_sudoku_because_of_square() {
      assertThat(validateSudoku(new Integer[][]{{3, 1, 4, 2}, {1, 3, 2, 4}, {2, 4, 3, 1}, {4, 2, 1, 3}}), is(false));
   }

   @Test
   public void valid_a_wrong_2_sudoku_because_of_size() {
      fail();
      //assertThat(validateSudoku(new Integer[][]{{1, 2, 3}, {2, 3, 1}, {3, 1, 2}}), is(false));
   }

   private Boolean validateDimension(Integer[] dimension) {
      boolean isValid = true;

      for (int i = 1; i <= dimension.length; i++) {
         isValid &= Arrays.asList(dimension).contains(i);
      }

      return isValid;
   }


   private boolean validateSudoku(Integer[][] sudoku) {
      boolean isValid = sudoku[0].length != 0;

      isValid = validateLines(sudoku, isValid);

      isValid = validateColumn(sudoku, isValid);

      return isValid;
   }

   private boolean validateLines(Integer[][] sudoku, boolean valid) {
      for (Integer[] line : sudoku) {
         valid &= validateDimension(line);
      }
      return valid;
   }

   private boolean validateColumn(Integer[][] sudoku, boolean valid) {
      return validateLines(toColumn(sudoku), valid);
   }

   private Integer[][] toColumn(Integer[][] sudoku) {
      Integer[][] result = new Integer[sudoku.length][sudoku[0].length];

      for (int i = 0; i < sudoku.length; i++) {
         for (int j = 0; j < sudoku[0].length; j++) {
            result[j][i] = sudoku[i][j];
         }
      }
      return result;
   }

}