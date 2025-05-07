package com.example.findtrip;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchFilterActivity extends AppCompatActivity {

    // UI Components
    private MaterialButton btnPromo, btnHotels, btnExplore, btnFlights;
    private EditText searchEditText, minPriceEdit, maxPriceEdit;
    private EditText startDateEdit, endDateEdit;
    private ImageView notificationIcon, homeIcon, favoritesIcon, cartIcon, profileIcon;
    private CardView filterCardView;
    private ImageButton closeButton;

    // Calendar for date selection
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfilter);

        // Initialize date format
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        // Initialize UI components
        initializeViews();
        setupListeners();

        // By default, the filter card is hidden
        filterCardView.setVisibility(View.GONE);
    }

    private void initializeViews() {
        // Category buttons
        btnPromo = findViewById(R.id.btnPromo);
        btnHotels = findViewById(R.id.btnHotels);
        btnExplore = findViewById(R.id.btnExplore);
        btnFlights = findViewById(R.id.btnFlights);

        // Search field
        searchEditText = findViewById(R.id.searchEditText);

        // Filter card elements
        filterCardView = findViewById(R.id.filterCardView);
        minPriceEdit = findViewById(R.id.minprice);
        maxPriceEdit = findViewById(R.id.maxprice);
        startDateEdit = findViewById(R.id.startDateText);
        endDateEdit = findViewById(R.id.endDateText);
        closeButton = findViewById(R.id.closeButton);

        // Bottom navigation icons
        homeIcon = findViewById(R.id.homeIcon);
        favoritesIcon = findViewById(R.id.favoritesIcon);
        cartIcon = findViewById(R.id.cartIcon);
        profileIcon = findViewById(R.id.profileIcon);

        // Header elements
        notificationIcon = findViewById(R.id.notificationIcon);
    }

    private void setupListeners() {
        // Category button listeners
        setupCategoryButtons();

        // Search field listener
        setupSearchField();

        // Date field listeners
        setupDateFields();

        // Price field listeners
        setupPriceFields();

        // Close button for filter card
        closeButton.setOnClickListener(v -> filterCardView.setVisibility(View.GONE));

        // Bottom navigation listeners
        setupBottomNavigation();

        // Notification icon listener
        notificationIcon.setOnClickListener(v -> showNotifications());
    }

    private void setupCategoryButtons() {
        // Default setup: Promo is selected
        setActiveButton(btnPromo);

        btnPromo.setOnClickListener(v -> {
            setActiveButton(btnPromo);
            loadPromoContent();
        });

        btnHotels.setOnClickListener(v -> {
            setActiveButton(btnHotels);
            loadHotelsContent();
            // Show filter when hotel is selected
            filterCardView.setVisibility(View.VISIBLE);
        });

        btnExplore.setOnClickListener(v -> {
            setActiveButton(btnExplore);
            loadExploreContent();
        });

        btnFlights.setOnClickListener(v -> {
            setActiveButton(btnFlights);
            loadFlightsContent();
        });
    }

    private void setActiveButton(MaterialButton activeButton) {
        // Reset all buttons to default state
        btnPromo.setBackgroundTintList(getColorStateList(R.color.white));
        btnHotels.setBackgroundTintList(getColorStateList(R.color.white));
        btnExplore.setBackgroundTintList(getColorStateList(R.color.white));
        btnFlights.setBackgroundTintList(getColorStateList(R.color.white));

        btnPromo.setTextColor(getColor(R.color.black));
        btnHotels.setTextColor(getColor(R.color.black));
        btnExplore.setTextColor(getColor(R.color.black));
        btnFlights.setTextColor(getColor(R.color.black));

        // Set active button state
        activeButton.setBackgroundTintList(getColorStateList(R.color.emerald));
        activeButton.setTextColor(getColor(R.color.white));
    }

    private void setupSearchField() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Search functionality as user types
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupDateFields() {
        // Start date picker
        startDateEdit.setOnClickListener(v -> showDatePicker(startDateEdit));

        // End date picker
        endDateEdit.setOnClickListener(v -> showDatePicker(endDateEdit));
    }

    private void showDatePicker(final EditText dateField) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateField.setText(dateFormat.format(calendar.getTime()));

                // If both dates are set, filter results
                if (!startDateEdit.getText().toString().isEmpty() &&
                        !endDateEdit.getText().toString().isEmpty()) {
                    filterByDates();
                }
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void setupPriceFields() {
        TextWatcher priceWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Filter by price when both fields have values
                if (!minPriceEdit.getText().toString().isEmpty() &&
                        !maxPriceEdit.getText().toString().isEmpty()) {
                    filterByPrice();
                }
            }
        };

        minPriceEdit.addTextChangedListener(priceWatcher);
        maxPriceEdit.addTextChangedListener(priceWatcher);
    }

    private void setupBottomNavigation() {
        homeIcon.setOnClickListener(v -> navigateToHome());
        favoritesIcon.setOnClickListener(v -> navigateToFavorites());
        cartIcon.setOnClickListener(v -> navigateToCart());
        profileIcon.setOnClickListener(v -> navigateToProfile());
    }

    // Content loading methods
    private void loadPromoContent() {
        // Hide filter card for promo section
        filterCardView.setVisibility(View.GONE);
        Toast.makeText(this, "Loading Promotions", Toast.LENGTH_SHORT).show();
        // TODO: Load promotion content
    }

    private void loadHotelsContent() {
        Toast.makeText(this, "Loading Hotels", Toast.LENGTH_SHORT).show();
        // TODO: Load hotels content
    }

    private void loadExploreContent() {
        // Hide filter card for explore section
        filterCardView.setVisibility(View.GONE);
        Toast.makeText(this, "Loading Explore", Toast.LENGTH_SHORT).show();
        // TODO: Load explore content
    }

    private void loadFlightsContent() {
        // Hide filter card for flights section
        filterCardView.setVisibility(View.GONE);
        Toast.makeText(this, "Loading Flights", Toast.LENGTH_SHORT).show();
        // TODO: Load flights content
    }

    // Filter methods
    private void performSearch(String query) {
        if (query.length() > 2) {
            // Only search when query is at least 3 characters
            // TODO: Implement actual search logic
            Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
        }
    }

    private void filterByDates() {
        try {
            // Parse input dates
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();

            startDate.setTime(dateFormat.parse(startDateEdit.getText().toString()));
            endDate.setTime(dateFormat.parse(endDateEdit.getText().toString()));

            // Check if end date is after start date
            if (endDate.before(startDate)) {
                Toast.makeText(this, "End date must be after start date", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Apply date filtering logic
            Toast.makeText(this, "Filtering by date range", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show();
        }
    }

    private void filterByPrice() {
        try {
            int minPrice = Integer.parseInt(minPriceEdit.getText().toString());
            int maxPrice = Integer.parseInt(maxPriceEdit.getText().toString());

            if (maxPrice < minPrice) {
                Toast.makeText(this, "Maximum price must be greater than minimum price", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Apply price filtering logic
            Toast.makeText(this, "Filtering by price range: $" + minPrice + " - $" + maxPrice, Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    // Navigation methods
    private void navigateToHome() {
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        // Already on home screen
    }

    private void navigateToFavorites() {
        Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show();
        // TODO: Navigate to favorites screen
    }

    private void navigateToCart() {
        Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
        // TODO: Navigate to cart screen
    }

    private void navigateToProfile() {
        Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        // TODO: Navigate to profile screen
    }

    private void showNotifications() {
        Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
        // TODO: Show notifications panel or navigate to notifications screen
    }
}