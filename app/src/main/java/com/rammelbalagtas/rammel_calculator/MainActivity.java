package com.rammelbalagtas.rammel_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // class variable declaration
    private String operand1;  // 1st number in the operation
    private String operand2; // 2nd number in the operation
    private int mathOperationId; // ID of the operation clicked
    private double doubleResult; // result of math operation
    private boolean isResultDisplayed; // flag to indicate that result has already been calculated and displayed
    private TextView textViewScreen; // instance of the text view screen
    private String digitScreen; // string value of the number display

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization of values
        operand1 = "";
        operand2 = "";
        digitScreen = "0";
        setDigitListener();           // set number and decimal buttons listener
        setOperationListener();       // set basic math operation listener
        setSpecialFunctionListener(); // set special math functions listener
        textViewScreen = findViewById(R.id.tv_screen); // get instance of screen text view
        textViewScreen.setText(digitScreen);

    }

    /**
     * Set event listener for different basic math operations
     */
    private void setOperationListener() {
        // Addition
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(clickOperationListener);
        // Subtraction
        Button btnMinus = findViewById(R.id.btn_minus);
        btnMinus.setOnClickListener(clickOperationListener);
        // Multiplication
        Button btnMultiply = findViewById(R.id.btn_multiply);
        btnMultiply.setOnClickListener(clickOperationListener);
        // Division
        Button btnDivide = findViewById(R.id.btn_divide);
        btnDivide.setOnClickListener(clickOperationListener);
        // Modulo
        Button btnModulo = findViewById(R.id.btn_modulo);
        btnModulo.setOnClickListener(clickOperationListener);
        // Equals
        Button btnEquals = findViewById(R.id.btn_equals);
        btnEquals.setOnClickListener(clickOperationListener);
        // Clear screen
        Button btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(clickClearListener);
    }

    /**
     * Set event listener for special functions (e.g. square root, factorial, PI, percentage)
     */
    private void setSpecialFunctionListener() {
        //Square root
        Button btnSqrt = findViewById(R.id.btn_sqrt);
        btnSqrt.setOnClickListener(clickSpecialFunctionListener);
        // Factorial
        Button btnFactorial = findViewById(R.id.btn_factorial);
        btnFactorial.setOnClickListener(clickSpecialFunctionListener);
        // PI(π) value
        Button btnPi = findViewById(R.id.btn_pi);
        btnPi.setOnClickListener(clickSpecialFunctionListener);
        // Percentage in decimal format
        Button btnPercent = findViewById(R.id.btn_percent);
        btnPercent.setOnClickListener(clickSpecialFunctionListener);
    }

    /**
     * Set event listener for numbers and decimal
     */
    private void setDigitListener() {
        //Button 0
        Button btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(clickNumberListener);
        //Button 1
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(clickNumberListener);
        //Button 2
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(clickNumberListener);
        //Button 3
        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(clickNumberListener);
        //Button 4
        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(clickNumberListener);
        //Button 5
        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(clickNumberListener);
        //Button 6
        Button btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(clickNumberListener);
        //Button 7
        Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(clickNumberListener);
        //Button 8
        Button btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(clickNumberListener);
        //Button 9
        Button btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(clickNumberListener);
        //Button decimal
        Button btnDecimal = findViewById(R.id.btn_decimal);
        btnDecimal.setOnClickListener(clickNumberListener);
    }

    /**
     * Event listener for digits and decimal
     */
    private final View.OnClickListener clickNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View number) {

            //no action required if digit starts with zero and user pressed zero
            if (digitScreen.equals("0") && number.getId() == R.id.btn0) {
                textViewScreen.setText(digitScreen);
                return;
            }

            //Remove leading zero when the first digit is entered
            if (digitScreen.equals("0")) digitScreen = "";

            switch (number.getId()) {
                case R.id.btn0:
                    digitScreen += "0";
                    break;
                case R.id.btn1:
                    digitScreen += "1";
                    break;
                case R.id.btn2:
                    digitScreen += "2";
                    break;
                case R.id.btn3:
                    digitScreen += "3";
                    break;
                case R.id.btn4:
                    digitScreen += "4";
                    break;
                case R.id.btn5:
                    digitScreen += "5";
                    break;
                case R.id.btn6:
                    digitScreen += "6";
                    break;
                case R.id.btn7:
                    digitScreen += "7";
                    break;
                case R.id.btn8:
                    digitScreen += "8";
                    break;
                case R.id.btn9:
                    digitScreen += "9";
                    break;
                case R.id.btn_decimal:
                    if (!digitScreen.contains(".")) {
                        if (digitScreen.equals("")) {
                            digitScreen += "0.";
                        } else {
                            digitScreen += ".";
                        }
                    }
                default:
                    break;
            }
            textViewScreen.setText(digitScreen); //update screen with new number
        }
    };

    /**
     * Event listener for basic math operations
     */
    private final View.OnClickListener clickOperationListener = new View.OnClickListener() {
        @Override
        public void onClick(View operation) {

            if (operation.getId() == R.id.btn_equals) {
                if (mathOperationId != 0) { //only proceed if there is an operation clicked
                    if (!isResultDisplayed) {
                        operand2 = digitScreen;
                        switch (mathOperationId) {
                            case R.id.btn_add:
                                doubleResult = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                                break;
                            case R.id.btn_minus:
                                doubleResult = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                                break;
                            case R.id.btn_multiply:
                                doubleResult = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                                break;
                            case R.id.btn_divide:
                                doubleResult = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                                break;
                            case R.id.btn_modulo:
                                doubleResult = Double.parseDouble(operand1) % Double.parseDouble(operand2);
                            default:
                        }
                        // check if value has decimal places (show an integer if there is no decimal value)
                        if (doubleResult % 1 == 0) {
                            operand1 = String.valueOf((int) doubleResult);
                        } else {
                            operand1 = String.valueOf(doubleResult);
                        }
                        digitScreen = "0";
                        textViewScreen.setText(operand1);
                        isResultDisplayed = true; //flag to indicate that the result is already displayed
                    }
                }
            } else {
                mathOperationId = operation.getId();
                if (operand1.equals("")) { //only set the value once, in case user clicks an operation multiple times
                    operand1 = digitScreen;
                    digitScreen = "0";
                }
            }
        }
    };

    /**
     * Event listener for special math functions
     */
    private final View.OnClickListener clickSpecialFunctionListener = new View.OnClickListener() {
        @Override
        public void onClick(View function) {
            switch (function.getId()) {
                case R.id.btn_sqrt:
                    // compute for the square root
                    double result = Math.sqrt(Double.parseDouble(digitScreen));
                    // check if value has decimal places
                    if (result % 1 == 0) {
                        digitScreen = String.valueOf((int) result);
                    } else {
                        digitScreen = String.valueOf(result);
                    }
                    break;
                case R.id.btn_factorial:
                    // compute for the factorial value by calling a recursive method
                    if (Double.parseDouble(digitScreen) % 1 == 0) {
                        digitScreen = String.valueOf(factorial(Integer.parseInt(digitScreen)));
                    } else showToastMessage(getString(R.string.error_message_factorial));
                    break;
                case R.id.btn_pi:
                    // pass the PI value (replace the value on the screen)
                    digitScreen = String.valueOf(Math.PI);
                    break;
                case R.id.btn_percent:
                    // compute the decimal equivalent of the percentage value
                    digitScreen = String.valueOf(Double.parseDouble(digitScreen) / 100);
                default:
                    break;
            }
            // update the screen
            textViewScreen.setText(digitScreen);
        }
    };

    /**
     * Set event listener for clear button
     */
    private final View.OnClickListener clickClearListener = new View.OnClickListener() {
        @Override
        public void onClick(View button) {
            // Initialize values and update the screen
            mathOperationId = 0;
            operand1 = "";
            operand2 = "";
            doubleResult = 0.0;
            digitScreen = "0";
            isResultDisplayed = false;
            textViewScreen.setText(digitScreen);
        }
    };

    /**
     * Recursive method for getting the factorial (currently allows integer only)
     *
     * @param n - number to get the factorial for
     * @return - factorial value
     */
    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    /**
     * Reusable method for showing any warning/error message
     *
     * @param message - message to be displayed
     */
    private void showToastMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}