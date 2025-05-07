package com.example.findtrip;


import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    // UI Components
    private EditText cardNumberEditText;
    private EditText cardHolderEditText;
    private EditText expiryDateEditText;
    private EditText cvaEditText;
    private CheckBox termsCheckBox;
    private CheckBox rememberCardCheckBox;
    private Button payButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        // Initialize UI components
        initializeViews();

        // Set up input validation
        setupInputValidation();

        // Set up button click listeners
        setupButtonListeners();
    }

    private void initializeViews() {
        cardNumberEditText = findViewById(R.id.cardnumber);
        cardHolderEditText = findViewById(R.id.cardholder);
        expiryDateEditText = findViewById(R.id.expirydate);
        cvaEditText = findViewById(R.id.cva);
        termsCheckBox = findViewById(R.id.buttonterms);
        rememberCardCheckBox = findViewById(R.id.buttonremember);
        payButton = findViewById(R.id.pay);
        cancelButton = findViewById(R.id.cancel);
    }

    private void setupInputValidation() {
        // Card number validation - format: XXXX XXXX XXXX XXXX
        cardNumberEditText.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;
            private String previousText = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) {
                    return;
                }

                isFormatting = true;

                // Remove all non-digits
                String digits = s.toString().replaceAll("\\D", "");

                // Limit to 16 digits
                if (digits.length() > 16) {
                    digits = digits.substring(0, 16);
                }

                // Format with spaces
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < digits.length(); i++) {
                    if (i > 0 && i % 4 == 0) {
                        formatted.append(" ");
                    }
                    formatted.append(digits.charAt(i));
                }

                if (!formatted.toString().equals(s.toString())) {
                    s.replace(0, s.length(), formatted.toString());
                }

                isFormatting = false;
            }
        });

        // Expiry date validation - format: MM/YY
        expiryDateEditText.addTextChangedListener(new TextWatcher() {
            private String previousText = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousText = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                // Remove all non-digits
                String digits = input.replaceAll("\\D", "");

                // Format as MM/YY
                StringBuilder formatted = new StringBuilder();

                if (digits.length() > 0) {
                    // First two digits (month)
                    String month = digits.substring(0, Math.min(2, digits.length()));
                    formatted.append(month);

                    // Add slash if we have two digits for month
                    if (digits.length() > 2) {
                        formatted.append("/");

                        // Append year digits
                        formatted.append(digits.substring(2, Math.min(4, digits.length())));
                    } else if (month.length() == 2 && !input.contains("/")) {
                        // Auto-add slash after two month digits
                        formatted.append("/");
                    }
                }

                if (!formatted.toString().equals(input)) {
                    s.replace(0, s.length(), formatted.toString());
                }
            }
        });

        // CVA validation - limit to 3 or 4 digits
        cvaEditText.setFilters(new InputFilter[] {
                new InputFilter.LengthFilter(4)
        });

        cvaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove non-digits
                String input = s.toString();
                String digits = input.replaceAll("\\D", "");

                if (!digits.equals(input)) {
                    s.replace(0, s.length(), digits);
                }
            }
        });
    }

    private void setupButtonListeners() {
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    processPayment();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close activity or go back to previous screen
                finish();
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;

        // Validate card number (should be 16 digits)
        String cardNumber = cardNumberEditText.getText().toString().replaceAll("\\s", "");
        if (cardNumber.length() != 16) {
            cardNumberEditText.setError("Please enter a valid 16-digit card number");
            isValid = false;
        }

        // Validate card holder name (cannot be empty)
        String cardHolder = cardHolderEditText.getText().toString().trim();
        if (TextUtils.isEmpty(cardHolder)) {
            cardHolderEditText.setError("Please enter the card holder name");
            isValid = false;
        }

        // Validate expiry date (format MM/YY and not expired)
        String expiryDate = expiryDateEditText.getText().toString().trim();
        if (!isValidExpiryDate(expiryDate)) {
            expiryDateEditText.setError("Please enter a valid expiry date (MM/YY)");
            isValid = false;
        }

        // Validate CVA (3 or 4 digits)
        String cva = cvaEditText.getText().toString().trim();
        if (cva.length() < 3) {
            cvaEditText.setError("CVA must be 3 or 4 digits");
            isValid = false;
        }

        // Check if terms and conditions are accepted
        if (!termsCheckBox.isChecked()) {
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidExpiryDate(String expiryDate) {
        // Check format
        if (!expiryDate.matches("\\d{2}/\\d{2}")) {
            return false;
        }

        try {
            // Parse the expiry date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yy", Locale.getDefault());
            sdf.setLenient(false);
            Date expiry = sdf.parse(expiryDate);

            // Get the last day of the expiry month
            Calendar expiryCalendar = Calendar.getInstance();
            expiryCalendar.setTime(expiry);
            expiryCalendar.set(Calendar.DAY_OF_MONTH, expiryCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

            // Compare with current date
            return expiryCalendar.getTime().after(new Date());

        } catch (ParseException e) {
            return false;
        }
    }

    private void processPayment() {
        // Here you would typically send the payment details to your payment processor
        // For this example, we'll just show a success message

        // Get card details
        String cardNumber = cardNumberEditText.getText().toString().replaceAll("\\s", "");
        String cardHolder = cardHolderEditText.getText().toString();
        String expiryDate = expiryDateEditText.getText().toString();
        String cva = cvaEditText.getText().toString();
        boolean rememberCard = rememberCardCheckBox.isChecked();

        // IMPORTANT: In a real app, you would never log or store complete card details!
        // This is just for demonstration purposes.

        // Mask card number for display
        String maskedCardNumber = maskCardNumber(cardNumber);

        // Save card details if remember card is checked
        if (rememberCard) {
            saveCardDetails(maskedCardNumber, cardHolder, expiryDate);
        }

        // Show success message
        Toast.makeText(this, "Payment processed successfully!", Toast.LENGTH_LONG).show();

        // Close the activity or navigate to confirmation screen
        // In a real app, you might navigate to a receipt screen
        finish();
    }

    private String maskCardNumber(String cardNumber) {
        // Only show last 4 digits
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    private void saveCardDetails(String maskedCardNumber, String cardHolder, String expiryDate) {
        // In a real app, you would securely store these details
        // This could be in SharedPreferences with encryption or a secure storage solution

        // For this example, we just log that we're saving the card
        // (In a real app, you would use proper secure storage)
        System.out.println("Saving card: " + maskedCardNumber + ", " + cardHolder + ", " + expiryDate);
    }
}
